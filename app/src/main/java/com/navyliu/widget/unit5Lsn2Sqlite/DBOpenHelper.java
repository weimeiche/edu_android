package com.navyliu.widget.unit5Lsn2Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

class DBOpenHelper extends SQLiteOpenHelper {
    private final String TAG = this.getClass().getName();

    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    // 数据库第一次创建的时候调用
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ============sqlite=");
        String sql = "CREATE TABLE friend(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", name VARCHAR(50))";
        Log.d(TAG, "onCreate: ============" + sql);
        db.execSQL(sql);
    }

    @Override
    // 数据库版本号发生改变时调用
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 删除列 ALTER TABLE b_egg DROP COLUMN check_ip
        // 添加列 ALTER TABLE b_egg ADD check_ip VARCHAR(20) DEFAULT NULL
        // 更新列 ALTER TABLE b_egg MODIFY COLUMN check_ip VARCHAR(50)
        Log.d(TAG, "onUpgrade: ==============sqlite====");
        switch (oldVersion) {
            case 1:
                String sql = "ALTER TABLE person ADD phone VARCHAR(20) DEFAULT NULL";
                db.execSQL(sql);
                break;
        }
    }
}
