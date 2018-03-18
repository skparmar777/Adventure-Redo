import com.google.gson.annotations.SerializedName;

public class Room {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("items")
    private String[] items;
    @SerializedName("directions")
    private Direction[] directions;

    /**
     * Room constructor
     */
    public Room() {
        name = "";
        description = "";
    }
    /**
     * name getter
     */
    public String getName() {

        return name;
    }
    /**
     * description getter
     */
    public String getDescription() {
        return description;
    }
    /**
     * items array getter
     */
    public String[] getItems() {
        return items;
    }
    /**
     * direction array getter
     */
    public Direction[] getDirections() {
        return directions;
    }
    /**
     * items array setter
     */
    public void setItems(String[] items) {
        this.items = items;
    }

}