package com.emt.fatri.machinemonitormvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * description: 登录返回信息
 * Created by kingkong on 2018/8/23 0023.
 * changed by kingkong on 2018/8/23 0023.
 */

public class LoginResponseData {
    @SerializedName("UserId")
    public int userId;


    @Override
    public String toString() {
        return "UserInfo ---"
                +"userId"+":"+userId;

    }
}
