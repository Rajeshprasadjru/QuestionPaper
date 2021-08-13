package com.questionpaper.Model;

public class RegisterData {
    String name, password, email,uid,course,enno;

    public RegisterData() {

    }

    public RegisterData(String name, String password, String email, String uid, String course, String enno) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.uid = uid;
        this.course = course;
        this.enno = enno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEnno() {
        return enno;
    }

    public void setEnno(String enno) {
        this.enno = enno;
    }
}
