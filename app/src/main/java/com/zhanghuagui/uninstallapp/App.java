package com.zhanghuagui.uninstallapp;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.zhanghuagui.uninstallapp.db.DaoMaster;
import com.zhanghuagui.uninstallapp.db.DaoSession;

public class App extends Application {

    private static App sInstance;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this, "zhanghuagui.db", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static App getInstance() {
        return sInstance;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
