package com.ftable21.android.weather.dynamic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;

// 感谢: https://github.com/Mixiaoxiao/Weather/tree/master/WeatherGithub/src/com/mixiaoxiao/dynamicweather

public abstract class BaseDrawer {

    static final String TAG = BaseDrawer.class.getSimpleName();
    private GradientDrawable skyDrawable;
    protected int width;
    protected int height;
    protected final boolean isNight;
    private final float desity;
    protected Context mContext;

    public enum Type {
        DEFAULT, CLEAR_D, CLEAR_N, RAIN_D, RAIN_N, SNOW_D, SNOW_N, CLOUDY_D, CLOUDY_N,
        OVERCAST_D, OVERCAST_N, FOG_D, FOG_N, HAZE_D, HAZE_N, SAND_D, SAND_N,
        WIND_D, WIND_N, RAIN_SNOW_D, RAIN_SNOW_N, UNKNOWN_D, UNKNOWN_N
    }

    public static final class SkyBackground {
        public static final int[] BLACK = new int[] { 0xff000000, 0xff000000 };
        public static final int[] CLEAR_D = new int[] { 0xff3d99c2, 0xff4f9ec5 };
        public static final int[] CLEAR_N = new int[] { 0xff0b0f25, 0xff252b42 };
        public static final int[] OVERCAST_D = new int[] { 0xff33425f, 0xff617688 };
        public static final int[] OVERCAST_N = new int[] { 0xff262921, 0xff23293e };
        public static final int[] RAIN_D = new int[] { 0xff4f80a0, 0xff4d748e };
        public static final int[] RAIN_N = new int[] { 0xff0d0d15, 0xff22242f };
        public static final int[] FOG_D = new int[] { 0xff688597, 0xff44515b };
        public static final int[] FOG_N = new int[] { 0xff2f3c47, 0xff24313b };
        public static final int[] SNOW_D = new int[] { 0xff4f80a0, 0xff4d748e };
        public static final int[] SNOW_N = new int[] { 0xff1e2029, 0xff212630 };
        public static final int[] CLOUDY_D = new int[] { 0xff4f80a0, 0xff4d748e };
        public static final int[] CLOUDY_N = new int[] { 0xff071527,0xff252b42};
        public static final int[] HAZE_D = new int[] {  0xff616e70,0xff474644 };
        public static final int[] HAZE_N = new int[] { 0xff373634, 0xff25221d };
        public static final int[] SAND_D = new int[] { 0xffb5a066, 0xffd5c086 };
        public static final int[] SAND_N = new int[] { 0xff312820, 0xff514840 };
    }

    public static BaseDrawer makeDrawerByType(Context context, Type type) {
        switch (type) {
            case CLEAR_D:
                return new SunnyDrawer(context);
            case CLEAR_N:
                return new StarDrawer(context);
            case RAIN_D:
                return new RainDrawer(context, false);
            case RAIN_N:
                return new RainDrawer(context, true);
            case SNOW_D:
                return new SnowDrawer(context, false);
            case SNOW_N:
                return new SnowDrawer(context, true);
            case CLOUDY_D:
                return new CloudyDrawer(context, false);
            case CLOUDY_N:
                return new CloudyDrawer(context, true);
            case OVERCAST_D:
                return new OvercastDrawer(context, false);
            case OVERCAST_N:
                return new OvercastDrawer(context, true);
            case FOG_D:
                return new FogDrawer(context, false);
            case FOG_N:
                return new FogDrawer(context, true);
            case HAZE_D:
                return new HazeDrawer(context, false);
            case HAZE_N:
                return new HazeDrawer(context, true);
            case SAND_D:
                return new SandDrawer(context, false);
            case SAND_N:
                return new SandDrawer(context, true);
            case WIND_D:
                return new WindDrawer(context, false);
            case WIND_N:
                return new WindDrawer(context, true);
            case RAIN_SNOW_D:
                return new RainAndSnowDrawer(context, false);
            case RAIN_SNOW_N:
                return new RainAndSnowDrawer(context, true);
            case UNKNOWN_D:
                return new UnknownDrawer(context, false);
            case UNKNOWN_N:
                return new UnknownDrawer(context, true);
            case DEFAULT:
            default:
                return new DefaultDrawer(context);
        }
    }

    public BaseDrawer(Context context, boolean isNight) {
        super();
        this.isNight = isNight;
        this.mContext = context;
        this.desity = context.getResources().getDisplayMetrics().density;
    }

    protected void setSize(int width, int height) {
        if (this.width != width && this.height != height) {
            this.width = width;
            this.height = height;
            if (this.skyDrawable != null) {
                skyDrawable.setBounds(0, 0, width, height);
            }
        }
    }

    public boolean draw(Canvas canvas, float alpha) {
        drawSkyBackground(canvas, alpha);
        boolean needDrawNextFrame = drawWeather(canvas, alpha);
        return needDrawNextFrame;
    }

    public abstract boolean drawWeather(Canvas canvas, float alpha);

    protected void drawSkyBackground(Canvas canvas, float alpha) {
        if (skyDrawable == null) {
            skyDrawable = makeSkyBackground();
            skyDrawable.setBounds(0,0,width,height);
        }
        skyDrawable.setAlpha(Math.round(alpha * 255f));
        skyDrawable.draw(canvas);
    }

    public GradientDrawable makeSkyBackground() { return new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, getSkyBackgroundGradient()); }

    protected int[] getSkyBackgroundGradient() { return isNight ? SkyBackground.CLEAR_N : SkyBackground.CLEAR_D; }

    public static float getRandom(float min, float max) {
        if (max < min) { throw new IllegalArgumentException("max should bigger than min,fuck you!"); }
        return (float)(min + Math.random() * (max - min));
    }

    public float dp2px(float dp) { return dp * desity; }

    public static int convertAlphaColor(float percent,final int originalColor) {
        int newAlpha = (int) (percent * 255) & 0xFF;
        return (newAlpha << 24) | (originalColor & 0xFFFFFF);
    }

    public static float fixAlpha(float alpha) {
        if (alpha > 1f) {
            return 1f;
        }
        if (alpha < 0f) {
            return 0f;
        }
        return alpha;
    }

    public static float getDownRandFloat(float min, float max) {
        float bigend = ((min + max) * max) / 2f;
        float x = getRandom(min, bigend);
        int sum = 0;
        for (int i = 0; i < max; i++) {
            sum += (max - i);
            if (sum > x) {
                return i;
            }
        }
        return min;
    }
}
