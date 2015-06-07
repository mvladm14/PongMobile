package inria.pongv2.models;

import android.os.Parcel;
import android.os.Parcelable;

import inria.pongv2.utils.Converter;
import models.PhoneCoordinates;
import models.Player;

/**
 * Created by Vlad on 6/2/2015.
 */
public class ParcelablePlayer extends Player implements Parcelable {

    public ParcelablePlayer(long id, String username, PhoneCoordinates phoneCoordinates) {
        this.setId(id);
        this.setUsername(username);
        this.setPhoneCoordinates(phoneCoordinates);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(getId());
        parcel.writeString(getUsername());
        parcel.writeParcelable(Converter.convertGyroCoordsToParcelableGyroCoords(getPhoneCoordinates()), i);
    }
}
