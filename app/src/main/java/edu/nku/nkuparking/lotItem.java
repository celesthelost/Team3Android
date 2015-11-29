package edu.nku.nkuparking;



import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

    public class lotItem implements   Parcelable {

        protected String idLot;
        protected String LotName;

        protected String Latitude;
        protected String Longitude;


        public String getidLot() {
            return idLot;
        }
        public void setidLot(String name) {
            this.idLot = idLot;
        }
        public String getLotName() {
            return LotName;
        }
        public void setLotName(String LotName) {
            this.LotName = LotName;
        }
        public String getLatitude() {
            return Latitude;
        }
        public void setLatitude(String Latitude) {
            this.Latitude = Latitude;
        }
        public String getLongitude() {
            return Longitude;
        }
        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }
        protected lotItem(){}

        protected lotItem(Parcel in) {
            idLot = in.readString();
            LotName = in.readString();
            Latitude = in.readString();
            Longitude = in.readString();

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(idLot);
            dest.writeString(LotName);
            dest.writeString(Latitude);
            dest.writeString(Longitude);

        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<lotItem> CREATOR = new Parcelable.Creator<lotItem>() {
            @Override
            public lotItem createFromParcel(Parcel in) {
                return new lotItem(in);
            }

            @Override
            public lotItem[] newArray(int size) {
                return new lotItem[size];
            }
        };

        }

