package com.emt.fatri.machinemonitormvp.utils;

import android.util.Log;

import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;

import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/9/26 0026.
 * changed by kingkong on 2018/9/26 0026.
 */

public class StatisticsUtils {
    private static final String TAG = StatisticsUtils.class.getSimpleName();

    /**
     * 根据传感器值 统计该机器得分
     */
    public static int statisticMachineScore(List<SensorInfo> sensorInfoList){

        if(sensorInfoList==null || sensorInfoList.size()==0)
        {
            return -1;
        }
        int total=sensorInfoList.size();
        int score=0;
        for(int i=0;i<total;i++)
        {
            if(sensorInfoList.get(i).status==0)
            {
                score++;
            }
        }

        Log.v(TAG,"total="+total+", score="+score);
        return (int)(score*1.0f/total*100);
    }
}
