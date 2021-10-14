package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class VaccinationAppointment2 extends AppCompatActivity {

    DrawerLayout drawerLayout;

    TextView textView_name, textView_surname, textView_amka, textView_phone, textView_email, textView_datetime;
    Button button_cancel, button_change;

    //Interface for accessing and modifying preference data returned by Context.getSharedPreferences(String, int).
    // For any particular set of preferences, there is a single instance of this class that all clients share.
    // Modifications to the preferences must go through an Editor object to ensure the preference values
    // remain in a consistent state and control when they are committed to storage.
    // Objects that are returned from the various get methods must be treated as immutable by the application.
    SharedPreferences sharedPreferences;

    DatabaseReference reff;

    long maxid = 0;

    //private static final will be considered as constant and the constant can be accessed
    // within this class only. Since, the keyword static included, the value will be constant
    // for all the objects of the class.
    // private final variable value will be like constant per object
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_AMKA = "amka";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_DATETIME = "datetime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_appointment2);

        drawerLayout = findViewById(R.id.drawer_layout);

        textView_name = findViewById(R.id.name);
        textView_surname = findViewById(R.id.surname);
        textView_amka = findViewById(R.id.amka);
        textView_phone = findViewById(R.id.phone);
        textView_email = findViewById(R.id.email);
        textView_datetime = findViewById(R.id.datetime);
        button_cancel = findViewById(R.id.button_cancel);
        button_change = findViewById(R.id.button_change);

        // Retrieve and hold the contents of the preferences file 'name',
        // returning a SharedPreferences through which you can retrieve and modify its values.
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);
        String surname = sharedPreferences.getString(KEY_SURNAME,null);
        String amka = sharedPreferences.getString(KEY_AMKA,null);
        String phone = sharedPreferences.getString(KEY_PHONE,null);
        String email = sharedPreferences.getString(KEY_EMAIL,null);
        String datetime = sharedPreferences.getString(KEY_DATETIME,null);

        if (name != null || surname != null || amka != null || phone != null || email != null){

            textView_name.setText(getResources().getString(R.string.vaccination_appointment_textview1) +name);
            textView_surname.setText(getResources().getString(R.string.vaccination_appointment_textview2) +surname);
            textView_amka.setText(getResources().getString(R.string.vaccination_appointment_textview3) +amka);
            textView_phone.setText(getResources().getString(R.string.vaccination_appointment_textview4) +phone);
            textView_email.setText(getResources().getString(R.string.vaccination_appointment_textview5) +email);
            textView_datetime.setText(getResources().getString(R.string.vaccination_appointment_textview6) +datetime);

        }

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(VaccinationAppointment2.this,getResources().getString(R.string.vaccination_appointment_toast2), Toast.LENGTH_SHORT).show();
                finish();

                reff = FirebaseDatabase.getInstance().getReference().child("appointment");

                Query query = reff.orderByChild("amka").equalTo(amka);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot Snapshot: dataSnapshot.getChildren()) {
                            Snapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                Intent intent = new Intent(VaccinationAppointment2.this, VaccinationAppointment.class);
                startActivity(intent);
            }
        });

        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaccinationAppointment2.this, VaccinationAppointment3.class);
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
