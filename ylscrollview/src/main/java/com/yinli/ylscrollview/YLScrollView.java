package com.yinli.ylscrollview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Yin Li on 16/03/15.
 */
public class YLScrollView extends FrameLayout {

    private ScrollView mScrollView;
    private TextView mTextView;
    private LinearLayout mContainer;
    private int indicatorVisibility;

    public YLScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context mContext) {
        mContainer = new LinearLayout(mContext);
        FrameLayout.LayoutParams params0 =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mContainer.setLayoutParams(params0);

        mTextView = new TextView(mContext);
        LinearLayout.LayoutParams params2 =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params2.gravity = Gravity.CENTER_VERTICAL;
        mTextView.setRotation(270f);
        mTextView.setText("Scroll to view more");
        mTextView.setTextColor(Color.LTGRAY);
        mTextView.setLayoutParams(params2);

        mScrollView = new ScrollView(mContext);
        LinearLayout.LayoutParams params1 =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mScrollView.setScrollBarStyle(SCROLLBARS_OUTSIDE_OVERLAY);
        mScrollView.setLayoutParams(params1);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View subView = getChildAt(0);
        removeView(subView);
        mScrollView.addView(subView);

        mContainer.addView(mScrollView);
        mContainer.addView(mTextView);

        addView(mContainer);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (indicatorVisibility == VISIBLE) {
            /* Pivot is the geometric center of the text */
            int textWidth = mTextView.getWidth() / 2;
            int textSize = (int) mTextView.getTextSize();

            int gap = textWidth - textSize;
            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mTextView.getLayoutParams();
            params2.setMargins(0, 0, -gap, 0);
            mTextView.setLayoutParams(params2);

            LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mScrollView.getLayoutParams();
            params1.weight = 1.0f;
            params1.setMargins(0, 0, -gap, 0);
            mScrollView.setLayoutParams(params1);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        int containerHeight = getHeight();

        View view = mScrollView.getChildAt(0);
        int contentHeight = view.getHeight();

        indicatorVisibility = contentHeight > containerHeight ? VISIBLE : GONE;
        mTextView.setVisibility(indicatorVisibility);
    }
}
