package com.ftable21.android.weather.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AirNowCity {

    @SerializedName("pub_time") public String pubTime;
    @SerializedName("aqi") public String aqi;
    @SerializedName("main") public String mainContaminants;
    @SerializedName("qlty") public String airQuality;
    @SerializedName("pm10") public String pm10;
    @SerializedName("pm25") public String pm25;
    @SerializedName("no2") public String no2;
    @SerializedName("so2") public String so2;
    @SerializedName("co") public String co;
    @SerializedName("o3") public String o3;
}
