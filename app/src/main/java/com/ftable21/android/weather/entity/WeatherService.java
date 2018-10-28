package com.ftable21.android.weather.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherService {

    @SerializedName("basic") public Basic mBasic;
    @SerializedName("daily_forecast") public ArrayList<DailyForecast> mDailyForecastArrayList;
    @SerializedName("hourly") public ArrayList<Hourly> mHourlyArrayList;
    @SerializedName("lifestyle") public ArrayList<LifeStyle> mLifeStyleArrayList;
    @SerializedName("now") public Now mNow;
    @SerializedName("update") public Update mUpdate;

    @SerializedName("air_now_city") public AirNowCity mAirNowCity;

    public void setAirNowCity(AirNowCity data) { this.mAirNowCity = data; }
}
