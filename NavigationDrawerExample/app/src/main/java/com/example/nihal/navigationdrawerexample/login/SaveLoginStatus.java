package com.example.nihal.navigationdrawerexample.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.nihal.navigationdrawerexample.Constants;
public class SaveLoginStatus {


    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(Constants.LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }


    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(Constants.LOGGED_IN_PREF, false);
    }

    public static void setLoggedOut(Context context , boolean loggedOut){

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(Constants.LOGGED_IN_PREF,loggedOut);
        editor.clear();
        editor.apply();
    }
    public static void putPref(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }


}