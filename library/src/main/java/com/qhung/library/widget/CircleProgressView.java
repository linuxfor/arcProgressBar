package com.qhung.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.qhung.library.R;

/**
 * Created by qhung on 2016/5/24.
 */
public class CircleProgressView extends View {

    private Paint mExCirclePaint;
    private Paint mExCirclePaint2;
    private Paint mExCirclePaint3;
    private Paint mExCirclePaint4;
    private float exCircleWidth;
    private float centerX;
    private float centerY;
    private RectF exCircle;
    private RectF exCircle2;
    private RectF exCircle3;

    private Paint arcPaint;

    private int progressBarReachedColor;
    private int progressBarUnreachedColor;
    private int exCircleColor;
    private int maxValue;
    private int progress;

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        if (a != null) {

            exCircleWidth = a.getDimension(R.styleable.CircleProgressView_circle_progressbar_width, dipToPx(25));
            progressBarReachedColor = a.getColor(R.styleable.CircleProgressView_circle_progressbar_color, 0xfff39114);
            progressBarUnreachedColor = a.getColor(R.styleable.CircleProgressView_circle_progressbar_unreached_color, 0xff1e1f21);
            exCircleColor = a.getColor(R.styleable.CircleProgressView_exCircleColor, 0xff2a2b2d);
            maxValue = a.getInteger(R.styleable.CircleProgressView_circle_max_value, 100);
            a.recycle();
        }

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mExCirclePaint = new Paint();
        mExCirclePaint.setAntiAlias(true);
        mExCirclePaint.setStyle(Paint.Style.STROKE);
        mExCirclePaint.setColor(progressBarUnreachedColor);
        mExCirclePaint.setStrokeWidth(exCircleWidth);
        mExCirclePaint.setShadowLayer(15, 0, 0, Color.BLACK);

        arcPaint = new Paint();
        arcPaint.setColor(progressBarReachedColor);
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(exCircleWidth);
        arcPaint.setShadowLayer(15, 0, 0, progressBarReachedColor);


        mExCirclePaint2 = new Paint();
        mExCirclePaint2.setAntiAlias(true);
        mExCirclePaint2.setStyle(Paint.Style.STROKE);
        mExCirclePaint2.setColor(exCircleColor);
        mExCirclePaint2.setStrokeWidth(exCircleWidth);
        mExCirclePaint2.setShadowLayer(15, 0, 0, 0xff2a2b2d);

        mExCirclePaint3 = new Paint();
        mExCirclePaint3.setAntiAlias(true);
        mExCirclePaint3.setColor(0xff232426);
        mExCirclePaint3.setStyle(Paint.Style.STROKE);
        mExCirclePaint3.setStrokeWidth(exCircleWidth);
        mExCirclePaint3.setShadowLayer(15, 0, 0, Color.BLACK);

        mExCirclePaint4 = new Paint();
        mExCirclePaint4.setAntiAlias(true);
        mExCirclePaint4.setStyle(Paint.Style.FILL);
        mExCirclePaint4.setColor(0xff1e1f21);
        mExCirclePaint4.setStrokeWidth(exCircleWidth);
        mExCirclePaint4.setShadowLayer(2, 0, 0, Color.BLACK);


    }

    private void initView() {

        exCircle = new RectF();
        exCircle.left = exCircleWidth;
        exCircle.right = centerX * 2 - exCircleWidth;
        exCircle.top = exCircleWidth;
        exCircle.bottom = centerY * 2 - exCircleWidth;

        Log.d("qq", exCircle.toString());
        exCircle2 = new RectF();
        exCircle2.top = exCircle.top + exCircleWidth;
        exCircle2.bottom = exCircle.bottom - exCircleWidth;
        exCircle2.left = exCircle.left + exCircleWidth;
        exCircle2.right = exCircle.right - exCircleWidth;

        exCircle3 = new RectF();
        exCircle3.top = exCircle2.top + exCircleWidth;
        exCircle3.bottom = exCircle2.bottom - exCircleWidth;
        exCircle3.left = exCircle2.left + exCircleWidth;
        exCircle3.right = exCircle2.right - exCircleWidth;

        LinearGradient linearGradient = new LinearGradient((exCircle3.right + exCircle3.left) / 2,
                exCircle3.top, (exCircle3.right + exCircle3.left) / 2, exCircle3.bottom,
                0xff424345, 0xff1e1d20, Shader.TileMode.CLAMP);
        mExCirclePaint4.setShader(linearGradient);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        centerX = min / 2;
        centerY = min / 2;
        initView();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawExCircle(canvas, exCircle, mExCirclePaint);

        canvas.drawArc(exCircle, 45, progress * 1f / maxValue * 360, false, arcPaint);
        drawExCircle(canvas, exCircle3, mExCirclePaint3);
        drawExCircle(canvas, exCircle2, mExCirclePaint2);
        drawExCircle(canvas, exCircle3, mExCirclePaint4);


    }

    public void setMaxValue(int maxValue) {
        if (maxValue > 0)
            this.maxValue = maxValue;
        postInvalidate();
    }

    public void setProgress(int progress) {
        if (progress < maxValue)
            this.progress = progress;
        else this.progress = maxValue;
        postInvalidate();
    }

    private void drawExCircle(Canvas canvas, RectF f, Paint mExCirclePaint) {
        canvas.drawArc(f, 0, 360, false, mExCirclePaint);
    }


    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
}
