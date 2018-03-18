import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

import static org.junit.Assert.*;

public class LayoutTest {
    public static final String LAYOUT_JSON = "  {\"startingRoom\": \"MatthewsStreet\",\n" +
            "  \"endingRoom\": \"Siebel1314\",\n" +
            "  \"rooms\": [\n" +
            "    {\n" +
            "      \"name\": \"MatthewsStreet\",\n" +
            "      \"description\": \"You are on Matthews, outside the Siebel Center\",\n" +
            "      \"items\": [\"coin\"],\n" +
            "      \"directions\": [\n" +
            "        {\n" +
            "          \"directionName\": \"East\",\n" +
            "          \"room\": \"SiebelEntry\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"SiebelEntry\",\n" +
            "      \"description\": \"You are in the west entry of Siebel Center.  You can see the elevator, the ACM office, and hallways to the north and east.\",\n" +
            "\t  \"items\": [\"sweatshirt\", \"key\"],\n" +
            "      \"directions\": [\n" +
            "        {\n" +
            "          \"directionName\": \"West\",\n" +
            "          \"room\": \"MatthewsStreet\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"directionName\": \"Northeast\",\n" +
            "          \"room\": \"AcmOffice\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"directionName\": \"North\",\n" +
            "          \"room\": \"SiebelNorthHallway\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"directionName\": \"East\",\n" +
            "          \"room\": \"SiebelEastHallway\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }]}\n";

    private static Layout layout;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        layout = gson.fromJson(LAYOUT_JSON, Layout.class);
    }

    @Test
    public void getStartingRoom() {
        assertEquals("MatthewsStreet", layout.getStartingRoom());
    }

    @Test
    public void getEndingRoomTest() {
        assertEquals("Siebel1314", layout.getEndingRoom());
    }
    @Test
    public void getRoomsNameAt0Test() {
        assertEquals("MatthewsStreet", layout.getRooms()[0].getName());

    }

    @Test
    public void getRoomsNameAt1Test() {
        assertEquals("SiebelEntry", layout.getRooms()[1].getName());

    }

    @Test
    public void getRoomsDescriptionAt0Test() {
        assertEquals("You are on Matthews, outside the Siebel Center", layout.getRooms()[0].getDescription());

    }

    @Test
    public void getRoomsDescriptionAt1Test() {
        assertEquals("You are in the west entry of Siebel Center.  You can see the elevator, the ACM " +
                "office, and hallways to the north and east.", layout.getRooms()[1].getDescription());

    }

    @Test
    public void getRoomsItemTest() {
        assertEquals("sweatshirt", layout.getRooms()[1].getItems()[0]);

    }

}