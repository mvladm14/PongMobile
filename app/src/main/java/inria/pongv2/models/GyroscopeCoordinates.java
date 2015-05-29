package inria.pongv2.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class GyroscopeCoordinates implements Parcelable {

    private double x;
    private double y;
    private double z;

    public GyroscopeCoordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static final Parcelable.Creator<GyroscopeCoordinates> CREATOR
            = new Parcelable.Creator<GyroscopeCoordinates>() {
        public GyroscopeCoordinates createFromParcel(Parcel in) {
            return new GyroscopeCoordinates(in);
        }

        public GyroscopeCoordinates[] newArray(int size) {
            return new GyroscopeCoordinates[size];
        }
    };

    private GyroscopeCoordinates(Parcel in) {
        x = in.readLong();
        y = in.readLong();
        z = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(x);
        parcel.writeDouble(y);
        parcel.writeDouble(z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof GyroscopeCoordinates)
                && Objects.equals(getX(), ((GyroscopeCoordinates) obj).getX())
                && Objects.equals(getY(), ((GyroscopeCoordinates) obj).getY())
                && Objects.equals(getZ(), ((GyroscopeCoordinates) obj).getZ());
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y + " z: " + z;
    }
}
