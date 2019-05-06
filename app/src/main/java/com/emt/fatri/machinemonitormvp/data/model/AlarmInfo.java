package com.emt.fatri.machinemonitormvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * description:报警信息
 * Created by kingkong on 2018/10/24 0024.
 * changed by kingkong on 2018/10/24 0024.
 */

public class AlarmInfo {

    @SerializedName("zoneId")
    public int areaId;

    @SerializedName("id")
    public int id;

    @SerializedName("device")
    public int deviceName;

    @SerializedName("description")
    public String description;

    @SerializedName("level")
    public int level;

    @SerializedName("alarm_check")
    public int confirmState;

    @SerializedName("occurred")
    public String alarmTime;

    @SerializedName("canceled")
    public String endTime;


    @Override
    public String toString() {
        return super.toString();
    }
}
