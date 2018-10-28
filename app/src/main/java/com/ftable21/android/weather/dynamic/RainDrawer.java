package com.ftable21.android.weather.dynamic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class RainDrawer extends BaseDrawer {

    static final String TAG = RainDrawer.class.getSimpleName();
    public enum RainLevel{ LIGHT,MEDIUM,HEAVY,VERY_HEAVY;}
    private RainDrawable drawable;
    private ArrayList<RainHolder> holders = new ArrayList<RainHolder>();
    private final int cfg_count = 50;

    public static class RainDrawable{
        public float x,y;
        public float length;
        public Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        public RainDrawable() {
            super();
            paint.setStyle(Paint.Style.STROKE);
        }
        public void setColor(int color){
            this.paint.setColor(color);
        }
        public void setStrokeWidth(float strokeWidth){
            this.paint.setStrokeWidth(strokeWidth);
        }
        public void setLocation(float x,float y, float length){
            this.x = x;
            this.y = y;
            this.length = length;
        }
        public void draw(Canvas canvas){
            canvas.drawLine(x, y - length, x, y, paint);
        }
    }

    public RainDrawer(Context context, boolean isNight) {
        super(context, isNight);
        drawable = new RainDrawable();
    }

    @Override
    public boolean drawWeather(Canvas canvas,float alpha) {
        for (RainHolder holder : holders) {
            holder.updateRandom(drawable, alpha);
            drawable.draw(canvas);
        }
        return true;
    }

    @Override
    protected void setSize(int width, int height) {
        super.setSize(width, height);
        if (this.holders.size() == 0) {
            final float rainWidth =  dp2px(2);
            final float minRainHeight = dp2px(8);
            final float maxRainHeight = dp2px(14);
            final float speed = dp2px(400);
            for (int i = 0; i < cfg_count; i++) {
                float x = getRandom(0f, width);
                RainHolder holder = new RainHolder(x, rainWidth, minRainHeight, maxRainHeight, height,speed);
                holders.add(holder);
            }
        }
    }

    @Override
    protected int[] getSkyBackgroundGradient() { return isNight ? SkyBackground.RAIN_N : SkyBackground.RAIN_D; }

    public static class RainHolder {
        public float x;
        public final float rainWidth;
        public final float rainHeight;
        public final float maxY;
        public float curTime;
        public final int rainColor;
        public final float v;

        public RainHolder(float x,float rainWidth,float minRainHeight,float maxRainHeight, float maxY,float speed ) {
            super();
            this.x = x;
            this.rainWidth = rainWidth;
            this.rainHeight = getRandom(minRainHeight, maxRainHeight);
            this.rainColor = Color.argb((int) (getRandom(0.1f, 0.5f) * 255f), 0xff, 0xff, 0xff);
            this.maxY = maxY;
            this.v = speed * getRandom(0.9f, 1.1f);
            final float maxTime = maxY;
            this.curTime = getRandom(0, maxTime);
        }

        public void updateRandom(RainDrawable drawable,float alpha) {
            curTime += 0.025f;
            float curY = curTime * this.v;
            if((curY - this.rainHeight) > this.maxY){
                this.curTime = 0f;
            }
            drawable.setColor(Color.argb((int) (Color.alpha(rainColor) * alpha), 0xff, 0xff, 0xff));
            drawable.setStrokeWidth(rainWidth);
            drawable.setLocation(x, curY, rainHeight);
        }
    }
}
