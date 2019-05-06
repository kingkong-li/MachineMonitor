package com.emt.fatri.machinemonitormvp.ui.history;

import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.MachineInfo;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;

import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/9/12 0012.
 * changed by kingkong on 2018/9/12 0012.
 */

public class HistoryContract {

    /**
     * V视图层
     */
    interface IHistoryView {

        void showMachineStateHistory(List<MachineInfo> historyDataList);

        void showCurrentState(int score);


    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IHistoryPresenter {

        void getCurrentScore(int machineId,int machineType);
        void getHistoryData(int machineId,int machineType);
    }

    /**
     * 逻辑处理层
     */
    interface IHistoryModel {
        void getCurrentScore(int machineId,int machineType,OnHttpCallBack<List<SensorInfo>> callBack);
        void getHistoryData(int machineId,int machineType,OnHttpCallBack<List<MachineInfo>> callBack);
    }
}
