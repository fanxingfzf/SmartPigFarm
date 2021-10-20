package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Map.BaiduMapActivity;
import com.example.myapplication.util.ToastUtil;

public class OperatingFloorActivity extends BaseActivity implements View.OnClickListener {

    private Button Btn_Location;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_floor);
        init();
        initView();
        Btn_Location.setOnClickListener(this);
    }

    @Override
    public void init() {
        intent=new Intent();
    }

    @Override
    public void initView() {
        Btn_Location=findViewById(R.id.btn_location);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_location:
                intent.setClass(OperatingFloorActivity.this, BaiduMapActivity.class);
                ToastUtil.toastShort(this,"百度地图");
                startActivity(intent);
                break;

        }
    }
}