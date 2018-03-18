import com.google.gson.annotations.SerializedName;

public class Layout {
    @SerializedName("startingRoom")
    private String startingRoom;
    @SerializedName("endingRoom")
    private String endingRoom;
    @SerializedName("rooms")
    private Room[] rooms;
    /**
     * layout constructor
     */
    Layout(String startRoom, String endRoom, Room[] allRooms) {
        startingRoom = startRoom;
        endingRoom = endRoom;

        rooms = allRooms;
    }
    /**
     * startingRoom getter
     */
    public String getStartingRoom() {
        return startingRoom;
    }
    /**
     * endingRoom getter
     */
    public String getEndingRoom() {
        return endingRoom;
    }
    /**
     * Room array getter
     */
    public Room[] getRooms() {
        return rooms;
    }
}