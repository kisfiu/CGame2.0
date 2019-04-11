package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * Controller for the Rules.fxml, for the Rules page.
 * @author kisfiu
 */
public class RulesController {

    /**
     * Button functioning as going back to the menu.
     */
    @FXML
    private Button backButton;

    /**
     * Go back button, if someone clicks on it, then they will return to the menu of the game.
     * @param event is an action event for the button.
     * @throws IOException if the input is not right.
     */
    @FXML
    private void handBackto(final ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
