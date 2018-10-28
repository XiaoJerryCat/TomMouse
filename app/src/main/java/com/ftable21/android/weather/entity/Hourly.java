package com.ftable21.android.weather.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * TODO: Array
 */
public class Hourly {

    @SerializedName("time") public String hourlyTime;
    @SerializedName("tmp") public String tmp;
    @SerializedName("cond_code") public String condStatusCode;
    @SerializedName("cond_txt") public String condStatusTxt;
    @SerializedName("wind_deg") public String windDeg;
    @SerializedName("wind_dir") public String windDir;
    @SerializedName("wind_sc") public String windSc;
    @SerializedName("wind_spd") public String windSpd;
    @SerializedName("hum") public String relativeHum;
    @SerializedName("pres") public String AtmosphericPres;
    @SerializedName("dew") public String dewPointTmp;
    @SerializedName("cloud") public String cloudiness;
    @SerializedName("pop") public String rainProbability;
}
