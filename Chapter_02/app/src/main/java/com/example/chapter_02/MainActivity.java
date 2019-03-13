package com.example.chapter_02;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.chapter_02.binderpool.BinderPoolActivity;
import com.example.chapter_02.messenger.MessengerActivity;
import com.example.chapter_02.model.User;
import com.example.chapter_02.provider.ProviderActivity;
import com.example.chapter_02.socket.TCPClientActivity;
import com.example.chapter_02.utils.MyConstants;
import com.example.chapter_02.utils.MyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MainActivity";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.recoverFromFile).setOnClickListener(this);
        findViewById(R.id.persistToFile).setOnClickListener(this);
        findViewById(R.id.messenger).setOnClickListener(this);
        findViewById(R.id.book).setOnClickListener(this);
        findViewById(R.id.provider).setOnClickListener(this);
        findViewById(R.id.tcp).setOnClickListener(this);
        findViewById(R.id.binder).setOnClickListener(this);

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(this,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void persistToFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User(0, "jack", true);
                File dir = new File(MyConstants.CHAPTER_2_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                    Log.d(TAG, "if");
                }
                if (!dir.exists()) {
                    dir.mkdirs();
                    Log.d(TAG, "if");
                }
                File cachedFile = new File(MyConstants.CACHE_FILE_PATH);
                ObjectOutputStream objectOutputStream = null;
                try {
                    objectOutputStream = new ObjectOutputStream(
                            new FileOutputStream(cachedFile));
                    objectOutputStream.writeObject(user);
                    Log.d(TAG, user.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    MyUtils.close(objectOutputStream);
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recoverFromFile:
                recoverFromFile();
                break;
            case R.id.persistToFile:   
                persistToFile();
                break;
            case R.id.messenger:
                Intent intent = new Intent(this, MessengerActivity.class);
                startActivity(intent);
                break;
            case R.id.book:
                Intent intent1 = new Intent(this, BookManagerActivity.class);
                startActivity(intent1);
                break;
            case R.id.provider:
                Intent intent2 = new Intent(this, ProviderActivity.class);
                startActivity(intent2);
                break;
            case R.id.tcp:
                Intent intent3 = new Intent(this, TCPClientActivity.class);
                startActivity(intent3);
                break;
            case R.id.binder:
                Intent intent4 = new Intent(this, BinderPoolActivity.class);
                startActivity(intent4);
            default:
                break;
        }
    }

    private void recoverFromFile() {
        User user = null;
        File cachedFile = new File(MyConstants.CACHE_FILE_PATH);
        if (cachedFile.exists()) {
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(
                        new FileInputStream(cachedFile));
                user = (User) objectInputStream.readObject();
                Log.d(TAG, user.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                MyUtils.close(objectInputStream);
            }
        }
    }
}
