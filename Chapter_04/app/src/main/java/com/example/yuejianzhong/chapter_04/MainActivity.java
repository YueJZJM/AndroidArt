package com.example.yuejianzhong.chapter_04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.CircleView).setOnClickListener(this);
        findViewById(R.id.HorizontalScrollViewEx).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.CircleView:
                intent = new Intent(this, CircleViewActivity.class);
                startActivity(intent);
                break;
            case R.id.HorizontalScrollViewEx:
                intent = new Intent(this, HorizontalScrollViewExActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
