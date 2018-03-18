import java.util.Scanner;

/**
 * @author Sejal
 * This class plays the actual adventure game.
 */
public class PlayGame {
    /**
     * This is the main method that calls helper functions to create gameplay
     * @param args arguments taken
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the Adventure Game! Enter a url to load up your custom JSON " +
                "or enter 'N' to play the Siebel simulation!");
        String url = keyboard.nextLine();

        //check if url is N for siebel simulation: if T, loads siebel simulation
        if (url.toUpperCase().equals("N")) {
            url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
        }
        Layout gameLayout = ParseJson.loadSimulation(url);

        AdventureHelpers.playGame(gameLayout);

    }
}