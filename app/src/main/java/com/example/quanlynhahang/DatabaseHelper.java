package com.example.quanlynhahang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME ="dbBanHang.db";
    public static  final int VERSION =1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(HoaDonDAO.SQL_Hoa_Don);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists " + HoaDonDAO.TABLE__Hoa_Don);
    }
}
