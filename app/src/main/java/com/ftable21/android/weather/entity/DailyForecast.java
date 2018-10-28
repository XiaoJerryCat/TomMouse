package com.ftable21.android.weather.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * TODO: Array
 */
public class DailyForecast {

    @SerializedName("cond_code_d") public String dCondStatusCode;
    @SerializedName("cond_code_n") public String nCodeStatusCode;
    @SerializedName("cond_txt_d") public String dCondStatusTxt;
    @SerializedName("cond_txt_n") public String nCondStatusTxt;
    @SerializedName("date") public String forecastDate;
    @SerializedName("hum") public String relativeHum;
    @SerializedName("pcpn") public String rainfall;
    @SerializedName("pres") public String AtmosphericPres;
    @SerializedName("pop") public String rainProbability;
    @SerializedName("tmp_max") public String tmpMax;
    @SerializedName("tmp_min") public String tmpMin;
    @SerializedName("uv_index") public String ultravioletStr;
    @SerializedName("vis") public String vis;
    @SerializedName("wind_deg") public String windDeg;
    @SerializedName("wind_dir") public String windDir;
    @SerializedName("wind_sc") public String windSc;
    @SerializedName("wind_spd") public String windSpd;
    @SerializedName("sr") public String sunRise;
    @SerializedName("ss") public String sunDown;
    @SerializedName("mr") public String monthRise;
    @SerializedName("ms") public String monthDown;
}
