package com.example.kazuki.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyItem.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MyDbEntry.TABLE_NAME + " (" +
                    MyDbEntry._ID + " INTEGER PRIMARY KEY," +
                    MyDbEntry.COLUMN_NAME_TITLE + " TEXT," +
                    MyDbEntry.COLUMN_NAME_SUBTITLE + " INTEGER)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MyDbEntry.TABLE_NAME;

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static class MyDbEntry implements BaseColumns {
        private static final String TABLE_NAME = "entry";
        private static final String COLUMN_NAME_TITLE = "item";
        private static final String COLUMN_NAME_SUBTITLE = "label";
    }
}
