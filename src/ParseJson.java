import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Adapted from Data by zilles on 9/8/17.
 */
public class ParseJson {
    /**
     * Indicator for successful url request.
     */
    private static final int STATUS_OK = 200;


    /**
     * This function reads the contents of a file located in the project's 'data' directory into a String
     *
     * @param filename contains the name of file
     * @return a String containing the file's contents
     */
    public static String getFileContentsAsString(String filename) {

        // Java uses Paths as an operating system-independent specification of the location of files.
        // In this case, we're looking for files that are in a directory called 'data' located in the
        // root directory of the project, which is the 'current working directory'.
        final Path path = FileSystems.getDefault().getPath("data", filename);

        try {
            // Read all of the bytes out of the file specified by 'path' and then convert those bytes
            // into a Java String.  Because this operation can fail if the file doesn't exist, we
            // include this in a try/catch block
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            // Since we couldn't find the file, there is no point in trying to continue.  Let the
            // user know what happened and exit the run of the program.  Note: we're only exiting
            // in this way because we haven't talked about exceptions and throwing them in CS 126 yet.
            System.out.println("Couldn't find file: " + filename);
            System.exit(-1);
            return null;  // note that this return will never execute, but Java wants it there.
        }
    }

    /**
     * this method makes a request to load the url from the web
     *
     * @param url the url the user tries to load
     * @throws UnirestException
     * @throws MalformedURLException
     */
    static void makeApiRequest(String url) throws UnirestException, MalformedURLException {
        final HttpResponse<String> stringHttpResponse;

        // throws MalformedURLException if the url is malformed
        new URL(url);

        stringHttpResponse = Unirest.get(url).asString();
        // check to see if the request was successful
        // convert the payload JSON into Java objects
        if (stringHttpResponse.getStatus() == STATUS_OK) {
            String json = stringHttpResponse.getBody();
            Gson gson = new Gson();
            final Layout gameLayout = gson.fromJson(json, Layout.class);
            AdventureHelpers.playGame(gameLayout);
        }
    }

    public static void loadSimulation() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the Adventure Game! Enter a url to load up your custom JSON " +
                "or enter 'N' to play the Siebel simulation!");
        String url = keyboard.nextLine();

        //check if url is N for siebel simulation: if T, loads siebel simulation
        if (url.toUpperCase().equals("N")) {
            url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
        }
        //parses json into objects
        int sentinel = 0;
        while (sentinel < 1) {
            // Make an HTTP request to the above URL
            try {
                makeApiRequest(url);
                sentinel = 1;
            } catch (UnirestException e) {
                System.out.println("Network not responding. Please try again");
            } catch (MalformedURLException e) {
                System.out.println("Bad URL: " + url);
                System.out.println(("Please try another url"));
            }
            sentinel = 0;
            if (sentinel == 0) {
                url = keyboard.next();
                if (url.toUpperCase().equals("N")) {
                    String json = getFileContentsAsString("siebel.json");
                    Gson gson = new Gson();
                    final Layout gameLayout = gson.fromJson(json, Layout.class);
                    AdventureHelpers.playGame(gameLayout);
                }
            }
        }

        System.exit(0);

    }
}
