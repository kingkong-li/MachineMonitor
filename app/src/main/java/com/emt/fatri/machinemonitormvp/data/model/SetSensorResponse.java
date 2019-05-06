package com.emt.fatri.machinemonitormvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * description:设置传感器返回信息
 * Created by kingkong on 2018/9/25 0025.
 * changed by kingkong on 2018/9/25 0025.
 */

public class SetSensorResponse {
    @SerializedName("SensorId")
    public int sensorId;


    @Override
    public String toString() {
        return "SetSensorResponse ---"
                +"sensorId"+":"+sensorId;

    }
}
