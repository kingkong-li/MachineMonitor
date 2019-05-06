package com.emt.fatri.machinemonitormvp.ui.areas;

import com.emt.fatri.machinemonitormvp.base.OnHttpCallBack;
import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;
import com.emt.fatri.machinemonitormvp.data.model.UserInfo;
import com.emt.fatri.machinemonitormvp.utils.MainApplication;
import com.emt.fatri.machinemonitormvp.utils.SharedPreferenceUtil;

import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/8/27 0027.
 * changed by kingkong on 2018/8/27 0027.
 */

public class AreasPresenter implements AreasContract.IAreasPresenter {
    private AreasContract.IAreasView mAreasView;
    private AreasContract.IAreasModel mAreasModel;
    public AreasPresenter(AreasContract.IAreasView areasView) {
        this.mAreasView = areasView;
        mAreasModel = new AreasModel();
    }
    @Override
    public void getAreaInfo() {
        int userId= SharedPreferenceUtil.getInt(MainApplication.getInstance(),
                UserInfo.USER_ID,-1);
        mAreasModel.getAreaInfo(userId, new OnHttpCallBack<List<AreaInfo>>() {

            @Override
            public void onSuccessful(List<AreaInfo> areasResponseData) {

                mAreasView.showAreaIntroduce(areasResponseData);
            }

            @Override
            public void onFailed(String errorMsg) {

            }
        });
    }

    @Override
    public void getMyAreaList() {
        mAreasModel.getMyAreaList(new OnHttpCallBack<List<AreaInfo>>() {
            @Override
            public void onSuccessful(List<AreaInfo> infoList) {
                mAreasView.showMyAreas(infoList);
            }

            @Override
            public void onFailed(String errorMsg) {

            }
        });
    }
}
