package com.emt.fatri.machinemonitormvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * description:获取区域信息返回数据类型
 * Created by kingkong on 2018/8/27 0027.
 * changed by kingkong on 2018/8/27 0027.
 */

public class AreaInfo {

    /**区域id 字段*/
    public static final String AREA_ID = "area_id";
    /**区域名称 字段*/
    public static final String AREA_NAME = "area_name";
    /**区域描述 字段*/
    public static final String AREA_DESCRIPTION = "area_description";
    /**区域状态 字段*/
    public static final String AREA_STATE = "area_state";

    @SerializedName("ZoneId")
    public int mAreaId;

    @SerializedName("ZoneName")
    public String mAreaName;

    @SerializedName("ZoneDesc")
    public String mAreaDescription;

    @SerializedName("ZoneStat")
    public int mAreaState;


    @Override
    public String toString() {
        return "AreaInfo ---"
                +"mAreaId"+":"+mAreaId+","
                +"mAreaDescription"+":"+mAreaDescription+","
                +"mAreaState"+":"+mAreaState;

    }
}
