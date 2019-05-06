package com.emt.fatri.machinemonitormvp.ui.addsensor;

import android.util.Log;

import com.emt.fatri.machinemonitormvp.base.APIService;
import com.emt.fatri.machinemonitormvp.base.GlobalField;
import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;
import com.emt.fatri.machinemonitormvp.data.model.HttpResult;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;
import com.emt.fatri.machinemonitormvp.data.model.SensorSetInfo;
import com.emt.fatri.machinemonitormvp.data.model.SetSensorResponse;
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
 * Created by kingkong on 2018/9/20 0020.
 * changed by kingkong on 2018/9/20 0020.
 */

public class AddSensorModel implements AddSensorContract.IAddSensorModel {
    private static final String TAG = AddSensorModel.class.getSimpleName();

    @Override
    public void registerSensor(SensorSetInfo sensorInfo, final OnHttpCallBack<SetSensorResponse> callBack) {
        RetrofitUtils.newInstance(GlobalField.BASE_URL)//实例化Retrofit对象
                .create(APIService.class)
                .setSensor(sensorInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Subscriber<HttpResult<SetSensorResponse>>() {
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
                    public void onNext(HttpResult<SetSensorResponse> httpResult) {
                        if(GlobalField.DEBUG_LOG)Log.v(TAG,"onNext httpResult="+httpResult.toString());
                        if (httpResult.getResultCode() == 0) {
                            callBack.onSuccessful(httpResult.getData());
                        } else {
                            callBack.onFailed("添加传感器失败");
                        }
                    }
                });
    }
}
