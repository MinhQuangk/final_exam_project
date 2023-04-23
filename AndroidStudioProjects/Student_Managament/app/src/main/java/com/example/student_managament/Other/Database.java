package com.example.student_managament.Other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.student_managament.Model.Student;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudenManagamentSystem";
    private static final int VERSION = 1;
    //Bảng Sinh viên

    private static String TABLE_STUDENT="Students";
    private static String ID_STUDENT ="StudentID";
    private static String STUDENT_NAME="studentName";
    private static String GENDER = "gender";
    private static String STUDENT_PHONE="studentPhone";
    private static String STUDENT_EMAIL= "studentEmail";
    private static String STUDENT_ADDRESS="studentAddress";

    //Tạo bảng sinh viên
    private static final String SQLQuery = "CREATE TABLE " + TABLE_STUDENT + "("
            + ID_STUDENT + " INTEGER PRIMARY KEY,"
            + STUDENT_NAME + " TEXT,"
            + GENDER + " TEXT,"
            + STUDENT_PHONE + " TEXT,"
            + STUDENT_EMAIL + " TEXT,"
            + STUDENT_ADDRESS + " TEXT)";
    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //thêm thông tin sinh viên
    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(STUDENT_NAME,student.getFullName());
        values.put(GENDER,student.getGender());
        values.put(STUDENT_PHONE,student.getPhone());
        values.put(STUDENT_EMAIL,student.getEmail());
        values.put(STUDENT_ADDRESS,student.getAddresss());

        db.insert(TABLE_STUDENT,null,values);
        db.close();

    }
    // cập nhật thông tin sinh viên
    public boolean updateStudent(Student student,int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(STUDENT_NAME,student.getFullName());
        values.put(GENDER,student.getGender());
        values.put(STUDENT_PHONE,student.getPhone());
        values.put(STUDENT_EMAIL,student.getEmail());
        values.put(STUDENT_ADDRESS,student.getAddresss());

        db.update(TABLE_STUDENT,values,ID_STUDENT+"="+id,null);
        return true ;
    }
    // xóa thông tin sinh viên
    public int deleteStudent(int id ) {
        SQLiteDatabase db = this.getReadableDatabase();
        int res =db.delete(TABLE_STUDENT,ID_STUDENT+"="+id,null);
        return res;
    }
    // lấy danh sách sinh viên
    public Cursor getDataStudent(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_STUDENT,null);
        return cursor;
    }
}
