import com.google.gson.annotations.SerializedName;

public class Direction {
    @SerializedName("directionName")
    private String directionName;
    @SerializedName("room")
    private String room;

    /**
     * direction name getter
     */
    public String getDirectionName() {
        return directionName;
    }
    /**
     * Room getter
     */
    public String getRoom() {
        return room;
    }

}
