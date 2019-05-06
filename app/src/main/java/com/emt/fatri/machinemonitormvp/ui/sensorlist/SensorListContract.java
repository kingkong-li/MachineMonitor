package com.emt.fatri.machinemonitormvp.ui.sensorlist;

import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;

import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/9/6 0006.
 * changed by kingkong on 2018/9/6 0006.
 */

public class SensorListContract {

    /**
     * V视图层
     */
    interface ISensorListView {

        void showSensorList(List<SensorInfo> info);
    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface ISensorListPresenter {
        void getSensorList(int machineId,int machineType);
    }

    /**
     * 逻辑处理层
     */
    interface ISensorListModel {
        void getSensorList(int machineId,int machineType,OnHttpCallBack<List<SensorInfo>> callBack);
    }
}
