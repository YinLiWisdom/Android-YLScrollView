package com.yinli.ylscrollview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yinli on 18/03/15.
 */
public class YLVerticalGraphicLabelView extends View {

    private float MID_POINT_RADIUS = 10;
    private float ARROW_X_LEN = 20;
    private float ARROW_Y_LEN = 20;
    private float STROKE_WIDTH = 5;
    private float LENGTH_RATIO = 1f / 2f;
    private int fillColor;

    public YLVerticalGraphicLabelView(Context context) {
        super(context, null);
    }

    public YLVerticalGraphicLabelView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public YLVerticalGraphicLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Resources res = getResources();
        MID_POINT_RADIUS = res.getDimension(R.dimen.graphic_mid_point_radius);
        ARROW_X_LEN = res.getDimension(R.dimen.graphic_arrow_width);
        ARROW_Y_LEN = res.getDimension(R.dimen.graphic_arrow_height);
        STROKE_WIDTH = res.getDimension(R.dimen.graphic_stroke_width);
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
        invalidate();
    }

    public float getMidPointRadius() {
        return MID_POINT_RADIUS;
    }

    public float getArrowXLen() {
        return ARROW_X_LEN;
    }

    public float getArrowYLen() {
        return ARROW_Y_LEN;
    }

    public float getStrokeWidth() {
        return STROKE_WIDTH;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // Use Color.parseColor to define HTML colors
        paint.setColor(fillColor);

        drawTopLine(canvas, paint);
        drawMidCircle(canvas, paint);
        drawBotLine(canvas, paint);
    }

    private void drawTopLine(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(STROKE_WIDTH);
//        paint.setPathEffect(null);
        paint.setStyle(Paint.Style.STROKE);

        float point_y_s = getMeasuredHeight() * (1 - LENGTH_RATIO) / 2;
        float point_y_e = getMeasuredHeight() / 2 - 2 * MID_POINT_RADIUS;

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        path.moveTo(getMeasuredWidth() / 2, point_y_s);
        path.lineTo(getMeasuredWidth() / 2, point_y_e);

        canvas.drawPath(path, paint);

        drawArrow(canvas, paint, true, getMeasuredWidth() / 2, point_y_s);
    }

    private void drawBotLine(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setStyle(Paint.Style.STROKE);

        float point_y_s = getMeasuredHeight() / 2 + 2 * MID_POINT_RADIUS;
        float point_y_e = getMeasuredHeight() * (1 + LENGTH_RATIO) / 2;

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        path.moveTo(getMeasuredWidth() / 2, point_y_s);
        path.lineTo(getMeasuredWidth() / 2, point_y_e);

        canvas.drawPath(path, paint);

        drawArrow(canvas, paint, false, getMeasuredWidth() / 2, point_y_e);
    }

    private void drawMidCircle(Canvas canvas, Paint paint) {

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, MID_POINT_RADIUS, paint);
    }

    private void drawArrow(Canvas canvas, Paint paint, boolean up, float x, float y) {

        paint.setStyle(Paint.Style.FILL);

        float startX = x;
        float startY;
        if (up) {
            startY = y + ARROW_Y_LEN;
        } else {
            startY = y - ARROW_Y_LEN;
        }

        /* Start Point */
        PointF point_0 = new PointF(startX, startY);
        /* Left Point */
        PointF point_1 = new PointF(startX - ARROW_X_LEN / 2, startY);
        /* Peak point */
        PointF point_2 = new PointF(startX, y);
        /* Right Point */
        PointF point_3 = new PointF(startX + ARROW_X_LEN / 2, startY);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        path.moveTo(point_0.x, point_0.y);
        path.lineTo(point_1.x, point_1.y);
        path.lineTo(point_2.x, point_2.y);
        path.lineTo(point_3.x, point_3.y);
        path.close();

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
