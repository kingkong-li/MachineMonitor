package com.emt.fatri.machinemonitormvp.ui.sensorlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;

import java.util.List;

/**
 * description:地区列表
 * Created by kingkong on 2018/5/11 0011.
 * changed by kingkong on 2018/5/11 0011.
 */

public class SensorListRecycleViewAdapter extends RecyclerView.Adapter<SensorListRecycleViewAdapter.MyHolder> {

    private static final String TAG = SensorListRecycleViewAdapter.class.getSimpleName();
    private Context mContext;
    private List<SensorInfo> mData;

    public SensorListRecycleViewAdapter(Context context, List<SensorInfo> data ){
        mContext=context;
        mData=data;
    }
    public void setData(List<SensorInfo> data)
    {
        mData=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.sensor_item_layout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.nodeName.setText(mData.get(position).sensorName);
        holder.nodeDescription.setText(mData.get(position).location);
        holder.nodeValue.setText(String.valueOf(mData.get(position).value));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  Intent intent=new Intent();
//                  intent.setClass(mContext,MachineActivity.class);
//                  intent.putExtra(GlobalField.AREA_ID,mData.get(position).mAreaId);
//                  Log.e(TAG,mData.get(position).mAreaDescription);
//                  mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class MyHolder extends ViewHolder {
        TextView nodeName;
        TextView nodeDescription;
        TextView nodeValue;
        MyHolder(View itemView) {
            super(itemView);
            nodeName =itemView.findViewById(R.id.node_name_TextView);
            nodeDescription =itemView.findViewById(R.id.node_description_TextView);
            nodeValue=itemView.findViewById(R.id.node_value);
        }

    }


}
