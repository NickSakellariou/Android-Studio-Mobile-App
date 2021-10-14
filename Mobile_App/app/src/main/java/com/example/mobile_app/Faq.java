package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;


public class Faq extends AppCompatActivity {

    DrawerLayout drawerLayout;
    WebView webView1, webView2, webView3, webView4, webView5, webView6, webView7, webView8, webView9,webView10, webView11,
            webView12, webView13, webView14, webView15, webView16, webView17, webView18, webView19, webView20, webView21,
            webView22,webView23, webView24, webView25, webView26, webView27,webView28, webView29, webView30, webView31,
            webView32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        drawerLayout = findViewById(R.id.drawer_layout);

        webView1 = (WebView) findViewById(R.id.webview1);
        webView2 = (WebView) findViewById(R.id.webview2);
        webView3 = (WebView) findViewById(R.id.webview3);
        webView4 = (WebView) findViewById(R.id.webview4);
        webView5 = (WebView) findViewById(R.id.webview5);
        webView6 = (WebView) findViewById(R.id.webview6);
        webView7 = (WebView) findViewById(R.id.webview7);
        webView8 = (WebView) findViewById(R.id.webview8);
        webView9 = (WebView) findViewById(R.id.webview9);
        webView10 = (WebView) findViewById(R.id.webview10);
        webView11 = (WebView) findViewById(R.id.webview11);
        webView12 = (WebView) findViewById(R.id.webview12);

        webView13 = (WebView) findViewById(R.id.webview13);
        webView14 = (WebView) findViewById(R.id.webview14);
        webView15 = (WebView) findViewById(R.id.webview15);
        webView16 = (WebView) findViewById(R.id.webview16);
        webView17 = (WebView) findViewById(R.id.webview17);
        webView18 = (WebView) findViewById(R.id.webview18);
        webView19 = (WebView) findViewById(R.id.webview19);
        webView20 = (WebView) findViewById(R.id.webview20);
        webView21 = (WebView) findViewById(R.id.webview21);
        webView22 = (WebView) findViewById(R.id.webview22);
        webView23 = (WebView) findViewById(R.id.webview23);
        webView24 = (WebView) findViewById(R.id.webview24);
        webView25 = (WebView) findViewById(R.id.webview25);
        webView26 = (WebView) findViewById(R.id.webview26);
        webView27 = (WebView) findViewById(R.id.webview27);
        webView28 = (WebView) findViewById(R.id.webview28);
        webView29 = (WebView) findViewById(R.id.webview29);
        webView30 = (WebView) findViewById(R.id.webview30);
        webView31 = (WebView) findViewById(R.id.webview31);
        webView32 = (WebView) findViewById(R.id.webview32);


        String title1 = getResources().getString(R.string.faq_title1);
        String question1 = getResources().getString(R.string.question1);
        String question2 = getResources().getString(R.string.question2);
        String question3 = getResources().getString(R.string.question3);
        String question4 = getResources().getString(R.string.question4);
        String question5 = getResources().getString(R.string.question5);
        String question6 = getResources().getString(R.string.question6);
        String question7 = getResources().getString(R.string.question7);
        String question8 = getResources().getString(R.string.question8);
        String question9 = getResources().getString(R.string.question9);
        String question10 = getResources().getString(R.string.question10);

        String title2 = getResources().getString(R.string.faq_title2);
        String answer1 = getResources().getString(R.string.answer1);
        String answer2 = getResources().getString(R.string.answer2);
        String answer3 = getResources().getString(R.string.answer3);
        String answer4 = getResources().getString(R.string.answer4);
        String answer5 = getResources().getString(R.string.answer5);
        String answer6 = getResources().getString(R.string.answer6);
        String answer7 = getResources().getString(R.string.answer7);
        String answer8 = getResources().getString(R.string.answer8);
        String answer9 = getResources().getString(R.string.answer9);
        String answer10 = getResources().getString(R.string.answer10);

        webView1.loadData (title1, "text/html", null);
        webView2.loadData (question1, "text/html", null);
        webView3.loadData (question2, "text/html", null);
        webView4.loadData (question3, "text/html", null);
        webView5.loadData (question4, "text/html", null);
        webView6.loadData (question5, "text/html", null);
        webView7.loadData (question6, "text/html", null);
        webView8.loadData (question7, "text/html", null);
        webView9.loadData (question8, "text/html", null);
        webView10.loadData (question9, "text/html", null);
        webView11.loadData (question10, "text/html", null);

        webView12.loadData (title2, "text/html", null);
        webView13.loadData (question1, "text/html", null);
        webView14.loadData (answer1, "text/html", null);
        webView15.loadData (question2, "text/html", null);
        webView16.loadData (answer2, "text/html", null);
        webView17.loadData (question3, "text/html", null);
        webView18.loadData (answer3, "text/html", null);
        webView19.loadData (question4, "text/html", null);
        webView20.loadData (answer4, "text/html", null);
        webView21.loadData (question5, "text/html", null);
        webView22.loadData (answer5, "text/html", null);
        webView23.loadData (question6, "text/html", null);
        webView24.loadData (answer6, "text/html", null);
        webView25.loadData (question7, "text/html", null);
        webView26.loadData (answer7, "text/html", null);
        webView27.loadData (question8, "text/html", null);
        webView28.loadData (answer8, "text/html", null);
        webView29.loadData (question9, "text/html", null);
        webView30.loadData (answer9, "text/html", null);
        webView31.loadData (question10, "text/html", null);
        webView32.loadData (answer10, "text/html", null);
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