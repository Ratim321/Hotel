package com.example.hotel.model;

import java.util.Comparator;

public class Hotel {

    private String  image,roomid,hotelid,roomtype,price,size,Hotelname,address,location,phoneno,email,rating,fromdate,todate;

    public String getHotelname() {
        return Hotelname;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public Hotel() {
    }

    public void setHotelname(String hotelname) {
        Hotelname = hotelname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Hotel(String hotelname, String image, String location, String hotelid, String roomid, String roomtype, String size, String address, String price, String phoneno, String email, String rating) {
        Hotelname = hotelname;
        this.image = image;
        this.location = location;
        this.hotelid = hotelid;
        this.roomid = roomid;
        this.roomtype = roomtype;
        this.size = size;
        this.address = address;
        this.price = price;
        this.phoneno = phoneno;
        this.email = email;
        this.rating = rating;
    }


}
