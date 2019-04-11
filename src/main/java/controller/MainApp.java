package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

/**
 * Main app.
 * Extends application.Application
 * @author kisfiu
 */
public class MainApp extends Application {
    /**
     * Stage Variable primaryStage declares the primary stage that we are using.
     * AnchorPane Variable menu is the main menu
     */
    private Stage primaryStage; private AnchorPane menu;

     /**
     * @param primaryStage is the UI's primary stage
     */
    @Override
    public final void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ColorGame");
        showmenu();
    }



    /**
     * Initializes the root layout.
     */
    public final void showmenu() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Menu.fxml"));
            menu = (AnchorPane) loader.load();

            Logger.info("My first log entry");
            Logger.warn("Test warning.");

            // Show the scene containing the root layout.
            Scene scene = new Scene(menu);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.error("BAMM Exception :: ", e);
        }
    }


    /**
     * Returns the main stage.
     * @return primaryStage
     */
    public final Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @param args Any arguments to be included in a string representation of this event.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
