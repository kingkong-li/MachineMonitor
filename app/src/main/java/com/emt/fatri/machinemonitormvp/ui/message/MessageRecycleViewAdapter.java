package com.emt.fatri.machinemonitormvp.ui.message;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.data.model.AlarmInfo;
import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;

import java.util.List;

/**
 * description:地区列表
 * Created by kingkong on 2018/5/11 0011.
 * changed by kingkong on 2018/5/11 0011.
 */

public class MessageRecycleViewAdapter extends RecyclerView.Adapter<MessageRecycleViewAdapter.MyHolder> {

    private static final String TAG = MessageRecycleViewAdapter.class.getSimpleName();
    private Context mContext;
    private List<AlarmInfo> mData;
    private OnItemClickListener mOnItemClickListener;

    public MessageRecycleViewAdapter(Context context, List<AlarmInfo> data ){
        mContext=context;
        mData=data;
    }
    public void setData(List<AlarmInfo> data)
    {
        mData=data;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.message_item_layout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.alarmDescription.setText(mData.get(position).description);
        holder.alarmTime.setText(mData.get(position).alarmTime);
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

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class MyHolder extends ViewHolder {
        TextView alarmDescription;
        TextView alarmTime;
        Button btnConfirm;
        Button btnCancel;
        MyHolder(View itemView) {
            super(itemView);
            alarmDescription =itemView.findViewById(R.id.tv_message_desc);
            alarmTime=itemView.findViewById(R.id.tv_message_time);
            btnConfirm=itemView.findViewById(R.id.btn_confirm);
            btnCancel=itemView.findViewById(R.id.btn_cancel);
        }

    }


}
