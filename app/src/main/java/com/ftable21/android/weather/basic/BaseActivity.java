package com.ftable21.android.weather.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.ftable21.android.weather.WeatherApplication;
import com.ftable21.android.weather.data.WeatherLocationListener;
import com.ftable21.android.weather.service.LocationService;

public class BaseActivity extends AppCompatActivity{

    static { AppCompatDelegate.setCompatVectorFromResourcesEnabled(true); }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemStatusBar();
        initBDLocation();
    }

    @Override
    protected void onDestroy() { super.onDestroy(); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { return super.onCreateOptionsMenu(menu); }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { return super.onOptionsItemSelected(item); }

    protected void initSystemStatusBar() { getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); }

    protected void initBDLocation() {
        LocationService locationService =  WeatherApplication.getLocationService();
        locationService.registerListener(new WeatherLocationListener());
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
    }
}
