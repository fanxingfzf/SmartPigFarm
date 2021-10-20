package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.UseFile.ImageUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TouxiangActivity extends BaseActivity {

    private ImageView ivAvatar;
    private Button mBitmapSave;

    public static final int REQUEST_CODE_TAKE = 1;
    public static final int REQUEST_CODE_CHOOSE = 0;
    private Uri imageUri;//图片的位置
    private String imageBase64;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touxiang);
        init();
        initView();
        initData();
        initEvent();

    }

    @Override
    public void init() {
        intent = new Intent();
    }

    @Override
    public void initView() {
        ivAvatar = findViewById(R.id.iv_photo_home);
    }

    @Override
    public void initData() {
        getDataFromSpf();
    }

    private void getDataFromSpf() {
        SharedPreferences spfRecord = getSharedPreferences("spfRecord", MODE_PRIVATE);
        String image64 = spfRecord.getString("image_64", "");
        ivAvatar.setImageBitmap(ImageUtil.base64ToImage(image64));
    }

    @Override
    public void initEvent() {

    }

    public void takePhoto(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            //判断是否有相机这个权限
            doTake();//真正的去拍照
        } else {
            //去申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                doTake();
            } else {
                Toast.makeText(this, "你没有获得摄像头权限~", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {
                Toast.makeText(this, "你没有获得访问相册权限~", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void doTake() {
        File imageTemp = new File(getExternalCacheDir(), "imageOut.jpg");

        try {
            if (imageTemp.exists()) {
                imageTemp.delete();
            }
            imageTemp.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT > 24) {
            //contentProvider
            imageUri = FileProvider.getUriForFile(this, "com.example.myapplication.fileprovider", imageTemp);
        } else {
            imageUri = Uri.fromFile(imageTemp);//api 24一下才能获取
        }
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, REQUEST_CODE_TAKE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE) {
            if (requestCode == RESULT_OK) {
                //获取拍摄的照片
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    ivAvatar.setImageBitmap(bitmap);
                    String imageToBase64 = ImageUtil.imageToBase64(bitmap);
                    imageBase64 = imageToBase64;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE) {

            if (Build.VERSION.SDK_INT < 19) {
                handleImageBeforeApil9(data);
            } else {
                handleImageOnApil9(data);
            }
        }
    }

    private void handleImageBeforeApil9(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @TargetApi(19)
    private void handleImageOnApil9(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {

            String documentId = DocumentsContract.getDocumentId(uri);

            if (TextUtils.equals(uri.getAuthority(), "com.android.providers,media.documents")) {
                String id = documentId.split(":")[1];//解析出它的id和suche
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath=getImagePath(uri,selection);
            } else if (TextUtils.equals(uri.getAuthority(), "com.android.providers,downloads.documents")) {

                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagePath = getImagePath(contentUri, null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri, null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        //数据库这边的操作
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();//关闭以免内存泄露
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

            ivAvatar.setImageBitmap(bitmap);
            String imageToBase64 = ImageUtil.imageToBase64(bitmap);
            imageBase64 = imageToBase64;
        }
    }

    public void choosePhoto(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //判断是否有相机这个权限
            openAlbum();//真正的去打开相册
        } else {
            //去申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    private void openAlbum() {
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_CHOOSE);
    }

    public void save(View view) {


        SharedPreferences spfRecord = getSharedPreferences("spfRecord", MODE_PRIVATE);
        SharedPreferences.Editor edit = spfRecord.edit();
        edit.putString("image_64", imageBase64);
        edit.apply();
        this.finish();
    }
}