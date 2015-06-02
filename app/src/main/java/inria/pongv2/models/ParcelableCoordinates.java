package inria.pongv2.models;

import android.os.Parcel;
import android.os.Parcelable;

import models.Coordinates;

/**
 * Created by Vlad on 6/2/2015.
 */
public class ParcelableCoordinates extends Coordinates implements Parcelable {

    public ParcelableCoordinates(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getX());
        parcel.writeInt(getY());
    }
}
