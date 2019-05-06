package com.emt.fatri.machinemonitormvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * description:设备信息
 * Created by kingkong on 2018/9/3 0003.
 * changed by kingkong on 2018/9/3 0003.
 */

public class MachineInfo {

    public static final String DEV_ID="DevId";
    public static final String DEV_TYPE="DevType";

    /**检测状态的时间*/
    public String checkTime;

    @SerializedName("DevId")
    public int mMachineId;

    @SerializedName("Name")
    public String mMachineName;

    @SerializedName("DevType")
    public int mMachineType;

    @SerializedName("DevStat")
    public int mMachineState;


    @Override
    public String toString() {
        return "MachineInfo ---"
                +"mMachineName"+":"+mMachineName
                +"mAreaState"+":"+mMachineState;

    }
}
