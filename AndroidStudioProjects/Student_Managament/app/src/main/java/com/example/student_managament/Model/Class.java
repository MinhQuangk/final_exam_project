package com.example.student_managament.Model;

public class Class {
    private String id ;
    private String name ;
    private int quantiy;

    private int teacher_id;

    public Class() {
    }

    public Class(String id, String name, int quantiy,int teacher_id) {
        this.id = id;
        this.name = name;
        this.quantiy = quantiy;
        this.teacher_id =teacher_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }
}
