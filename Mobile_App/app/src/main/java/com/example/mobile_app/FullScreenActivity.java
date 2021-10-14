package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class FullScreenActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        imageView = (ImageView) findViewById(R.id.image_view);


        //Return the intent that started this activity.
        Intent i = getIntent();

        //Retrieves a map of extended data from the intent.
        int position = i.getExtras().getInt("id");

        ImageAdapter imageAdapter = new ImageAdapter(this);

        //Sets a drawable as the content of this ImageView.
        imageView.setImageResource(imageAdapter.imageArray[position]);

    }
}
