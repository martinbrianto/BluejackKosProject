package com.example.projectlab.Model;

import java.util.Calendar;

public class Book {

    private String bookingID;
    private String userID;
    private String bookingDate;
    private Kos kosdata;

    public Book(String bookingID, String userID, String bookingDate, Kos kosdata) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.bookingDate = bookingDate;
        this.kosdata = kosdata;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Kos getKosdata() {
        return kosdata;
    }

    public void setKosdata(Kos kosdata) {
        this.kosdata = kosdata;
    }
}
