package com.example.student_managament.Other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.student_managament.Model.Class;
import com.example.student_managament.Model.Student;
import com.example.student_managament.Model.Subject;
import com.example.student_managament.Model.Teacher;

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
            + ID_STUDENT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + STUDENT_NAME + " NVARCHAR(50),"
            + GENDER + " NVARCHAR(10),"
            + STUDENT_PHONE + " TEXT,"
            + STUDENT_EMAIL + " TEXT,"
            + STUDENT_ADDRESS + " NVARCHAR(50))";

    //Tạo Bảng Môn Học
    private static String TABLE_SUBJECT = "Subjects" ;
    private static String ID_SUBJECT ="subjectID";
    private static String SUBJECT_NAME ="subjectName";
    private static String CREDIT = "credit";
    private static String SEMESTER = "semester";
    private static String DEPARTMENT = "department";

    private static final String SQLQuerySubject = "CREATE TABLE " + TABLE_SUBJECT + "("
            + ID_SUBJECT + " TEXT PRIMARY KEY,"
            + SUBJECT_NAME + " NVARCHAR(50),"
            + CREDIT + " TEXT,"
            + SEMESTER + " TEXT,"
            + DEPARTMENT + " TEXT)";
    // tạo bảng giáo viên
    private static String TEACHER_TABLE = "teachers";
    private static String TEACHER_ID = "teacherId";
    private static String TEACHER_NAME = "teacherName";
    private static String TEACHER_BIRTHDAY = "teacherBirthday";
    private static String TEACHER_ACADEMIC ="teacherAcademic";
    private static String TEACHER_GENDER = "teacherGender";
    private static String TEACHER_EMAIL = "teacherEmail";
    private static String TEACHER_ADDRESSS= "teacherAddress";
    private static String SQLQueryTeacher =  "CREATE TABLE " + TEACHER_TABLE + "("
            + TEACHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TEACHER_NAME + " NVARCHAR(50),"
            + TEACHER_BIRTHDAY + " TEXT,"
            + TEACHER_GENDER + " NVARCHAR(10),"
            +TEACHER_ACADEMIC+"TEXT,"
            + TEACHER_EMAIL + " TEXT,"
            + TEACHER_ADDRESSS + " NVARCHAR(50))";
    // tạo bảng Lớp
    private static String CLASS_TABLES="Classes";
    private static String CLASS_ID = "classID";
    private static String CLASS_NAME ="className";
    private static String QUANTITY = "quantity";

    private static String ID_TEACHER = "IDteacher";

    private static final String SQLQueryClass = "CREATE TABLE " + CLASS_TABLES + "("
            + CLASS_ID + " TEXT PRIMARY KEY,"
            + CLASS_NAME + " NVARCHAR(50),"
            + QUANTITY + " INTEGER,"
            + ID_TEACHER + " INTEGER, FOREIGN KEY ("+ID_TEACHER+") REFERENCES "+TEACHER_TABLE+"("+TEACHER_ID+"))";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQLQueryStudent);
        sqLiteDatabase.execSQL(SQLQuerySubject);
        sqLiteDatabase.execSQL(SQLQueryTeacher);
        sqLiteDatabase.execSQL(SQLQueryClass);
        sqLiteDatabase.execSQL("PRAGMA encoding = \"UTF-8\"");
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
        values.put(SEMESTER,subject.getSemester());
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
        values.put(SEMESTER,subject.getSemester());
        values.put(DEPARTMENT,subject.getDepartment());

        db.update(TABLE_SUBJECT,values,ID_SUBJECT+"="+'"'+id+'"',null);
        return true ;
    }
    // xóa thông tin môn học
    public int deleteSubject(String id ) {
        SQLiteDatabase db = this.getReadableDatabase();
        int res =db.delete(TABLE_SUBJECT,ID_SUBJECT+"="+'"'+id+'"',null);
        return res;
    }
    // lấy danh sách môn học
    public Cursor getDataSubject(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_SUBJECT,null);

        return cursor;
    }
    //CURD giảng viên
    //thêm thông tin giảng viên
    public void addTeacher(Teacher teacher){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TEACHER_NAME,teacher.getName());
        values.put(TEACHER_GENDER,teacher.getGender());
        values.put(TEACHER_BIRTHDAY,teacher.getBirthday());
        values.put(TEACHER_EMAIL,teacher.getEmail());
        values.put(TEACHER_ADDRESSS,teacher.getAddress());

        db.insert(TEACHER_TABLE,null,values);
        db.close();

    }
    // cập nhật thông tin giảng viên
    public boolean updateTeacher(Teacher teacher,int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(TEACHER_NAME,teacher.getName());
        values.put(TEACHER_GENDER,teacher.getGender());
        values.put(TEACHER_BIRTHDAY,teacher.getBirthday());
        values.put(TEACHER_EMAIL,teacher.getEmail());
        values.put(TEACHER_ADDRESSS,teacher.getAddress());

        db.update(TEACHER_TABLE,values,ID_SUBJECT+"="+id,null);
        return true ;
    }
    // xóa thông tin giảng viên
    public int deleteTeacher(int id ) {
        SQLiteDatabase db = this.getReadableDatabase();
        int res =db.delete(TEACHER_TABLE,TEACHER_ID+"="+id,null);
        return res;
    }
    // lấy danh sách giảng viên
    public Cursor getDatateacher(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TEACHER_TABLE,null);

        return cursor;
    }
//    CURD lớp học
    //thêm thông tin lớp học
public void addClass(Class aclass){
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();

    values.put(CLASS_ID,aclass.getId());
    values.put(CLASS_NAME,aclass.getName());
    values.put(QUANTITY,aclass.getQuantiy());
    values.put(TEACHER_ID,aclass.getTeacher_id());

    db.insert(CLASS_TABLES,null,values);
    db.close();

}
    // cập nhật thông tin lớp học
    public boolean updateClass(Class aclass,String id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CLASS_ID,aclass.getId());
        values.put(CLASS_NAME,aclass.getName());
        values.put(QUANTITY,aclass.getQuantiy());
        values.put(TEACHER_ID,aclass.getTeacher_id());

        db.update(CLASS_TABLES,values,CLASS_ID+"="+'"'+id+'"',null);
        return true ;
    }
    // xóa thông tin lớp học
    public int deleteClass(String id ) {
        SQLiteDatabase db = this.getReadableDatabase();
        int res =db.delete(CLASS_TABLES,ID_SUBJECT+"="+'"'+id+'"',null);
        return res;
    }
    // lấy danh sách lớp học
    public Cursor getDataClass(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CLASS_TABLES,null);

        return cursor;
    }
}
