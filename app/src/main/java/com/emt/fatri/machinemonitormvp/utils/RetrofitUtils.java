package com.emt.fatri.machinemonitormvp.utils;



import android.util.Log;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Retofit网络请求工具类
 * Created by HDL on 2016/7/29.
 */
public class RetrofitUtils {
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒
    private static final String TAG =RetrofitUtils.class.getSimpleName();

    private static Retrofit mRetrofit;

    private RetrofitUtils() {

    }

    public static Retrofit newInstance(String url) {
        mRetrofit = null;
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logInterceptor)
        .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS).build();//设置连接时间为12s
        mRetrofit = new Retrofit.Builder()
                .client(client)//添加一个client,不然retrofit会自己默认添加一个
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return mRetrofit;
    }



    public static class HttpLogger implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            // 这个log记得发布时候要关闭，不然会泄漏用户信息
            Log.d(TAG,"HttpLogInfo=" +message);
        }
    }


}
