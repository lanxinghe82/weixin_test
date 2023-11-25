package com.example.weixin_two;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(@Nullable Context context) {
        super(context, "ChatLog.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table chat(_id integer primary key autoincrement,name varvhar(20),img integer,text text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
