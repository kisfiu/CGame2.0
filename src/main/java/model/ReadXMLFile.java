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
public final class ReadXMLFile {
    /**
     * It's only for the checkstyle.
     */
    private ReadXMLFile() {
    }
    /**
     * Represents the names and the points of the players.
     */
    private static String name1; private static String name2; private static String pontos1; private static String pontos2;

    /**
     *
     * @param player1Points is the point of the first player
     */
    public static void setPoints1(final String player1Points) {
        pontos1 = player1Points;
    }

    /**
     *
     * @param player2Points is the point of the second player
     */
    public static void setPoints2(final String player2Points) {
        pontos2 = player2Points;
    }

    /**
     *
     * @param playerOneName is the name of the first player
     */
    public static void settName1(final String playerOneName) {
        name1 = playerOneName;
    }

    /**
     *
     * @param playerTwoName is the name of the second player
     */
    public static void settName2(final String playerTwoName) {
        name2 = playerTwoName;
    }


    /**
     * Checks if the player is already in the database or not.
     * Parsing
     * @param argv Any arguments to be included in a string representation of this event.
     */
    public static void main(final String[] argv) {
        try {
            File fXmlFile = new File("src/main/java/model/players.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            Logger.info("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("player");

            Logger.info("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Logger.info("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String neve = eElement.getAttribute("name");
                    if (neve.equals(name1)) {
                        Logger.info("\n" + "It was already used." + "\n");
                    }

                    String pontja = eElement.getElementsByTagName("points").item(0).getTextContent();
                    Logger.info(pontja);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("BAMM", e);
        }
    }
}
