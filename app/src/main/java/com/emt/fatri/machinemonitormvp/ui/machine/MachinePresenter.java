package com.emt.fatri.machinemonitormvp.ui.machine;

import android.util.Log;

import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.MachineInfo;


import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/9/3 0003.
 * changed by kingkong on 2018/9/3 0003.
 */

public class MachinePresenter implements MachineContract.IMachinePresenter {
    private static final String TAG = MachinePresenter.class.getSimpleName();
    private MachineContract.IMachineView mMachineView;
    private MachineContract.IMachineModel mMachineModel;

    public MachinePresenter(MachineContract.IMachineView machineView) {
        this.mMachineView = machineView;
        mMachineModel = new MachineModel();
    }


    @Override
    public void getMachineInfo(int areaId) {
        mMachineModel.getMachineInfo(areaId, new OnHttpCallBack<List<MachineInfo>>() {
            @Override
            public void onSuccessful(List<MachineInfo> machineInfoList) {
                mMachineView.showMachineInfo(machineInfoList);
                Log.v(TAG,"onSuccessful");
            }

            @Override
            public void onFailed(String errorMsg) {
                Log.v(TAG,"onFailed"+errorMsg);
            }
        });
    }
}
