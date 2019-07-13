package com.example.studentinfodemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StduentInfoDBHelper extends SQLiteOpenHelper {

    private static final String CREATE_STUDENT_TABLESQL = "create table "
                    + StudentInfoContract.StudentEntry.TABLE_NAME +" ("
                    + StudentInfoContract.StudentEntry._ID +" integer primary key, "
                    + StudentInfoContract.StudentEntry.COLUMN_NAME_NAME +" text, "
                    + StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER +" integer);";
    private static final String DROP_STUDENT_TABLESQL = "drop table "
            + StudentInfoContract.StudentEntry.TABLE_NAME + ";";
    //  构造方法
    public StduentInfoDBHelper(Context context) {
        super(  context,
                StudentInfoContract.StudentEntry.DATABASE_NAME,
                null,
                StudentInfoContract.StudentEntry.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLESQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_STUDENT_TABLESQL);
    }
}
