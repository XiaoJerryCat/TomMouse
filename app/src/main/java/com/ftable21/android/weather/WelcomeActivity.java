package com.ftable21.android.weather;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;

import com.ftable21.android.weather.basic.BaseActivity;
import com.ftable21.android.weather.util.WeatherUtil;

import java.util.ArrayList;
import java.util.List;

import com.ftable21.android.weather.R;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (WeatherUtil.queryAllLocationMsg() == null || WeatherUtil.queryAllLocationMsg().size() == 0) {
            setContentView(R.layout.activity_welcome);
            requestPermissions();
        } else { gotoMainPage(); }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                int count = 0;
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        count += result;
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                    .setMessage("授权失败,无法使用完整功能.")
                                    .setCancelable(false)
                                    .setNegativeButton("重新授权", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) { requestPermissions(); }
                                    });
                            builder.show();
                        }
                    }
                }
                if (count == 0) { gotoMainPage(); }
                break;
                default:break;
        }
    }

    private void requestPermissions() {
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else { gotoMainPage(); }
    }

    private void gotoMainPage() {
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
