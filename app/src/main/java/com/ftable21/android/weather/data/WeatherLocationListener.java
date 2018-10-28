package com.ftable21.android.weather.data;

import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.ftable21.android.weather.WeatherApplication;
import com.ftable21.android.weather.data.db.LocationMsg;
import com.ftable21.android.weather.util.WeatherUtil;

public class WeatherLocationListener extends BDAbstractLocationListener {

    private static final String TAG = WeatherLocationListener.class.getSimpleName();
    public static boolean updateFlag;

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        LocationMsg locationMsg = null;
        updateFlag = false;
        String addr = bdLocation.getAddrStr();
        String country = bdLocation.getCountry();
        String province = bdLocation.getProvince();
        String city = bdLocation.getCity();
        String district = bdLocation.getDistrict();
        String street = bdLocation.getStreet();
        locationMsg = new LocationMsg(addr, country, province, city, district, street);
        WeatherUtil.insertLocationMsg(locationMsg);
        Log.d(TAG, "onReceiveLocation: " + bdLocation.getLocType());
        updateFlag = true;
        Log.d(TAG, "onReceiveLocation: location msg: " + locationMsg.toString());
        WeatherApplication.getLocationService().stop();
    }
}
