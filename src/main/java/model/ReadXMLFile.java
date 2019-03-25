package model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import view.StartGameController;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile {

	
	static String neve1;static String neve2;static String pontos1;static String pontos2;
	public static void setpontok1(String player1pontok)     {pontos1 = player1pontok;}
	public static void setpontok2(String player2pontok)     {pontos2 = player2pontok;}
	public static void settName1(String playeronetext)     {neve1 = playeronetext;}
	public static void settName2(String playertwotext)     {neve2 = playertwotext;}

	
	
  public static void main(String argv[]) {

    try {

	File fXmlFile = new File("src/main/java/model/players.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);

	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("player");

	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);

		System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			String neve = eElement.getAttribute("name");
			if (neve.equals(neve1))
			{
				System.out.println("\n" + "ezmárvolt" + "\n");
	 		}
			
			String pontja = eElement.getElementsByTagName("points").item(0).getTextContent();
			System.out.println(pontja);
			
//			if(pontja == )
			
//			
//			System.out.println("Player Name : " + eElement.getAttribute("name"));
//			System.out.println("Matches played : " + eElement.getElementsByTagName("matchesplayed").item(0).getTextContent());
//			System.out.println("Matches won : " + eElement.getElementsByTagName("matcheswon").item(0).getTextContent());
//			System.out.println("Matches draw : " + eElement.getElementsByTagName("matchesdraw").item(0).getTextContent());
//			System.out.println("Points : " + eElement.getElementsByTagName("points").item(0).getTextContent());

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
  }

}