package inria.pongv2.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

import com.fluentinterface.ReflectionBuilder;
import com.fluentinterface.builder.Builder;

public class GyroscopeCoordinates implements Parcelable {

	private double x;
	private double y;
	private double z;
	
	public static GryoscopeCoordinatesBuilder create() {
		return ReflectionBuilder.implementationFor(GryoscopeCoordinatesBuilder.class).create();
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

	public interface GryoscopeCoordinatesBuilder extends Builder<GyroscopeCoordinates> {
		public GryoscopeCoordinatesBuilder withX(double x);
		public GryoscopeCoordinatesBuilder withY(double y);
		public GryoscopeCoordinatesBuilder withZ(double z);
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
}
