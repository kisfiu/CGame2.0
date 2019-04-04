package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import controller.MainApp;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.pmw.tinylog.Logger;



/**
 * Controller for the Menu.fxml, for the menu in the game.
 *
 */
public class MenuController
{
	@FXML
	private Button startbutton;
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException 
	{		
		Node node=(Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/StartGame.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	
	@FXML
	private Button toplistbutton;

	/**
	 * Sets up the Toplist button
	 */
	@FXML
	private void handletoplistbutton(ActionEvent event) throws IOException, TransformerException, ParserConfigurationException, SAXException 
	{
//    	SetToplist.main(null);
		Node node=(Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/Toplist.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	
	
	@FXML
	private Label elso;	
	@FXML
	private Label masodik;
	@FXML
	private Text texxt;
	@FXML
	private Button rulesbutton;

	/**
	 * Sets up the Rules button
	 */
	@FXML
	private void handlerulesbutton(ActionEvent e) throws IOException
	{
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/Rules.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
//		Node node=(Node) e.getSource();
//		Scene scene = node.getScene();
//		Node noode=(Node) node;
//		String sajt = "toplistbutton";
//		noode= scene.lookup("#toplistbutton");
//		noode.setDisable(true);
	}

	/**
	 *
	 */
	// Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MenuController() {}

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}