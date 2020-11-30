package com.example.androidcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "main_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void downloadDog(View view) {
        Log.d(LOG_TAG,"Dog button was hit");
    }

    public void downloadCat(View view) {
        Log.d(LOG_TAG,"Cat button was hit");
    }

    public void downloadBunny(View view) {
        Log.d(LOG_TAG,"Bunny button was hit");
    }
}