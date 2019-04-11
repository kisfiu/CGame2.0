package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ReadXMLFile;
import model.XmlProba;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import controller.MainApp;
import org.pmw.tinylog.Logger;


/**
 * Controller for the StartGame.fxml, the game's starting page.
 */
public class StartGameController {
    /**
     *  Button for starting the game.
     */
    @FXML
    private Button letsGoButton;

    /**
     * If we start the game, then it sets up the labels.
     * @throws IOException if the input is not right.
     */
    @FXML
    public final void okClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));
        Parent root = loader.load();
        GameController gamecontroller = loader.getController();
        gamecontroller.setName1(playeronetext.getText());
        gamecontroller.setName2(playertwotext.getText());
        XmlProba.setName1(playeronetext.getText());
        XmlProba.setName2(playertwotext.getText());
        ReadXMLFile.settName1(playeronetext.getText());
        ReadXMLFile.settName2(playertwotext.getText());

        try {
            XmlProba.main(null);
        } catch (TransformerException e) {
            e.printStackTrace();
            Logger.error("BAMM Exception :: ", e);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            Logger.error("BAMM Exception :: PCE ::", e);
        }

        /**
         * Players can not play with the same name.
         */
        if (playeronetext.getText().isEmpty() || playertwotext.getText().isEmpty() || playeronetext.getText().equals(playertwotext.getText())) {
            letsGoButton.setDisable(true);
            nameslabel.setText("Hey thats not good");
            letsGoButton.setDisable(false);
        } else {
            letsGoButton.setDisable(false);    letsGoButton.getScene().setRoot(root);
        }
    }

    /**
     * Button for going back.
     */
    @FXML
    private Button gobackbutton;
    /**
     * Handles the Back button, to return to the menu.
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

    /**
     * Labels and texts for the players.
     */
    @FXML private Label nameslabel; @FXML private Label playeronelabel; @FXML private Label playertwolabel; @FXML private TextField playeronetext; @FXML private TextField playertwotext;


    /**
     * Reference to the main application.
     */
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public StartGameController() {
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
