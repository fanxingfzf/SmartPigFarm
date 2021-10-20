package com.example.myapplication.bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mySQLite.db";//数据库名称
    private static final String TABLE_NAME_SMARTPIGDATA = "smartpigdata";//表名称

    private static final String CREATE_TABLE_SQL = " create table " + TABLE_NAME_SMARTPIGDATA + " (id integer primary key autoincrement,Temperature text, Shidu text,Anqi text,Guangzhao text); ";

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(SmartPigData smartPigData) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("Temperature", smartPigData.getTemperature());
        values.put("Shidu", smartPigData.getShidu());
        values.put("Anqi", smartPigData.getAnqi());
        values.put("Guangzhao", smartPigData.getGuangzhao());

        return db.insert(TABLE_NAME_SMARTPIGDATA, null, values);
    }

    public int deleteFromDbByTemperature(String temperature) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_SMARTPIGDATA, "temperature like ?", new String[]{temperature});
    }

    public int updataData(SmartPigData smartPigData) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("Temperature", smartPigData.getTemperature());
        values.put("Shidu", smartPigData.getShidu());
        values.put("Anqi", smartPigData.getAnqi());
        values.put("Guangzhao", smartPigData.getGuangzhao());

        return db.update(TABLE_NAME_SMARTPIGDATA, values, "Temperature like ?", new String[]{smartPigData.getTemperature()});
    }

    public List<SmartPigData> queryFromDbByTemperature(String Temperature) {
        SQLiteDatabase db = getWritableDatabase();
        List<SmartPigData> smartPigDataList = new ArrayList<>();


        Cursor cursor = db.query(TABLE_NAME_SMARTPIGDATA, null, "Temperature like ?", new String[]{Temperature}, null, null, null);


        if (cursor != null) {

            while (cursor.moveToNext()) {
                String Temperature1 = cursor.getString(cursor.getColumnIndex("Temperature"));
                String Shidu = cursor.getString(cursor.getColumnIndex("Shidu"));
                String Anqi = cursor.getString(cursor.getColumnIndex("Anqi"));
                String Guangzhao = cursor.getString(cursor.getColumnIndex("Guangzhao"));

                SmartPigData smartPigData = new SmartPigData();
                smartPigData.setTemperature(Temperature1);
                smartPigData.setShidu(Shidu);
                smartPigData.setAnqi(Anqi);
                smartPigData.setGuangzhao(Guangzhao);

                smartPigDataList.add(smartPigData);
            }

            cursor.close();
        }
        return smartPigDataList;
    }



    public List<SmartPigData> queryAllFromDb() {
        SQLiteDatabase db = getWritableDatabase();
        List<SmartPigData> smartPigDataList = new ArrayList<>();


        Cursor cursor = db.query(TABLE_NAME_SMARTPIGDATA, null, null, null, null, null, null);


        if (cursor != null) {

            while (cursor.moveToNext()) {
                String Temperature1 = cursor.getString(cursor.getColumnIndex("Temperature"));
                String Shidu = cursor.getString(cursor.getColumnIndex("Shidu"));
                String Anqi = cursor.getString(cursor.getColumnIndex("Anqi"));
                String Guangzhao = cursor.getString(cursor.getColumnIndex("Guangzhao"));

                SmartPigData smartPigData = new SmartPigData();
                smartPigData.setTemperature(Temperature1);
                smartPigData.setShidu(Shidu);
                smartPigData.setAnqi(Anqi);
                smartPigData.setGuangzhao(Guangzhao);

                smartPigDataList.add(smartPigData);
            }

            cursor.close();
        }
        return smartPigDataList;
    }

}
