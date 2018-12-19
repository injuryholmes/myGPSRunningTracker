package com.example.allenholmes.mygpsrunningtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void trackingServiceButtonOnClick(View v) {
//        Intent intent = new Intent(this, LogLocationService.class);
//        startService(intent);
        Intent intent = new Intent(this, GoogleMapActivity.class);
        startActivity(intent);
    }

}
