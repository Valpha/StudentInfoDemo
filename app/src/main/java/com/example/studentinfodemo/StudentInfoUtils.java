package com.example.studentinfodemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class StudentInfoUtils {
    private static StduentInfoDBHelper m_dbHelper;
    private static Context m_ctx;
    private static String[] columns = new String[]{
            StudentInfoContract.StudentEntry._ID,                   //  第0列
            StudentInfoContract.StudentEntry.COLUMN_NAME_NAME,      //  第1列
            StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER   //  第2列
    };
    //  设置上下文
    public static void setContext(Context ctx) {
        m_ctx = ctx;
        if (m_dbHelper == null) {
            m_dbHelper = new StduentInfoDBHelper(m_ctx);
        }
    }

    //  按照学生姓名和学号添加至数据库
    public static long insertStudent(Student stu) {
        assert (m_dbHelper != null);
        SQLiteDatabase db = m_dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentInfoContract.StudentEntry.COLUMN_NAME_NAME, stu.getName());
        values.put(StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER, stu.getStunumber());
        long insertcount = db.insert(StudentInfoContract.StudentEntry.TABLE_NAME, null, values);
        db.close();
        return insertcount;
    }

    //  按照姓名删除学生
    public static int deleteStudent(String name) {
        assert (m_dbHelper != null);
        SQLiteDatabase db = m_dbHelper.getWritableDatabase();
        String whereClause = StudentInfoContract.StudentEntry.COLUMN_NAME_NAME + " = ?";
        String[] whereArgs = new String[]{name};
        int deletecount = db.delete(StudentInfoContract.StudentEntry.TABLE_NAME, whereClause, whereArgs);
        db.close();
        return deletecount;
    }

    //  按照学号删除学生
    public static int deleteStudent(int stunum) {
        assert (m_dbHelper != null);
        SQLiteDatabase db = m_dbHelper.getWritableDatabase();
        String whereClause = StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER + " = ?";
        String[] whereArgs = new String[]{String.valueOf(stunum)};
        int deletecount = db.delete(StudentInfoContract.StudentEntry.TABLE_NAME, whereClause, whereArgs);
        db.close();
        return deletecount;
    }

    //  按姓名+学号双匹配方式删除学生
    public static int deleteStudent(String name, int stunum) {
        assert (m_dbHelper != null);
        SQLiteDatabase db = m_dbHelper.getWritableDatabase();
        String whereClause =
                StudentInfoContract.StudentEntry.COLUMN_NAME_NAME + " = ?" + " AND " +
                StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER + " = ?";
        String[] whereArgs = new String[]{name, String.valueOf(stunum)};
        int deletecount = db.delete(StudentInfoContract.StudentEntry.TABLE_NAME, whereClause, whereArgs);
        db.close();
        return deletecount;
    }

    //  根据学号更新姓名
    public static int updateStudent(int stunum, String newname) {
        assert (m_dbHelper != null);
        SQLiteDatabase db = m_dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentInfoContract.StudentEntry.COLUMN_NAME_NAME, newname);
        String whereClause = StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER + " = ?";
        String[] whereArgs = new String[]{String.valueOf(stunum)};
        int updatecount = db.update(StudentInfoContract.StudentEntry.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
        return updatecount;
    }

    //  查询所有条目
    public static List<Student> queryStudent() {
        assert (m_dbHelper != null);
        SQLiteDatabase db = m_dbHelper.getWritableDatabase();
        Cursor cursor = db.query(StudentInfoContract.StudentEntry.TABLE_NAME,
                columns, null, null, null, null, null);
        ArrayList<Student> stulist = new ArrayList<Student>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String tmpname = cursor.getString(cursor.getColumnIndex(StudentInfoContract.StudentEntry.COLUMN_NAME_NAME));
                int tmpstunumber = cursor.getInt(cursor.getColumnIndex(StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER));
                int tmp_id = cursor.getInt(cursor.getColumnIndex(StudentInfoContract.StudentEntry._ID));
                Student tmpstu = new Student(tmp_id, tmpname, tmpstunumber);
                stulist.add(tmpstu);
            }
        }
        db.close();
        return stulist;
    }

    //  按照学号查找条目
    public static List<Student> queryStudent(int stunum) {
        assert (m_dbHelper != null);
        SQLiteDatabase db = m_dbHelper.getWritableDatabase();
        String selection = StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(stunum)};
        Cursor cursor = db.query(StudentInfoContract.StudentEntry.TABLE_NAME,
                columns, selection, selectionArgs, null, null, null);
        ArrayList<Student> stulist = new ArrayList<Student>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String tmpname = cursor.getString(cursor.getColumnIndex(StudentInfoContract.StudentEntry.COLUMN_NAME_NAME));
                int tmpstunumber = cursor.getInt(cursor.getColumnIndex(StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER));
                int tmp_id = cursor.getInt(cursor.getColumnIndex(StudentInfoContract.StudentEntry._ID));
                Student tmpstu = new Student(tmp_id, tmpname, tmpstunumber);
                stulist.add(tmpstu);
            }
        }
        db.close();
        return stulist;
    }

    //  按照姓名查找条目
    public static List<Student> queryStudent(String name) {
        assert (m_dbHelper != null);
        SQLiteDatabase db = m_dbHelper.getWritableDatabase();
        String selection = StudentInfoContract.StudentEntry.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = new String[]{name};
        Cursor cursor = db.query(StudentInfoContract.StudentEntry.TABLE_NAME,
                columns, selection, selectionArgs, null, null, null);
        ArrayList<Student> stulist = new ArrayList<Student>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String tmpname = cursor.getString(cursor.getColumnIndex(StudentInfoContract.StudentEntry.COLUMN_NAME_NAME));
                int tmpstunumber = cursor.getInt(cursor.getColumnIndex(StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER));
                int tmp_id = cursor.getInt(cursor.getColumnIndex(StudentInfoContract.StudentEntry._ID));
                Student tmpstu = new Student(tmp_id, tmpname, tmpstunumber);
                stulist.add(tmpstu);
            }
        }
        db.close();
        return stulist;
    }

    //  按照姓名&学号双匹配方式查找条目
    public static List<Student> queryStudent(String name, int stunum) {
        assert (m_dbHelper != null);
        SQLiteDatabase db = m_dbHelper.getWritableDatabase();
        String selection =
                StudentInfoContract.StudentEntry.COLUMN_NAME_NAME + " = ?" + " AND " +
                        StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER + " = ?";
        String[] selectionArgs = new String[]{name, String.valueOf(stunum)};
        Cursor cursor = db.query(StudentInfoContract.StudentEntry.TABLE_NAME,
                columns, selection, selectionArgs, null, null, null);
        ArrayList<Student> stulist = new ArrayList<Student>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String tmpname = cursor.getString(cursor.getColumnIndex(StudentInfoContract.StudentEntry.COLUMN_NAME_NAME));
                int tmpstunumber = cursor.getInt(cursor.getColumnIndex(StudentInfoContract.StudentEntry.COLUMN_NAME_STNUMBER));
                int tmp_id = cursor.getInt(cursor.getColumnIndex(StudentInfoContract.StudentEntry._ID));
                Student tmpstu = new Student(tmp_id, tmpname, tmpstunumber);
                stulist.add(tmpstu);
            }
        }
        db.close();
        return stulist;
    }
}
