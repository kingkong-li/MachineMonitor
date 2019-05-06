package com.emt.fatri.machinemonitormvp.ui.history;

import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.MachineInfo;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;
import com.emt.fatri.machinemonitormvp.utils.StatisticsUtils;

import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/9/12 0012.
 * changed by kingkong on 2018/9/12 0012.
 */

public class HistoryPresenter implements HistoryContract.IHistoryPresenter {

    private HistoryContract.IHistoryView mHistoryView;
    private HistoryContract.IHistoryModel mHistoryModel;

    public HistoryPresenter(HistoryContract.IHistoryView historyView) {
        this.mHistoryView =historyView ;
        mHistoryModel = new HistoryModel();
    }


    @Override
    public void getCurrentScore(int machineId, int machineType) {
        mHistoryModel.getCurrentScore(machineId, machineType, new OnHttpCallBack<List<SensorInfo>>() {
            @Override
            public void onSuccessful(List<SensorInfo> sensorInfos) {
                mHistoryView.showCurrentState(StatisticsUtils.statisticMachineScore(sensorInfos));

            }

            @Override
            public void onFailed(String errorMsg) {

            }
        });
    }

    @Override
    public void getHistoryData(int machineId, int machineType) {
            mHistoryModel.getHistoryData(machineId, machineType, new OnHttpCallBack<List<MachineInfo>>() {
                @Override
                public void onSuccessful(List<MachineInfo> machineInfos) {

                    mHistoryView.showMachineStateHistory(machineInfos);
                }

                @Override
                public void onFailed(String errorMsg) {

                }
            });
    }
}
