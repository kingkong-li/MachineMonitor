package com.emt.fatri.machinemonitormvp.ui.message;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.base.GlobalField;
import com.emt.fatri.machinemonitormvp.data.model.AlarmInfo;
import com.emt.fatri.machinemonitormvp.ui.areas.AreasRecycleViewAdapter;
import com.emt.fatri.machinemonitormvp.ui.machine.MachineActivity;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends Fragment implements MessageContract.IMessageView{

    private static final String TAG =MessageFragment.class.getSimpleName() ;
    private Context mContext;
    private Toolbar mToolbar;
    /**报警信息列表*/
    private  RecyclerView mRecyclerView;
    private  MessageRecycleViewAdapter mMessageAdapter;
    private MessagePresenter mMessagePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG,"onCreate");
        mContext=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.v(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.activity_message, container, false);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated(): " + savedInstanceState);
        super.onViewCreated(view, savedInstanceState);

        mToolbar=view.findViewById(R.id.fragment_toolbar);
        mToolbar.setTitle("");
        setHasOptionsMenu(true);
        if ((getActivity()) != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        }

        mRecyclerView=view.findViewById(R.id.message_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mMessageAdapter =new MessageRecycleViewAdapter(mContext,null);
        mRecyclerView.setAdapter(mMessageAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mMessagePresenter=new MessagePresenter(this);
        useTestData();
    }

    @Override
    public void showAlarmMessage() {

    }

    private void useTestData(){

        List<AlarmInfo> alarmInfoList=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            AlarmInfo alarmInfo=new AlarmInfo();
            alarmInfo.description="东渡一号港机头部故障";
            alarmInfo.alarmTime=String.valueOf(System.currentTimeMillis());
            alarmInfoList.add(alarmInfo);
        }
        mMessageAdapter.setData(alarmInfoList);
    }
}
