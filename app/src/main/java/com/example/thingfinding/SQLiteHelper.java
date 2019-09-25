package com.example.thingfinding;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * auther: MO
 * Date: 2017/1/11
 * Time:18:33
 * Des:
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String dbname="REBOT.db";
    private static final int version=1;
    private static  SQLiteHelper dbHelper;
    //也可以不指定字段的类型、长度，因为int类型也可以保存Char类型的创建学生表

    private final String createTb1="CREATE TABLE Users (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username VARCHAR2,password VARCHAR2,avatar BLOB," +
            "name VARCHAR2,phone VARCHAR2,id VARCHAR2,email VARCHAR2,address VARCHAR2)";
    private final String createTb="CREATE TABLE AddressBook (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR2 NOT NULL,phone VARCHAR2 NOT NULL,address VARCHAR2 NOT NULL)";


    public SQLiteHelper(Context context, String information, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, information, factory, version);
    }

    public SQLiteHelper(Context context){
        super(context, dbname, null, version);
    }


    public static SQLiteHelper getInstance(Context context) {

        if (dbHelper == null) { //单例模式
            dbHelper = new SQLiteHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建一个数据库表 User ，字段：_id、information、avatar。
        db.execSQL(createTb);
        db.execSQL(createTb1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
