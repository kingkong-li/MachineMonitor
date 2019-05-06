package com.emt.fatri.machinemonitormvp.ui.areas;

import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;

import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/8/27 0027.
 * changed by kingkong on 2018/8/27 0027.
 */

public class AreasContract {
    /**
     * V视图层
     */
    interface IAreasView {

        void showMyAreas(List<AreaInfo> infoList);

        void showAreaIntroduce(List<AreaInfo> info);

        void showLastErrorTime(String lastErrorTime);

        void showCurrentState(int code);


    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IAreasPresenter {
        void getAreaInfo();
        void getMyAreaList();
    }

    /**
     * 逻辑处理层
     */
    interface IAreasModel {
        void getAreaInfo(int userId,OnHttpCallBack<List<AreaInfo>> callBack);
        void getMyAreaList(OnHttpCallBack<List<AreaInfo>> callBack);
    }
}
