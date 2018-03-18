import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import com.google.gson.Gson;

public class RoomTest {
    public static final String ROOM_JSON = "{\n" +
            "  \"name\": \"SiebelEntry\",\n" +
            "  \"description\": \"You are in the west entry of Siebel Center.  You can see the elevator, the ACM office, and hallways to the north and east.\",\n" +
            "  \"items\": [\n" +
            "    \"sweatshirt\",\n" +
            "    \"key\"\n" +
            "  ],\n" +
            "  \"directions\": [\n" +
            "    {\n" +
            "      \"directionName\": \"West\",\n" +
            "      \"room\": \"MatthewsStreet\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"directionName\": \"Northeast\",\n" +
            "      \"room\": \"AcmOffice\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"directionName\": \"North\",\n" +
            "      \"room\": \"SiebelNorthHallway\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"directionName\": \"East\",\n" +
            "      \"room\": \"SiebelEastHallway\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    public static final String ROOM_ARRAY_JSON = "[\n" +
            "  {\n" +
            "    \"name\": \"MatthewsStreet\",\n" +
            "    \"description\": \"You are on Matthews, outside the Siebel Center\",\n" +
            "    \"items\": [\n" +
            "      \"coin\"\n" +
            "    ],\n" +
            "    \"directions\": [\n" +
            "      {\n" +
            "        \"directionName\": \"East\",\n" +
            "        \"room\": \"SiebelEntry\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"SiebelEntry\",\n" +
            "    \"description\": \"You are in the west entry of Siebel Center.  You can see the elevator, the ACM office, and hallways to the north and east.\",\n" +
            "    \"items\": [\n" +
            "      \"sweatshirt\",\n" +
            "      \"key\"\n" +
            "    ],\n" +
            "    \"directions\": [\n" +
            "      {\n" +
            "        \"directionName\": \"West\",\n" +
            "        \"room\": \"MatthewsStreet\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"directionName\": \"Northeast\",\n" +
            "        \"room\": \"AcmOffice\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"directionName\": \"North\",\n" +
            "        \"room\": \"SiebelNorthHallway\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"directionName\": \"East\",\n" +
            "        \"room\": \"SiebelEastHallway\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]";

    private static Room room;
    private static Room[] roomArray;

    private static ArrayList<Room> rooms;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        room = gson.fromJson(ROOM_JSON, Room.class);
        roomArray = gson.fromJson(ROOM_ARRAY_JSON, Room[].class);

        rooms = new ArrayList<Room>();
        for (int i = 0; i < roomArray.length; i++) {
            rooms.add(roomArray[i]);
        }
    }
    @Test
    public void getNameTest() {
        assertEquals("SiebelEntry", room.getName());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("You are in the west entry of Siebel Center.  You can see the elevator, the ACM office, and hallways to the north and east.", room.getDescription());
    }

    @Test
    public void getDirectionTest() {
        assertEquals("West", room.getDirections()[0].getDirectionName());
        assertEquals("MatthewsStreet", room.getDirections()[0].getRoom());
    }

    @Test
    public void getItemsTest() {
        assertEquals("key", room.getItems()[1]);
    }

    @Test
    public void getNameArrayTest() {
        assertEquals("MatthewsStreet", rooms.get(0).getName());

    }

    @Test
    public void getCurrDescriptionTest() {
        assertEquals("You are on Matthews, outside the Siebel Center", rooms.get(0).getDescription());

    }

    @Test
    public void getDirectionsArrayTest() {
        assertEquals("East", rooms.get(0).getDirections()[0].getDirectionName());
        assertEquals("SiebelEntry", rooms.get(0).getDirections()[0].getRoom());
    }

    @Test
    public void getItemsArrayTest() {
        assertEquals("coin", rooms.get(0).getItems()[0]);
    }

}