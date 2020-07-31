package com.tabletennissimulator;

import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * JavaFX Table Tennis Tournament Simulator
 */
public class App extends Application {


    /**
     * The window ('scene') holding the application
     */
    private Scene window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL url = null;
        Parent root = null;

        try
        {
            url  = getClass().getResource( "/App.fxml" );
            root = FXMLLoader.load( url );
            System.out.println( "  fxmlResource = " + "App.fxml");
        }
        catch ( Exception ex ) {
            System.out.println("Exception on FXMLLoader.load()");
            System.out.println("  * url: " + url);
            System.out.println("  * " + ex);
            System.out.println("    ----------------------------------------\n");
            throw ex;
        }

        primaryStage.setTitle("Table Tennis Simulator");

        window = new Scene(root);

        primaryStage.setScene(window);

        primaryStage.show();
    }

    /**
     * Gets the players
     * @return the list of players found in the
     * @throws FileNotFoundException Throws if the JSON file could not be found
     */
    public static ArrayList<TableTennisPlayer> getPlayers() throws FileNotFoundException {

        // Use the Gson Builder to only serialize fields with the Expose annotation
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        JsonReader jsonReader;

        try {
            jsonReader = new JsonReader(new FileReader("src/main/data/players-attributes.json"));
        }
        catch (FileNotFoundException e) {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            System.out.println("Current relative path is: " + s);
            throw e;
        }

        List<TableTennisPlayer> foundPlayers = Arrays.asList(gson.fromJson(jsonReader, TableTennisPlayer[].class));
        foundPlayers.sort(Comparator.comparingInt(TableTennisPlayer::getId));

        return new ArrayList(foundPlayers);
    }



}