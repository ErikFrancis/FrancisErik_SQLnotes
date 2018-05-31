package com.example.francise4856.mycontactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editPhone;
    EditText editAddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_Name);
        editPhone = findViewById(R.id.editText_Phone);
        editAddress = findViewById(R.id.editText_Address);

        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: instantiated DatabaseHelper");
    }

    public void addData(View view) {
        Log.d("MyContactApp", "MainActivity: Add contact button pressed");

        boolean isInserted = myDb.insertData(editName.getText().toString(),editPhone.getText().toString(),editAddress.getText().toString());

        if (isInserted == true) {
            Toast.makeText(MainActivity.this, "Success - contact inserted", Toast.LENGTH_LONG).show();
        }
        else if (isInserted == false) {
            Toast.makeText(MainActivity.this, "Failure - contact not inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void viewData (View view) {
        Cursor res = myDb.getAllData();
        Log.d("MyAcontactApp", "MainActivity: viewData: recieved cursor " + res.getCount());

        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            for (int i = 0; i < res.getCount(); i++) {
                for (int j = 0; j < res.getColumnNames().length; j++) {
                    buffer.append(res.getString(j) + "\n");
                }
                buffer.append("\n");
                res.moveToNext();
            }
            //Append res column, 0,1,2,3 to the buffer, delimited by "/n"

        }
        Log.d("MyContactApp", "MainActivity: ViewData: assembled StringBuffer");
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        Log.d("MyContactApp", "MainActivity: showMessage: building alert dialog");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public static final String EXTRA_MESSAGE = "com.example.francise4856.mycontactapp.MESSAGE";
    public void SearchRecord(View view) {
        Log.d("MyContactApp", "MainActivity: launching SearchActivity");

        Cursor cursor = myDb.getAllData();
        StringBuffer message = new StringBuffer();

        Log.d("MyContactApp", "MainActivity: created string buffer");

        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(editName.getText().toString())) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    for (int j = 0; j < cursor.getColumnNames().length; j++) {
                        message.append(cursor.getString(j) + "\n");
                    }
                    message.append("\n");
                    cursor.moveToNext();
                }
            }
            //Append res column, 0,1,2,3 to the buffer, delimited by "/n"

        }


        Log.d("MyContactApp", "MainActivity: constructed message" + message.toString() + "hello");
        android.content.Intent intent = new android.content.Intent(this, SearchActivity2.class);
        intent.putExtra(EXTRA_MESSAGE, message.toString());
        startActivity(intent);
        Log.d("MyContactApp", "MainActivity: started SearchActivity");
    }


}
