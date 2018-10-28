package com.ftable21.android.weather.data;

public class WeatherHourly {

    private String hourly;
    private int icon;
    private String type;
    private String tmp;

    public WeatherHourly(String hourly, int icon, String type, String tmp) {
        this.hourly = hourly;
        this.icon = icon;
        this.type = type;
        this.tmp = tmp;
    }

    public String getHourly() {
        return hourly;
    }

    public void setHourly(String hourly) {
        this.hourly = hourly;
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

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }
}
