package com.example.allenholmes.mygpsrunningtracker;

import android.net.Uri;

public class LogProviderContract {
    public static final String AUTHORITY = "com.example.allenholmes.mygpsrunningtracker.LogContentProvider";
    public static final Uri RUN_LOG_URI = Uri.parse("content://" + AUTHORITY + "/run_log");

    public static final Uri ALL_URI = Uri.parse("content://" + AUTHORITY + "/");

    public static final String KEY_ID = "_id";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_ELEVATION = "elevation";
    public static final String KEY_TIME = "time";

    public static final String CONTENT_TYPE_SINGLE = "vnd.android.cusor.item/LogContentProvider.data.text";
    public static final String CONTENT_TYPE_MULTIPLE = "vnd.android.cursor.dir/LogContentProvider.data.text";
}
