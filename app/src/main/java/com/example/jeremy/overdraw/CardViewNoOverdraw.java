package com.example.jeremy.overdraw;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CardViewNoOverdraw extends View {
    private static final String TAG = "CardViewNoOverdraw";

    private static final int mCardSpacing = 150;
    private int mCardLeft = 10;

    private List<Card> mCardList = new ArrayList<>();

    private Paint mPaint = new Paint();

    private int[] resIds = new int[]{
            R.drawable.alex,
            R.drawable.chris,
            R.drawable.claire,
            R.drawable.colt
    };

    public CardViewNoOverdraw(Context context) {
        this(context, null);
    }

    public CardViewNoOverdraw(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardViewNoOverdraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Resources resources = getResources();
        for (int resId : resIds) {
            mCardList.add(new Card(resources, resId, mCardLeft));
            mCardLeft += mCardSpacing;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxHeight = mCardList.get(0).getHeight();
        for (Card card : mCardList.subList(1, mCardList.size())) {
            maxHeight = Math.min(maxHeight, card.getHeight());
        }
        heightMeasureSpec = maxHeight;
        Log.d(TAG, "onMeasure: " + widthMeasureSpec + ", " + heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        for (Card card : mCardList.subList(0, mCardList.size() - 1)) {
            drawDisplayedCard(canvas, card);
        }
        drawLastCard(canvas, mCardList.get(mCardList.size() - 1));
        // invalidate();
    }

    private void drawLastCard(Canvas canvas, Card card) {
        canvas.drawBitmap(card.getBitmap(), card.getX(), 0, mPaint);
    }

    private void drawDisplayedCard(Canvas canvas, Card card) {
        canvas.save();
        int left = card.getX();
        canvas.clipRect(left, 0, left + mCardSpacing, card.getHeight());
        canvas.drawBitmap(card.getBitmap(), left, 0, mPaint);
        canvas.restore();
    }
}
