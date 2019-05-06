package com.emt.fatri.machinemonitormvp.ui.areas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.emt.fatri.machinemonitormvp.R;

/**
 * description:文字在右侧的sliderView
 * Created by kingkong on 2018/10/17 0017.
 * changed by kingkong on 2018/10/17 0017.
 */

public class RightTextSliderView extends BaseSliderView {
    protected RightTextSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.right_text_slider_layout,null);
        ImageView target = v.findViewById(com.daimajia.slider.library.R.id.daimajia_slider_image);
        TextView description = v.findViewById(com.daimajia.slider.library.R.id.description);
        description.setText(getDescription());
        bindEventAndShow(v, target);
        return v;
    }
}
