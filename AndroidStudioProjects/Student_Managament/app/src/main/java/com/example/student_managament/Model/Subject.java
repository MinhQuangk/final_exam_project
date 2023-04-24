package com.example.student_managament.Model;

public class Subject {
    private String id_subject ;
    private String name_subject;
    private int credit;
    //số tín chỉ
    private String semester;
    //học kì hiện tại của môn học
    private String department;
    // khoa của môn học


    public Subject(String id_subject, String name_subject, int credit, String semester, String department) {
        this.id_subject = id_subject;
        this.name_subject = name_subject;
        this.credit = credit;
        this.semester = semester;
        this.department = department;
    }

    public Subject() {
    }

    public String getId_subject() {
        return id_subject;
    }

    public void setId_subject(String id_subject) {
        this.id_subject = id_subject;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
