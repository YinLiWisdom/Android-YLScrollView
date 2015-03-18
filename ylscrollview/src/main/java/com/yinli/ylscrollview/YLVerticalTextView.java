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
public class YLVerticalTextView extends TextView{

    private boolean isTopDown;

    public YLVerticalTextView(Context context) {
        super(context, null);
    }

    public YLVerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public YLVerticalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /* Retrieve styles attributes */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.YLVerticalTextView, defStyleAttr, 0);
        try {
            isTopDown = typedArray.getBoolean(R.styleable.YLVerticalTextView_topDown, true);
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
            canvas.translate(getMeasuredWidth(), 0);
            canvas.rotate(90);
        } else {
            canvas.translate(0, getMeasuredHeight());
            canvas.rotate(-90);
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
