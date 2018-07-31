package com.sample.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class UUtil {
    public static void setTagDebug(String tag, String message) {
        Log.d(tag, message);
    }

    public static void navigateIntent(Context mcContext, Class<?> cls) {
        mcContext.startActivity(new Intent(mcContext, cls));
    }

    public static void logoutApp(Context mContext, Class<?> cls) {
        Intent in = new Intent(mContext, cls);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(in);
    }

    public static void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public static void showProgressDialog(ProgressDialog progressDialog, String message) {
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hideProgressDialog(ProgressDialog progressDialog) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static boolean isNetworkConnected(ConnectivityManager cm) {
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }
        return false;
    }
}