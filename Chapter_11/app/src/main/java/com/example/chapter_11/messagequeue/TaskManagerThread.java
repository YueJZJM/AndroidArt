package com.example.chapter_11.messagequeue;

import android.util.Log;

import com.example.chapter_11.messagequeue.TaskManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskManagerThread implements Runnable {

    private static final String TAG = "TaskManagerThread";

    private TaskManager mTaskManager;

    private ExecutorService mPool;

    private final int POOL_SIZE = 1;

    private final int SLEEP_TIME = 300;

    private boolean isStop = false;

    public TaskManagerThread() {
        this.mTaskManager = TaskManager.getInstance();
        this.mPool = Executors.newFixedThreadPool(POOL_SIZE);
    }

    @Override
    public void run() {
        while (!isStop) {
            Log.d(TAG, String.valueOf(isStop));
            Runnable task = mTaskManager.getTasks();
            if (task != null) {
                mPool.execute(task);
            } else {
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
