package com.ftable21.android.weather.dynamic;

import android.content.Context;
import android.graphics.Canvas;

public class UnknownDrawer extends BaseDrawer {

	public UnknownDrawer(Context context, boolean isNight) {
		super(context, isNight);
	}

	@Override
	public boolean drawWeather(Canvas canvas, float alpha) { return true; }
}
