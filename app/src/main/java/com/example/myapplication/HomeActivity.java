package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myapplication.activity.GuanyuActivity;
import com.example.myapplication.activity.ShebeiActivity;
import com.example.myapplication.activity.ShezhiActivity;
import com.example.myapplication.activity.TouxiangActivity;
import com.example.myapplication.activity.ZhanghaoActivity;

public class HomeActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout Touxiang;
    private LinearLayout Zhanghao;
    private LinearLayout Shebei;
    private LinearLayout Shezhi;
    private LinearLayout Guanyu;
    private Button Btn1;
    private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        initView();
        Touxiang.setOnClickListener(this);
        Zhanghao.setOnClickListener(this);
        Shebei.setOnClickListener(this);
        Shezhi.setOnClickListener(this);
        Guanyu.setOnClickListener(this);
    }

    @Override
    public void init() {
        intent=new Intent();
    }

    @Override
    public void initView() {
        Touxiang =findViewById(R.id.touxiang);
        Zhanghao =findViewById(R.id.zhanghao);
        Shebei =findViewById(R.id.shebei);
        Shezhi =findViewById(R.id.shezhi);
        Guanyu =findViewById(R.id.guanyu);

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
            case R.id.touxiang:
                intent.setClass(HomeActivity.this, TouxiangActivity.class);
                startActivity(intent);
                break;
            case R.id.zhanghao:
                intent.setClass(HomeActivity.this, ZhanghaoActivity.class);
                startActivity(intent);
                break;
            case R.id.shebei:
                intent.setClass(HomeActivity.this, ShebeiActivity.class);
                startActivity(intent);
                break;
            case R.id.shezhi:
                intent.setClass(HomeActivity.this, ShezhiActivity.class);
                startActivity(intent);
                break;
            case R.id.guanyu:
                intent.setClass(HomeActivity.this, GuanyuActivity.class);
                startActivity(intent);
                break;

        }
    }
}