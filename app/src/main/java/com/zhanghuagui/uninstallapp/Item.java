package com.zhanghuagui.uninstallapp;

import android.graphics.drawable.Drawable;

public class Item {

    private String mAppName;

    private String mPackageName;

    private Drawable mIcon;

    private boolean mIsWhite;

    public String getAppName() {
        return mAppName;
    }

    public void setAppName(String mAppName) {
        this.mAppName = mAppName;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable mIcon) {
        this.mIcon = mIcon;
    }

    public boolean isWhite() {
        return mIsWhite;
    }

    public void setWhite(boolean mIsWhite) {
        this.mIsWhite = mIsWhite;
    }
}
