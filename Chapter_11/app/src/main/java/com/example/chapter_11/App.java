package com.example.chapter_11;

import android.app.Application;

import com.example.chapter_11.messagequeue.MyActivityLifecycleCallbacks;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
    }
}
