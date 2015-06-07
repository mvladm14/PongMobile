package inria.pongv2.models;

import android.os.Parcel;
import android.os.Parcelable;

import models.PhoneCoordinates;

/**
 * Created by Vlad on 6/2/2015.
 */
public class ParcelablePhoneCoords extends PhoneCoordinates implements Parcelable {

    public ParcelablePhoneCoords(double x, double y, double z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(getX());
        parcel.writeDouble(getY());
        parcel.writeDouble(getZ());
    }
}
