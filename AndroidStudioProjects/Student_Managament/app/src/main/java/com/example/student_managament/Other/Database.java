package com.example.student_managament.Other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.student_managament.Model.Student;
import com.example.student_managament.Model.Subject;

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
    private static final String SQLQueryStudent = "CREATE TABLE " + TABLE_STUDENT + "("
            + ID_STUDENT + " INTEGER PRIMARY KEY,"
            + STUDENT_NAME + " TEXT,"
            + GENDER + " TEXT,"
            + STUDENT_PHONE + " TEXT,"
            + STUDENT_EMAIL + " TEXT,"
            + STUDENT_ADDRESS + " TEXT)";

    //Tạo bảng môn học
    private static String TABLE_SUBJECT = "Subjects" ;
    private static String ID_SUBJECT ="subjectID";
    private static String SUBJECT_NAME ="subjectName";
    private static String CREDIT = "credit";
    private static String SEMSESTER = "semester";
    private static String DEPARTMENT = "department";

    private static final String SQLQuerySubject = "CREATE TABLE " + TABLE_SUBJECT + "("
            + ID_SUBJECT + " TEXT PRIMARY KEY,"
            + SUBJECT_NAME + " TEXT,"
            + CREDIT + " TEXT,"
            + SEMSESTER + " TEXT,"
            + DEPARTMENT + " TEXT)";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQLQueryStudent);
        sqLiteDatabase.execSQL(SQLQuerySubject);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //CURD sinh viên
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


    //CURD môn học
    public void addSubject(Subject subject){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_SUBJECT,subject.getId_subject());
        values.put(SUBJECT_NAME,subject.getName_subject());
        values.put(CREDIT,subject.getCredit());
        values.put(SEMSESTER,subject.getSemester());
        values.put(DEPARTMENT,subject.getDepartment());

        db.insert(TABLE_SUBJECT,null,values);
        db.close();

    }
    // cập nhật thông tin môn học
    public boolean updateSubject(Subject subject,String id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(SUBJECT_NAME,subject.getName_subject());
        values.put(CREDIT,subject.getCredit());
        values.put(SEMSESTER,subject.getSemester());
        values.put(DEPARTMENT,subject.getDepartment());

        db.update(TABLE_SUBJECT,values,ID_SUBJECT+"="+id,null);
        return true ;
    }
    // xóa thông tin môn học
    public int deleteSubject(String id ) {
        SQLiteDatabase db = this.getReadableDatabase();
        int res =db.delete(TABLE_STUDENT,ID_SUBJECT+"="+id,null);
        return res;
    }
    // lấy danh sách môn học
    public Cursor getDataSubject(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_SUBJECT,null);

        return cursor;
    }

}
