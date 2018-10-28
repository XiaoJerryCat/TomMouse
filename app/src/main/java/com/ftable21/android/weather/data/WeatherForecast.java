package com.ftable21.android.weather.data;

public class WeatherForecast {

    private String week;
    private String date;
    private int icon;
    private String type;
    private String minTmp;
    private String maxTmp;

    public WeatherForecast(String week, String date, int icon, String type, String minTmp, String maxTmp) {
        this.week = week;
        this.date = date;
        this.icon = icon;
        this.type = type;
        this.minTmp = minTmp;
        this.maxTmp = maxTmp;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMinTmp() {
        return minTmp;
    }

    public void setMinTmp(String minTmp) {
        this.minTmp = minTmp;
    }

    public String getMaxTmp() {
        return maxTmp;
    }

    public void setMaxTmp(String maxTmp) {
        this.maxTmp = maxTmp;
    }
}
