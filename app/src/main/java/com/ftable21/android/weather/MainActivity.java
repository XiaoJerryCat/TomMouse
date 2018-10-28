package com.ftable21.android.weather;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.ftable21.android.weather.R;
import com.ftable21.android.weather.basic.BaseActivity;
import com.ftable21.android.weather.dynamic.BaseDrawer;
import com.ftable21.android.weather.dynamic.DynamicWeatherView;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_dynamic)
    DynamicWeatherView mDynamicWeatherView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setDefaultFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDynamicWeatherView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDynamicWeatherView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDynamicWeatherView.onDestroy();
    }

    private void setDefaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment, new WeatherFragment());
        transaction.commit();
    }

    public void updateCurDrawerType(BaseDrawer.Type type) { mDynamicWeatherView.setDrawerType(type); }
}
