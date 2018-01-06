package com.example.wxl.wxlandroidorm;

import android.app.Application;

import com.example.wxl.wxlandroidorm.dao.DaoMaster;
import com.example.wxl.wxlandroidorm.dao.DaoSession;

/**
 * Created by wxl on 2017/12/21.
 */

public class Myapplication extends Application {


    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        initdb();


    }

    private void initdb() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "wxl-2-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
         daoSession = daoMaster.newSession();

    }
}
