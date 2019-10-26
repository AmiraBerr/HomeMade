package com.example.homemade;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.Timer;

import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper my_Data_Base;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_Data_Base = new DatabaseHelper(this);

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

         Intent intent = new Intent(MainActivity.this , HomeActivity.class);

         startActivity(intent);

         finish();

            }
        },2000);
    }



}
