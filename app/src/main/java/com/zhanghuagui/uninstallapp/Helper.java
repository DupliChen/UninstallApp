package com.zhanghuagui.uninstallapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.zhanghuagui.uninstallapp.db.WhiteItem;
import com.zhanghuagui.uninstallapp.db.WhiteItemDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    private static List<Item> getAllPackages() {
        PackageManager packageManager = App.getInstance().getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        List<Item> items = new ArrayList<>();
        for (PackageInfo info : installedPackages) {
            Item item = new Item();
            item.setAppName(info.applicationInfo.loadLabel(packageManager).toString());
            item.setPackageName(info.packageName);
            item.setIcon(info.applicationInfo.loadIcon(packageManager));
            item.setWhite(false);
            items.add(item);
        }
        return items;
    }

    private static List<WhiteItem> loadWhiteList() {
        WhiteItemDao whiteItemDao = App.getInstance().getDaoSession().getWhiteItemDao();
        return whiteItemDao.queryBuilder().list();
    }

    public static void deleteDB(Item item) {
        App.getInstance().getDaoSession().getWhiteItemDao().queryBuilder()
                .where(WhiteItemDao.Properties.MPackageName.eq(item.getPackageName()))
                .buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public static void deleteAllDB() {
        App.getInstance().getDaoSession().getWhiteItemDao().deleteAll();
    }

    public static void insertDB(Item item) {
        WhiteItem whiteItem = new WhiteItem();
        whiteItem.setPackageName(item.getPackageName());
        App.getInstance().getDaoSession().getWhiteItemDao().insert(whiteItem);
    }

    public static void insertAllDB(List<Item> items) {
        for (Item item : items) {
            insertDB(item);
        }
    }

    public static List<Item> loadItems() {
        List<Item> items = getAllPackages();
        List<WhiteItem> whiteItems = loadWhiteList();
        for (Item item : items) {
            for (WhiteItem whiteItem : whiteItems) {
                if (TextUtils.equals(item.getPackageName(), whiteItem.getPackageName())) {
                    item.setWhite(true);
                    break;
                }
            }
        }
        return items;
    }

    public static List<Item> loadClearItems() {
        List<Item> items = getAllPackages();
        List<WhiteItem> whiteItems = loadWhiteList();
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            for (WhiteItem whiteItem : whiteItems) {
                if (TextUtils.equals(item.getPackageName(), whiteItem.getPackageName())) {
                    iterator.remove();
                    break;
                }
            }
        }
        return items;
    }
}
