package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class VaccinationStatistics extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView textViewReferencedate, textViewDaytotal, textViewDailydose1, textViewDailydose2, textViewTotalvaccinations;
    TextView Referencedate, Daytotal, Dailydose1, Dailydose2, Totalvaccinations;
    TextView textViewResult;
    DatePicker date_from;
    DatePicker date_to;
    TableLayout table_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_statistics);

        drawerLayout = findViewById(R.id.drawer_layout);
        date_from = findViewById(R.id.date_picker1);
        date_to = findViewById(R.id.date_picker2);

        table_layout = findViewById(R.id.table_layout);

        Button button_statistics = findViewById(R.id.button_statistics);

        button_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dfDateTime  = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                Calendar calendar1 = new GregorianCalendar(date_from.getYear(),
                        date_from.getMonth(),
                        date_from.getDayOfMonth());
                Calendar calendar2 = new GregorianCalendar(date_to.getYear(),
                        date_to.getMonth(),
                        date_to.getDayOfMonth());

                Referencedate = findViewById(R.id.referencedate);
                Referencedate.setText(getResources().getString(R.string.statistics_column1));
                Daytotal = findViewById(R.id.daytotal);
                Daytotal.setText(getResources().getString(R.string.statistics_column2));
                Dailydose1 = findViewById(R.id.dailydose1);
                Dailydose1.setText(getResources().getString(R.string.statistics_column3));
                Dailydose2 = findViewById(R.id.dailydose2);
                Dailydose2.setText(getResources().getString(R.string.statistics_column4));
                Totalvaccinations = findViewById(R.id.totalvaccinations);
                Totalvaccinations.setText(getResources().getString(R.string.statistics_column5));

                textViewReferencedate = findViewById(R.id.text_view_referencedate);
                textViewReferencedate.setText("");
                textViewDaytotal = findViewById(R.id.text_view_daytotal);
                textViewDaytotal.setText("");
                textViewDailydose1 = findViewById(R.id.text_view_dailydose1);
                textViewDailydose1.setText("");
                textViewDailydose2 = findViewById(R.id.text_view_dailydose2);
                textViewDailydose2.setText("");
                textViewTotalvaccinations = findViewById(R.id.text_view_totalvaccinations);
                textViewTotalvaccinations.setText("");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://data.gov.gr/api/v1/query/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                EndpointAPI endpointAPI = retrofit.create(EndpointAPI.class);

                Call<List<Post>> call = endpointAPI.getPosts(dfDateTime.format(calendar1.getTime()),dfDateTime.format(calendar2.getTime()));

                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (!response.isSuccessful()){
                            textViewResult.setText("Code: " + response.code());
                            return;
                        }
                        List<Post> posts = response.body();

                        Calendar calendar1 = new GregorianCalendar(date_from.getYear(),
                                date_from.getMonth(),
                                date_from.getDayOfMonth());

                        Calendar calendar2 = new GregorianCalendar(date_to.getYear(),
                                date_to.getMonth(),
                                date_to.getDayOfMonth());

                        LocalDate startDate = calendar1.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate endDate = calendar2.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        String content1 = "";
                        int content2 = 0;
                        int content3 = 0;
                        int content4 = 0;
                        int content5 = 0;

                        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1))
                        {

                            for (Post post: posts){

                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                                LocalDateTime dateTime = LocalDateTime.parse(post.getReferencedate(), formatter);
                                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy");

                                if(date.format(formatter2).equals(dateTime.format(formatter2))){
                                    content1 = dateTime.format(formatter2);
                                    content2 += post.getDaytotal();
                                    content3 += post.getDailydose1();
                                    content4 += post.getDailydose2();
                                    content5 += post.getTotalvaccinations();
                                }

                            }
                            if(content2 != 0 && content3 != 0 && content4 != 0 && content5 != 0){
                                textViewReferencedate.append(content1 + "\n");
                                textViewDaytotal.append(content2 + "\n");
                                textViewDailydose1.append(content3 + "\n");
                                textViewDailydose2.append(content4 + "\n");
                                textViewTotalvaccinations.append(content5 + "\n");
                            }

                            content1 = "";
                            content2 = 0;
                            content3 = 0;
                            content4 = 0;
                            content5 = 0;
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        textViewResult.setText(t.getMessage());
                    }
                });

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