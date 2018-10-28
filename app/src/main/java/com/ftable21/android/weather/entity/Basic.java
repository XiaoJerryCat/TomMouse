package com.ftable21.android.weather.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Basic {

    @SerializedName("cid") public String id;
    @SerializedName("location") public String location;
    @SerializedName("parent_city") public String parentCity;
    @SerializedName("admin_area") public String adminArea;
    @SerializedName("lat") public String lat;
    @SerializedName("lon") public String lon;
    @SerializedName("cnty") public String county;
    @SerializedName("tz") public String timeZone;
}
