package com.example.francise4856.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactApp2018.db";
    public static final String TABLE_NAME = "ContactApp2018_table";
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
        contentValue.put(COLUMN_PHONE_CONTACT, phone);
        contentValue.put(COLUMN_ADDRESS_CONTACT, address);

        long result = db.insert(TABLE_NAME, null, contentValue);
        if (result == -1) {
            Log.d("MyContactApp", "Databasehelper: Contnt insert - FAILED");
            return false;
        }
        else {
            Log.d("MyContactApp","Databasehelper: Content insert - PASSED");
            return true;
        }
    }


}
