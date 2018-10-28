package com.ftable21.android.weather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.ftable21.android.weather.ApiManager;
import com.ftable21.android.weather.WeatherApplication;
import com.ftable21.android.weather.data.db.LocationMsg;
import com.ftable21.android.weather.data.db.dao.LocationMsgDao;
import com.ftable21.android.weather.entity.AirNowCity;
import com.ftable21.android.weather.entity.WeatherService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class WeatherUtil {

    private static String TAG = WeatherUtil.class.getSimpleName();

    public static List<LocationMsg> queryAllLocationMsg() {
        LocationMsgDao locationMsgDao = WeatherApplication.mDaoSession.getLocationMsgDao();
        return locationMsgDao.loadAll();
    }

    public static void insertLocationMsg(LocationMsg msg) {
        LocationMsgDao locationMsgDao = WeatherApplication.mDaoSession.getLocationMsgDao();
        //if (queryLocationMsg(msg.getAddr()) != null) { locationMsgDao.update(msg); }
        locationMsgDao.deleteAll(); //TODO: 待删除.
        locationMsgDao.insert(msg);
    }

    public static int locationMsgSize() {
        LocationMsgDao locationMsgDao = WeatherApplication.mDaoSession.getLocationMsgDao();
        return locationMsgDao.loadAll().size();
    }

    /**
    public static LocationMsg queryLocationMsg(String addr) {
        LocationMsgDao locationMsgDao = WeatherApplication.mDaoSession.getLocationMsgDao();
        return locationMsgDao.queryBuilder().where(LocationMsgDao.Properties.Addr.eq(addr)).unique();
    }
     */

    public static AirNowCity requestWeatherAirData(String data) {
        OkHttpClient client = WeatherApplication.getClient();
        String url = WeatherApplication.HE_AIR_NOW_CIT_URL + "&location=" + data;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Log.d(TAG, "requestWeatherAirData: URL= '" + url + "'");
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
            return new Gson().fromJson(jsonArray.getJSONObject(0).getJSONObject("air_now_city").toString(), AirNowCity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void requestWeatherData(String data) {
        OkHttpClient client = WeatherApplication.getClient();
        String url = WeatherApplication.HE_CONVENTIONAL_WEATHER_INFO_URI + "&location=" + data;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Log.i(TAG,"requestWeatherData: URL= '" + url + "'");
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
        WeatherService weatherService = new Gson().fromJson(jsonArray.getJSONObject(0).toString(),
                WeatherService.class);
        weatherService.setAirNowCity(requestWeatherAirData(ApiManager.getParentCity(queryAllLocationMsg().get(locationMsgSize() - 1))));
        ApiManager.setWeatherService(weatherService);
    } catch (Exception e) {
        e.printStackTrace();
    }

        /**
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
                    ApiManager.setWeatherService(new Gson().fromJson(jsonArray.getJSONObject(0).toString(),
                            WeatherService.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
         */
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) { return false; }
        else {
            NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
            if (infos != null) {
                for (int i = 0;i < infos.length;++i) {
                    if (infos[i].getState() == NetworkInfo.State.CONNECTED) { return true; }
                }
            }
        }
        return false;
    }
}
