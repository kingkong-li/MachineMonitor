package com.emt.fatri.machinemonitormvp.ui.machine;

import android.util.Log;

import com.emt.fatri.machinemonitormvp.base.APIService;
import com.emt.fatri.machinemonitormvp.base.GlobalField;
import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.HttpResult;
import com.emt.fatri.machinemonitormvp.data.model.MachineInfo;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;
import com.emt.fatri.machinemonitormvp.utils.RetrofitUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * description:
 * Created by kingkong on 2018/9/3 0003.
 * changed by kingkong on 2018/9/3 0003.
 */

public class MachineModel implements MachineContract.IMachineModel {
    private static final String TAG =MachineModel.class.getSimpleName() ;

    @Override
    public void getMachineInfo(int areaId, final OnHttpCallBack<List<MachineInfo>> callBack) {
        RetrofitUtils.newInstance(GlobalField.BASE_URL)
                .create(APIService.class)
                .getMachineInfo(areaId,-1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<List<MachineInfo>>>() {
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
                    public void onNext(HttpResult<List<MachineInfo>> httpResult) {
                        if(GlobalField.DEBUG_LOG)Log.v(TAG,"onNext httpResult="+httpResult.toString());
                        if (httpResult.getResultCode() == 0) {
                            callBack.onSuccessful(httpResult.getData());
                        } else {
                            callBack.onFailed("获取该设备信息失败!");
                        }
                    }
                });
    }
}
