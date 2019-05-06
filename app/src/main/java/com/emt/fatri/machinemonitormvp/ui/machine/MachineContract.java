package com.emt.fatri.machinemonitormvp.ui.machine;

import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;
import com.emt.fatri.machinemonitormvp.data.model.MachineInfo;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;

import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/9/3 0003.
 * changed by kingkong on 2018/9/3 0003.
 */

public class MachineContract {
    /**
     * V视图层
     */
    interface IMachineView {

        void showMachineInfo(List<MachineInfo> info);
    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IMachinePresenter {
        void getMachineInfo(int areaId);
    }

    /**
     * 逻辑处理层
     */
    interface IMachineModel {
        void getMachineInfo(int areaId,OnHttpCallBack<List<MachineInfo>> callBack);
    }
}
