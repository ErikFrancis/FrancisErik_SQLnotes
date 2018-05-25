package com.example.francise4856.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Target;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactApp2018.db";
    public static final String TABLE_NAME = "ContactApp2018_table";
    public static final String TABLE_PHONE = "ContactApp2018_table";
    public static final String TABLE_ADDRESS = "ContactApp2018_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_PHONE_CONTACT = "contact";
    public static final String COLUMN_ADDRESS_CONTACT = "contact";

    public static final String SQL_CREATE_ENTRIES =
            "Create Table " +  TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_CONTACT + " TEXT)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE ID EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("MyContactApp", "Databasehelper: constructed databasehelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp", "Databasehelper: creating database");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp", "Databasehelper: upgrading database");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertData (String name, String phone, String address){
        Log.d("MyContactApp","Databasehelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues contentValue = new android.content.ContentValues();

        contentValue.put(COLUMN_NAME_CONTACT, name);
        long resultname = db.insert(TABLE_NAME, null, contentValue);
        contentValue.put(COLUMN_PHONE_CONTACT, phone);
        long resultphone = db.insert(TABLE_PHONE, null, contentValue);
        contentValue.put(COLUMN_ADDRESS_CONTACT, address);
        long resultaddress = db.insert(TABLE_ADDRESS, null, contentValue);

        if (resultname == -1 || resultphone == -1 || resultaddress == -1 ) {
            Log.d("MyContactApp", "Databasehelper: Contnt insert - FAILED");
            return false;
        }
        else {
            Log.d("MyContactApp","Databasehelper: Content insert - PASSED");
            return true;
        }
    }

    public Cursor getAllData() {
        Log.d("MyContactApp", "Databasehelper: calling getAllData method");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " " + TABLE_PHONE + " " + TABLE_ADDRESS, null);
        Log.d("MyContactApp", "Databasehelper: cursor res db");
        return res;
    }

}
