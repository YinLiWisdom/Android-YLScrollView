package com.yinli.ylscrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Yin Li on 18/03/15.
 */
public class YLVerticalTextLabelView extends TextView{

    private boolean isTopDown;

    public YLVerticalTextLabelView(Context context) {
        this(context, null);
    }

    public YLVerticalTextLabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YLVerticalTextLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /* Retrieve styles attributes */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.YLVerticalTextLabelView, defStyleAttr, 0);
        try {
            isTopDown = typedArray.getBoolean(R.styleable.YLVerticalTextLabelView_ylsv_topDown, false);
        } finally {
            typedArray.recycle();
        }

        init();
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {

        TextPaint textPaint = getPaint();
        /* Use the original text color */
        textPaint.setColor(getCurrentTextColor());
        /* Use the original drawable states  */
        textPaint.drawableState = getDrawableState();

        canvas.save();

        if (isTopDown) {
            canvas.rotate(-90);
            canvas.translate(-getMeasuredHeight(), 0);
        } else {
            canvas.rotate(90);
            canvas.translate(0, -getMeasuredWidth());
        }

        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        getLayout().draw(canvas);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /* Exchange width with height */
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }
}
