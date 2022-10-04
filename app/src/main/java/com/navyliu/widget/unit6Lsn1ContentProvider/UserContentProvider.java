package com.navyliu.widget.unit6Lsn1ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class UserContentProvider extends ContentProvider {

    String TAG = this.getClass().getName();
    static final String PROVIDER_NAME = "com.navyliu.widgit.userProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/user";
    static final Uri CONTENT_URI = Uri.parse(URL);

    String ID = "id";
    String NAME = "name";
    String PASSWORD = "password";
    static final int USER = 1;
    static final int USER_ID = 2;

    private SQLiteDatabase database;
    private OpenHelper helper;
    private static UriMatcher matcher;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(PROVIDER_NAME, "user", USER);
        matcher.addURI(PROVIDER_NAME, "user/#", USER_ID);
    }

    public UserContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
//        throw new UnsupportedOperationException("Not yet implemented");
        if (matcher.match(uri) == 1) {
            // 如果uri匹配正确，打开数据库
//            SQLiteDatabase database = helper.getReadableDatabase();
            database = helper.getWritableDatabase();
            // 添加用户记录
            long rowId = database.insert("user", null, values);
            Log.d(TAG, "insert: =====" + rowId);
            // 用户添加成功
            if (rowId > 0) {
                // 在前面已有的Uri后面追加ID
                Uri userUri = ContentUris.withAppendedId(uri, rowId);
                // 通知数据已经发生改变
                getContext().getContentResolver().notifyChange(userUri, null);
                return userUri;
            }
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
//        return false;

        helper = new OpenHelper(this.getContext(), "user.db", null, 1);

        /**
         * 如果数据库不存在，就创建一个可写的数据库
         */
        database = helper.getWritableDatabase();
        return database != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
//        throw new UnsupportedOperationException("Not yet implemented");
        Cursor cursor;
        if (matcher.match(uri) == 1) {
            Log.d(TAG, "query==========");
            // 如果uri匹配正确，打开数据库
            database = helper.getReadableDatabase();
            // 添加用户记录
            cursor = database.query("user", projection, selection, selectionArgs, null, null, null);
//            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        return 0;
    }

}