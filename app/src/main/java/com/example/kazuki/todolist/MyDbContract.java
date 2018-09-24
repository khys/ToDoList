package com.example.kazuki.todolist;

import android.provider.BaseColumns;

public final class MyDbContract {
    public MyDbContract() {}

    public static class MyDbEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "item";
        public static final String COLUMN_NAME_SUBTITLE = "label";
        public static final String COLUMN_NAME_NUMBER = "number";
    }
}
