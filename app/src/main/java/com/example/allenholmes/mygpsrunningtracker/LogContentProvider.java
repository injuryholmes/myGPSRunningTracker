package com.example.allenholmes.mygpsrunningtracker;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class LogContentProvider extends ContentProvider {
    private DBHelper dbHelper = null;
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(LogProviderContract.AUTHORITY, "run_log", 1);
        uriMatcher.addURI(LogProviderContract.AUTHORITY, "run_log/#", 2);
    }

    @Override
    public boolean onCreate() {
        this.dbHelper = new DBHelper(this.getContext(), "run_log_DB", null, 1);
        return true;
    }


    @Override
    public String getType(Uri uri) {
        String contentType;
        if (uri.getLastPathSegment() == null) {
            contentType = LogProviderContract.CONTENT_TYPE_SINGLE;
        } else {
            contentType = LogProviderContract.CONTENT_TYPE_MULTIPLE;
        }
        return contentType;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d("g53mdp", uri.toString() + " " + uriMatcher.match(uri));
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch(uriMatcher.match(uri)) {
            case 2:
                String sqlQuery = "SELECT instruction FROM run_log_DB WHERE _id=?";
                return db.rawQuery(sqlQuery, selectionArgs);
            case 1:
                return db.query("run_log", projection, selection, selectionArgs, null, null, sortOrder);
            default:
                return null;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d("g53mdp", uri.toString() + " " + uriMatcher.match(uri));
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case 2:
                selection = "_ID = ?";
                return db.update("run_log", values, selection, selectionArgs);
            default:
                return 0;
        }
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert("run_log", null, values);
        db.close();
        Uri nu = ContentUris.withAppendedId(uri, id);
        Log.d("g53mdp", nu.toString());
        getContext().getContentResolver().notifyChange(nu, null);
        return nu;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        selection = "_ID = ?";
        return db.delete("run_log", selection, selectionArgs);
    }

}
