package com.example.androidcourse;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MainWorker extends Worker {

    public static final String LOG_TAG = "download_main_worker";

    public MainWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Log.d(LOG_TAG , "Network conection exists");
        Log.d(LOG_TAG , "Starting download...");

        Data data = getInputData();
        String searchAnimal = data.getString("search");



        return Result.success();
    }
}
