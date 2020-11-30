package com.example.androidcourse;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class MainWorker extends Worker {

    public static final String LOG_TAG = "download_main_worker";
    public static final String CHANNEL_ID = "downloadNotificationChannel";
    public static final int notificationId = 123;

    public MainWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Log.d(LOG_TAG , "Network conection esablished");
        Log.d(LOG_TAG , "Starting download...");

        Data data = getInputData();
        String searchAnimal = data.getString("search");

        Random random = new Random();
        String imageNumber = String.valueOf(random.nextInt(5));

        File localFile = null;

        try {
            localFile = File.createTempFile("images", "jpg");
            Log.d(LOG_TAG , "TempFile created");
        } catch (IOException e){
            Log.w(LOG_TAG , "TempFile creation failed!");
        }

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(searchAnimal).child(imageNumber + ".jpg");

        final long ONE_MEGABYTE = 1024 * 1024;
        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

                Log.d(LOG_TAG , "Download completed!");

                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Download completed!")
                        .setContentText("Tap to view image")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setLargeIcon(bitmap)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(bitmap)
                                .bigLargeIcon(null))
                        ;

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

                CharSequence name = "name";
                NotificationChannel channel =  new NotificationChannel(CHANNEL_ID,name,NotificationManager.IMPORTANCE_DEFAULT);

                notificationManager.createNotificationChannel(channel);
                notificationManager.notify(notificationId,builder.build());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(LOG_TAG , "Download failed!");
            }
        });

        return Result.success();
    }



}
