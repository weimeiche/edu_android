package com.navyliu.widget.unit5Lsn2Sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.navyliu.widget.R;

public class SqliteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);


        DBOpenHelper dbOpenHelper = new DBOpenHelper(this, "friend3.db", null, 2);
        SQLiteDatabase readableDatabase = dbOpenHelper.getReadableDatabase();
//        SQLiteDatabase sqLiteDatabase = new SQLiteDatabase();
//        dbOpenHelper.onCreate("frid.db");

    }
}