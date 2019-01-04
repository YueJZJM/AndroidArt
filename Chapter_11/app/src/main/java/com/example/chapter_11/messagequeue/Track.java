package com.example.chapter_11.messagequeue;

import android.util.Log;

import java.sql.Time;

public class Track {
    private final static String TAG ="Track";
    private TaskManager taskManager;
    private TaskManagerThread taskManagerThread;

    private Track() {
        taskManager = TaskManager.getInstance();
        taskManagerThread = new TaskManagerThread();
        new Thread(taskManagerThread).start();
    }

    private static class TrackHolder {
        private static final Track INSTANCE = new Track();
    }

    public static Track getInstance() {
        return TrackHolder.INSTANCE;
    }

    public void trackEvent() {
        taskManager.addTasks(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                Log.d(TAG, String.valueOf(time));
            }
        });
    }

}
