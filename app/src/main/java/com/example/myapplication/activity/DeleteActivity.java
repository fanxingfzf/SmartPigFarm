package com.example.myapplication.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.bean.MySQLiteOpenHelper;
import com.example.myapplication.util.ToastUtil;

public class DeleteActivity extends BaseActivity {

    private EditText etTemperature;
    private MySQLiteOpenHelper mMySQLiteOpenHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initView();
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        etTemperature=findViewById(R.id.et_temperature);
        mMySQLiteOpenHelper=new MySQLiteOpenHelper(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    public void delete(View view) {
        String Temperature = etTemperature.getText().toString().trim();

        //按姓名从数据库中删除
        int row=mMySQLiteOpenHelper.deleteFromDbByTemperature(Temperature);
        if(row>0){
            ToastUtil.toastLong(this,"删除成功，删了"+row+"条数据");
        }else {
            ToastUtil.toastLong(this,"删除失败，没有找到符合条件的数据");
        }
    }
}