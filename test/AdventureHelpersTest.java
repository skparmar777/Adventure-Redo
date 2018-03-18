import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdventureHelpersTest {
    public static Layout gameLayout;
    public static String url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";


    @Before
    public void setUp() throws Exception {
        gameLayout = ParseJson.loadSimulation(url);
    }

    @Test
    public void findStartingRoom() {
        assertEquals(gameLayout.getStartingRoom(),AdventureHelpers.findStartingRoom(gameLayout).getName());
    }

    @Test
    public void takeItem() {
        String userInput = "take coin";
        String[] parsedInput = userInput.trim().toUpperCase().split(" ");
        Room currentRoom = AdventureHelpers.findStartingRoom(gameLayout);
        AdventureHelpers.takeItem(userInput, parsedInput, currentRoom);
        assertEquals(1, AdventureHelpers.myItems.size());
    }

    @Test
    public void dropItem() {
        String userInput = "take coin";
        String[] parsedInput = userInput.trim().toUpperCase().split(" ");
        Room currentRoom = AdventureHelpers.findStartingRoom(gameLayout);
        AdventureHelpers.takeItem(userInput, parsedInput, currentRoom);
        userInput = "drop coin";
        parsedInput = userInput.trim().toUpperCase().split(" ");
        AdventureHelpers.dropItem(userInput, parsedInput, currentRoom);
        assertEquals(0, AdventureHelpers.myItems.size());
    }

    @Test
    public void roomMove() {
        String userInput = "move east";
        String[] parsedInput = userInput.trim().toUpperCase().split(" ");
        Room currentRoom = AdventureHelpers.findStartingRoom(gameLayout);
        Room movedRoom = AdventureHelpers.roomMove(userInput, parsedInput,gameLayout, currentRoom);
        assertEquals(gameLayout.getRooms()[1], movedRoom);

    }
}