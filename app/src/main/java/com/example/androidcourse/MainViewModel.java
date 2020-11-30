package com.example.androidcourse;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
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

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        Data inputData = new Data.Builder()
                .putString("search" , animal)
                .build();

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MainWorker.class)
                .setInputData(inputData)
                .setConstraints(constraints)
                .build();

        Log.d(LOG_TAG, "Waiting for an internet connection...");

        workManager.beginUniqueWork(
                "download-random-image",
                ExistingWorkPolicy.APPEND,
                request)
                .enqueue();

    }

}
