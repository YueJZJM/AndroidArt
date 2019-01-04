package com.example.chapter_11;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

public class LocalIntentService extends IntentService {

    private final static String TAG = "LocalIntentService";
    public LocalIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getStringExtra("mainactivity");
        Log.d(TAG, action);
        SystemClock.sleep(3000);
        if ("main1".equals(action)) {
            Log.d(TAG, "action: " + action);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
