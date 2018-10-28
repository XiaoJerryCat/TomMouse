package com.ftable21.android.weather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.ftable21.android.weather.WeatherApplication;
import com.ftable21.android.weather.entity.WeatherService;

public class AqiView extends View{

    private final float density;
    private TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF = new RectF();
    private WeatherService mAir;

    public AqiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        density = context.getResources().getDisplayMetrics().density;
        textPaint.setTextAlign(Paint.Align.CENTER);
        if(isInEditMode()){
            return;
        }
        textPaint.setTypeface(WeatherApplication.getTypeface(context));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final float w = getWidth();
        final float h = getHeight();
        if (w <= 0 || h <= 0) { return; }
        final float lineSize = h / 10f;
        if (mAir == null || TextUtils.isEmpty(mAir.mAirNowCity.aqi)) {
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setTextSize(lineSize * 1.25f);
            textPaint.setColor(0xaaffffff);
            canvas.drawText("暂无数据", w / 2f, h / 2f, textPaint);
            return;
        }
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(lineSize * 1.00f);
        canvas.drawText("空气质量", w / 2f, (h / 2f) - 30, textPaint);
        float currAqiPercent = -1f;
        try {
            currAqiPercent = Float.valueOf(mAir.mAirNowCity.aqi) / 500f;
            currAqiPercent = Math.min(currAqiPercent, 1f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        float aqiArcRadius = lineSize * 4f;
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(lineSize * 1);
        textPaint.setColor(0x55ffffff);
        rectF.set(-aqiArcRadius, -aqiArcRadius, aqiArcRadius, aqiArcRadius);
        final int saveCount = canvas.save();
        canvas.translate(w / 2f, h / 2f);
        final float startAngle = -210f;
        final float sweepAngle = 240f;
        canvas.drawArc(rectF, startAngle + sweepAngle * currAqiPercent, sweepAngle * (1f - currAqiPercent), false, textPaint);
        if (currAqiPercent >= 0f) {
            textPaint.setColor(0x99ffffff);
            canvas.drawArc(rectF, startAngle, sweepAngle * currAqiPercent, false, textPaint);
            textPaint.setColor(0xffffffff);
            textPaint.setStrokeWidth(lineSize / 8f);
            canvas.drawCircle(0, 0, lineSize / 3f, textPaint);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setTextSize(lineSize * 1.5f);
            textPaint.setColor(0xffffffff);
            try {
                canvas.drawText(mAir.mAirNowCity.aqi + "", 0, lineSize * 3, textPaint);
            } catch (Exception e) {
            }
            textPaint.setTextSize(lineSize * 1f);
            textPaint.setColor(0x88ffffff);
            try {
                canvas.drawText(mAir.mAirNowCity.airQuality + "", 0, lineSize * 4.25f, textPaint);
            } catch (Exception e) {
            }
            canvas.rotate(startAngle + sweepAngle * currAqiPercent - 180f);
            textPaint.setStyle(Paint.Style.STROKE);
            textPaint.setColor(0xffffffff);
            float startX = lineSize / 3f;
            canvas.drawLine(-startX, 0, -lineSize * 4.5f, 0, textPaint);
        }
        canvas.restoreToCount(saveCount);
    }

    public void setData(WeatherService aqi) {
        if (aqi != null) {
            this.mAir = aqi;
            invalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { super.onSizeChanged(w, h, oldw, oldh); }
}
