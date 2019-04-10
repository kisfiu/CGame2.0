package model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import org.pmw.tinylog.Logger;


/**
 * It is for the testing. How the xml database compares the players.
 */
public class ReadXMLFile {

	/**
	 * Represents the names and the points of the players
	 */
	private static String name1; private static String name2; private static String pontos1; private static String pontos2;

	public static void setPoints1(String player1pontok)     { pontos1 = player1pontok; }
	public static void setPoints2(String player2pontok)     {pontos2 = player2pontok; }
	public static void settName1(String playeronetext)     { name1 = playeronetext; }
	public static void settName2(String playertwotext)     { name2 = playertwotext; }

	public static void main(String argv[])
	{

		try
		{
			File fXmlFile = new File("src/main/java/model/players.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			Logger.info("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("player");

			Logger.info("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				Logger.info("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					String neve = eElement.getAttribute("name");
					if (neve.equals(name1))
					{
						Logger.info("\n" + "It was already used." + "\n");
					}

					String pontja = eElement.getElementsByTagName("points").item(0).getTextContent();
					Logger.info(pontja);
				}
			}
    	}
		catch (Exception e)
		{
			e.printStackTrace();
			Logger.error("BAMM", e);
    	}
  	}
}
