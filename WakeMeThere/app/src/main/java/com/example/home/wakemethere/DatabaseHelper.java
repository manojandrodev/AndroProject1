package com.example.home.wakemethere;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by home on 7/5/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "alarms.db";
    public static final String tableName = "location_table";
    public static final String id = "alarm_id";
    public static final String name = "name";
    public static final String latitude = "Latitude";
    public static final String longitude = "Longitude";
    public static final String NELatitude = "NELat";
    public static final String NELongitude = "NELng";
    public static final String SWLatitude = "SWLat";
    public static final String SWLongitude = "SWLng";
    public static final String address = "address";
    public static final String isEnabled = "isEnabled";
    public static final String isHour = "isHour";


    public DatabaseHelper(Context context) {
        super(context, databaseName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tableName+"( "+
                id+" integer primary key AUTOINCREMENT, "
                +name+" text, "+
                latitude+" text, "+
                longitude+" text, "+
                NELatitude+" text, "+
                NELongitude+" text, "+
                SWLatitude+" text, "+
                SWLongitude+" text, "+
                address+" text, "+
                isEnabled+" boolean, "+
                isHour+" boolean) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertAlarmData(String name, String latitude, String longitude, String NELatitude,String NELongitude,String SWLatitude,String SWLongitude,String address, Boolean isHour){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.name,name);
        contentValues.put(this.latitude,latitude);
        contentValues.put(this.longitude,longitude);
        contentValues.put(this.NELatitude,NELatitude);
        contentValues.put(this.NELongitude,NELongitude);
        contentValues.put(this.SWLatitude,SWLatitude);
        contentValues.put(this.SWLongitude,SWLongitude);
        contentValues.put(this.address,address);
        contentValues.put(this.isEnabled,true);
        contentValues.put(this.isHour,isHour);
        long result = db.insert(this.tableName,null,contentValues);
        if (result==-1){
            return false;
        }
        return true;
    }
    public Cursor getAllAlarmData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+this.tableName,null);
    }
    public Cursor getSingleAlarmData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+this.tableName+" where id = "+id,null);
    }

    public boolean updateAlarmData(String id,String name, String latitude, String longitude, String NELatitude,String NELongitude,String SWLatitude,String SWLongitude,String address, Boolean isHour){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.id,id);
        contentValues.put(this.name,name);
        contentValues.put(this.latitude,latitude);
        contentValues.put(this.longitude,longitude);
        contentValues.put(this.NELatitude,NELatitude);
        contentValues.put(this.NELongitude,NELongitude);
        contentValues.put(this.SWLatitude,SWLatitude);
        contentValues.put(this.SWLongitude,SWLongitude);
        contentValues.put(this.address,address);
        contentValues.put(this.isEnabled,true);
        contentValues.put(this.isHour,isHour);
        long result = db.update(this.tableName,contentValues," id = ? ",new String[]{id});
        if (result==-1){
            return false;
        }
        return true;
    }
    public boolean deleteAlarm(String id){
        SQLiteDatabase db = this.getWritableDatabase();
       int resultRows= db.delete(this.tableName," id = ? ",new String[]{id});
        if (resultRows!=1){
            return false;
        }
        return true;

    }
}


