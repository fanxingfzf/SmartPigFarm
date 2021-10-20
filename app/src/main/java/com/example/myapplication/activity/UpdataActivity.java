package com.example.myapplication.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.bean.MySQLiteOpenHelper;
import com.example.myapplication.bean.SmartPigData;
import com.example.myapplication.util.ToastUtil;

public class UpdataActivity extends BaseActivity {

    private EditText etTemperature,etShidu,etAnqi,etGuangzhao;

    private MySQLiteOpenHelper mMySQLiteOpenHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);

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

    public void updata(View view) {
        String Temperature = etTemperature.getText().toString().trim();//trim去除编辑框里面的回车和空格键
        String Shidu = etShidu.getText().toString().trim();
        String Anqi = etAnqi.getText().toString().trim();
        String Guangzhao = etGuangzhao.getText().toString().trim();

        SmartPigData smartPigData=new SmartPigData();
        smartPigData.setTemperature(Temperature);
        smartPigData.setShidu(Shidu);
        smartPigData.setAnqi(Anqi);
        smartPigData.setGuangzhao(Guangzhao);

        //更新数据库
        long rowId = mMySQLiteOpenHelper.updataData(smartPigData);
        if(rowId>0){
            ToastUtil.toastShort(this,"更新成功!");
        }else {
            ToastUtil.toastShort(this,"没有数据被更新!");
        }
    }
}