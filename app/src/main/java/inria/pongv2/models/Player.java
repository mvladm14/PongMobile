package inria.pongv2.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

import com.fluentinterface.ReflectionBuilder;
import com.fluentinterface.builder.Builder;

public class Player implements Parcelable {

	private long id;
	private String username;
	private GyroscopeCoordinates gyroscopeCoordinates;

	public static PlayerBuilder create() {
		return ReflectionBuilder.implementationFor(PlayerBuilder.class)
				.create();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeLong(id);
		parcel.writeString(username);
	}


    public static final Parcelable.Creator<Player> CREATOR
            = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    private Player(Parcel in) {
        id = in.readLong();
        username = in.readString();
    }



    public interface PlayerBuilder extends Builder<Player> {
		public PlayerBuilder withUsername(String username);

		public PlayerBuilder withGyroscopeCoordinates(
				GyroscopeCoordinates gyroscopeCoordinates);
		
		public PlayerBuilder withId(long id);
	}

	public GyroscopeCoordinates getGyroscopeCoordinates() {
		return gyroscopeCoordinates;
	}

	public void setGyroscopeCoordinates(
			GyroscopeCoordinates gyroscopeCoordinates) {
		this.gyroscopeCoordinates = gyroscopeCoordinates;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUsername(), getGyroscopeCoordinates());
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof GyroscopeCoordinates)
				&& Objects.equals(getUsername(), ((Player) obj).getUsername())
				&& Objects.equals(getGyroscopeCoordinates(),
						((Player) obj).getGyroscopeCoordinates());
	}
}
