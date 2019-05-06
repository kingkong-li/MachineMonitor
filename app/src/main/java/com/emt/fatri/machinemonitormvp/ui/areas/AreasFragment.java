package com.emt.fatri.machinemonitormvp.ui.areas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.base.GlobalField;
import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;
import com.emt.fatri.machinemonitormvp.data.source.DatabaseHelper;
import com.emt.fatri.machinemonitormvp.ui.machine.MachineActivity;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注区域列表界面
 * create by kingkong
 *
 */
public class AreasFragment extends Fragment implements AreasContract.IAreasView {
    private AreasPresenter mAreasPresenter;
    private RecyclerView mRecyclerView;
    private AreasRecycleViewAdapter mAreasRecycleViewAdapter;
    private SliderLayout mDemoSlider;

    private RecyclerView mDialogRecyclerView;
    private AreasRecycleViewAdapter mDialogRecylerViewAdapter;
    private Context mContext;
    private List<AreaInfo> mAttentionAreaList;
    private List<AreaInfo> mAreaList;
    private AlertDialog mAlertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_areas, container, false);
        mAreasPresenter=new AreasPresenter(this);
        mAreasPresenter.getMyAreaList();
        mContext=this.getActivity();
        mRecyclerView=view.findViewById(R.id.area_list_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAreasRecycleViewAdapter =new AreasRecycleViewAdapter(mContext,null);
        mRecyclerView.setAdapter(mAreasRecycleViewAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mAreasRecycleViewAdapter.setOnItemClickListener(new AreasRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent();
                intent.setClass(mContext,MachineActivity.class);
                intent.putExtra(GlobalField.AREA_ID,mAttentionAreaList.get(position).mAreaId);
                mContext.startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {

            }
        });

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    DatabaseHelper.deleteAreaUseId(mAttentionAreaList.get(position).mAreaId);
                                    mAttentionAreaList.remove(position);

                                    mAreasRecycleViewAdapter.notifyItemRemoved(position);
                                }
                                mAreasRecycleViewAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    DatabaseHelper.deleteAreaUseId(mAttentionAreaList.get(position).mAreaId);
                                    mAttentionAreaList.remove(position);
                                    mAreasRecycleViewAdapter.notifyItemRemoved(position);
                                }
                                mAreasRecycleViewAdapter.notifyDataSetChanged();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);

        mDemoSlider = view.findViewById(R.id.slider);


//        // 添加网络图片
//        HashMap<String,String> urlMaps = new HashMap<>();
//        urlMaps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
//        urlMaps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
//        urlMaps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
//        // 也可以传本地图片，重新搞一个map String int就行
//        for(String name : urlMaps.keySet()){
//            TextSliderView textSliderView = new TextSliderView(this);
//            textSliderView
//                    .description(name)//描述
//                    .image(urlMaps.get(name))//image方法可以传入图片url、资源id、File 这里可以传入本地图片
//                    .setScaleType(BaseSliderView.ScaleType.Fit)//图片缩放类型
//                    .setOnSliderClickListener(onSliderClickListener);//图片点击
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle().putString("extra",name);//传入参数
//            mDemoSlider.addSlider(textSliderView);//添加一个滑动页面
//        }

        // 添加本地图片
        ArrayList<Integer> localPicture=new ArrayList<>();
        localPicture.add(R.drawable.new0);
        localPicture.add(R.drawable.new1);
        localPicture.add(R.drawable.new2);

        for(int i=0;i<localPicture.size();i++){
            RightTextSliderView textSliderView = new RightTextSliderView(mContext);
            textSliderView
                    .description("东渡港口11号港机发生故障")//描述
                    .image(localPicture.get(i))//image方法可以传入图片url、资源id、File 这里可以传入本地图片
                    .setScaleType(BaseSliderView.ScaleType.Fit)//图片缩放类型
                    .setOnSliderClickListener(onSliderClickListener);//图片点击
            textSliderView.bundle(new Bundle());

            switch (i){
                case 0:
                    textSliderView.description("最新故障消息如下:\n东渡港口11号港机发生故障");
                    break;
                case 1:
                    textSliderView.description(" 厦大港口7号港机螺丝松动");
                    break;
                case 2:
                    textSliderView.description(" 观音山4号港机发动机损坏");
                    break;
            }

            mDemoSlider.addSlider(textSliderView);//添加一个滑动页面
        }




        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);//滑动动画
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//默认指示器样式
        mDemoSlider.setCustomIndicator((PagerIndicator)view.findViewById(R.id.custom_indicator2));//自定义指示器
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        mDemoSlider.setDuration(4000);//设置滚动时间，也是计时器时间
        mDemoSlider.addOnPageChangeListener(onPageChangeListener);
        Button addButton=view.findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAreaListDialog();
            }
        });


        return view;
    }



    private BaseSliderView.OnSliderClickListener onSliderClickListener=new BaseSliderView.OnSliderClickListener() {
        @Override
        public void onSliderClick(BaseSliderView slider) {
//            ToastUtils.getInstance().show(slider.getBundle().get("extra")+"");

        }
    };
    //页面改变监听
    private ViewPagerEx.OnPageChangeListener onPageChangeListener=new ViewPagerEx.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            Log.d("ansen", "Page Changed: " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    };


    @Override
    public void showMyAreas(List<AreaInfo> infoList) {
        mAttentionAreaList=infoList;
        mAreasRecycleViewAdapter.setData(infoList);
    }

    @Override
    public void showAreaIntroduce(List<AreaInfo> info) {
        mAreaList=info;
        mDialogRecylerViewAdapter.setData(info);
    }



    @Override
    public void showLastErrorTime(String lastErrorTime) {

    }

    @Override
    public void showCurrentState(int code) {

    }

    private void showAreaListDialog()
    {
        //请求数据
        mAreasPresenter.getAreaInfo();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.area_list_dialog, null);
        mDialogRecyclerView=view.findViewById(R.id.area_list_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mDialogRecyclerView.setLayoutManager(layoutManager);
        mDialogRecylerViewAdapter =new AreasRecycleViewAdapter(mContext,null);
        mDialogRecyclerView.setAdapter(mDialogRecylerViewAdapter);
        mDialogRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));

        mDialogRecylerViewAdapter.setOnItemClickListener(new AreasRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

                DatabaseHelper.insertNewItemToDb(mAreaList.get(position));
                mAlertDialog.dismiss();
                mAreasPresenter.getMyAreaList();

            }

            @Override
            public void onLongClick(int position) {

            }
        });

        mAlertDialog =new AlertDialog.Builder(mContext)
                .setView(view).create();
        mAlertDialog.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        mDemoSlider.stopAutoCycle();
    }

    @Override
    public void onResume() {
        super.onResume();
        mDemoSlider.startAutoCycle();
    }
}
