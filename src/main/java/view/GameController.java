package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Merge;
import model.ReadXMLFile;
import model.XmlProba;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import controller.MainApp;

import org.pmw.tinylog.Logger;


/**
 * Controller for the Game.fxml
 * How the players take turns according to the rules.
 * @author kisfiu
 */
public class GameController {
    /**
     * Buttons to create the game's field.
     */
    @FXML private Button button00; @FXML private Button button01; @FXML private Button button02; @FXML private Button button03; @FXML private Button button04; @FXML private Button button05; @FXML private Button button06;
    /**
     * Buttons to create the game's field.
     */
    @FXML private Button button10; @FXML private Button button11; @FXML private Button button12; @FXML private Button button13; @FXML private Button button14; @FXML private Button button15; @FXML private Button button16;
    /**
     * Buttons to create the game's field.
     */
    @FXML private Button button20; @FXML private Button button21; @FXML private Button button22; @FXML private Button button23; @FXML private Button button24; @FXML private Button button25; @FXML private Button button26;
    /**
     * Buttons to create the game's field.
     */
    @FXML private Button button30; @FXML private Button button31; @FXML private Button button32; @FXML private Button button33; @FXML private Button button34; @FXML private Button button35; @FXML private Button button36;
    /**
     * Buttons to create the game's field.
     */
    @FXML private Button button40; @FXML private Button button41; @FXML private Button button42; @FXML private Button button43; @FXML private Button button44; @FXML private Button button45; @FXML private Button button46;
    /**
     * Buttons to create the game's field.
     */
    @FXML private Button button50; @FXML private Button button51; @FXML private Button button52; @FXML private Button button53; @FXML private Button button54; @FXML private Button button55; @FXML private Button button56;
    /**
     * Buttons to create the game's field.
     */
    @FXML private Button button60; @FXML private Button button61; @FXML private Button button62; @FXML private Button button63; @FXML private Button button64; @FXML private Button button65; @FXML private Button button66;

    /**
     * Go back button.
     */
    @FXML
    private Button backbutton;

    /**
     * Handles the back button, on click the StartGame screen.
     * @param event is an action event for the button.
     * @throws IOException if the input is not right.
     */
    @FXML
    private void goback(final ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/StartGame.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Logger.info("Back button");
    }

    /**
     * Player one's label.
     */
    @FXML
    private Label player1Label;

    /**
     * Sets the label according to the first player.
     * @param player1Name player one's name.
     */
    public final void setName1(final String player1Name) {
        player1Label.setText(player1Name);
    }

    /**
     * Player two's label.
     */
    @FXML
    private Label player2Label;

    /**
     * Sets the label according to the second player.
     * @param player2Name player one's name.
     */
    public final void setName2(final String player2Name) {
        player2Label.setText(player2Name);
    }

    /**
     * Label for the steps.
     */
    @FXML
    private Label stepLabel;

    /**
     * Label for the first player's points.
     */
    @FXML
    private Label points1Label;

    /**
     * Label for the second player's points.
     */
    @FXML
    private Label points2Label;


    /**
     * Setting up the colors for the circles for the next player, to know which ones they can choose next.
     * @param nodeColor is for the buttons to decide the color
     */
    final void colorChooser(final Node nodeColor) {
        if (nodeColor != null) {
            Color buttonColor = (Color) ((Region) nodeColor).getBackground().getFills().get(0).getFill();
            if (buttonColor == Color.LIGHTGRAY) {
                nodeColor.setStyle("-fx-background-color: DARKSLATEGRAY");
            } else if (buttonColor == Color.VIOLET) {
                nodeColor.setStyle("-fx-background-color: DARKMAGENTA");
            } else if (buttonColor == Color.LIGHTSKYBLUE) {
                nodeColor.setStyle("-fx-background-color: MIDNIGHTBLUE");
            } else if (buttonColor == Color.TRANSPARENT) {
                nodeColor.setStyle("-fx-background-color: TRANSPARENT");
            }
        }
    }

    /**
     * It changes the color of the circles that the player on turn can choose.
     * @param node is for the buttons to decide the color
     */
    final void colorAction(final Node node) {
        Node nodeColor = (Node) node;
        String coordinates = nodeColor.getId().toString();
        String[] buttonCoordinates = coordinates.substring(coordinates.length() - 2).split("");
        int x = Integer.parseInt(buttonCoordinates[0]);
        int y = Integer.parseInt(buttonCoordinates[1]);
        String button = "#button";
        String button1, button2, button3, button4, button5, button6, button7, button8;
        button1 = button; button2 = button; button3 = button; button4 = button;
        button5 = button; button6 = button; button7 = button; button8 = button;

        button1 += (x - 1); button1 += (y - 1); button2 += (x - 1); button2 += y;
        button3 += (x - 1); button3 += (y + 1); button4 += x; button4 += (y - 1);
        button5 += x; button5 += (y + 1); button6 += (x + 1); button6 += (y - 1);
        button7 += (x + 1); button7 += y; button8 += (x + 1); button8 += (y + 1);

        Scene scene = node.getScene();

        nodeColor = (Button) scene.lookup(button1); colorChooser(nodeColor);
        nodeColor = (Button) scene.lookup(button2); colorChooser(nodeColor);
        nodeColor = (Button) scene.lookup(button3); colorChooser(nodeColor);
        nodeColor = (Button) scene.lookup(button4); colorChooser(nodeColor);
        nodeColor = (Button) scene.lookup(button5); colorChooser(nodeColor);
        nodeColor = (Button) scene.lookup(button6); colorChooser(nodeColor);
        nodeColor = (Button) scene.lookup(button7); colorChooser(nodeColor);
        nodeColor = (Button) scene.lookup(button8); colorChooser(nodeColor);
    }

    /**
     * These are counters: winning, draw, each buttons and steps.
     */
    private int winner1 = 0; private int winner2 = 0; private int draw1 = 0; private int draw2 = 0; private int reds = 0; private int blues = 0; private int steps = 0;

    /**
     * Decides if the player on turn can choose the given circle or not. They can choose the dark ones.
     * @param event is an action event for the button.
     * @throws TransformerException if an exceptional condition occurred during the transformation process.
     * @throws ParserConfigurationException indicates a serious configuration error.
     * @throws IOException if it reads an xml file that has the wrong structure
     * @throws SAXException for the XML parser
     */
    @FXML
    public final void stepPossibility(final ActionEvent event) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        Node node = (Node) event.getSource();
        ReadXMLFile.main(null);
        if (steps < 1) {
            Color color = (Color) ((Region) node).getBackground().getFills().get(0).getFill();
            if (color == Color.LIGHTGRAY) {
                node.setDisable(false);
                node.setStyle("-fx-background-color: GREENYELLOW;");
                steps = 1;
                colorAction(node);
            } else {
                node.setDisable(true);
                node.setDisable(false);
            }
        } else if (steps < 2) {
            Color color = (Color) ((Region) node).getBackground().getFills().get(0).getFill();
            if (color == Color.GREENYELLOW) {
                node.setDisable(false);
                colorAction(node);
                node.setStyle("-fx-background-color: TRANSPARENT;");
                node.setDisable(true);
                steps = 2;
            } else {
                node.setDisable(true);
                node.setDisable(false);
            }
        } else {
            Color color = (Color) ((Region) node).getBackground().getFills().get(0).getFill();
            if (color == Color.DARKSLATEGRAY) {
                int x;
                int y;
                for (x = 0; x < 7; ++x) {
                    for (y = 0; y < 7; ++y) {
                        String backButton;
                        backButton = "#button"; backButton += x; backButton += y;
                        Scene scene = node.getScene();
                        Node buttonNode = (Node) node;
                        buttonNode = (Button) scene.lookup(backButton);
                        color = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                        if (color == Color.DARKSLATEGRAY) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: LIGHTGRAY;");
                        } else if (color == Color.DARKMAGENTA) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: VIOLET;");
                        } else if (color == Color.MIDNIGHTBLUE) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: LIGHTSKYBLUE;");
                        } else if (color == Color.TRANSPARENT) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: TRANSPARENT;");
                        }
                    }
                }
                node.setDisable(false);
                node.setStyle("-fx-background-color: GREENYELLOW;");
            } else if (color == Color.DARKMAGENTA) {
                int x;
                int y;
                for (x = 0; x < 7; ++x) {
                    for (y = 0; y < 7; ++y) {
                        String backButton;
                        backButton = "#button"; backButton += x; backButton += y;
                        Scene scene = node.getScene();
                        Node buttonNode = (Node) node;
                        buttonNode = (Button) scene.lookup(backButton);
                        color = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                        if (color == Color.DARKSLATEGRAY) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: LIGHTGRAY;");
                        } else if (color == Color.DARKMAGENTA) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: VIOLET;");
                        } else if (color == Color.MIDNIGHTBLUE) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: LIGHTSKYBLUE;");
                        } else if (color == Color.TRANSPARENT) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: TRANSPARENT;");
                        }
                    }
                }
                node.setDisable(false);
                node.setStyle("-fx-background-color: GREENYELLOW;");
                reds += 1;
                String redsAll = Integer.toString(reds);
                points2Label.setText(redsAll);
            } else if (color == Color.MIDNIGHTBLUE) {
                int x;
                int y;
                for (x = 0; x < 7; ++x) {
                    for (y = 0; y < 7; ++y) {
                        String backButton;
                        backButton = "#button"; backButton += x; backButton += y;
                        Scene scene = node.getScene();
                        Node buttonNode = (Node) node;
                        buttonNode = (Button) scene.lookup(backButton);

                        color = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                        if (color == Color.DARKSLATEGRAY) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: LIGHTGRAY;");
                        } else if (color == Color.DARKMAGENTA) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: VIOLET;");
                        } else if (color == Color.MIDNIGHTBLUE) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: LIGHTSKYBLUE;");
                        } else if (color == Color.TRANSPARENT) {
                            buttonNode.setDisable(false);
                            buttonNode.setStyle("-fx-background-color: TRANSPARENT;");
                        }
                    }
                }
                node.setDisable(false);
                node.setStyle("-fx-background-color: GREENYELLOW;");
                blues += 1;
                String bluesAll = Integer.toString(blues);
                points1Label.setText(bluesAll);
            } else if (color == Color.GREENYELLOW) {
                Node buttonNode = (Node) node;
                String buttonCoordinatesY = buttonNode.getId().toString();
                String[] coordinatesButton = buttonCoordinatesY.substring(buttonCoordinatesY.length() - 2).split("");
                int xxx = Integer.parseInt(coordinatesButton[0]);
                int yyy = Integer.parseInt(coordinatesButton[1]);
                int green = 0; int red = 0; int blue = 0;
                String buttonChoosen = "#button";
                String buttonnn1, buttonnn2, buttonnn3, buttonnn4, buttonnn5, buttonnn6, buttonnn7, buttonnn8;
                buttonnn1 = buttonChoosen; buttonnn2 = buttonChoosen; buttonnn3 = buttonChoosen; buttonnn4 = buttonChoosen; buttonnn5 = buttonChoosen; buttonnn6 = buttonChoosen; buttonnn7 = buttonChoosen; buttonnn8 = buttonChoosen;

                buttonnn1 += (xxx - 1); buttonnn1 += (yyy - 1); buttonnn2 += (xxx - 1); buttonnn2 += yyy;
                buttonnn3 += (xxx - 1); buttonnn3 += (yyy + 1); buttonnn4 += xxx; buttonnn4 += (yyy - 1);
                buttonnn5 += xxx; buttonnn5 += (yyy + 1); buttonnn6 += (xxx + 1); buttonnn6 += (yyy - 1);
                buttonnn7 += (xxx + 1); buttonnn7 += yyy; buttonnn8 += (xxx + 1); buttonnn8 += (yyy + 1);
                Scene scene = node.getScene();
                buttonNode = (Button) scene.lookup(buttonnn1);
                if (buttonNode != null) {
                    Color ccolorr = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                    if (ccolorr == Color.LIGHTGRAY) {
                        green += 1;
                    } else if (ccolorr == Color.VIOLET) {
                        red += 1;
                    } else if (ccolorr == Color.LIGHTSKYBLUE) {
                        blue += 1;
                    }
                }
                buttonNode = (Button) scene.lookup(buttonnn2);
                if (buttonNode != null) {
                    Color ccolorr = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                    if (ccolorr == Color.LIGHTGRAY) {
                        green += 1;
                    } else if (ccolorr == Color.VIOLET) {
                        red += 1;
                    } else if (ccolorr == Color.LIGHTSKYBLUE) {
                        blue += 1;
                    }
                }
                buttonNode = (Button) scene.lookup(buttonnn3);
                if (buttonNode != null) {
                    Color ccolorr = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                    if (ccolorr == Color.LIGHTGRAY) {
                        green += 1;
                    } else if (ccolorr == Color.VIOLET) {
                        red += 1;
                    } else if (ccolorr == Color.LIGHTSKYBLUE) {
                        blue += 1;
                    }
                }
                buttonNode = (Button) scene.lookup(buttonnn4);
                if (buttonNode != null) {
                    Color ccolorr = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                    if (ccolorr == Color.LIGHTGRAY) {
                        green += 1;
                    } else if (ccolorr == Color.VIOLET) {
                        red += 1;
                    } else if (ccolorr == Color.LIGHTSKYBLUE) {
                        blue += 1;
                    }
                }
                buttonNode = (Button) scene.lookup(buttonnn5);
                if (buttonNode != null) {
                    Color ccolorr = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                    if (ccolorr == Color.LIGHTGRAY) {
                        green += 1;
                    } else if (ccolorr == Color.VIOLET) {
                        red += 1;
                    } else if (ccolorr == Color.LIGHTSKYBLUE) {
                        blue += 1;
                    }
                }
                buttonNode = (Button) scene.lookup(buttonnn6);
                if (buttonNode != null) {
                    Color ccolorr = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                    if (ccolorr == Color.LIGHTGRAY) {
                        green += 1;
                    } else if (ccolorr == Color.VIOLET) {
                        red += 1;
                    } else if (ccolorr == Color.LIGHTSKYBLUE) {
                        blue += 1;
                    }
                }
                buttonNode = (Button) scene.lookup(buttonnn7);
                if (buttonNode != null) {
                    Color ccolorr = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                    if (ccolorr == Color.LIGHTGRAY) {
                        green += 1;
                    } else if (ccolorr == Color.VIOLET) {
                        red += 1;
                    } else if (ccolorr == Color.LIGHTSKYBLUE) {
                        blue += 1;
                    }
                }
                buttonNode = (Button) scene.lookup(buttonnn8);
                if (buttonNode != null) {
                    Color ccolorr = (Color) ((Region) buttonNode).getBackground().getFills().get(0).getFill();
                    if (ccolorr == Color.LIGHTGRAY) {
                        green += 1;
                    } else if (ccolorr == Color.VIOLET) {
                        red += 1;
                    } else if (ccolorr == Color.LIGHTSKYBLUE) {
                        blue += 1;
                    }
                }

                /**
                 * Decides if the game could go on. No more circles means it over.
                 */
                if (green == 0 && red == 0 && blue == 0) {
                    if (reds > blues) {
                        stepLabel.setText("No more possible steps! The pink player won!");
                        winner1 = 1;
                    } else if (reds == blues) {
                        stepLabel.setText("No more possible steps! DRAW!");
                        draw1 = 1; draw2 = 1;
                    } else {
                        stepLabel.setText("No more possible steps! The blue player won!");
                        winner2 = 1;
                    }
                }

                /**
                 * Which player has collected 10 of their own circles first.
                 * That one won.
                 */
                if (reds == 10) {
                    stepLabel.setText("The pink player won!");
                    winner1 = 1;
                }
                if (blues == 10) {
                    stepLabel.setText("The blue player won!");
                    winner2 = 1;
                }

                /**
                 * If someone won then all of the circles will be 'turned off'
                 */
                if (stepLabel.getText().equals("The blue player won!")
                        ||
                        stepLabel.getText().equals("No more possible steps! The pink player won!")
                        ||
                        stepLabel.getText().equals("No more possible steps! DRAW!")
                        ||
                        stepLabel.getText().equals("The pink player won!")
                        ||
                        stepLabel.getText().equals("No more possible steps! The blue player won!")) {
                    int x;
                    int y;
                    for (x = 0; x < 7; ++x) {
                        for (y = 0; y < 7; ++y) {
                            String backButton;
                            backButton = "#button"; backButton += x; backButton += y;
                            Scene sceene = node.getScene();
                            Node buttonNode2 = (Node) node;
                            buttonNode2 = (Button) sceene.lookup(backButton);
                            buttonNode2.setDisable(true);
                        }
                    }

                    /**
                     * From integer to string so the xml database can work with the date.
                     */
                    String player1Points = Integer.toString(blues);
                    String player2Points = Integer.toString(reds);
                    String player1Winner = Integer.toString(winner1);
                    String player2Winner = Integer.toString(winner2);
                    String player1Draw = Integer.toString(draw1);
                    String player2Draw = Integer.toString(draw2);

                    /**
                     * Gives the data to the XmlProda.java so it can make the database.
                     */
                    XmlProba.setPoints1(player1Points);
                    XmlProba.setPoints2(player2Points);
                    XmlProba.setWins1(player1Winner);
                    XmlProba.setWins2(player2Winner);
                    XmlProba.setDraws1(player1Draw);
                    XmlProba.setDraws2(player2Draw);

                    /**
                     * Data for ReadXMLFile.java
                     */
                    ReadXMLFile.setPoints1(player1Points);
                    ReadXMLFile.setPoints2(player2Points);
                    ReadXMLFile.main(null);
                    try {
                        XmlProba.main(null);
                    } catch (TransformerException e1) {
                        e1.printStackTrace();
                        Logger.error("BAMM Exception :: ", e1);
                    } catch (ParserConfigurationException e1) {
                        e1.printStackTrace();
                        Logger.error("BAMM Exception :: PCE ::", e1);
                    }

                    /**
                     * Merging the two xml files to create the database.
                     */
                    Merge.calculate();
                }
                node.setDisable(false);
                colorAction(node);
                node.setStyle("-fx-background-color: TRANSPARENT;");
                node.setDisable(true);
            }
        }
    }

    /**
     * For disabling, autogenerated.
     * @param b is the parameter for this
     */
    private void setDisable(final boolean b) {
    }

    /**
     * Reference to the main application.
     */
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public GameController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp refers the main app
     */
    public final void setMainApp(final MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
