package com.example.student_managament.Model;

public class Score {
    private int id ;
    private String type ;
    private double score ;
    private int student_id;
    private String subject_id;

    public Score(int id, String type, double score, int student_id, String subject_id) {
        this.id = id;
        this.type = type;
        this.score = score;
        this.student_id = student_id;
        this.subject_id = subject_id;
    }

    public Score(String type, double score, int student_id, String subject_id) {
        this.type = type;
        this.score = score;
        this.student_id = student_id;
        this.subject_id = subject_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
}
