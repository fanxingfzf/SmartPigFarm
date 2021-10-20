package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.bean.MySQLiteOpenHelper;
import com.example.myapplication.bean.SmartPigData;

import java.util.List;

public class QueryActivity extends BaseActivity {

    private EditText etTemperature;
    private TextView tvResult;
    private MySQLiteOpenHelper mMySQLiteOpenHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(R.layout.activity_query);
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        etTemperature = findViewById(R.id.et_temperature);
        mMySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        tvResult = findViewById(R.id.tvResult);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    public void query(View view) {

        String Temperature = etTemperature.getText().toString().trim();
        if (TextUtils.isEmpty(Temperature)) {
            //查询所有数据
            List<SmartPigData> smartPigDatas = mMySQLiteOpenHelper.queryAllFromDb();
            showData(smartPigDatas);
            return;
        }

        //按温度从数据库中查询
        List<SmartPigData> smartPigDatas = mMySQLiteOpenHelper.queryFromDbByTemperature(Temperature);
        showData(smartPigDatas);
    }

    private void showData(List<SmartPigData> smartPigDatas) {
        String result = "";


        StringBuilder stringBuilder = new StringBuilder();

//        for (SmartPigData s : smartPigDatas) {
//            result += "温度:" + s.getTemperature() + "湿度：" + s.getShidu() + "氨气:"
//                    + s.getAnqi() + "光照" + s.getGuangzhao() + "\n";
//        }

        for (SmartPigData s : smartPigDatas) {
            stringBuilder.append("温度:");
            stringBuilder.append(s.getTemperature());
            stringBuilder.append("湿度：");
            stringBuilder.append(s.getShidu());
            stringBuilder.append("氨气:");
            stringBuilder.append(s.getAnqi());
            stringBuilder.append("光照");
            stringBuilder.append(s.getGuangzhao() + "\n");

            result += "温度:" + s.getTemperature() + "湿度：" + s.getShidu() + "氨气:"
                    + s.getAnqi() + "光照" + s.getGuangzhao() + "\n";
        }
        tvResult.setText(stringBuilder.toString());
    }
}