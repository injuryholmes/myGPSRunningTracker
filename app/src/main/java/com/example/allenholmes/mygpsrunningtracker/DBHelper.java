package com.example.allenholmes.mygpsrunningtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.allenholmes.mygpsrunningtracker.LogProviderContract.KEY_ELEVATION;
import static com.example.allenholmes.mygpsrunningtracker.LogProviderContract.KEY_ID;
import static com.example.allenholmes.mygpsrunningtracker.LogProviderContract.KEY_LATITUDE;
import static com.example.allenholmes.mygpsrunningtracker.LogProviderContract.KEY_LONGITUDE;
import static com.example.allenholmes.mygpsrunningtracker.LogProviderContract.KEY_TIME;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "run_log_DB", null, 1);
    }
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static final String SQLITE_CREATE =
            "CREATE TABLE if not exists run_log (" +
                    KEY_ID + " integer PRIMARY KEY autoincrement," +
                    KEY_LATITUDE + " real," +
                    KEY_LONGITUDE + " real," +
                    KEY_ELEVATION + " real," +
                    KEY_TIME + " real );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLITE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS run_log_DB");
        onCreate(db);
    }
}
