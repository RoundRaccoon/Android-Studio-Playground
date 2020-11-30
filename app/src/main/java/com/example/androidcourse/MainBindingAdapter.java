package com.example.androidcourse;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.databinding.BindingAdapter;

public class MainBindingAdapter {

    @BindingAdapter("textTitleRes")
    public static void setTitle(TextView textView, @StringRes int textTitleRes) {
        Context context = textView.getContext();
        textView.setText(context.getString(textTitleRes));
    }

}
