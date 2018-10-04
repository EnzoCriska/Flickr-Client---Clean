/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.admin.flickrclient_clean.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.flickrclient_clean.R;
import com.example.admin.flickrclient_clean.presentation.UI.adapter.AdapterRecycle;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class BitmapCache extends LruCache<String, Bitmap> {

    public BitmapCache(int maxSize) {
        super(maxSize);
    }

    public static int getCacheSize() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // use 1/8th of the available memory for this memory cache.
        return maxMemory / 8;
    }

    @Override
    public void resize(int maxSize) {
        super.resize(maxSize);
    }

    @Override
    public void trimToSize(int maxSize) {
        super.trimToSize(maxSize);
    }

    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
    }

    @Override
    protected Bitmap create(String key) {
        return super.create(key);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return super.sizeOf(key, value);
    }

    public Bitmap getBitmap(String key){
        return this.get(key);
    }

    public void setBitmap(String key, Bitmap bitmap) {
        if (!hasBitmap(key)) {
            this.put(key, bitmap);
        }
    }

    public boolean hasBitmap(String key){
        return this.get(key) != null;
    }

    public void setorDownBitmap(String key, Context context, ImageView iv){
        if (this.hasBitmap(key)){
            iv.setImageBitmap(this.getBitmap(key));
        }else{
            Picasso.get()
                        .load(key)
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .into(iv);
//                iv.setOnClickListener((View.OnClickListener) context);

        }
    }
}
