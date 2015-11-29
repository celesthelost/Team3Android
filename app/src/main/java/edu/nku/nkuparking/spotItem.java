package edu.nku.nkuparking;



import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class spotItem implements   Parcelable {

    protected String idSpot;
    protected String SpotNumber;

    protected String SpotLot;
    protected String SpotStatus;
    protected String ReservedBy;


    public String getidSpot() {
        return idSpot;
    }
    public void setidSpot(String idSpot) {
        this.idSpot = idSpot;
    }
    public String getSpotNumber() {
        return SpotNumber;
    }
    public void setSpotNumber(String SpotNumber) {
        this.SpotNumber = SpotNumber;
    }
    public String getSpotLot() {
        return SpotLot;
    }
    public void setSpotLot(String SpotLot) {
        this.SpotLot = SpotLot;
    }
    public String getSpotStatus() {
        return SpotStatus;
    }
    public void setSpotStatus(String SpotStatus) {
        this.SpotStatus = SpotStatus;
    }

    public String getReservedBy() {
        return ReservedBy;
    }
    public void setReservedBy(String ReservedBy) {
        this.ReservedBy = ReservedBy;
    }

    protected spotItem(){}

    protected spotItem(Parcel in) {
        idSpot = in.readString();
        SpotNumber = in.readString();
        SpotLot = in.readString();
        SpotStatus = in.readString();
        ReservedBy = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idSpot);
        dest.writeString(SpotNumber);
        dest.writeString(SpotLot);
        dest.writeString(SpotStatus);
        dest.writeString(ReservedBy);

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<spotItem> CREATOR = new Parcelable.Creator<spotItem>() {
        @Override
        public spotItem createFromParcel(Parcel in) {
            return new spotItem(in);
        }

        @Override
        public spotItem[] newArray(int size) {
            return new spotItem[size];
        }
    };

}
