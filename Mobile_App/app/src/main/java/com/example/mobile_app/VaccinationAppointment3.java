package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class VaccinationAppointment3 extends AppCompatActivity {

    DrawerLayout drawerLayout;

    DatePicker edit_date;
    TimePicker edit_time;

    Button button_change_datetime;
    Button button_cancel_change_datetime;

    SharedPreferences sharedPreferences;

    DatabaseReference reff;

    Appointment1 appointment;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_DATETIME = "datetime";
    private static final String KEY_AMKA = "amka";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_appointment3);

        drawerLayout = findViewById(R.id.drawer_layout);

        edit_date = findViewById(R.id.date_picker);
        edit_time = findViewById(R.id.time_picker);

        edit_time.setIs24HourView(true); //Set Time to 24 hours Format

        button_change_datetime = findViewById(R.id.button_change_datetime);
        button_cancel_change_datetime = findViewById(R.id.button_cancel_change_datetime);

        appointment = new Appointment1();

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String amka = sharedPreferences.getString(KEY_AMKA,null);

        button_change_datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dfDateTime  = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.getDefault());

                DatePicker datePicker = findViewById(R.id.date_picker);
                TimePicker timePicker =findViewById(R.id.time_picker);

                Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getHour(),
                        timePicker.getMinute());

                long time = calendar.getTimeInMillis();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_DATETIME, dfDateTime.format(calendar.getTime()));
                editor.apply();

                appointment.setDatetime(dfDateTime.format(calendar.getTime()));

                reff = FirebaseDatabase.getInstance().getReference().child("appointment");

                Query query = reff.orderByChild("amka").equalTo(amka);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot Snapshot: dataSnapshot.getChildren()) {
                            Snapshot.getRef().child("datetime").setValue(dfDateTime.format(calendar.getTime()));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                Intent intent = new Intent(VaccinationAppointment3.this, VaccinationAppointment2.class);
                startActivity(intent);

                Toast.makeText(VaccinationAppointment3.this, getResources().getString(R.string.vaccination_appointment_toast3), Toast.LENGTH_SHORT).show();


            }
        });

        button_cancel_change_datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaccinationAppointment3.this, VaccinationAppointment2.class);
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
        MainActivity.redirectActivity(this, VaccinationAppointment2.class);
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
