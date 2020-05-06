package com.zhanghuagui.uninstallapp.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class WhiteItem {

    @Id(autoincrement = true)
    private Long mId;
    @Property
    private String mPackageName;

    @Generated(hash = 303993317)
    public WhiteItem(Long mId, String mPackageName) {
        this.mId = mId;
        this.mPackageName = mPackageName;
    }

    @Generated(hash = 1454278319)
    public WhiteItem() {
    }

    public Long getId() {
        return this.mId;
    }

    public void setId(Long mId) {
        this.mId = mId;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

    public String getMPackageName() {
        return this.mPackageName;
    }

    public void setMPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

}
