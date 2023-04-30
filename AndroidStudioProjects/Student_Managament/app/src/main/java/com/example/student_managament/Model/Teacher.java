package com.example.student_managament.Model;

public class Teacher {
    private int id ;
    private String name ;
    private String gender ;
    private String birthday;
    private String academic;
    private String email;
    private String address ;

    public Teacher(int id, String name, String gender, String birthday, String academic, String email, String address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.academic = academic;
        this.email = email;
        this.address = address;
    }

    public Teacher() {
    }

    public Teacher(String name, String gender, String birthday, String academic, String email, String address) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.academic = academic;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }
}
