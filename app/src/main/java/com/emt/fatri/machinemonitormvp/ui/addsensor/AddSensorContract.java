package com.emt.fatri.machinemonitormvp.ui.addsensor;

import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;
import com.emt.fatri.machinemonitormvp.data.model.SensorSetInfo;
import com.emt.fatri.machinemonitormvp.data.model.SetSensorResponse;

import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/9/20 0020.
 * changed by kingkong on 2018/9/20 0020.
 */

public class AddSensorContract {

    /**
     * V视图层
     */
    interface IAddSensorView {

        void showSensorId(int id);


    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IAddSensorPresenter {
        void registerSensor(SensorSetInfo sensorInfo);
    }

    /**
     * 逻辑处理层
     */
    interface IAddSensorModel {
        void registerSensor(SensorSetInfo sensorInfo, OnHttpCallBack<SetSensorResponse> callBack);
    }

}