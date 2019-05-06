package com.emt.fatri.machinemonitormvp.ui.addsensor;


import android.util.Log;

import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.SensorSetInfo;
import com.emt.fatri.machinemonitormvp.data.model.SetSensorResponse;

/**
 * description:
 * Created by kingkong on 2018/9/20 0020.
 * changed by kingkong on 2018/9/20 0020.
 */

public class AddSensorPresenter implements AddSensorContract.IAddSensorPresenter {
    private static final String TAG = AddSensorPresenter.class.getSimpleName();
    private AddSensorContract.IAddSensorView mAddSensorView;
    private AddSensorContract.IAddSensorModel mAddSensorModel;
    public AddSensorPresenter(AddSensorContract.IAddSensorView addSensorView) {
        this.mAddSensorView = addSensorView;
        mAddSensorModel = new AddSensorModel();
    }

    @Override
    public void registerSensor(SensorSetInfo sensorInfo) {

        mAddSensorModel.registerSensor(sensorInfo, new OnHttpCallBack<SetSensorResponse>() {
            @Override
            public void onSuccessful(SetSensorResponse setSensorResponse) {
                mAddSensorView.showSensorId(setSensorResponse.sensorId);
            }

            @Override
            public void onFailed(String errorMsg) {

                Log.e(TAG,"onFailed errorMsg="+errorMsg);
            }
        });
    }
}
