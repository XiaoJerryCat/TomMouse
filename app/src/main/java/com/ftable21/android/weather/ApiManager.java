package com.ftable21.android.weather;

import android.icu.text.SimpleDateFormat;

import com.ftable21.android.weather.R;
import com.ftable21.android.weather.data.db.LocationMsg;
import com.ftable21.android.weather.dynamic.BaseDrawer;
import com.ftable21.android.weather.entity.WeatherService;

import java.util.Calendar;
import java.util.Date;

public final class ApiManager {

    private static WeatherService mWeatherService = null;

    public static WeatherService getWeatherService() { return mWeatherService; }

    public static void setWeatherService(WeatherService mWeatherService) { ApiManager.mWeatherService = mWeatherService; }

    public static BaseDrawer.Type makeWeatherType(WeatherService data) {
        if (data == null) {
            return BaseDrawer.Type.DEFAULT;
        }
        final boolean isNight = isNight(data);
        final int typeCode = Integer.valueOf(mWeatherService.mNow.condStatusCode);
        switch (typeCode) {
            case 100:
                return isNight ? BaseDrawer.Type.CLEAR_N : BaseDrawer.Type.CLEAR_D;
            case 101:
            case 102:
            case 103:
                return isNight ? BaseDrawer.Type.CLOUDY_N : BaseDrawer.Type.CLOUDY_D;
            case 104:
                return isNight ? BaseDrawer.Type.OVERCAST_N : BaseDrawer.Type.OVERCAST_D;
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
                return isNight ? BaseDrawer.Type.WIND_N : BaseDrawer.Type.WIND_D;
            case 300:
            case 301:
            case 302:
            case 303:
            case 304:
            case 305:
            case 306:
            case 307:
            case 308:
            case 309:
            case 310:
            case 311:
            case 312:
            case 313:
                return isNight ? BaseDrawer.Type.RAIN_N : BaseDrawer.Type.RAIN_D;
            case 400:
            case 401:
            case 402:
            case 403:
            case 407:
                return isNight ? BaseDrawer.Type.SNOW_N : BaseDrawer.Type.SNOW_D;
            case 404:
            case 405:
            case 406:
                return isNight ? BaseDrawer.Type.RAIN_SNOW_N : BaseDrawer.Type.RAIN_SNOW_D;
            case 500:
            case 501:
                return isNight ? BaseDrawer.Type.FOG_N : BaseDrawer.Type.FOG_D;
            case 502:
            case 504:
                return isNight ? BaseDrawer.Type.HAZE_N : BaseDrawer.Type.HAZE_D;
            case 503:
            case 506:
            case 507:
            case 508:
                return isNight ? BaseDrawer.Type.SAND_N : BaseDrawer.Type.SAND_D;
            default:
                return isNight ? BaseDrawer.Type.UNKNOWN_N : BaseDrawer.Type.UNKNOWN_D;
        }
    }

    public static boolean isNight(WeatherService data) {
        if (data == null) { return false; }
        try {
            final Date date = new Date();
            final int curTime = Integer.valueOf(new SimpleDateFormat("HHmm").format(date));
            final int srTime = Integer.valueOf(mWeatherService.mDailyForecastArrayList.get(0).sunRise.replaceAll(":", ""));
            final int ssTime = Integer.valueOf(mWeatherService.mDailyForecastArrayList.get(0).sunDown.replaceAll(":", ""));
            if (curTime > srTime && curTime <= ssTime) { return false; }
            else { return true; }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static int makeWeatherIcon(WeatherService dateService, int i, final String type) {
        boolean isNight = isNight(dateService);
        int code = 999;
        if (type.equals("F")) { code = Integer.valueOf(dateService.mDailyForecastArrayList.get(i).dCondStatusCode); }
        else if (type.equals("H")) { code = Integer.valueOf(dateService.mHourlyArrayList.get(i).condStatusCode); }
        switch (code) {
            case 100: return isNight ? R.drawable.weather_icon_100n : R.drawable.weather_icon_100;
            case 101: return R.drawable.weather_icon_101;
            case 102: return R.drawable.weather_icon_102;
            case 103: return isNight ? R.drawable.weather_icon_103n : R.drawable.weather_icon_103;
            case 104: return isNight ? R.drawable.weather_icon_104n : R.drawable.weather_icon_104;
            case 200: return R.drawable.weather_icon_200;
            case 201: return R.drawable.weather_icon_201;
            case 202: return R.drawable.weather_icon_202;
            case 203: return R.drawable.weather_icon_203;
            case 204: return R.drawable.weather_icon_204;
            case 205: return R.drawable.weather_icon_205;
            case 206: return R.drawable.weather_icon_206;
            case 207: return R.drawable.weather_icon_207;
            case 208: return R.drawable.weather_icon_208;
            case 209: return R.drawable.weather_icon_209;
            case 210: return R.drawable.weather_icon_210;
            case 211: return R.drawable.weather_icon_211;
            case 212: return R.drawable.weather_icon_212;
            case 213: return R.drawable.weather_icon_213;
            case 300: return isNight ? R.drawable.weather_icon_300n : R.drawable.weather_icon_300;
            case 301: return isNight ? R.drawable.weather_icon_301n : R.drawable.weather_icon_301;
            case 302: return R.drawable.weather_icon_302;
            case 303: return R.drawable.weather_icon_303;
            case 304: return R.drawable.weather_icon_304;
            case 305: return R.drawable.weather_icon_305;
            case 306: return R.drawable.weather_icon_306;
            case 307: return R.drawable.weather_icon_307;
            case 309: return R.drawable.weather_icon_309;
            case 310: return R.drawable.weather_icon_310;
            case 311: return R.drawable.weather_icon_311;
            case 312: return R.drawable.weather_icon_312;
            case 313: return R.drawable.weather_icon_313;
            case 314: return R.drawable.weather_icon_314;
            case 315: return R.drawable.weather_icon_315;
            case 316: return R.drawable.weather_icon_316;
            case 317: return R.drawable.weather_icon_317;
            case 318: return R.drawable.weather_icon_318;
            case 399: return R.drawable.weather_icon_399;
            case 400: return R.drawable.weather_icon_400;
            case 401: return R.drawable.weather_icon_401;
            case 402: return R.drawable.weather_icon_402;
            case 403: return R.drawable.weather_icon_403;
            case 404: return R.drawable.weather_icon_404;
            case 405: return R.drawable.weather_icon_405;
            case 406: return R.drawable.weather_icon_406;
            case 407: return isNight ? R.drawable.weather_icon_407n : R.drawable.weather_icon_407;
            case 408: return R.drawable.weather_icon_408;
            case 409: return R.drawable.weather_icon_409;
            case 410: return R.drawable.weather_icon_410;
            case 499: return R.drawable.weather_icon_499;
            case 500: return R.drawable.weather_icon_500;
            case 501: return R.drawable.weather_icon_501;
            case 502: return R.drawable.weather_icon_502;
            case 503: return R.drawable.weather_icon_503;
            case 504: return R.drawable.weather_icon_504;
            case 507: return R.drawable.weather_icon_507;
            case 508: return R.drawable.weather_icon_508;
            case 509: return R.drawable.weather_icon_509;
            case 510: return R.drawable.weather_icon_510;
            case 511: return R.drawable.weather_icon_511;
            case 512: return R.drawable.weather_icon_512;
            case 513: return R.drawable.weather_icon_513;
            case 514: return R.drawable.weather_icon_514;
            case 515: return R.drawable.weather_icon_515;
            case 900: return R.drawable.weather_icon_900;
            case 901: return R.drawable.weather_icon_901;
            case 999:
            default: return R.drawable.weather_icon_999;
        }
    }

    public static String prettyMonthDate(String data) {
        final String[] strings = data.split("-");
        final int month = Integer.valueOf(strings[1]);
        final int day = Integer.valueOf(strings[2]);
        return month + "/" + day;
    }

    public static String prettyHourlyDate(String data) {
        final String[] strings = data.split("-");
        final String[] curHourly = strings[2].split(" ");
        return curHourly[1];
    }

    public static String prettyWeekDate(String data) {
        final String[] strings = data.split("-");
        final int year = Integer.valueOf(strings[0]);
        final int month = Integer.valueOf(strings[1]);
        final int day = Integer.valueOf(strings[2]);
        Calendar calendar = Calendar.getInstance();
        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH) + 1;
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (curYear == year && curMonth == month) {
            if (curDay == day) { return "今天"; }
            else if ((curDay + 1) == day) { return "明天"; }
            else if ((curDay - 1) == day) { return "昨天"; }
        }
        calendar.set(year, month - 1, day);
        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (isFirstSunday) {
            dayOfWeek = dayOfWeek - 1;
            if (dayOfWeek == 0) { dayOfWeek = 7; }
        }
        switch (dayOfWeek) {
            case 1: return "周一";
            case 2: return "周二";
            case 3: return "周三";
            case 4: return "周四";
            case 5: return "周五";
            case 6: return "周六";
            case 7: return "周日";
            default:break;
        }
        return data;
    }

    // TODO: 待删除
    public static boolean isNeedUpdate(WeatherService data) {
        final String updateTime = data.mUpdate.locUpdate;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date updateDate = format.parse(updateTime);
            Date curDate = new Date();
            long interval = curDate.getTime() - updateDate.getTime();
            if ((interval >= 0) && (interval < 75 * 60 * 1000)) { return false; }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String getParentCity(LocationMsg data) { return data.getCity(); }

    public static String getDistrict(LocationMsg data) { return data.getDistrict(); }
}
