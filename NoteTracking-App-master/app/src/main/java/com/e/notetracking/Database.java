package com.example.notetracking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "notedb";
    private static final String DATABASE_TABLE = "notesTable";

    private static final String KEY_ID = "id";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_TIME = "time";
    private static final String KEY_DETAIL = "details";
    private static final String KEY_DATE = "date";

    Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE nametable(id INT PRIMARY KEY, subjectTEXT, detailsTEXT, dateTEXT, timeTEXT);
        String query = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + "INT PRIMARY KEY," +
                KEY_SUBJECT + "TEXT, " +
                KEY_DETAIL + "TEXT, " +
                KEY_DATE + "TEXT, " +
                KEY_TIME + "TEXT " + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long addNote(Notes notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        // c.put(KEY_TIME, notes.getTime());
        c.put(KEY_SUBJECT, notes.getSubject());
        c.put(KEY_DETAIL, notes.getDetail());
        c.put(KEY_DATE, notes.getDate());
        c.put(KEY_TIME, notes.getTime());

        long ID = db.insert(DATABASE_TABLE, null, c);
        Log.d("Inserted", "ID -> " + ID);
        return ID;
    }

    public Notes getNote(long id) {
        // select * from databaseTable where id=1
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_SUBJECT, KEY_DETAIL, KEY_DATE, KEY_TIME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

            return new Notes(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4));
        }
        public List<Notes> getNotess(){
            SQLiteDatabase db = this.getReadableDatabase();
            List<Notes> allNotess = new ArrayList<>();
            // select * from databaseName
            String query = "SELECT * FROM " + DATABASE_TABLE;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst() ) {
                do {
                    Notes notes = new Notes();
                    notes.setID(cursor.getLong(0));
                    notes.setSubject(cursor.getString(1));
                    notes.setDetail(cursor.getString(2));
                    notes.setDate(cursor.getString(3));
                    notes.setTime(cursor.getString(4));

                    allNotess.add(notes);
                } while (cursor.moveToNext());
            }
            return allNotess;
        }

    }



