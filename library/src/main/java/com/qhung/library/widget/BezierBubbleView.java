package com.qhung.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by qhung on 2016/5/22.
 */
public class BezierBubbleView extends View {

    private Point startPoint;
    private Paint bezierPaint;
    private int radius = 50;
    private boolean touched;
    private Rect rect;

    private Point a;
    private Point b;
    private Point c;
    private Point d;
    private Point controlPoint;

    public BezierBubbleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierBubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        startPoint.x = 300;
        startPoint.y = 300;
        controlPoint = new Point();
        bezierPaint = new Paint();
        a = new Point();
        b = new Point();
        c = new Point();
        d = new Point();
        bezierPaint.setColor(Color.RED);
        bezierPaint.setStyle(Paint.Style.FILL);
        bezierPaint.setAntiAlias(true);
        getParent().requestDisallowInterceptTouchEvent(true);
        rect = new Rect();
        rect.left = startPoint.x - radius;
        rect.top = startPoint.y - radius;
        rect.right = startPoint.x + radius;
        rect.bottom = startPoint.y + radius;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && !touched) {
            if (rect.contains((int) event.getX(), (int) event.getY())) {
                touched = true;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP
                || event.getAction() == MotionEvent.ACTION_CANCEL) {
            touched = false;
        }
        controlPoint.x = (int) (event.getX() + startPoint.x) / 2;
        controlPoint.y = (int) (event.getY() + startPoint.y) / 2;
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculate();
    }

    private void calculate() {
        
    }
}
