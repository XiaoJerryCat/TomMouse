package com.ftable21.android.weather.data.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class LocationMsg {

    private String addr;
    private String country;
    private String province;
    private String city;
    private String district;
    private String street;

    @Generated(hash = 1366292879)
    public LocationMsg(String addr, String country, String province, String city, String district, String street) {
        this.addr = addr;
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
    }

    @Generated(hash = 597260832)
    public LocationMsg() {
    }

    @Override
    public String toString() {
        return "LocationMsg{" +
                "addr='" + addr + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    public String getAddr() { return addr; }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
