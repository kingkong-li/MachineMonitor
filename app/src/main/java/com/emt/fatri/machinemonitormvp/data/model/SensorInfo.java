package com.emt.fatri.machinemonitormvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * description:传感器信息
 * Created by kingkong on 2018/5/10 0010.
 * changed by kingkong on 2018/5/10 0010.
 */

public class SensorInfo {

    @SerializedName("SensorId")
    public int sensorId;

    @SerializedName("SensorType")
    public int sensorType;

    @SerializedName("Name")
    public String sensorName;


    @SerializedName("Data")
    public float value;

    @SerializedName("Status")
    public int status;

    @SerializedName("Location")
    public String location;



    @Override
    public String toString() {
        return "SensorInfo ---"
                +"sensorId"+":"+sensorId+","
                +"sensorName"+":"+sensorName;

    }
}
