package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;


public class VaccinationAppointment extends AppCompatActivity {

    DrawerLayout drawerLayout;

    EditText editText_name, editText_surname, editText_amka, editText_phone, editText_email;
    Button button_save;

    //Interface for accessing and modifying preference data returned by Context.getSharedPreferences(String, int).
    // For any particular set of preferences, there is a single instance of this class that all clients share.
    // Modifications to the preferences must go through an Editor object to ensure the preference values
    // remain in a consistent state and control when they are committed to storage.
    // Objects that are returned from the various get methods must be treated as immutable by the application.
    SharedPreferences sharedPreferences;

    DatabaseReference reff;

    long maxid = 0;

    Appointment1 appointment;

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
        setContentView(R.layout.activity_vaccination_appointment);

        drawerLayout = findViewById(R.id.drawer_layout);

        editText_name = findViewById(R.id.edit_text1);
        editText_surname = findViewById(R.id.edit_text2);
        editText_amka = findViewById(R.id.edit_text3);
        editText_phone = findViewById(R.id.edit_text4);
        editText_email = findViewById(R.id.edit_text5);
        button_save = findViewById(R.id.button_save);

        appointment = new Appointment1();

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        //when open activity first check shared preferences data available or not

        String name = sharedPreferences.getString(KEY_NAME, null);

        if (name != null) {
            //if data is available call on VaccinationAppointment2
            Intent intent = new Intent(VaccinationAppointment.this, VaccinationAppointment2.class);
            startActivity(intent);
        }

        //getInstance -> Gets the default FirebaseDatabase instance
        //getReference -> Gets a DatabaseReference for the database root node.
        reff = FirebaseDatabase.getInstance().getReference().child("appointment");

        //Add a listener for changes in the data at this location.
        // Each time the data changes,
        // your listener will be called with an immutable snapshot of the data.
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateName() | !validateSurname() | !validateAMKA() | !validatePhone() | !validateEmail()) {
                    return;
                }

                SimpleDateFormat dfDateTime  = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.getDefault());
                int year = 2021;
                int month = randBetween(6, 7); //Months will be between July and August
                int hour = randBetween(9, 22); //Hours will be displayed in between 9 to 22
                int min = 0;
                int sec = 0;


                GregorianCalendar gc = new GregorianCalendar(year, month, 1);
                int day = randBetween(1, gc.getActualMaximum(gc.DAY_OF_MONTH));

                gc.set(year, month, day, hour, min,sec);

                appointment.setName(editText_name.getText().toString().trim());
                appointment.setSurname(editText_surname.getText().toString().trim());
                appointment.setAmka(editText_amka.getText().toString().trim());
                appointment.setPhone(editText_phone.getText().toString().trim());
                appointment.setEmail(editText_email.getText().toString().trim());
                appointment.setDatetime(dfDateTime.format(gc.getTime()));

                reff.child(String.valueOf(maxid + 1)).setValue(appointment);


                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, editText_name.getText().toString());
                editor.putString(KEY_SURNAME, editText_surname.getText().toString());
                editor.putString(KEY_AMKA, editText_amka.getText().toString());
                editor.putString(KEY_PHONE, editText_phone.getText().toString());
                editor.putString(KEY_EMAIL, editText_email.getText().toString());
                editor.putString(KEY_DATETIME, dfDateTime.format(gc.getTime()));
                editor.apply();

                Intent intent = new Intent(VaccinationAppointment.this, VaccinationAppointment2.class);
                startActivity(intent);

                Toast.makeText(VaccinationAppointment.this, getResources().getString(R.string.vaccination_appointment_toast1), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    private boolean validateName() {
        String name = editText_name.getText().toString().trim();
        String checkname = "[a-zA-Zα-ωΑ-Ω]+";

        if (name.isEmpty()) {
            editText_name.setError(getResources().getString(R.string.vaccination_appointment_name_error1));
            return false;
        } else if (!name.matches(checkname)) {
            editText_name.setError(getResources().getString(R.string.vaccination_appointment_name_error2));
            return false;
        } else {
            editText_name.setError(null);
            return true;
        }
    }

    private boolean validateSurname() {
        String surname = editText_surname.getText().toString().trim();
        String checksurname = "[a-zA-Zα-ωΑ-Ω]+";

        if (surname.isEmpty()) {
            editText_surname.setError(getResources().getString(R.string.vaccination_appointment_surname_error1));
            return false;
        } else if (!surname.matches(checksurname)) {
            editText_surname.setError(getResources().getString(R.string.vaccination_appointment_surname_error2));
            return false;
        } else {
            editText_surname.setError(null);
            return true;
        }
    }

    private boolean validateAMKA() {
        String amka = editText_amka.getText().toString().trim();

        if (amka.isEmpty()) {
            editText_amka.setError(getResources().getString(R.string.vaccination_appointment_amka_error1));
            return false;
        } else if (amka.length() != 11) {
            editText_amka.setError(getResources().getString(R.string.vaccination_appointment_amka_error2));
            return false;
        }else if(!amka.matches("[0-9]+")){
            editText_amka.setError(getResources().getString(R.string.vaccination_appointment_amka_error3));
            return false;
        } else {
            editText_amka.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = editText_phone.getText().toString().trim();

        if (phone.isEmpty()) {
            editText_phone.setError(getResources().getString(R.string.vaccination_appointment_phone_error1));
            return false;
        } else if (phone.length() != 10) {
            editText_phone.setError(getResources().getString(R.string.vaccination_appointment_phone_error2));
            return false;
        }else if(!phone.matches("[0-9]+")){
            editText_phone.setError(getResources().getString(R.string.vaccination_appointment_phone_error3));
            return false;
        } else {
            editText_phone.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String email = editText_email.getText().toString().trim();
        String checkmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) {
            editText_email.setError(getResources().getString(R.string.vaccination_appointment_email_error1));
            return false;
        } else if (!email.matches(checkmail)) {
            editText_email.setError(getResources().getString(R.string.vaccination_appointment_email_error2));
            return false;
        } else {
            editText_email.setError(null);
            return true;
        }
    }


    public void ClickMenu(View view) {
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickHome(View view) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickGallery(View view) {
        MainActivity.redirectActivity(this, Gallery.class);
    }

    public void ClickVaccinationAppointment(View view) {
        MainActivity.redirectActivity(this, VaccinationAppointment.class);
    }

    public void ClickVaccinationStatistics(View view){
        MainActivity.redirectActivity(this,VaccinationStatistics.class);
    }

    public void ClickFaq(View view){
        MainActivity.redirectActivity(this,Faq.class);
    }

    public void ClickExit(View view) {
        MainActivity.exit(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        MainActivity.closeDrawer(drawerLayout);
    }
}
