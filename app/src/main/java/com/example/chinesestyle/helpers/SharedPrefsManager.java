package com.example.chinesestyle.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {
    private static final String PREFS_NAME = "ChineseStylePrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_NICKNAME = "nickname";
    private static final String KEY_FONT_SIZE = "fontSize";
    private static final String KEY_LAST_CLASSIC_ID = "lastClassicId";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_REMEMBER_PASSWORD = "rememberPassword";
    private static final String KEY_AUTO_LOGIN = "autoLogin";
    private static SharedPrefsManager instance;
    private SharedPreferences sharedPreferences;

    private SharedPrefsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefsManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefsManager(context.getApplicationContext());
        }
        return instance;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setUserId(long userId) {
        sharedPreferences.edit().putLong(KEY_USER_ID, userId).apply();
    }

    public long getUserId() {
        return sharedPreferences.getLong(KEY_USER_ID, -1);
    }

    public void setNickname(String nickname) {
        sharedPreferences.edit().putString(KEY_NICKNAME, nickname).apply();
    }

    public String getNickname() {
        return sharedPreferences.getString(KEY_NICKNAME, "");
    }

    public void setFontSize(float size) {
        sharedPreferences.edit().putFloat(KEY_FONT_SIZE, size).apply();
    }

    public float getFontSize() {
        return sharedPreferences.getFloat(KEY_FONT_SIZE, 16f);  // 默认字体大小
    }

    public void setLastClassicId(int classicId) {
        sharedPreferences.edit().putInt(KEY_LAST_CLASSIC_ID, classicId).apply();
    }

    public int getLastClassicId() {
        return sharedPreferences.getInt(KEY_LAST_CLASSIC_ID, -1);
    }

    public String getContact() {
        return sharedPreferences.getString(KEY_CONTACT, "");
    }

    public void setContact(String contact) {
        sharedPreferences.edit().putString(KEY_CONTACT, contact).apply();
    }

    public boolean isDarkTheme() {
        return sharedPreferences.getBoolean("dark_theme", false);
    }

    public void setDarkTheme(boolean isDarkTheme) {
        sharedPreferences.edit().putBoolean("dark_theme", isDarkTheme).apply();
    }

    public boolean isRememberPassword() {
        return sharedPreferences.getBoolean(KEY_REMEMBER_PASSWORD, false);
    }

    public void setRememberPassword(boolean rememberPassword) {
        sharedPreferences.edit().putBoolean(KEY_REMEMBER_PASSWORD, rememberPassword).apply();
    }

    public boolean isAutoLogin() {
        return sharedPreferences.getBoolean(KEY_AUTO_LOGIN, false);
    }

    public void setAutoLogin(boolean autoLogin) {
        sharedPreferences.edit().putBoolean(KEY_AUTO_LOGIN, autoLogin).apply();
    }

    public String getLoginId() {
        return sharedPreferences.getString("loginId", "");
    }

    public void setLoginId(String loginId) {
        sharedPreferences.edit().putString("loginId", loginId).apply();
    }

    public String getPassword() {
        return sharedPreferences.getString("password", "");
    }

    public void setPassword(String password) {
        sharedPreferences.edit().putString("password", password).apply();
    }
}
