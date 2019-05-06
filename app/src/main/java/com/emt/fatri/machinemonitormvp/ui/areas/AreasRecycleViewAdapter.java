package com.emt.fatri.machinemonitormvp.ui.areas;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.base.GlobalField;
import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;
import com.emt.fatri.machinemonitormvp.ui.machine.MachineActivity;

import java.util.List;

/**
 * description:地区列表
 * Created by kingkong on 2018/5/11 0011.
 * changed by kingkong on 2018/5/11 0011.
 */

public class AreasRecycleViewAdapter extends RecyclerView.Adapter<AreasRecycleViewAdapter.MyHolder> {

    private static final String TAG = AreasRecycleViewAdapter.class.getSimpleName();
    private Context mContext;
    private List<AreaInfo> mData;
    private OnItemClickListener mOnItemClickListener;

    public AreasRecycleViewAdapter(Context context, List<AreaInfo> data ){
        mContext=context;
        mData=data;
    }
    public void setData(List<AreaInfo> data)
    {
        mData=data;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.area_item_layout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.mNodeName.setText(mData.get(position).mAreaName);
        holder.mNodeDescription.setText(mData.get(position).mAreaDescription);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mOnItemClickListener!=null)
                {
                    mOnItemClickListener.onClick(position);
                }

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mOnItemClickListener!=null)
                {
                    mOnItemClickListener.onLongClick(position);
                }
                return false;
            }
        });
        if(mData.get(position).mAreaState==0)
        {
            holder.mNodeStateIcon.setImageResource(R.drawable.emoticon);
        }
        else
        {
            holder.mNodeStateIcon.setImageResource(R.drawable.report_problem);
        }

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class MyHolder extends ViewHolder {
        TextView mNodeName;
        TextView mNodeDescription;
        ImageView mNodeStateIcon;
        MyHolder(View itemView) {
            super(itemView);
            mNodeName =itemView.findViewById(R.id.node_name_TextView);
            mNodeDescription=itemView.findViewById(R.id.node_description_TextView);
            mNodeStateIcon=itemView.findViewById(R.id.state_icon);
        }

    }


}
