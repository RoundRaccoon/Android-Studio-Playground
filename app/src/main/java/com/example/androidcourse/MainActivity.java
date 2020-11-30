package com.example.androidcourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.work.WorkManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidcourse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "main_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainViewModel viewModel = new MainViewModel(WorkManager.getInstance(this));

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setMainViewModel(viewModel);

    }
}