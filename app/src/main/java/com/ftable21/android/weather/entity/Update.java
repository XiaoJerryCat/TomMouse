package com.ftable21.android.weather.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Update {

    @SerializedName("loc") public String locUpdate;
    @SerializedName("utc") public String utcUpdate;
}
