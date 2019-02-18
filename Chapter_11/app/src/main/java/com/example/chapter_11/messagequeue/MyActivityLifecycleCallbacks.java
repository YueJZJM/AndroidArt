package com.example.chapter_11.messagequeue;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;


public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d("Main2Activity", "onActivityStarted");
    }


    @Override
    public void onActivityResumed(Activity activity) {

        Log.d("Main2Activity", "onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {

        Log.d("Main2Activity", "onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {

        Log.d("Main2Activity", "onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
