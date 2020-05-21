package com.example.projectlab.Model;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class Kos implements Parcelable{
    private String ID;
    private int pic;
    private String name;
    private int price;
    private String facility;
    private String desc;
    private String longitude;
    private String latitude;

    public Kos(String ID, int pic, String name, int price, String facility, String desc, String longitude, String latitude) {
        this.ID = ID;
        this.pic = pic;
        this.name = name;
        this.price = price;
        this.facility = facility;
        this.desc = desc;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    protected Kos(Parcel in) {
        ID = in.readString();
        pic = in.readInt();
        name = in.readString();
        price = in.readInt();
        facility = in.readString();
        desc = in.readString();
        longitude = in.readString();
        latitude = in.readString();
    }

    public static final Creator<Kos> CREATOR = new Creator<Kos>() {
        @Override
        public Kos createFromParcel(Parcel in) {
            return new Kos(in);
        }

        @Override
        public Kos[] newArray(int size) {
            return new Kos[size];
        }
    };

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ID);
        parcel.writeInt(pic);
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeString(facility);
        parcel.writeString(desc);
        parcel.writeString(longitude);
        parcel.writeString(latitude);
    }
}

