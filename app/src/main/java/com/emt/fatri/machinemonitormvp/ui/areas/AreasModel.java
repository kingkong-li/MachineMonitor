package com.emt.fatri.machinemonitormvp.ui.areas;

import android.util.Log;

import com.emt.fatri.machinemonitormvp.base.APIService;
import com.emt.fatri.machinemonitormvp.base.GlobalField;
import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;
import com.emt.fatri.machinemonitormvp.data.model.HttpResult;
import com.emt.fatri.machinemonitormvp.data.source.DatabaseHelper;
import com.emt.fatri.machinemonitormvp.utils.RetrofitUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * description:
 * Created by kingkong on 2018/8/27 0027.
 * changed by kingkong on 2018/8/27 0027.
 */

public class AreasModel implements AreasContract.IAreasModel {
    private static final String TAG =AreasModel.class.getSimpleName() ;

    @Override
    public void getAreaInfo(int userId, final OnHttpCallBack<List<AreaInfo>> callBack) {

        RetrofitUtils.newInstance(GlobalField.BASE_URL)//实例化Retrofit对象
                .create(APIService.class)//创建RxJava---->LoginService对象
                .getAreaList(userId)//调用登录的接口
                .subscribeOn(Schedulers.io())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Subscriber<HttpResult<List<AreaInfo>>>() {
                    @Override
                    public void onCompleted() {
                        if(GlobalField.DEBUG_LOG) Log.v(TAG,"onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(GlobalField.ERROR_LOG)Log.v(TAG,"onError");
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFailed("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFailed("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFailed("网络连接超时!!");
                        } else {
                            callBack.onFailed("发生未知错误" + e.getMessage());
                        }


                    }

                    @Override
                    public void onNext(HttpResult<List<AreaInfo>> httpResult) {
                        if(GlobalField.DEBUG_LOG)Log.v(TAG,"onNext httpResult="+httpResult.toString());
                        if (httpResult.getResultCode() == 0) {
                            callBack.onSuccessful(httpResult.getData());     //登录成功
                        } else {
                            callBack.onFailed("获取地区列表失败!");//登录失败
                        }
                    }
                });
    }

    @Override
    public void getMyAreaList(final OnHttpCallBack<List<AreaInfo>> callBack) {


        Observable.create(new Observable.OnSubscribe<List<AreaInfo>>() {
            @Override
            public void call(Subscriber<? super List<AreaInfo>> subscriber) {
                List<AreaInfo> areaInfoList = DatabaseHelper.getAreaInfoList();//从数据库获取
                subscriber.onNext(areaInfoList);
                subscriber.onCompleted();
                }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<AreaInfo>>() {
                    @Override
                    public void call(List<AreaInfo> data) {

                        callBack.onSuccessful(data);
                    }
                });

    }

}
