package com.example.androidcourse;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.ViewModel;
import androidx.work.WorkManager;

public class MainViewModel extends ViewModel {

    public static final String LOG_TAG = "main_tag";
    private final WorkManager workManager;

    public String dogText = "Dog" , catText = "Cat" , bunnyText = "Bunny";

    public MainViewModel(WorkManager workManager) {
        this.workManager = workManager;
    }

    public void download(View view){
        Button button = (Button) view;
        String animal = button.getText().toString();
        Log.d(LOG_TAG,animal + " button was hit from viewmodel");
    }

}
