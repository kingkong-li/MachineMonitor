package com.emt.fatri.machinemonitormvp.ui.machine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.base.GlobalField;
import com.emt.fatri.machinemonitormvp.data.model.MachineInfo;
import com.emt.fatri.machinemonitormvp.ui.addsensor.AddSensorActivity;
import com.emt.fatri.machinemonitormvp.ui.addsensor.AddSensorContract;
import com.emt.fatri.machinemonitormvp.ui.history.HistoryActivity;
import com.emt.fatri.machinemonitormvp.ui.sensorlist.SensorListActivity;

import java.util.List;

/**
 * description:
 * Created by kingkong on 2018/9/19 0019.
 * changed by kingkong on 2018/9/19 0019.
 */

public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<MachineInfo> mData;

    public MyPagerAdapter(Context context , List<MachineInfo> list) {
        mContext = context;
        mData = list;
    }
    public void setData(List<MachineInfo> data){
        mData=data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        if(mData==null || mData.size()==0)
        {
            return null;
        }
        View view = View.inflate(mContext, R.layout.machine_item_layout,null);
        TextView tv = view.findViewById(R.id.tv_machine_name);

        Typeface typeFace1 = Typeface.createFromAsset(mContext.getAssets(), "fonts/NotoSerifKR-ExtraLight.otf");

        tv.setTypeface(typeFace1);
        tv.setText(mData.get(position).mMachineName);
        Button historyButton=view.findViewById(R.id.btn_history_info);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mContext,HistoryActivity.class);
                intent.putExtra(MachineInfo.DEV_ID,mData.get(position).mMachineId);
                intent.putExtra(MachineInfo.DEV_TYPE,mData.get(position).mMachineType);
                mContext.startActivity(intent);
            }
        });
        TextView tvCurrentState = view.findViewById(R.id.tv_current_state);

        Typeface typeFace2 = Typeface.createFromAsset(mContext.getAssets(), "fonts/NotoSansJP-Light.otf");
        tvCurrentState.setTypeface(typeFace2);
        if(mData.get(position).mMachineState==0)
        {
            tvCurrentState.setText("当前运行良好");
        }else
        {
            tvCurrentState.setText("出现故障请检查");
        }
        Button addSensorButton=view.findViewById(R.id.add_sensor);
        Typeface typeFace3 = Typeface.createFromAsset(mContext.getAssets(), "fonts/NotoSansTC-Light.otf");
        addSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mContext,AddSensorActivity.class);
                mContext.startActivity(intent);
            }
        });
        addSensorButton.setTypeface(typeFace3);

        Button sensorListButton=view.findViewById(R.id.sensor_list);
        sensorListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mContext,SensorListActivity.class);
                intent.putExtra(MachineInfo.DEV_ID,mData.get(position).mMachineId);
                intent.putExtra(MachineInfo.DEV_TYPE,mData.get(position).mMachineType);
                mContext.startActivity(intent);
            }
        });



        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
