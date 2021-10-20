package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;

public class DataActivity extends BaseActivity {

    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        init();
    }

    @Override
    public void init() {
        intent = new Intent();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    public void insertData(View view) {
        intent.setClass(DataActivity.this, InsertActivity.class);
        startActivity(intent);
    }

    public void deleteData(View view) {
        intent.setClass(DataActivity.this, DeleteActivity.class);
        startActivity(intent);
    }

    public void updataData(View view) {
        intent.setClass(DataActivity.this, UpdataActivity.class);
        startActivity(intent);
    }

    public void queryData(View view) {
        intent.setClass(DataActivity.this, QueryActivity.class);
        startActivity(intent);
    }
}