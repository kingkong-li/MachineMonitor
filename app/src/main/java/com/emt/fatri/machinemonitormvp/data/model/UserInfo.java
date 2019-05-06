package com.emt.fatri.machinemonitormvp.data.model;



/**
 * 用户实体类
 * Created by Administrator on 2016/7/22.
 */
public class UserInfo {

    /**用户ID字段*/
    public static final String USER_ID = "user_id";
    /**用户名字段*/
    public static final String USER_NAME = "user_name";
    /**用户密码字段*/
    public static final String USER_PASSWORD = "user_password";

    public String mUserName;//用户名

    public String mPassWord;//密码

    public int mUserId; //用户ID


    public UserInfo(String mUserName, String mPassWord) {
        this.mUserName = mUserName;
        this.mPassWord = mPassWord;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "mUserName='" + mUserName + '\'' +
                ", mPassWord='" + mPassWord + '\'' ;
    }
}
