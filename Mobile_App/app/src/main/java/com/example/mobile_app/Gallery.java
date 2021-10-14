package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class Gallery extends AppCompatActivity {

    GridView gridView;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        drawerLayout = findViewById(R.id.drawer_layout);

        gridView = (GridView) findViewById(R.id.grid_view);

        // An adapter actually bridges between UI components and the data source that fill data into UI Component.
        //The adapter provides access to the data items.
        //The adapter is also responsible for making a View for each item in the data set.
        // Adapter can be used to supply the data to like spinner, list view, grid view etc.
        gridView.setAdapter(new ImageAdapter(this));

        // The ListView and GridView are subclasses of AdapterView and they can be populated by binding them to an Adapter,
        // which retrieves data from an external source and creates a View that represents each data entry.
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),FullScreenActivity.class);
                intent.putExtra("id",position);
                startActivity(intent);

            }
        });
    }

    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        MainActivity.redirectActivity(this,MainActivity.class);
    }

    public void ClickHome(View view){
        MainActivity.redirectActivity(this,MainActivity.class);
    }

    public void ClickGallery(View view){
        MainActivity.redirectActivity(this,Gallery.class);
    }

    public void ClickVaccinationAppointment(View view){
        MainActivity.redirectActivity(this,VaccinationAppointment.class);
    }

    public void ClickVaccinationStatistics(View view){
        MainActivity.redirectActivity(this,VaccinationStatistics.class);
    }

    public void ClickFaq(View view){
        MainActivity.redirectActivity(this,Faq.class);
    }

    public void ClickExit(View view){
        MainActivity.exit(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        MainActivity.closeDrawer(drawerLayout);
    }
}