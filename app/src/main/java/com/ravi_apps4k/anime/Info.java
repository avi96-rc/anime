package com.ravi_apps4k.anime;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class Info extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setTitle(getIntent().getStringExtra("title"));
    }

    public void sharethisapp(View item) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody =

                "सपना चौधरी के सारे डांस वीडियोस एक ही ऐप में देखे " +
                        "\n\uD83D\uDC47\uD83C\uDFFB\uD83D\uDC47\uD83C\uDFFB\uD83D\uDC47\uD83C\uDFFB\uD83D\uDC47" +
                        "\uD83C\uDFFB\uD83D\uDC47\uD83C\uDFFB\uD83D\uDC47\uD83C\uDFFB\uD83D\uDC83\uD83C\uDFFB\uD83D\uDC83\uD83C\uDFFB\uD83D\uDC83\uD83C\uDFFB\uD83D\uDC83\uD83C\uDFFB\uD83D\uDC83\uD83C\uDFFB\n" +

                        "https://play.google.com/store/apps/details?id=" + getPackageName();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "सपना चौधरी डांस");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "अपने दोस्तों के साथ शेयर करे"));
    }







    public void rateThisApp(View view) {
        String myYoutubeLInk = "https://play.google.com/store/apps/details?id="+getPackageName();
        Uri webpage = Uri.parse(myYoutubeLInk);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }





}
