package com.example.myapplication.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.bean.MySQLiteOpenHelper;
import com.example.myapplication.bean.SmartPigData;
import com.example.myapplication.util.ToastUtil;

public class InsertActivity extends BaseActivity {

    private EditText etTemperature,etShidu,etAnqi,etGuangzhao;

    private MySQLiteOpenHelper mMySQLiteOpenHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        initView();
        mMySQLiteOpenHelper =new MySQLiteOpenHelper(this);

    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        etTemperature=findViewById(R.id.et_temperature);
        etShidu=findViewById(R.id.et_shidu);
        etAnqi=findViewById(R.id.et_anqi);
        etGuangzhao=findViewById(R.id.et_guangzhao);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    public void insert(View view) {
        String Temperature = etTemperature.getText().toString().trim();//trim去除编辑框里面的回车和空格键
        String Shidu = etShidu.getText().toString().trim();
        String Anqi = etAnqi.getText().toString().trim();
        String Guangzhao = etGuangzhao.getText().toString().trim();

        SmartPigData smartPigData=new SmartPigData();
        smartPigData.setTemperature(Temperature);
        smartPigData.setShidu(Shidu);
        smartPigData.setAnqi(Anqi);
        smartPigData.setGuangzhao(Guangzhao);

        //插入数据库中
        long rowId = mMySQLiteOpenHelper.insertData(smartPigData);
        if(rowId!=-1){
            ToastUtil.toastShort(this,"添加成功!");
        }else {
            ToastUtil.toastShort(this,"添加失败!");
        }

    }
}