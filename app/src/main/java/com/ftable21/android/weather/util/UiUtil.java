package com.ftable21.android.weather.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;

public class UiUtil {

    public static void logDebug(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static float getTextPaintOffset(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return -(fontMetrics.bottom - fontMetrics.top) / 2f - fontMetrics.top;
    }

    public static ProgressDialog makeProgressDialog(Context context, String Title, String msg) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(Title);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(true);
        return progressDialog;
    }
}
