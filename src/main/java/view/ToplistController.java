package view;



import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

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



/**
 * Controller for the Toplist.fxml, for the toplist of the game. It shows the top 10 players and their points (in order).
 */
public class ToplistController
{

	@FXML
	private Button gobackbutton;

	/**
	 * Handles the Back button, to return to the menu of the game.
	 */
	@FXML
	private void handBackto(ActionEvent event) throws IOException 
	{		
		Node node=(Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
 
    @FXML private Label name1; @FXML private Label name6; 
    @FXML private Label name2; @FXML private Label name7; 
    @FXML private Label name3; @FXML private Label name8; 
    @FXML private Label name4; @FXML private Label name9; 
    @FXML private Label name5; @FXML private Label name10; 
    
    @FXML private Label points1; @FXML private Label points6; 
    @FXML private Label points2; @FXML private Label points7; 
    @FXML private Label points3; @FXML private Label points8; 
    @FXML private Label points4; @FXML private Label points9; 
    @FXML private Label points5; @FXML private Label points10; 
    
    @FXML
    Button muta;
	/**
	 * Handles the Let's see button. to reveal the toplist.
	 */
    @FXML
    public void mutasd() throws TransformerException, ParserConfigurationException, IOException, SAXException 
    {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();   
		domFactory.setIgnoringComments(true);  
		DocumentBuilder builder = domFactory.newDocumentBuilder();   
		Document doc = builder.parse(new File("src/main/java/model/merged.xml"));   
		NodeList nodes = doc.getElementsByTagName("player");  
		
		int[] arraypontok = new int[nodes.getLength()];
		int[] arraypontokeredeti = new int[nodes.getLength()];
		String[] arraynev = new String[nodes.getLength()];
		String[] arrayneveredeti = new String[nodes.getLength()];
		String frommergedpont;
		String frommergednev;
		int Ifrommerged;
		int k = 0;

		/**
		 * Sets up the names
		 */
		for(int j=0; j<nodes.getLength(); j=j+1)
		{	
			org.w3c.dom.Node Nmerged = nodes.item(j);
			Element Emerged = (Element) Nmerged;
			String nevmerged = Emerged.getAttribute("name");
			System.out.println(nevmerged);
			
			frommergedpont = Emerged.getElementsByTagName("points").item(0).getTextContent();
			frommergednev = Emerged.getAttribute("name");
			arraynev[k]= frommergednev;
			arrayneveredeti[k]= frommergednev;

			
			Ifrommerged = Integer.parseInt(frommergedpont);
			arraypontok[k]= Ifrommerged;
			arraypontokeredeti[k]= Ifrommerged;
			k++;
		}
		Arrays.sort(arraypontok);
		System.out.println(Arrays.toString(arraypontok));


		/**
		 * Sets up the points for the names
		 */
		for(int i = 0; i < arraypontok.length / 2; i++)
		{
		    int temp = arraypontok[i];
		    arraypontok[i] = arraypontok[arraypontok.length - i - 1];
		    arraypontok[arraypontok.length - i - 1] = temp;
		}
		System.out.println(Arrays.toString(arraypontok));
		System.out.println(Arrays.toString(arraypontokeredeti));
		
		String pont1 = Integer.toString(arraypontok[0]);	this.points1.setText(pont1);
		String pont2 = Integer.toString(arraypontok[1]);	this.points2.setText(pont2);
		String pont3 = Integer.toString(arraypontok[2]);	this.points3.setText(pont3);
		String pont4 = Integer.toString(arraypontok[3]);	this.points4.setText(pont4);
		String pont5 = Integer.toString(arraypontok[4]);	this.points5.setText(pont5);
		String pont6 = Integer.toString(arraypontok[5]);	this.points6.setText(pont6);
		String pont7 = Integer.toString(arraypontok[6]);	this.points7.setText(pont7);
		String pont8 = Integer.toString(arraypontok[7]);	this.points8.setText(pont8);
		String pont9 = Integer.toString(arraypontok[8]);	this.points9.setText(pont9);
		String pont10 = Integer.toString(arraypontok[9]);	this.points10.setText(pont10);

		/**
		 *
		 */
		int l = 0;
		for(int j=0; j<nodes.getLength(); j=j+1)
		{	
			org.w3c.dom.Node Nmerged = nodes.item(j);
			Element Emerged = (Element) Nmerged;

			String fFrommergedpont = Emerged.getElementsByTagName("points").item(0).getTextContent();
			
			for(int i=0; i<nodes.getLength(); i= i+1)
			{
				String nevmerged = Emerged.getAttribute("name");
				
				if(fFrommergedpont.equals(Integer.toString(arraypontok[i])))
				{
					arraynev[i]=nevmerged;
					l++;
				}

			}
			
		}


		/**
		 * Setting up the toplist from the array
		 */
		String nev1 = (arraynev[0]);	this.name1.setText(nev1);
		String nev2 = (arraynev[1]);	this.name2.setText(nev2);
		String nev3 = (arraynev[2]);	this.name3.setText(nev3);
		String nev4 = (arraynev[3]);	this.name4.setText(nev4);
		String nev5 = (arraynev[4]);	this.name5.setText(nev5);
		String nev6 = (arraynev[5]);	this.name6.setText(nev6);
		String nev7 = (arraynev[6]);	this.name7.setText(nev7);
		String nev8 = (arraynev[7]);	this.name8.setText(nev8);
		String nev9 = (arraynev[8]);	this.name9.setText(nev9);
		String nev10 = (arraynev[9]);	this.name10.setText(nev10);
		
		System.out.println(Arrays.toString(arraynev));
		
		
		System.out.println("\n\n_________________________toplistcreated_________________________");
   
    }	  
}