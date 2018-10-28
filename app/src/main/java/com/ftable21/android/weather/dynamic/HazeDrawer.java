package com.ftable21.android.weather.dynamic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;

import java.util.ArrayList;

public class HazeDrawer extends BaseDrawer {

	private GradientDrawable drawable;
	private ArrayList<HazeHolder> holders = new ArrayList<HazeHolder>();
	private final float minDX,maxDX,minDY, maxDY;

	public HazeDrawer(Context context, boolean isNight) {
		super(context, isNight);
		drawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
				isNight ? new int[]{0x55d4ba3f, 0x22d4ba3f} : new int[]{0x88cca667, 0x33cca667});//d4ba3f
		drawable.setShape(GradientDrawable.OVAL);
		drawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
		minDX = 0.04f;
		maxDX = 0.065f;
		minDY = -0.02f;
		maxDY = 0.02f;
	}

	@Override
	public boolean drawWeather(Canvas canvas, float alpha) {
			for(HazeHolder holder : holders){
				holder.updateRandom(drawable, minDX, maxDX, minDY, maxDY, 0, 0, this.width, this.height, alpha);
				try {
					drawable.draw(canvas);
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("FUCK", "drawable.draw(canvas)->" + drawable.getBounds().toShortString());
				}
			}
		return true;
	}

	@Override
	protected void setSize(int width, int height) {
		super.setSize(width, height);
		if(this.holders.size() == 0){
			final float minSize = dp2px(0.8f);
			final float maxSize = dp2px(4.4f);
			for (int i = 0; i < 80; i++) {
				final float starSize = getRandom(minSize, maxSize);
				HazeHolder holder = new HazeHolder(getRandom(0, width), getDownRandFloat(0, height ), starSize, starSize);
				holders.add(holder);
			}
		}
	}

	public static class HazeHolder {
		public float x;
		public float y;
		public float w;
		public float h;

		public HazeHolder(float x, float y, float w, float h) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;

		}

		public void updateRandom(GradientDrawable drawable, float minDX, float maxDX,
                                 float minDY, float maxDY, float minX, float minY, float maxX, float maxY, float alpha) {
			if (maxDX < minDX || (maxDY < minDY)) {
				throw new IllegalArgumentException("max should bigger than min!!!!");
			}
			this.x += (getRandom(minDX, maxDX) * w);;
			this.y += (getRandom(minDY, maxDY) * h);
			if(x > maxX){
				x = minX;
			}else if(x < minX){
				x = maxX;
			}
			if(y > maxY){
				y = minY;
			}else if(y < minY){
				y = maxY;
			}
			
			final int left = Math.round(x - w / 2f);
			final int right = Math.round(x + w / 2f);
			final int top = Math.round(y - h / 2f);
			final int bottom = Math.round (y + h / 2f);
			drawable.setAlpha((int)(255f * alpha));
			drawable.setBounds(left, top, right, bottom);
			drawable.setGradientRadius(w / 2.2f);  
		}
	}
	@Override
	protected int[] getSkyBackgroundGradient() { return isNight ? SkyBackground.HAZE_N : SkyBackground.HAZE_D; }
}
