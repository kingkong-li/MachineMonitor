package com.emt.fatri.machinemonitormvp.ui.history;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.utils.DensityUtils;

/**
 * description:机器状态得分显示View
 * Created by kingkong on 2018/9/17 0017.
 * changed by kingkong on 2018/9/17 0017.
 */

public class MachineStateView extends View {

    /** 圆环半径大小 dp （画笔大小）**/
    private static final int BIG_CIRCLE_SIZE = 16;
    /** 实线画笔大小 dp **/
    private static final float SOLID_CIRCLE_WIDTH = 5f;
    private static final String TAG = MachineStateView.class.getSimpleName();

    /** 圆环画笔 **/
    private Paint mBigCirclePaint;
    /** 实圆环线画笔 **/
    private Paint mSolidCirclePaint;
    /** 文字画笔 **/
    private Paint mTextPaint;
    /**得分*/
    private int mScore=0;
    private float mOffset;

    public MachineStateView(Context context) {
        super(context);
        init(context);
    }
    public MachineStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MachineStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mBigCirclePaint = new Paint();
        mBigCirclePaint.setStrokeWidth(DensityUtils.dp2px(context, BIG_CIRCLE_SIZE));
        mBigCirclePaint.setStyle(Paint.Style.STROKE);
        mBigCirclePaint.setColor(Color.YELLOW);
        mBigCirclePaint.setAntiAlias(true);

        mSolidCirclePaint = new Paint();
        mSolidCirclePaint.setStrokeWidth(DensityUtils.dp2px(context, SOLID_CIRCLE_WIDTH));
        mSolidCirclePaint.setColor(Color.RED);
        mSolidCirclePaint.setStyle(Paint.Style.STROKE);
        mSolidCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mSolidCirclePaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(DensityUtils.sp2px(context, 26));
        mTextPaint.setAntiAlias(true);
        mOffset= -(mTextPaint.getFontMetrics().ascent +
                mTextPaint.getFontMetrics().descent) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(300,300,200,mBigCirclePaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(120, 120, 480, 480,
                    -90, 3.6f*mScore, false, mSolidCirclePaint);
        }
        canvas.drawText(String.valueOf(mScore), 300, 300+mOffset, mTextPaint);
    }

    /**
     * 设置图形中间的文本信息
     * @param score 文本
     */
    public void setScore(int score)
    {
        if(score<0 || score>100)
        {
            Log.e(TAG,"only accept score between 0 and 100");
            return;
        }
        mScore =score;
        postInvalidate();
    }
}
