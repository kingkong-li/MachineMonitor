package com.emt.fatri.machinemonitormvp.ui.sensorlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.base.GlobalField;
import com.emt.fatri.machinemonitormvp.data.model.MachineInfo;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;
import com.emt.fatri.machinemonitormvp.ui.areas.AreasRecycleViewAdapter;

import java.util.List;

public class SensorListActivity extends Activity implements SensorListContract.ISensorListView {
    private SensorListRecycleViewAdapter mSensorListRecycleViewAdapter;
    private RecyclerView mRecyclerView;
    private SensorListPresenter mSensorListPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);
        mSensorListPresenter=new SensorListPresenter(this);
        Intent intent=getIntent();
        mSensorListPresenter.getSensorList(intent.getIntExtra(MachineInfo.DEV_ID,0),
                intent.getIntExtra(MachineInfo.DEV_TYPE,0));
        mRecyclerView=findViewById(R.id.sensor_list_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mSensorListRecycleViewAdapter =new SensorListRecycleViewAdapter(this,null);
        mRecyclerView.setAdapter(mSensorListRecycleViewAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void showSensorList(List<SensorInfo> info) {
        mSensorListRecycleViewAdapter.setData(info);
    }
}
