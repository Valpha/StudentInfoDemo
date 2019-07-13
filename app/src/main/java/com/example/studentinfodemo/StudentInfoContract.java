package com.example.studentinfodemo;

import android.provider.BaseColumns;

public class StudentInfoContract {
    public static class StudentEntry implements BaseColumns {
        public static String DATABASE_NAME = "student.db";
        public static int DATABASE_VERSION = 1;
        public static String TABLE_NAME = "student";
        public static String COLUMN_NAME_NAME = "name";
        public static String COLUMN_NAME_STNUMBER = "stnumber";
    }
}
