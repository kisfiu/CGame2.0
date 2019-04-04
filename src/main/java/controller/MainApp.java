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
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private AnchorPane menu;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ColorGame");
        showmenu();
    }



    /**
     * Initializes the root layout.
     */
    public void showmenu() {
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
        }
        catch (IOException e) {
            e.printStackTrace();
            Logger.error("BAMM Exception :: ", e);
        }
    }


    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     *
     */
    public static void main(String[] args) {
        launch(args);
    }
}