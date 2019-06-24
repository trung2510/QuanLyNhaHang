package com.example.quanlynhahang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {


    DatabaseHelper databaseHelper;
    SQLiteDatabase db;


    public static final String SQL_Hoa_Don = "CREATE TABLE HoaDonModel (" +
            " banSo text primary key," +
            " doAn text," +
            " gia double" +
            ");";

    public static final String TABLE__Hoa_Don = "HoaDonModel";


    public HoaDonDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }


    public int insertHoaDon(HoaDonModel hoaDonModel){
        ContentValues values = new ContentValues();
        values.put("banSo",hoaDonModel.getBanSo());
        values.put("doAn",hoaDonModel.getDoAn());
        values.put("gia",hoaDonModel.getGia());
        try{
            if (db.insert(TABLE__Hoa_Don,null,values) <0) {
                return -1;
            }
        }
        catch (Exception ex){
            Log.e("HoaDonDAO: ", ex.getMessage());
        }
        return 1;
    }

    public int updateHoaDon(HoaDonModel hoaDonModel){
        ContentValues values = new ContentValues();
        //values.put("banSo",hoaDonModel.getBanSo());
        values.put("doAn",hoaDonModel.getDoAn());
        values.put("gia",hoaDonModel.getGia());
        try{
            if (db.update(TABLE__Hoa_Don,values,"banSo=?",new String[]{hoaDonModel.getBanSo()}) <0) {
                return -1;
            }
        }
        catch (Exception ex){
            Log.e("HoaDonDAO: ", ex.getMessage());
        }
        return 1;
    }

    public int deleteHoaDon(String id){
        int result = db.delete(TABLE__Hoa_Don,"banSo=?",new String[]{id});
        if (result == 0 ) {
            return -1;
        }
        return 1;
    }

    public List<HoaDonModel> getAllHoaDon (){
        ArrayList<HoaDonModel> hoaDonModels = new ArrayList<>();
        String dem = "SELECT * FROM " +TABLE__Hoa_Don;

        Cursor cursor = db.rawQuery(dem,null);
        if (cursor.moveToFirst()){
            do {
                HoaDonModel hoaDonModel = new HoaDonModel();
                hoaDonModel.setBanSo(cursor.getString(0));
                hoaDonModel.setDoAn(cursor.getString(1));
                hoaDonModel.setGia(cursor.getDouble(2));
                hoaDonModels.add(hoaDonModel);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return hoaDonModels;
    }

}
