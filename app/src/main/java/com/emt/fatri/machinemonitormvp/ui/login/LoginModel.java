package com.emt.fatri.machinemonitormvp.ui.login;

import android.util.Log;


import com.emt.fatri.machinemonitormvp.base.APIService;
import com.emt.fatri.machinemonitormvp.base.GlobalField;
import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.HttpResult;
import com.emt.fatri.machinemonitormvp.data.model.LoginResponseData;
import com.emt.fatri.machinemonitormvp.data.model.UserInfo;
import com.emt.fatri.machinemonitormvp.utils.MainApplication;
import com.emt.fatri.machinemonitormvp.utils.RetrofitUtils;
import com.emt.fatri.machinemonitormvp.utils.SharedPreferenceUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 登录的业务处理类
 * Created by Administrator on 2016/7/22.
 */
public class LoginModel implements LoginContract.ILoginModel {

    private static final String TAG =LoginModel.class.getSimpleName() ;

    /**
     * 登录的具体业务处理--------网络请求都在这里做喽
     * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
     * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
     * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
     *
     * @param userInfo P层传递过来的数据
     * @param callBack P层传递过来的回调对象----------说白了就是成功还是失败了,你总的告诉我一声,
     *  我好通知V层来处理[这里可以不用回调,在代码中使用EventBus或者传递一个Handler过来也可以,不建议这样操作]
     */

    @Override
    public void login(final UserInfo userInfo, final OnHttpCallBack<LoginResponseData> callBack) {

        //登录的网络请求
        RetrofitUtils.newInstance(GlobalField.BASE_URL)//实例化Retrofit对象
                .create(APIService.class)//创建RxJava---->LoginService对象
                .userLogin(userInfo.mUserName, userInfo.mPassWord)//调用登录的接口
                .subscribeOn(Schedulers.io())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Subscriber<HttpResult<LoginResponseData>>() {
                    @Override
                    public void onCompleted() {
                        if(GlobalField.DEBUG_LOG)Log.v(TAG,"onCompleted");
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
                    public void onNext(HttpResult<LoginResponseData> httpResult) {
                        if(GlobalField.DEBUG_LOG)Log.v(TAG,"onNext httpResult="+httpResult.toString());
                        if (httpResult.getResultCode() == 0) {
                            callBack.onSuccessful(httpResult.getData());     //登录成功
                        } else {
                            clearLocalUserInfo();
                            callBack.onFailed("用户名或密码错误!");//登录失败
                        }
                    }
                });
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        if(GlobalField.DEBUG_LOG)Log.v(TAG,"saveUserInfo userInfo="+userInfo.toString());
        SharedPreferenceUtil.putString(MainApplication.getInstance(), UserInfo.USER_NAME,
                userInfo.mUserName);
        SharedPreferenceUtil.putString(MainApplication.getInstance(),UserInfo.USER_PASSWORD,
                userInfo.mPassWord);
        SharedPreferenceUtil.putInt(MainApplication.getInstance(),UserInfo.USER_ID,userInfo.mUserId);
    }

    /**
     * 清楚保存的用户信息，一般用在用户输入错误的时候
     */
    private void clearLocalUserInfo(){
        if(GlobalField.DEBUG_LOG)Log.v(TAG,"clearLocalUserInfo");
        SharedPreferenceUtil.putString(MainApplication.getInstance(), UserInfo.USER_NAME,
                "");
        SharedPreferenceUtil.putString(MainApplication.getInstance(),UserInfo.USER_PASSWORD,
                "");
        SharedPreferenceUtil.putInt(MainApplication.getInstance(),UserInfo.USER_ID,-1);
    }

}
