import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Sejal K. Parmar
 * netid: sejalkp2
 * This class contains the methods that create the gameplay for the Adventure game
 */

public class AdventureHelpers {
    //make arrayList to hold items that user picks up
    private static ArrayList<String> myItems;

    /**
     * This method holds the actual gameplay for the Adventure game
     *
     * @param gameLayout the layout/map to take in
     */
    public static void playGame(Layout gameLayout) {
        Scanner keyboard = new Scanner(System.in);
        myItems = new ArrayList<>();

        //set currentRoom to starting room before gameplay begins
        Room currentRoom = findStartingRoom(gameLayout);

        boolean gameIsPlaying = true;

        while (gameIsPlaying) {
            printCurrentEnvironmentMessage(currentRoom, gameLayout);

            String userInput = keyboard.nextLine();

            //check to see if user typed quit or exit, ends game
            checkEndGame(userInput);

            String[] parsedInput = userInput.trim().toUpperCase().split(" ");

            //check to see if user typed keyword, do action
            switch (parsedInput[0]) {
                case "GO": {
                    Room move = roomMove(userInput, parsedInput, gameLayout, currentRoom);
                    if (move != null) {
                        currentRoom = move;
                    }
                    break;
                }
                case "TAKE": {
                    Room itemTaken = takeItem(userInput, parsedInput, currentRoom);
                    if (itemTaken != null) {
                        currentRoom = itemTaken;
                    }
                    break;
                }
                case "DROP": {
                    Room itemDropped = dropItem(userInput, parsedInput, currentRoom);
                    if (itemDropped != null) {
                        currentRoom = itemDropped;
                    }
                    break;
                }
                case "LIST": {
                    listUserItems();
                    break;
                }
                default: {
                    System.out.println("I don't understand '" + userInput + "'");

                }

            }
        }
    }

    /**
     * finds starting room for game and sets it to current room.
     *
     * @param gameLayout layout of game
     * @return currentRoom
     */
    public static Room findStartingRoom(Layout gameLayout) {
        Room currentRoom = gameLayout.getRooms()[0];

        String startingRoom = gameLayout.getStartingRoom();

        for (Room room : gameLayout.getRooms()) {
            if (room.getName().equals(startingRoom)) {
                currentRoom = room;
                break;
            }
        }
        return currentRoom;
    }

    /**
     * This method checks if the game is over.
     *
     * @param userInput the user's input.
     */
    public static void checkEndGame(String userInput) {
        if (userInput.trim().equalsIgnoreCase("QUIT") ||
                userInput.trim().equalsIgnoreCase("EXIT")) {
            System.exit(0);
        }
    }

    /**
     * This method prints the current location's description and assets
     *
     * @param currentRoom the room the user is in
     * @param gameLayout  a layout object
     */
    public static void printCurrentEnvironmentMessage(Room currentRoom, Layout gameLayout) {
        System.out.println(currentRoom.getDescription());

        if (currentRoom.getName().equals(gameLayout.getStartingRoom())) {
            System.out.println("Your journey begins here");
        }
        if (currentRoom.getName().equals(gameLayout.getEndingRoom())) {
            System.out.println("You have reached your final destination");
            System.exit(0);
        }

        displayItems(currentRoom);

        displayDirections(currentRoom);
    }

    /**
     * takes item if valid, otherwise returns null
     *
     * @param userInput   full user input
     * @param parsedInput input parsed into array
     * @param currentRoom room
     * @return currentRoom w/ item removed
     */
    public static Room takeItem(String userInput, String[] parsedInput, Room currentRoom) {
        boolean takeItemCheck = false;
        ArrayList<String> currRoomItems = new ArrayList<>(Arrays.asList(currentRoom.getItems()));

        for (int itemIndex = 0; itemIndex < currRoomItems.size(); itemIndex++) {
            if (currRoomItems.get(itemIndex).toUpperCase().contains(parsedInput[1].toUpperCase())) {
                //add item to myList
                myItems.add(currentRoom.getItems()[itemIndex]);
                //remove item from getItems by setting that item to empty string
                currRoomItems.remove(itemIndex);
                currentRoom.setItems(currRoomItems.toArray(new String[currRoomItems.size()]));
                return currentRoom;
            }
        }
        if (!takeItemCheck) {
            System.out.println("I can't take " + userInput.trim().split(" ")[1]);
            return null;
        }

        return null;
    }

    /**
     * drops item if valid, otherwise returns null
     *
     * @param userInput   full user input
     * @param parsedInput input parsed into array
     * @param currentRoom room
     * @return currentRoom w/ item added
     */
    public static Room dropItem(String userInput, String[] parsedInput, Room currentRoom) {
        boolean dropItemCheck = false;

        ArrayList<String> currRoomItems = new ArrayList<>(Arrays.asList(currentRoom.getItems()));

        for (int itemIndex = 0; itemIndex < myItems.size(); itemIndex++) {
            if (parsedInput[1].trim().equalsIgnoreCase(myItems.get(itemIndex))) {
                //add item to currRoomItems
                currRoomItems.add(myItems.get(itemIndex));
                //remove item from myList
                myItems.remove(myItems.get(itemIndex));

                currentRoom.setItems(currRoomItems.toArray(new String[currRoomItems.size()]));
                return currentRoom;
            }
        }
        if (!dropItemCheck) {
            System.out.println("I can't drop " + userInput.trim().split(" ")[1]);
            return null;
        }
        return null;
    }

    /**
     * This method is a helper that displays the possible directions from a room
     *
     * @param currentRoom the room the user is in
     */
    public static void displayDirections(Room currentRoom) {
        if (currentRoom.getDirections() == null) {
            System.out.println("You can't go anywhere.");
            return;
        } else {
            System.out.print("From here, you can go: ");
            for (Direction direction : currentRoom.getDirections()) {
                if (currentRoom.getDirections().length == 1) {
                    System.out.print(direction.getDirectionName());
                    System.out.print('\n');
                } else {
                    System.out.print(direction.getDirectionName() + ", ");
                }
                String lastDirection = currentRoom.getDirections()[currentRoom.getDirections().length - 1].getDirectionName();
                if ((lastDirection.equals(direction.getDirectionName()) && (currentRoom.getDirections().length != 1))) {
                    System.out.print('\n');
                }
            }
        }
    }

    /**
     * This method is a helper that displays the items in a room
     *
     * @param currentRoom the room the user is in
     */
    public static void displayItems(Room currentRoom) {
        if (currentRoom.getItems() == null || currentRoom.getItems().length == 0) {
            System.out.println("This room does not contain anything.");
            return;
        } else {
            System.out.print("This room contains: ");
            for (String item : currentRoom.getItems()) {
                if (currentRoom.getItems().length == 1) {
                    System.out.print(item);
                    System.out.print('\n');
                } else {
                    System.out.print(item + ", ");
                }
                String lastItem = currentRoom.getItems()[currentRoom.getItems().length - 1];
                if ((lastItem.equalsIgnoreCase(item) && (currentRoom.getItems().length != 1))) {
                    System.out.print('\n');
                }
            }
        }
    }

    /**
     * moves room if valid, otherwise returns null.
     *
     * @param userInput   full user input
     * @param parsedInput input parsed into array
     * @param gameLayout  layout obj of game
     * @param currentRoom room
     * @return currentRoom
     */
    public static Room roomMove(String userInput, String[] parsedInput, Layout gameLayout, Room currentRoom) {
        boolean roomMoveCheck = false;

        for (Direction direction : currentRoom.getDirections()) {
            if (parsedInput[1].equalsIgnoreCase(direction.getDirectionName())) {
                String nextRoom = direction.getRoom();
                for (Room room : gameLayout.getRooms()) {
                    if (room.getName().equals(nextRoom.trim())) {
                        currentRoom = room;
                        return currentRoom;
                    }
                }
            }
        }
        if (!roomMoveCheck) {
            System.out.println("I can't go " + userInput.trim().split(" ")[1]);
            return null;
        }
        return null;
    }

    /**
     * lists out all of the user's items.
     */
    public static void listUserItems() {
        if (myItems.size() == 0) {
            System.out.println("You aren't carrying anything.");
        } else {
            System.out.print("You are carrying: ");
            for (int i = 0; i < myItems.size(); i++) {
                if (myItems.size() == 0) {
                    System.out.println("nothing");
                } else if (myItems.size() == 1) {
                    System.out.print(myItems.get(i));
                    System.out.print('\n');
                } else {
                    System.out.print(myItems.get(i) + ", ");
                }
                String lastItem = myItems.get(myItems.size() - 1);
                if ((lastItem.equalsIgnoreCase(myItems.get(i)) && myItems.size() != 1)) {
                    System.out.print('\n');
                }
            }
        }
    }
}
