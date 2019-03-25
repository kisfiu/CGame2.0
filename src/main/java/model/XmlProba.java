package model;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.scene.text.Text;

/**
 *
 * @author kisfiu
 */
public class XmlProba {

    /**
     * @param args the command line arguments
     */
	static String asd;static String asd1 = "0";static String asd2 = "0";static String pontos1 = "0";static String pontos2 = "0";
	static String nyertes1 = "0"; static String nyertes2 = "0"; static String draw1 = "0"; static String draw2 = "0";
	static String jatszott1 = "1"; static String jatszott2 = "1";
	
	public static void settName1(String playeronetext)     {asd1 = playeronetext;}
	public static void settName2(String playertwotext)     {asd2 = playertwotext;}
	public static void setpontok1(String player1pontok)     {pontos1 = player1pontok;}
	public static void setpontok2(String player2pontok)     {pontos2 = player2pontok;}
	public static void setnyertes1(String player1nyertes)     {nyertes1 = player1nyertes;}
	public static void setnyertes2(String player2nyertes)     {nyertes2 = player2nyertes;}
	public static void setdraw1(String player1draw)     {draw1 = player1draw;}
	public static void setdraw2(String player2draw)     {draw2 = player2draw;}


	
	
	public static void main(String[] args) throws TransformerException, ParserConfigurationException 
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        
        Element players = doc.createElement("players");
        doc.appendChild(players);
        
        
        Element player = doc.createElement("player");  
        player.setAttribute("name", asd1);          
        players.appendChild(player);

        
        Element matchesplayed = doc.createElement("matchesplayed");
        matchesplayed.setTextContent(jatszott1);
        player.appendChild(matchesplayed);

        Element matcheswon1 = doc.createElement("matcheswon");
//        String ennyinyertes1 = matcheswon.getTextContent();
//        int nyertespontok1 = (Integer.parseInt(nyertes1));
//        String pontokk2 = Integer.toString(pontok2);
        matcheswon1.setTextContent(nyertes2);

        
        player.appendChild(matcheswon1);
        
        Element matchesdraw1 = doc.createElement("matchesdraw");
        matchesdraw1.setTextContent(draw1);
        player.appendChild(matchesdraw1);
        
        Element points = doc.createElement("points");
//        points.setTextContent(pontos1);
     
        int pontok1;
        System.out.println(matcheswon1.getTextContent()); 
        if(Integer.parseInt(matcheswon1.getTextContent()) != 0 || Integer.parseInt(matchesdraw1.getTextContent()) != 0)
        {
           pontok1 = (Integer.parseInt(matcheswon1.getTextContent()) * 5 + Integer.parseInt(matchesdraw1.getTextContent()) * 3);
        }
        else 
        {
        	pontok1=0;
        }
        
//        int pontok2 = (Integer.parseInt(matcheswon2.getTextContent()) * 5 + Integer.parseInt(matchesdraw2.getTextContent()) * 3);
        int ennyi1 = Integer.parseInt(pontos1);
        pontok1 = pontok1 + ennyi1;
        String pontokk1 = Integer.toString(pontok1);
        points.setTextContent(pontokk1);
        
        player.appendChild(points);

        
        
        
        
        
        
        
        
        
        
        
        
        
        Element player2 = doc.createElement("player");
        player2.setAttribute("name", asd2);
        players.appendChild(player2);

        Element matchesplayed2 = doc.createElement("matchesplayed");
        matchesplayed2.setTextContent(jatszott2);
        player2.appendChild(matchesplayed2);

        Element matcheswon2 = doc.createElement("matcheswon");
        matcheswon2.setTextContent(nyertes1);
        player2.appendChild(matcheswon2);
        
        Element matchesdraw2 = doc.createElement("matchesdraw");
        matchesdraw2.setTextContent(draw2);
        player2.appendChild(matchesdraw2);
        
        Element points2 = doc.createElement("points");
        player2.appendChild(points2);
        
        int pontok2;
        System.out.println(matcheswon2.getTextContent()); 
        if(Integer.parseInt(matcheswon2.getTextContent()) != 0 || Integer.parseInt(matchesdraw2.getTextContent()) != 0)
        {
           pontok2 = (Integer.parseInt(matcheswon2.getTextContent()) * 5 + Integer.parseInt(matchesdraw2.getTextContent()) * 3);
        }
        else 
        {
        	pontok2=0;
        }
        
//        int pontok2 = (Integer.parseInt(matcheswon2.getTextContent()) * 5 + Integer.parseInt(matchesdraw2.getTextContent()) * 3);
        int ennyi2 = Integer.parseInt(pontos2);
        pontok2 = pontok2 + ennyi2;
        String pontokk2 = Integer.toString(pontok2);
        points2.setTextContent(pontokk2);

        
        
        System.out.println(asd1);
        System.out.println(asd2);
        System.out.println(pontos1);
        System.out.println(pontos2);
        System.out.println(nyertes1);
        System.out.println(nyertes2);
        System.out.println(matcheswon1.getTextContent());
 
        

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult file = new StreamResult(new File("src/main/java/model/output.xml"));

        transformer.transform(source, file);

    }

	

	



}