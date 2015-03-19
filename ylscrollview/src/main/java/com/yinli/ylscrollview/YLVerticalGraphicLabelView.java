package com.yinli.ylscrollview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yinli on 18/03/15.
 */
public class YLVerticalGraphicLabelView extends View {
    public YLVerticalGraphicLabelView(Context context) {
        super(context, null);
    }

    public YLVerticalGraphicLabelView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public YLVerticalGraphicLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#CD5C5C"));
        paint.setStrokeWidth(3);
        paint.setPathEffect(null);
        paint.setStyle(Paint.Style.STROKE);

/*        float deltaX = 10f;
        float deltaY = 10f;
        float frac = (float) 0.1;

        float point_x_1 = x0 + (float) ((1 - frac) * deltaX + frac * deltaY);
        float point_y_1 = y0 + (float) ((1 - frac) * deltaY - frac * deltaX);

        float point_x_2 = x1;
        float point_y_2 = y1;

        float point_x_3 = x0 + (float) ((1 - frac) * deltaX - frac * deltaY);
        float point_y_3 = y0 + (float) ((1 - frac) * deltaY + frac * deltaX);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        path.moveTo(point_x_1, point_y_1);
        path.lineTo(point_x_2, point_y_2);
        path.lineTo(point_x_3, point_y_3);
        path.lineTo(point_x_1, point_y_1);
        path.lineTo(point_x_1, point_y_1);
        path.close();

        canvas.drawPath(path, paint); */

        int x=80;
        int y=80;
        int radius=40;
        canvas.drawCircle(x,x, radius, paint);

        drawTopLine(canvas, paint);
        drawMidCircle(canvas, paint);
        drawBotLine(canvas, paint);

    }

    private void drawTopLine(Canvas canvas, Paint paint) {
        float point_y_s = 80;
        float point_y_e = (500 - 20) / 2;

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        path.moveTo(80, point_y_s);
        path.lineTo(80, point_y_e);

        canvas.drawPath(path, paint);
    }

    private void drawBotLine(Canvas canvas, Paint paint) {
        float point_y_s = 500 / 2 + 10;
        float point_y_e = 500;

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        path.moveTo(80, point_y_s);
        path.lineTo(80, point_y_e);

        canvas.drawPath(path, paint);
    }

    private void drawMidCircle(Canvas canvas, Paint paint) {

        int radius = 5;

//        // Use Color.parseColor to define HTML colors
//        paint.setColor(Color.parseColor("#CD5C5C"));

        canvas.drawCircle(80, 250, radius, paint);
    }

    private void drawArrow(boolean up) {


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
