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


/**
 * Controller for the Game.fxml
 * How the players take turns according to the rules.
 */
public class GameController
{
	@FXML	private Button button00; @FXML  private Button button01;
	@FXML	private Button button02; @FXML 	private Button button03;
	@FXML 	private Button button04; @FXML 	private Button button05;
	@FXML 	private Button button06; 
	@FXML	private Button button10; @FXML  private Button button11;
	@FXML	private Button button12; @FXML 	private Button button13;
	@FXML 	private Button button14; @FXML 	private Button button15;
	@FXML 	private Button button16;
	@FXML	private Button button20; @FXML  private Button button21;
	@FXML	private Button button22; @FXML 	private Button button23;
	@FXML 	private Button button24; @FXML 	private Button button25;
	@FXML 	private Button button26;
	@FXML	private Button button30; @FXML  private Button button31;
	@FXML	private Button button32; @FXML 	private Button button33;
	@FXML 	private Button button34; @FXML 	private Button button35;
	@FXML 	private Button button36;
	@FXML	private Button button40; @FXML  private Button button41;
	@FXML	private Button button42; @FXML 	private Button button43;
	@FXML 	private Button button44; @FXML 	private Button button45;
	@FXML 	private Button button46;
	@FXML	private Button button50; @FXML  private Button button51;
	@FXML	private Button button52; @FXML 	private Button button53;
	@FXML 	private Button button54; @FXML 	private Button button55;
	@FXML 	private Button button56;
	@FXML	private Button button60; @FXML  private Button button61;
	@FXML	private Button button62; @FXML 	private Button button63;
	@FXML 	private Button button64; @FXML 	private Button button65;
	@FXML 	private Button button66;
	
	//go back button
	@FXML
	private Button backbutton;
	@FXML
	private void goback(ActionEvent event) throws IOException 
	{		
		Node node=(Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/StartGame.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private Label playeronelabel;
	public void setName(String playeronetext)    {playeronelabel.setText(playeronetext);}
	@FXML
	private Label playertwolabel;
	public void ssetName(String playertwotext)    {playertwolabel.setText(playertwotext);}
	@FXML Label lepeslabel;
	@FXML
	private Label onepointslabel;
	@FXML
	private Label twopointslabel;


	/**
	 * Setting up the colors for the circles for the next player, to know which ones they can choose next.
	 */
	void szinvalaszto(Node noode)
	{
		if (noode != null) 
		{
			Color ccolor = (Color)((Region) noode).getBackground().getFills().get(0).getFill();
		    if (ccolor == Color.LIGHTGRAY) {noode.setStyle("-fx-background-color: DARKSLATEGRAY");}
		    else if (ccolor == Color.VIOLET) {noode.setStyle("-fx-background-color: DARKMAGENTA");}
		    else if (ccolor == Color.LIGHTSKYBLUE) {noode.setStyle("-fx-background-color: MIDNIGHTBLUE");}
		    else if (ccolor == Color.TRANSPARENT) {noode.setStyle("-fx-background-color: TRANSPARENT");}
		}
	}

	/**
	 * It changes the color of the circles that the player on turn can choose
	 */
	void szinezzunk(Node node)
	{
		Node noode=(Node) node;
		String coordinates = noode.getId().toString();
		String[] coordinatess = coordinates.substring(coordinates.length()-2).split("");
		int x = Integer.parseInt(coordinatess[0]);
		int y = Integer.parseInt(coordinatess[1]);
		String button = "#button";
		String button1, button2, button3, button4, button5, button6, button7, button8;
		button1 = button; button2 = button; button3 = button; button4 = button; button5 = button; button6 = button; button7 = button; button8 = button;      

		button1 += (x-1); button1 += (y-1); button2 += (x-1); button2 += y;
		button3 += (x-1); button3 += (y+1); button4 += x; button4 += (y-1);
		button5 += x; button5 += (y+1);		button6 += (x+1); button6 += (y-1);
		button7 += (x+1); button7 += y; 	button8 += (x+1); button8 += (y+1);
		
		Scene scene = node.getScene();
		
		noode =(Button) scene.lookup(button1); szinvalaszto(noode);
		noode =(Button) scene.lookup(button2); szinvalaszto(noode);
		noode =(Button) scene.lookup(button3); szinvalaszto(noode);
		noode =(Button) scene.lookup(button4); szinvalaszto(noode);
		noode =(Button) scene.lookup(button5); szinvalaszto(noode);
		noode =(Button) scene.lookup(button6); szinvalaszto(noode);
		noode =(Button) scene.lookup(button7); szinvalaszto(noode);
		noode =(Button) scene.lookup(button8); szinvalaszto(noode);
	}

	/**
	 *
	 */
	public int nyertes1 = 0;
	public int nyertes2 = 0;
	public int draw1 = 0;
	public int draw2 = 0;
	public int osszpiros = 0;
	public int osszkek = 0;
	public int lepes = 0;

	/**
	 * Decides if the player on turn can choose the given circle or not. They can choose the dark ones.
	 */
	@FXML
	public void lepeslehetoseg(ActionEvent e) throws InterruptedException, TransformerException, ParserConfigurationException, IOException, SAXException
	{
		Node node=(Node) e.getSource();
		ReadXMLFile.main(null);
		if(lepes<1)
		{
			Color color = (Color)((Region) node).getBackground().getFills().get(0).getFill();
		    if (color == Color.LIGHTGRAY)
		    {
				node.setDisable(false);
				node.setStyle("-fx-background-color: GREENYELLOW;");
				lepes = 1;
				szinezzunk(node);
		    }
			else {node.setDisable(true);node.setDisable(false);}
		}
		else if (lepes<2)
		{
			Color color = (Color)((Region) node).getBackground().getFills().get(0).getFill();
		    if (color == Color.GREENYELLOW)
			{
		    	node.setDisable(false);
		    	szinezzunk(node);
				node.setStyle("-fx-background-color: TRANSPARENT;");
		    	node.setDisable(true);
		    	lepes = 2;}
			else {node.setDisable(true);node.setDisable(false);}
		}
		else
		{	
			Color ccoolor = (Color)((Region) node).getBackground().getFills().get(0).getFill();
		    if (ccoolor == Color.DARKSLATEGRAY)
		    {
		    	int x;
				int y;
				for (x = 0; x<7; ++x)
				{
					for (y = 0; y<7; ++y)
					{
						String visszabutton;
						visszabutton = "#button"; visszabutton += x; visszabutton += y;
						Scene scene = node.getScene();
						Node nnoode = (Node) node;
						nnoode =(Button) scene.lookup(visszabutton);
						
						ccoolor = (Color)((Region) nnoode).getBackground().getFills().get(0).getFill();
					    if (ccoolor == Color.DARKSLATEGRAY)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: LIGHTGRAY;");
					    }
					    else if (ccoolor == Color.DARKMAGENTA)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: VIOLET;");
					    }
					    else if (ccoolor == Color.MIDNIGHTBLUE)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: LIGHTSKYBLUE;");
					    }
					    else if(ccoolor == Color.TRANSPARENT)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: TRANSPARENT;");
					    }		
					}
				}
				node.setDisable(false);
				node.setStyle("-fx-background-color: GREENYELLOW;");
		    }
		    else if (ccoolor == Color.DARKMAGENTA)
		    {
		    	int x;
				int y;
				for (x = 0; x<7; ++x)
				{
					for (y = 0; y<7; ++y)
					{
						String visszabutton;
						visszabutton = "#button"; visszabutton += x; visszabutton += y;
						Scene scene = node.getScene();
						Node nnoode = (Node) node;
						nnoode =(Button) scene.lookup(visszabutton);
						
						ccoolor = (Color)((Region) nnoode).getBackground().getFills().get(0).getFill();
					    if (ccoolor == Color.DARKSLATEGRAY)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: LIGHTGRAY;");
					    }
					    else if (ccoolor == Color.DARKMAGENTA)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: VIOLET;");
					    }
					    else if (ccoolor == Color.MIDNIGHTBLUE)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: LIGHTSKYBLUE;");
					    }
					    else if(ccoolor == Color.TRANSPARENT)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: TRANSPARENT;");
					    }	
					}
				}
				node.setDisable(false);
				node.setStyle("-fx-background-color: GREENYELLOW;");
				osszpiros += 1;
				String osszespiros = Integer.toString(osszpiros);
				twopointslabel.setText(osszespiros);
		    }
		    else if (ccoolor == Color.MIDNIGHTBLUE)
		    {
		    	int x;
				int y;
				for (x=0; x<7; ++x)
				{
					for (y=0; y<7; ++y)
					{
						String visszabutton;
						visszabutton = "#button"; visszabutton += x; visszabutton += y;
						Scene scene = node.getScene();
						Node nnoode = (Node) node;
						nnoode =(Button) scene.lookup(visszabutton);
						
						ccoolor = (Color)((Region) nnoode).getBackground().getFills().get(0).getFill();
					    if (ccoolor == Color.DARKSLATEGRAY)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: LIGHTGRAY;");
					    }
					    else if (ccoolor == Color.DARKMAGENTA)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: VIOLET;");
					    }
					    else if (ccoolor == Color.MIDNIGHTBLUE)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: LIGHTSKYBLUE;");
					    }
					    else if(ccoolor == Color.TRANSPARENT)
					    {
					    	nnoode.setDisable(false);
					    	nnoode.setStyle("-fx-background-color: TRANSPARENT;");
					    }	
					}
				}
				node.setDisable(false);
				node.setStyle("-fx-background-color: GREENYELLOW;");
				osszkek += 1; 
				String osszeskek = Integer.toString(osszkek);
				onepointslabel.setText(osszeskek);
		    }
		    else if (ccoolor == Color.GREENYELLOW)
		    {			
		    	Node noodee=(Node) node;
				String coordinates = noodee.getId().toString();
				String[] coordinatess = coordinates.substring(coordinates.length()-2).split("");
				int xxx = Integer.parseInt(coordinatess[0]);
				int yyy = Integer.parseInt(coordinatess[1]);
		    	int zold = 0; int piros = 0; int kek = 0;
				String buttonnn = "#button";
	    		String buttonnn1, buttonnn2, buttonnn3, buttonnn4, buttonnn5, buttonnn6, buttonnn7, buttonnn8;
	    		buttonnn1 = buttonnn; buttonnn2 = buttonnn; buttonnn3 = buttonnn; buttonnn4 = buttonnn; buttonnn5 = buttonnn; buttonnn6 = buttonnn; buttonnn7 = buttonnn; buttonnn8 = buttonnn;      

	    		buttonnn1 += (xxx-1); buttonnn1 += (yyy-1); buttonnn2 += (xxx-1); buttonnn2 += yyy;
	    		buttonnn3 += (xxx-1); buttonnn3 += (yyy+1); buttonnn4 += xxx; buttonnn4 += (yyy-1);
	    		buttonnn5 += xxx; buttonnn5 += (yyy+1);		buttonnn6 += (xxx+1); buttonnn6 += (yyy-1);
	    		buttonnn7 += (xxx+1); buttonnn7 += yyy; 	buttonnn8 += (xxx+1); buttonnn8 += (yyy+1);
	    		
	    		Scene scene = node.getScene(); 
	    		noodee =(Button) scene.lookup(buttonnn1); 	
	    		if (noodee != null) { Color ccolorr = (Color)((Region) noodee).getBackground().getFills().get(0).getFill(); if (ccolorr == Color.LIGHTGRAY) {zold += 1;} else if (ccolorr == Color.VIOLET) {piros += 1;} else if (ccolorr == Color.LIGHTSKYBLUE) {kek += 1;}  } 
	    		noodee =(Button) scene.lookup(buttonnn2); 		
	    		if (noodee != null) { Color ccolorr = (Color)((Region) noodee).getBackground().getFills().get(0).getFill(); if (ccolorr == Color.LIGHTGRAY) {zold += 1;} else if (ccolorr == Color.VIOLET) {piros += 1;} else if (ccolorr == Color.LIGHTSKYBLUE) {kek += 1;}  } 
	    		noodee =(Button) scene.lookup(buttonnn3); 		    	
	    		if (noodee != null) { Color ccolorr = (Color)((Region) noodee).getBackground().getFills().get(0).getFill(); if (ccolorr == Color.LIGHTGRAY) {zold += 1;} else if (ccolorr == Color.VIOLET) {piros += 1;} else if (ccolorr == Color.LIGHTSKYBLUE) {kek += 1;}  } 
	    		noodee =(Button) scene.lookup(buttonnn4); 
	    		if (noodee != null) { Color ccolorr = (Color)((Region) noodee).getBackground().getFills().get(0).getFill(); if (ccolorr == Color.LIGHTGRAY) {zold += 1;} else if (ccolorr == Color.VIOLET) {piros += 1;} else if (ccolorr == Color.LIGHTSKYBLUE) {kek += 1;}  } 
	    		noodee =(Button) scene.lookup(buttonnn5); 
	    		if (noodee != null) { Color ccolorr = (Color)((Region) noodee).getBackground().getFills().get(0).getFill(); if (ccolorr == Color.LIGHTGRAY) {zold += 1;} else if (ccolorr == Color.VIOLET) {piros += 1;} else if (ccolorr == Color.LIGHTSKYBLUE) {kek += 1;}  } 
	    		noodee =(Button) scene.lookup(buttonnn6); 		
	    		if (noodee != null) { Color ccolorr = (Color)((Region) noodee).getBackground().getFills().get(0).getFill(); if (ccolorr == Color.LIGHTGRAY) {zold += 1;} else if (ccolorr == Color.VIOLET) {piros += 1;} else if (ccolorr == Color.LIGHTSKYBLUE) {kek += 1;}  } 
	    		noodee =(Button) scene.lookup(buttonnn7); 		    
	    		if (noodee != null) { Color ccolorr = (Color)((Region) noodee).getBackground().getFills().get(0).getFill(); if (ccolorr == Color.LIGHTGRAY) {zold += 1;} else if (ccolorr == Color.VIOLET) {piros += 1;} else if (ccolorr == Color.LIGHTSKYBLUE) {kek += 1;}  } 
	    		noodee =(Button) scene.lookup(buttonnn8); 
	    		if (noodee != null) { Color ccolorr = (Color)((Region) noodee).getBackground().getFills().get(0).getFill(); if (ccolorr == Color.LIGHTGRAY) {zold += 1;} else if (ccolorr == Color.VIOLET) {piros += 1;} else if (ccolorr == Color.LIGHTSKYBLUE) {kek += 1;}  }

				/**
				 * Decides if the game could go on. No more circles means it over.
				 */
	    		if (zold == 0 && piros == 0 && kek == 0)
	    		{ 
	    			if(osszpiros > osszkek)
	    			{
		    			lepeslabel.setText("No more possible steps! The pink player won!");
		    			nyertes1 = 1;
	    			}
	    			else if(osszpiros == osszkek)
	    			{
		    			lepeslabel.setText("No more possible steps! DRAW!");
		    			draw1 = 1; draw2 = 1;
	    			}
	    			else
	    			{
		    			lepeslabel.setText("No more possible steps! The blue player won!");
		    			nyertes2 = 1;
	    			}
	    		}

				/**
				 * Which player has collected 10 of their own circles first.
				 * That one won.
				 */
	    		if (osszpiros == 10)
	    		{
	    			lepeslabel.setText("The pink player won!");
	    			nyertes1 = 1;
	    		}
	    		if (osszkek == 10)
	    		{
	    			lepeslabel.setText("The blue player won!");
	    			nyertes2 = 1;
	    		}

				/**
				 * If someone won then all of the circles will be 'turned off'
				 */
	    		if (lepeslabel.getText().equals("The blue player won!")  || lepeslabel.getText().equals("No more possible steps! The pink player won!") || lepeslabel.getText().equals("No more possible steps! DRAW!") || lepeslabel.getText().equals("The pink player won!") || lepeslabel.getText().equals("No more possible steps! The blue player won!"))
	    		{
	    			int x;
					int y;
					for (x=0; x<7; ++x)
					{
						for (y=0; y<7; ++y)
						{
							
							String visszabutton;
							visszabutton = "#button"; visszabutton += x; visszabutton += y;
							Scene sceene = node.getScene();
							Node nnnnoode = (Node) node;
							nnnnoode =(Button) sceene.lookup(visszabutton);
							nnnnoode.setDisable(true);
					    }	
					}

					/**
					 * From integer to string so the xml database can work with the date.
					 */
					String player1pontok = Integer.toString(osszkek);
					String player2pontok = Integer.toString(osszpiros);
					String player1nyertes = Integer.toString(nyertes1);
					String player2nyertes = Integer.toString(nyertes2);
					String player1draw = Integer.toString(draw1);
					String player2draw = Integer.toString(draw2);

					/**
					 * Gives the data to the XmlProda.java so it can make the database.
					 */
					XmlProba.setpontok1(player1pontok);
					XmlProba.setpontok2(player2pontok);
					XmlProba.setnyertes1(player1nyertes);
					XmlProba.setnyertes2(player2nyertes);
					XmlProba.setdraw1(player1draw);
					XmlProba.setdraw2(player2draw);

					/**
					 * Data for ReadXMLFile.java
					 */
					ReadXMLFile.setpontok1(player1pontok);
					ReadXMLFile.setpontok2(player2pontok);
					ReadXMLFile.main(null);
					try {
						XmlProba.main(null);
					} catch (TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParserConfigurationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					/**
					 * Merging the two xml files to create the database.
					 */
					Merge.szamolj();

					}

	    		node.setDisable(false);
	    		szinezzunk(node);
	    		node.setStyle("-fx-background-color: TRANSPARENT;");
	    		node.setDisable(true);
		    }
		}
	}

	/**
	 *
	 */
    private void setDisable(boolean b) 
    {
		// TODO Auto-generated method stub	
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
    public GameController() 
    {}

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
}