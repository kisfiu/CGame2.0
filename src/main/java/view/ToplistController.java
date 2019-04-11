package view;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.pmw.tinylog.Logger;


/**
 * Controller for the Toplist.fxml, for the top list of the game. It shows the top 10 players and their points (in order).
 */
public class ToplistController {
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
     * Labels for the names.
     */
    @FXML private Label name1; @FXML private Label name6; @FXML private Label name2; @FXML private Label name7; @FXML private Label name3; @FXML private Label name8; @FXML private Label name4; @FXML private Label name9; @FXML private Label name5; @FXML private Label name10;

    /**
     * Labels for the points.
     */
    @FXML private Label points1; @FXML private Label points6; @FXML private Label points2; @FXML private Label points7; @FXML private Label points3; @FXML private Label points8; @FXML private Label points4; @FXML private Label points9; @FXML private Label points5; @FXML private Label points10;

    /**
     * Button for the top list.
     */
    @FXML
    private Button showTopListButton;

    /**
     * Handles the Let's see button. to reveal the toplist.
     * @throws ParserConfigurationException indicates a serious configuration error.
     * @throws IOException if it reads an xml file that has the wrong structure
     * @throws SAXException for the XML parser
     */
    @FXML
    public final void showTopList() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setIgnoringComments(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(new File("src/main/java/model/merged.xml"));
        NodeList nodes = doc.getElementsByTagName("player");

        int[] arrayForThePoints = new int[nodes.getLength()];
        int[] pointsOriginal = new int[nodes.getLength()];
        String[] arrayForTheNames = new String[nodes.getLength()];
        String[] namesOriginal = new String[nodes.getLength()];
        String pointsFromMerged1;
        String namesFromMerged;
        int pointsFromMerged;
        int k = 0;

        /**
         * Sets up the names
         */
        for (int j = 0; j < nodes.getLength(); j = j + 1) {
            org.w3c.dom.Node mergedItems = nodes.item(j);
            Element mergedAttributes = (Element) mergedItems;
            String mergedName = mergedAttributes.getAttribute("name");
            Logger.info(mergedName);
            pointsFromMerged1 = mergedAttributes.getElementsByTagName("points").item(0).getTextContent();
            namesFromMerged = mergedAttributes.getAttribute("name");
            arrayForTheNames[k] = namesFromMerged;
            namesOriginal[k] = namesFromMerged;

            pointsFromMerged = Integer.parseInt(pointsFromMerged1);
            arrayForThePoints[k] = pointsFromMerged;
            pointsOriginal[k] = pointsFromMerged;
            k++;
        }
        Arrays.sort(arrayForThePoints);
        Logger.info(Arrays.toString(arrayForThePoints));

        /**
         * Sets up the points for the names
         */
        for (int i = 0; i < arrayForThePoints.length / 2; i++) {
            int temp = arrayForThePoints[i];
            arrayForThePoints[i] = arrayForThePoints[arrayForThePoints.length - i - 1];
            arrayForThePoints[arrayForThePoints.length - i - 1] = temp;
        }
        Logger.info(Arrays.toString(arrayForThePoints));
        Logger.info(Arrays.toString(pointsOriginal));

        String pont1 = Integer.toString(arrayForThePoints[0]);
        this.points1.setText(pont1);
        String pont2 = Integer.toString(arrayForThePoints[1]);
        this.points2.setText(pont2);
        String pont3 = Integer.toString(arrayForThePoints[2]);
        this.points3.setText(pont3);
        String pont4 = Integer.toString(arrayForThePoints[3]);
        this.points4.setText(pont4);
        String pont5 = Integer.toString(arrayForThePoints[4]);
        this.points5.setText(pont5);
        String pont6 = Integer.toString(arrayForThePoints[5]);
        this.points6.setText(pont6);
        String pont7 = Integer.toString(arrayForThePoints[6]);
        this.points7.setText(pont7);
        String pont8 = Integer.toString(arrayForThePoints[7]);
        this.points8.setText(pont8);
        String pont9 = Integer.toString(arrayForThePoints[8]);
        this.points9.setText(pont9);
        String pont10 = Integer.toString(arrayForThePoints[9]);
        this.points10.setText(pont10);

        /**
         * Sets up the points from the database
         */
        int l = 0;
        for (int j = 0; j < nodes.getLength(); j = j + 1) {
            org.w3c.dom.Node mergedNode = nodes.item(j);
            Element mergedElement = (Element) mergedNode;
            String mergedFromPoints = mergedElement.getElementsByTagName("points").item(0).getTextContent();
            for (int i = 0; i < nodes.getLength(); i = i + 1) {
                String mergedName = mergedElement.getAttribute("name");
                if (mergedFromPoints.equals(Integer.toString(arrayForThePoints[i]))) {
                    arrayForTheNames[i] = mergedName;
                    l++;
                }
            }
        }

        /**
         * Setting up the top list from the array.
         */
        String nev1 = (arrayForTheNames[0]);
        this.name1.setText(nev1);
        String nev2 = (arrayForTheNames[1]);
        this.name2.setText(nev2);
        String nev3 = (arrayForTheNames[2]);
        this.name3.setText(nev3);
        String nev4 = (arrayForTheNames[3]);
        this.name4.setText(nev4);
        String nev5 = (arrayForTheNames[4]);
        this.name5.setText(nev5);
        String nev6 = (arrayForTheNames[5]);
        this.name6.setText(nev6);
        String nev7 = (arrayForTheNames[6]);
        this.name7.setText(nev7);
        String nev8 = (arrayForTheNames[7]);
        this.name8.setText(nev8);
        String nev9 = (arrayForTheNames[8]);
        this.name9.setText(nev9);
        String nev10 = (arrayForTheNames[9]);
        this.name10.setText(nev10);

        Logger.info(Arrays.toString(arrayForTheNames));
        Logger.info("\n\n_________________________toplistcreated_________________________");
    }
}
