package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
       // XUI.initTheme(this);
        super.onCreate(savedInstanceState);

        //StatusBarUtils.initStatusBarStyle(this,false, ActivityCompat.getColor(this,R.color.main_blue));
    }
    public abstract void init();
    public abstract void initView();
    public abstract void initData();
    public abstract void initEvent();

}
