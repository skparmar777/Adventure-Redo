import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import com.google.gson.Gson;

public class DirectionTest {
    public static final String DIRECTION_JSON = "{\n" +
            "          \"directionName\": \"West\",\n" +
            "          \"room\": \"MatthewsStreet\"\n" +
            "        }";
    public static final String DIRECTION_ARRAY_JSON = "[\n" +
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
            "      ]";

    private static Direction direction;
    private static Direction[] directionArray;


    private static ArrayList<Direction> directions;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        direction = gson.fromJson(DIRECTION_JSON, Direction.class);
        directionArray = gson.fromJson(DIRECTION_ARRAY_JSON, Direction[].class);

        directions = new ArrayList<>();
        for (int i = 0; i < directionArray.length; i++) {
            directions.add(directionArray[i]);
        }
    }
    @Test
    public void getDirectionNameTest() {
        assertEquals("West", direction.getDirectionName());
    }

    @Test
    public void getRoomTest() {
        assertEquals("MatthewsStreet", direction.getRoom());
    }

    @Test
    public void getDirectionArrayAt0Test() {

        assertEquals("West", directions.get(0).getDirectionName());
    }

    @Test
    public void getDirectionArrayAt1Test() {

        assertEquals("Northeast", directions.get(1).getDirectionName());
    }

    @Test
    public void getDirectionArrayAt2Test() {

        assertEquals("North", directions.get(2).getDirectionName());
    }

    @Test
    public void getDirectionArrayAt3Test() {

        assertEquals("East", directions.get(3).getDirectionName());
    }

    @Test
    public void getRoomArrayAt0Test() {
        assertEquals("MatthewsStreet", directions.get(0).getRoom());
    }
    @Test
    public void getRoomArrayAt1Test() {
        assertEquals("AcmOffice", directions.get(1).getRoom());
    }
    @Test
    public void getRoomArrayAt2Test() {
        assertEquals("SiebelNorthHallway", directions.get(2).getRoom());
    }
    @Test
    public void getRoomArrayAt3Test() {
        assertEquals("SiebelEastHallway", directions.get(3).getRoom());
    }


}