package com.example.mobile_app;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter  extends BaseAdapter {

    private Context mContext;

    // Constructor
    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {

        return imageArray.length;
    }

    @Override
    public Object getItem(int position) {

        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
        return imageView;
    }

    public int[] imageArray = {
            R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4,R.drawable.home_image
    };
}

