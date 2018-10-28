package com.ftable21.android.weather;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.ftable21.android.weather.basic.BaseFragment;
import com.ftable21.android.weather.data.ForecastAdapter;
import com.ftable21.android.weather.data.HourlyAdapter;
import com.ftable21.android.weather.data.WeatherForecast;
import com.ftable21.android.weather.data.WeatherHourly;
import com.ftable21.android.weather.data.WeatherLocationListener;
import com.ftable21.android.weather.data.db.LocationMsg;
import com.ftable21.android.weather.entity.WeatherService;
import com.ftable21.android.weather.util.WeatherUtil;
import com.ftable21.android.weather.widget.AqiView;
import com.ftable21.android.weather.widget.SunWinView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WeatherFragment extends BaseFragment {

    private static String TAG = WeatherFragment.class.getSimpleName();

    @BindView(R.id.main_toolbar) Toolbar mToolbar;
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.collapsing_layout) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.update_time_text) TextView updateTime;
    @BindView(R.id.tmp_text) TextView tmpText;
    @BindView(R.id.weather_type_text) TextView weatherTypeText;

    @BindView(R.id.forecast_view) RecyclerView forecastView;
    private List<WeatherForecast> mWeatherForecastList;
    private ForecastAdapter mForecastAdapter;
    @BindView(R.id.hourly_view) RecyclerView hourlyView;
    private List<WeatherHourly> mWeatherHourlyList;
    private HourlyAdapter mHourlyAdapter;
    private LinearLayoutManager mHourlyManager;

    @BindView(R.id.sun_win_view) SunWinView mSunWinView;
    @BindView(R.id.aqi_view) AqiView mAqiView;

    @BindView(R.id.detailed_no2) TextView no2Text;
    @BindView(R.id.detailed_pm10) TextView pm10Text;
    @BindView(R.id.detailed_pm25) TextView pm25Text;
    @BindView(R.id.detailed_so2) TextView so2Text;
    @BindView(R.id.detailed_rainfall) TextView rainfallText;
    @BindView(R.id.detailed_relative_hum) TextView relativeHumText;
    @BindView(R.id.detailed_som_tmp) TextView somTmpText;
    @BindView(R.id.detailed_vis) TextView visText;

    @Override
    protected int getLayoutId() { return R.layout.main_fragment; }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ((MainActivity)mActivity).setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);

        mWeatherForecastList = new ArrayList<>();
        mForecastAdapter = new ForecastAdapter(mWeatherForecastList);
        mForecastAdapter.setOnClickListener((adapterView, viewItem, i, l) -> {
        });
        forecastView.setItemAnimator(new DefaultItemAnimator());
        forecastView.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastView.setAdapter(mForecastAdapter);

        mWeatherHourlyList = new ArrayList<>();
        mHourlyAdapter = new HourlyAdapter(mWeatherHourlyList);
        mHourlyManager = new LinearLayoutManager(getActivity());
        mHourlyManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHourlyAdapter.setOnClickListener(((adapterView, viewItem, i, l) -> {
        }));
        hourlyView.setItemAnimator(new DefaultItemAnimator());
        hourlyView.setLayoutManager(mHourlyManager);
        hourlyView.setAdapter(mHourlyAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(mActivity, "稍等...", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        WeatherApplication.getLocationService().start();
                        while (true) {
                            if (WeatherLocationListener.updateFlag) {
                                while (true) {
                                    if (WeatherUtil.queryAllLocationMsg().size() > 0) { break; }
                                }
                                WeatherUtil.requestWeatherData(ApiManager.getDistrict(WeatherUtil.queryAllLocationMsg().get(WeatherUtil.locationMsgSize() - 1)));
                                break;
                            }
                        }
                        newThreadOnMainUpdateUI();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }).start();
            }
        });
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LocationMsg> locationMsgList = WeatherUtil.queryAllLocationMsg();
                if (locationMsgList.size() <= 0) {
                    WeatherApplication.getLocationService().start();
                }
                else {
                    WeatherUtil.requestWeatherData(ApiManager.getDistrict(locationMsgList.get(WeatherUtil.locationMsgSize() - 1)));
                    newThreadOnMainUpdateUI();
                    return;
                }
                while (true) {
                    if (WeatherLocationListener.updateFlag) {
                        while (true) {
                            if (WeatherUtil.queryAllLocationMsg().size() > 0) { break; }
                        }
                        WeatherUtil.requestWeatherData(ApiManager.getDistrict(WeatherUtil.queryAllLocationMsg().get(WeatherUtil.locationMsgSize() - 1)));
                        break;
                    }
                }
                newThreadOnMainUpdateUI();
            }
        }).start();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (WeatherUtil.queryAllLocationMsg() == null || WeatherUtil.locationMsgSize() <= 0) { return; }
        while (true) {
            if (ApiManager.getWeatherService() != null) { break; }
        }
        updateUI();
    }

    private void updateUI() {
        WeatherService weatherService = ApiManager.getWeatherService();
        tmpText.setText(weatherService.mNow.tmp + "°");
        updateTime.setText("最后更新时间: " + weatherService.mUpdate.locUpdate);
        mCollapsingToolbarLayout.setTitle(weatherService.mBasic.location);
        weatherTypeText.setText(weatherService.mNow.condStatusTxt);
        ((MainActivity)mActivity).updateCurDrawerType(ApiManager.makeWeatherType(weatherService));
        createForecast(weatherService);
        createHourly(weatherService);
        mSunWinView.setData(weatherService);
        mAqiView.setData(weatherService);
        rainfallText.setText(weatherService.mNow.rainfall + "mm");
        somTmpText.setText(weatherService.mNow.somTmp + "°");
        visText.setText(weatherService.mNow.vis + "KM");
        relativeHumText.setText(weatherService.mNow.relativeHum + "%");
        no2Text.setText(weatherService.mAirNowCity.no2 + "μg/m³");
        so2Text.setText(weatherService.mAirNowCity.so2 + "μg/m³");
        pm10Text.setText(weatherService.mAirNowCity.pm10 + "μg/m³");
        pm25Text.setText(weatherService.mAirNowCity.pm25 + "μg/m³");
    }

    private void createForecast(WeatherService data) {
        mWeatherForecastList.clear();
        for (int i = 0;i < 7;++i) {
            final String week = ApiManager.prettyWeekDate(data.mDailyForecastArrayList.get(i).forecastDate);
            final String date = ApiManager.prettyMonthDate(data.mDailyForecastArrayList.get(i).forecastDate);
            final int icon = ApiManager.makeWeatherIcon(data, i, "F");
            final String type = data.mDailyForecastArrayList.get(i).dCondStatusTxt;
            final String minTmp = data.mDailyForecastArrayList.get(i).tmpMin;
            final String maxTmp = data.mDailyForecastArrayList.get(i).tmpMax;
            mWeatherForecastList.add(new WeatherForecast(week, date, icon, type, minTmp, maxTmp));
            mForecastAdapter.notifyDataSetChanged();
        }
    }

    private void createHourly(WeatherService data) {
        mWeatherHourlyList.clear();
        for (int i = 0;i < 8;++i) {
            final String hourly = ApiManager.prettyHourlyDate(data.mHourlyArrayList.get(i).hourlyTime);
            final int icon = ApiManager.makeWeatherIcon(data , i, "H");
            final String type = data.mHourlyArrayList.get(i).condStatusTxt;
            final String tmp = data.mHourlyArrayList.get(i).tmp;
            mWeatherHourlyList.add(new WeatherHourly(hourly, icon, type, tmp));
            mHourlyAdapter.notifyDataSetChanged();
        }
    }

    private void newThreadOnMainUpdateUI() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        });
    }
}
