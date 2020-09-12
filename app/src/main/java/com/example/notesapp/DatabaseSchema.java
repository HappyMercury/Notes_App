package com.example.notesapp;

import android.provider.BaseColumns;

public class DatabaseSchema {

    private DatabaseSchema(){}

    public class DatabaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "Notes";
        public static final String COLUMN_NAME_HEADING = "heading";
        public static final String COLUMN_NAME_CONTENT = "content";
    }

}
