package com.example.sqlitemanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ActionSQLiteOpenHelper extends SQLiteOpenHelper {


    private static final String name = "uavaction.db";//数据库名称

    private static final int version = 1; //数据库版本

    public ActionSQLiteOpenHelper(Context context) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //初始化数据库的表结构
        db.execSQL("create table uavaction(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "routename VARCHAR(20)," +
                "routetype VARCHAR(20)," +
                "inspectiontime VARCHAR(20)," +
                "towername VARCHAR(20)," +
                "towerlng DOUBLE(25)," +
                "towerlat DOUBLE(25)," +
                "longitude DOUBLE(25)," +
                "latitude DOUBLE(25)," +
                "height FLOAT(5)," +
                "yawangle DOUBLE(10)," +
                "pitchangle DOUBLE(10)," +
                "actionselect VARCHAR(20))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table uavaction add account varchar(20)");
    }
}
