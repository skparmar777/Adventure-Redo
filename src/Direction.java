import com.google.gson.annotations.SerializedName;

public class Direction {
    @SerializedName("directionName")
    private String directionName;
    @SerializedName("room")
    private String room;

    public Direction() {

    }
    public Direction(String name, String nextRoom) {
        directionName = name;
        room = nextRoom;
    }
    public String getDirectionName() {
        return directionName;
    }

    public String getRoom() {
        return room;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
