package com.example.hotel.model;

public class Booking {
    String hotename,roomtype,date,price,bookingnumber;

    public String getBookingnumber() {
        return bookingnumber;
    }

    public void setBookingnumber(String bookingnumber) {
        this.bookingnumber = bookingnumber;
    }

    public Booking() {
    }

    public String getHotename() {
        return hotename;
    }

    public Booking(String hotename, String roomtype, String date, String price) {
        this.hotename = hotename;
        this.roomtype = roomtype;
        this.date = date;
        this.price = price;
    }

    public void setHotename(String hotename) {
        this.hotename = hotename;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
