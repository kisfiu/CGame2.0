package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import controller.MainApp;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


/**
 * Controller for the Menu.fxml, for the menu in the game.
 *
 */
public class MenuController {

    /**
     * Button for starting the game.
     */
    @FXML
    private Button startButton;

    /**
     * Handles the start game button.
     * @param event is an action event for the button
     * @throws IOException if the input is not right.
     */
    @FXML
    private void handleButtonAction(final ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/StartGame.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button for the top list.
     */
    @FXML
    private Button toplistButton;

    /**
     * Sets up the Toplist button.
     * @param event  action event for the button
     * @throws IOException if the input is not right.
     */
    @FXML
    private void handletoplistbutton(final ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Toplist.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Labels for the players. Text fot the labels. And the Rules button.
     */
    @FXML private Label firstLabel; @FXML private Label secondLabel; @FXML private Text textLabel; @FXML private Button rulesButton;

    /**
     * Sets up the Rules button.
     * @param e is an action event for the button.
     * @throws IOException if the input is not right.
     */
    @FXML
    private void handlerulesbutton(final ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Rules.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Reference to the main application.
     */
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MenuController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp refers to the main app
     */
    public final void setMainApp(final MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
