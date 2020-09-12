package com.example.notesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Note.db";

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Note.SQLITE_CREATE_ENTRIES);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseSchema.DatabaseEntry.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNote(String heading,String content)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSchema.DatabaseEntry.COLUMN_NAME_HEADING, heading);
        contentValues.put(DatabaseSchema.DatabaseEntry.COLUMN_NAME_CONTENT, content);
        db.insert(DatabaseSchema.DatabaseEntry.TABLE_NAME, null, contentValues);
        return true;
    }


    public ArrayList<String> getAllHeadings() {
        ArrayList<String> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ DatabaseSchema.DatabaseEntry.TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(DatabaseSchema.DatabaseEntry.COLUMN_NAME_HEADING)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<NoteListItem> getFinalArrayList()
    {
        ArrayList<NoteListItem> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ DatabaseSchema.DatabaseEntry.TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            int length = res.getString(res.getColumnIndex(DatabaseSchema.DatabaseEntry.COLUMN_NAME_CONTENT)).length();
            String content = "";
            if(length>20)
            {
                length = 19;
                content = res.getString(res.getColumnIndex(DatabaseSchema.DatabaseEntry.COLUMN_NAME_CONTENT)).substring(0,length)+"......";

            }
            else
            {
                content = res.getString(res.getColumnIndex(DatabaseSchema.DatabaseEntry.COLUMN_NAME_CONTENT)).substring(0,length);
            }


            array_list.add(new NoteListItem(R.drawable.ic_note,res.getString(res.getColumnIndex(DatabaseSchema.DatabaseEntry.COLUMN_NAME_HEADING)),content));
            res.moveToNext();
        }
        return array_list;
    }

    public String getNoteHeading(String h)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ DatabaseSchema.DatabaseEntry.TABLE_NAME+" where "+ DatabaseSchema.DatabaseEntry.COLUMN_NAME_HEADING+"="+h,null);
        String heading = res.getString(res.getColumnIndex(DatabaseSchema.DatabaseEntry.COLUMN_NAME_HEADING));
        return heading;
    }

    public String getNoteContent(String h)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ DatabaseSchema.DatabaseEntry.TABLE_NAME+" where "+ DatabaseSchema.DatabaseEntry.COLUMN_NAME_HEADING+"="+h,null);
        String content = res.getString(res.getColumnIndex(DatabaseSchema.DatabaseEntry.COLUMN_NAME_CONTENT));

        return content;
    }

    public void deleteNote(String heading)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseSchema.DatabaseEntry.TABLE_NAME,DatabaseSchema.DatabaseEntry.COLUMN_NAME_HEADING+"=?",new String[]{heading});

    }

    public int getCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ DatabaseSchema.DatabaseEntry.TABLE_NAME,null);
        return res.getCount();

    }

}
