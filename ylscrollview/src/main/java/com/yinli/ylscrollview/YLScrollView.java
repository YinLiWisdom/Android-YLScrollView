package com.yinli.ylscrollview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by Yin Li on 16/03/15.
 */
public class YLScrollView extends FrameLayout {

    private ScrollView mScrollView;
    private YLVerticalTextLabelView mVerticalTextView;
    private YLVerticalGraphicLabelView mVerticalGraphicView;
    private LinearLayout mContainer;
    private int indicatorVisibility = -1;
    private int mTextViewMeasuredWidth;

    /* Style attributes */
    private int mAnimType;
    private String mText;
    private float mTextSize;
    private int mColor;
    private IndicatorType mType;

    public enum IndicatorType {
        Text(0), Graphic(1);

        public final int value;

        private IndicatorType(int value) {
            this.value = value;
        }

        public static IndicatorType fromValue(int value) {
            for (IndicatorType type : IndicatorType.values()) {
                if (type.value == value) {
                    return type;
                }
            }
            return null;
        }
    }

    public YLScrollView(Context context) {
        this(context, null);
    }

    public YLScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YLScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        /* Load default values from resources */
        final Resources res = getResources();
        final int defaultAnimType = res.getInteger(R.integer.default_indicator_animation_type);
        final String defaultText = res.getString(R.string.default_indicator_text);
        final float defaultTextSize = res.getDimension(R.dimen.default_indicator_text_size);
        final int defaultTextColor = res.getColor(R.color.default_indicator_fill_color);
        final int defaultIndicatorType = res.getInteger(R.integer.default_indicator_type);

        /* Retrieve styles attributes */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.YLScrollView, defStyle, 0);
        try {
            mAnimType = typedArray.getInt(R.styleable.YLScrollView_animationType, defaultAnimType);
            mText = typedArray.getString(R.styleable.YLScrollView_text);
            mTextSize = typedArray.getDimension(R.styleable.YLScrollView_textSize, defaultTextSize);
            mColor = typedArray.getColor(R.styleable.YLScrollView_fillColor, defaultTextColor);
            mType = IndicatorType.fromValue(typedArray.getInteger(R.styleable.YLScrollView_type, defaultIndicatorType));

            if (mText == null) {
                mText = defaultText;
            }
        } finally {
            typedArray.recycle();
        }

        init(context);
    }

    private void init(Context mContext) {

        mContainer = new LinearLayout(mContext);
        FrameLayout.LayoutParams params0 =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mContainer.setLayoutParams(params0);

        mVerticalTextView = new YLVerticalTextLabelView(mContext);
        LinearLayout.LayoutParams params2 =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params2.gravity = Gravity.CENTER_VERTICAL;
        mVerticalTextView.setText(mText);
        mVerticalTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mVerticalTextView.setTextColor(mColor);
        mVerticalTextView.setLayoutParams(params2);

        mVerticalGraphicView = new YLVerticalGraphicLabelView(mContext);
        float minWidth = Math.max(mVerticalGraphicView.getMidPointRadius() + mVerticalGraphicView.getStrokeWidth(), mVerticalGraphicView.getArrowXLen() + +mVerticalGraphicView.getStrokeWidth());
        LinearLayout.LayoutParams params3 =
                new LinearLayout.LayoutParams((int) (minWidth + 30), LinearLayout.LayoutParams.MATCH_PARENT);
        mVerticalGraphicView.setFillColor(mColor);
        mVerticalGraphicView.setLayoutParams(params3);

        mScrollView = new ScrollView(mContext);
        LinearLayout.LayoutParams params1 =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params1.weight = 1.0f;
        mScrollView.setScrollBarStyle(SCROLLBARS_OUTSIDE_OVERLAY);
        mScrollView.setLayoutParams(params1);
    }

    public int getAnimType() {
        return mAnimType;
    }

    public void setAnimType(int animType) {
        this.mAnimType = animType;
        invalidate();
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mVerticalTextView.setText(text);
        invalidate();
    }

    public float getTextSize() {
        return mVerticalTextView.getTextSize();
    }

    public void setTextSize(float textSize) {
        mVerticalTextView.setTextSize(textSize);
        invalidate();
    }

    public int getIndicatorColor() {
        int color = 0;
        switch (getIndicatorType()) {
            case Text:
                color = mVerticalTextView.getCurrentTextColor();
                break;
            case Graphic:
                color = mVerticalGraphicView.getFillColor();
                break;

        }
        return color;
    }

    public void setIndicatorColor(int color) {
        switch (getIndicatorType()) {
            case Text:
                mVerticalTextView.setTextColor(color);
                break;
            case Graphic:
                mVerticalGraphicView.setFillColor(color);
                break;
        }
        invalidate();
    }

    public IndicatorType getIndicatorType() {
        return mType;
    }

    public void setIndicatorType(IndicatorType mType) {
        this.mType = mType;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View subView = getChildAt(0);
        removeView(subView);
        mScrollView.addView(subView);

        mContainer.addView(mScrollView, 0);
        switch (mType) {
            case Text:
                mContainer.addView(mVerticalTextView);
                break;
            case Graphic:
                mContainer.addView(mVerticalGraphicView);
                break;
        }

        addView(mContainer);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {

/*            *//* Pivot is the geometric center of the text *//*
            final int textWidth = mVerticalTextView.getMeasuredWidth() / 2;
            final int textSize = (int) mVerticalTextView.getTextSize();

            final int gap = textWidth - textSize;

            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mVerticalTextView.getLayoutParams();
            params2.setMargins(0, 0, -gap, 0);
            mVerticalTextView.setLayoutParams(params2);

            LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mScrollView.getLayoutParams();
            params1.setMargins(0, 0, -gap, 0);
            mScrollView.setLayoutParams(params1);*/

            int containerHeight = getMeasuredHeight();
            View view = mScrollView.getChildAt(0);
            int contentHeight = view.getMeasuredHeight();
            indicatorVisibility = contentHeight > containerHeight ? VISIBLE : GONE;
            switch (mType) {
                case Text:
                    mVerticalTextView.setVisibility(indicatorVisibility);
                    break;
                case Graphic:
                    mVerticalGraphicView.setVisibility(indicatorVisibility);
                    break;
            }

        }
    }


}
