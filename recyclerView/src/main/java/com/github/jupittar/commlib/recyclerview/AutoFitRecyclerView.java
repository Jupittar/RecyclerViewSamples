package com.github.jupittar.commlib.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class AutoFitRecyclerView extends RecyclerView {

    private GridLayoutManager mLayoutManager;
    private int mColumnWidth = -1;

    public AutoFitRecyclerView(Context context) {

        super(context);
        init(context, null);
    }

    public AutoFitRecyclerView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(context, attrs);
    }

    public AutoFitRecyclerView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {

            int[] attrsArray = {
                    android.R.attr.columnWidth
            };

            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            mColumnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }

        mLayoutManager = new GridLayoutManager(getContext(), 1);
        setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {

        super.onMeasure(widthSpec, heightSpec);

        if (mColumnWidth > 0) {

            int spanCount = Math.max(1, getMeasuredWidth() / mColumnWidth);
            mLayoutManager.setSpanCount(spanCount);
        }
    }
}
