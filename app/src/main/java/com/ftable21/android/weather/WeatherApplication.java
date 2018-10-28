package com.ftable21.android.weather;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.net.LocalServerSocket;

import com.baidu.location.LocationClient;
import com.ftable21.android.weather.data.db.dao.DaoMaster;
import com.ftable21.android.weather.data.db.dao.DaoSession;
import com.ftable21.android.weather.service.LocationService;

import org.greenrobot.greendao.database.Database;

import okhttp3.OkHttpClient;

public class WeatherApplication extends Application {

    private static WeatherApplication instance;
    private static Context mContext;

    public static DaoSession mDaoSession;
    public static Typeface mTypeface;
    public static OkHttpClient client;

    public static LocationService sLocationService;

    public static final String HE_KEY = "8280311a547b429d95120e015b9c262c";
    public static final String HE_CONVENTIONAL_WEATHER_INFO_URI = "https://free-api.heweather.com/s6/weather?" + "key=" + HE_KEY;
    public static final String HE_AIR_NOW_CIT_URL = "https://free-api.heweather.com/s6/air/now?" + "key=" + HE_KEY;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance = this;

        sLocationService = new LocationService(mContext);

        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(mContext, "weather.db", null);
        Database database = openHelper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();

        if (client == null) { client = new OkHttpClient(); }
        if (mTypeface == null) { mTypeface = Typeface.createFromAsset(getAssets(),"fonts/ftable21.ttf"); }
    }

    public static Context getContext() {
        return mContext;
    }

    public static WeatherApplication getInstance() { return instance; }

    public static Typeface getTypeface(Context context) { return mTypeface; }

    public static OkHttpClient getClient() { return client; }

    public static LocationService getLocationService() { return sLocationService; }
}
