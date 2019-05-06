package com.emt.fatri.machinemonitormvp.ui.machine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.base.GlobalField;
import com.emt.fatri.machinemonitormvp.data.model.MachineInfo;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;
import java.util.List;

public class MachineActivity extends Activity implements MachineContract.IMachineView{
    private static final String TAG =MachineActivity.class.getSimpleName() ;
    private MachinePresenter mMachinePresenter;
    private int mAreaId;
    private Context mContext;
    private ViewPager mMachineViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);
        mContext=this;
        mMachinePresenter=new MachinePresenter(this);
        mAreaId=getIntent().getIntExtra(GlobalField.AREA_ID,1);
        mMachinePresenter.getMachineInfo(mAreaId);
        mMachineViewPager = findViewById(R.id.machine_view_pager);
        PageIndicatorView pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setAnimationType(AnimationType.WORM);

    }

    @Override
    public void showMachineInfo(List<MachineInfo> info) {

        for(int i=0;i<info.size();i++)
        {
            Log.v(TAG,info.get(i).toString()+"/n");
        }
        setViewpagerData(info);

    }

    private void setViewpagerData(List<MachineInfo> list) {

        //测试数据start
//        List<MachineInfo> lis1t=new ArrayList<>() ;
//        for(int i=0;i<5;i++)
//        {
//            MachineInfo machineInfo=new MachineInfo();
//            machineInfo.mMachineName="设备"+(i+1);
//            machineInfo.mMachineType=1;
//            machineInfo.mMachineState=i%2;
//            machineInfo.mMachineId=1;
//
//            lis1t.add(machineInfo);
//
//        }
       // 测试数据end

        mMachineViewPager.setAdapter(new MyPagerAdapter(this,list));
        mMachineViewPager.setPageTransformer(true, new CubeOutTransformer());
    }

}
