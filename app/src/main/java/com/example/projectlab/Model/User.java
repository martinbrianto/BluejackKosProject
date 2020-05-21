package com.example.projectlab.Model;

public class User {

    private String username;
    private String password;
    private String phone;
    private String gender;
    private String DOB;
    private String ID;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public User(String username, String password, String phone, String gender, String DOB, String ID) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.DOB = DOB;
        this.ID = ID;
    }
}
