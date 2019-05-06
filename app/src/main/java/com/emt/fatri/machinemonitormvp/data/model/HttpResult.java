package com.emt.fatri.machinemonitormvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * 网络请求信息统一封装
 * create by kingkong 2018/09/05
 * @param <T>
 */
public class HttpResult<T> {

    @SerializedName("code")
    private int resultCode;
    @SerializedName("error")
    private String resultMes;
    @SerializedName("data")
    private T data;

    public HttpResult() {
    }

    public HttpResult(int resultCode, String resultMes, T data) {
        this.resultCode = resultCode;
        this.resultMes = resultMes;
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMes() {
        return resultMes;
    }

    public void setResultMes(String resultMes) {
        this.resultMes = resultMes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "resultCode=" + resultCode +
                ", resultMes='" + resultMes + '\'' +
                ", data=" + data +
                '}';
    }
}
