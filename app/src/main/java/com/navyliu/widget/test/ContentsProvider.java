package com.navyliu.widget.test;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.regex.Matcher;

class ContentsProvider extends ContentProvider {

    private String MATCH = "com.navyliu.widgit.userProvider";

    private OpenHelper helper;
    private static UriMatcher matcher;
    private SQLiteDatabase database;


    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
    }

    @Override
    public boolean onCreate() {
        return false;
//        helper = new OpenHelper(getContext(), "user", null, null);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (matcher.match(uri) == 1) {
            // 新建
            database = helper.getWritableDatabase();

            long rowId = database.insert("friend", null, values);
            //
            if (rowId > 0) {
                ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(uri, null);
            }
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
