package com.ftable21.android.weather.service;

import android.content.Context;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class LocationService {

	private LocationClient client = null;
	private LocationClientOption mOption,DIYoption;
	private Object objLock = new Object();

	public LocationService(Context locationContext){
		synchronized (objLock) {
			if(client == null){
				client = new LocationClient(locationContext);
				client.setLocOption(getDefaultLocationClientOption());
			}
		}
	}
	
	public boolean registerListener(BDAbstractLocationListener listener){
		boolean isSuccess = false;
		if(listener != null){
			client.registerLocationListener(listener);
			isSuccess = true;
		}
		return  isSuccess;
	}
	
	public void unregisterListener(BDAbstractLocationListener listener){
		if(listener != null){
			client.unRegisterLocationListener(listener);
		}
	}

	public boolean setLocationOption(LocationClientOption option){
		boolean isSuccess = false;
		if(option != null){
			if(client.isStarted())
				client.stop();
			DIYoption = option;
			client.setLocOption(option);
		}
		return isSuccess;
	}

	public LocationClientOption getDefaultLocationClientOption(){
		if(mOption == null){
			mOption = new LocationClientOption();
			mOption.setLocationMode(LocationMode.Hight_Accuracy);
			mOption.setCoorType("bd09ll");
			mOption.setScanSpan(3000);
		    mOption.setIsNeedAddress(true);
		    mOption.setIsNeedLocationDescribe(true);
		    mOption.setNeedDeviceDirect(false);
		    mOption.setLocationNotify(false);
		    mOption.setIgnoreKillProcess(true);
		    mOption.setIsNeedLocationDescribe(true);
		    mOption.setIsNeedLocationPoiList(true);
		    mOption.SetIgnoreCacheException(false);
			mOption.setOpenGps(true);
		    mOption.setIsNeedAltitude(false);
		 
		}
		return mOption;
	}

	public LocationClientOption getOption(){
		if(DIYoption == null) {
			DIYoption = new LocationClientOption();
		}
		return DIYoption;
	}

	public void start(){
		synchronized (objLock) {
			if(client != null && !client.isStarted()){
				client.start();
			}
		}
	}
	public void stop(){
		synchronized (objLock) {
			if(client != null && client.isStarted()){
				client.stop();
			}
		}
	}

	public boolean isStart() {
		return client.isStarted();
	}

	public boolean requestHotSpotState(){
		return client.requestHotSpotState();
	}
	
}
