package com.emt.fatri.machinemonitormvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * description:传感器设置信息
 * Created by kingkong on 2018/9/21 0021.
 * changed by kingkong on 2018/9/21 0021.
 */

public class SensorSetInfo {
    @SerializedName("SensorId")
    public int sensorId=-1;

    @SerializedName("SensorType")
    public int sensorType;

    @SerializedName("Name")
    public String sensorName;

    @SerializedName("Model")
    public int sensorVersion;

    @SerializedName("Number")
    public String serialnumber;

    @SerializedName("Location")
    public String location;

    @SerializedName("DevType")
    public int devType;

    @SerializedName("DevId")
    public int devId;


    @Override
    public String toString() {
        return "SensorInfo ---"
                +"sensorId"+":"+ sensorId +","
                +"sensorType"+":"+ sensorType ;

    }

}
