package com.example.jeremy.overdraw;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Card {

    private int x;
    private int height;
    private Bitmap bitmap;

    public Card(Resources resources, int resId, int x) {
        this.x = x;
        this.bitmap = BitmapFactory.decodeResource(resources, resId);
        this.height = this.bitmap.getHeight();
    }

    public int getX() {
        return x;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getHeight() {
        return height;
    }
}
