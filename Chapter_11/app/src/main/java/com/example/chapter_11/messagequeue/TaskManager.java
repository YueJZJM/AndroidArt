package com.example.chapter_11.messagequeue;

import java.util.LinkedList;

public class TaskManager {

    private LinkedList<Runnable> tasks;

    private TaskManager() {
        tasks = new LinkedList<>();
    }

    private static class TaskManagerHolder {
        private static final TaskManager INSTANCE = new TaskManager();
    }

    public static TaskManager getInstance() {
        return TaskManagerHolder.INSTANCE;
    }

    public void addTasks(Runnable runnable) {
        synchronized (tasks) {
            tasks.addLast(runnable);
        }
    }

    public Runnable getTasks() {
        if (tasks.size() > 0) {
            return tasks.removeFirst();
        }
        return null;
    }
}
