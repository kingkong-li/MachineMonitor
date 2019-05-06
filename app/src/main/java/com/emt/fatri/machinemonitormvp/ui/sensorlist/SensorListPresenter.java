package com.emt.fatri.machinemonitormvp.ui.sensorlist;

import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;

import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/9/6 0006.
 * changed by kingkong on 2018/9/6 0006.
 */

public class SensorListPresenter implements SensorListContract.ISensorListPresenter {
    private SensorListContract.ISensorListView mSensorListView;
    private SensorListContract.ISensorListModel mSensorListModel;
    public SensorListPresenter(SensorListContract.ISensorListView sensorListView) {
        this.mSensorListView = sensorListView;
        mSensorListModel = new SensorListModel();
    }



    @Override
    public void getSensorList(int machineId, int machineType) {
        mSensorListModel.getSensorList(machineId, machineType, new OnHttpCallBack<List<SensorInfo>>() {
            @Override
            public void onSuccessful(List<SensorInfo> sensorInfos) {
                mSensorListView.showSensorList(sensorInfos);
            }

            @Override
            public void onFailed(String errorMsg) {

            }
        });
    }
}
