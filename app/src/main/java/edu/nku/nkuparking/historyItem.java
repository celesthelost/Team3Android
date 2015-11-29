package edu.nku.nkuparking;



import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class historyItem implements   Parcelable {

    protected String idHistory;
    protected String SpotName;

    protected String User;
    protected String active;
    protected String StartTime;
    protected String EndTime;
    protected String LotName;


    public String getidHistory() {
        return idHistory;
    }
    public void setidHistory(String idHistory) {
        this.idHistory = idHistory;
    }
    public String getSpotName() {
        return SpotName;
    }
    public void setSpotName(String SpotName) {
        this.SpotName = SpotName;
    }
    public String getUser() {
        return User;
    }
    public void setUser(String User) {
        this.User = User;
    }
    public String getactive() {
        return active;
    }
    public void setactive(String active) {
        this.active = active;
    }
    public String getStartTime() {
        return StartTime;
    }
    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }
    public String getEndTime() {
        return EndTime;
    }
    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public String getLotName() {
        return LotName;
    }
    public void setLotName(String LotName) {
        this.LotName = LotName;
    }

    protected historyItem(){}

    protected historyItem(Parcel in) {
        idHistory = in.readString();
        SpotName = in.readString();
        User = in.readString();
        active = in.readString();
        StartTime = in.readString();
        EndTime = in.readString();
        LotName = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idHistory);
        dest.writeString(SpotName);
        dest.writeString(User);
        dest.writeString(active);
        dest.writeString(StartTime);
        dest.writeString(EndTime);
        dest.writeString(LotName);

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<historyItem> CREATOR = new Parcelable.Creator<historyItem>() {
        @Override
        public historyItem createFromParcel(Parcel in) {
            return new historyItem(in);
        }

        @Override
        public historyItem[] newArray(int size) {
            return new historyItem[size];
        }
    };

}
