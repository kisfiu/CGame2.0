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

import org.pmw.tinylog.Logger;


/**
 *
 * @author kisfiu
 */

/**
 * Creates the output.xml. The output.xml has the two players whom just played
 */
public final class XmlProba {
    /**
     * It's only here for the checkstyle.
     */
    private XmlProba() {
    }

    /**
     * Name of the players.
     */
    private static String playerOneName = "0"; private static String playerTwoName = "0";

    /**
     * Points of the players.
     */
    private static String playerOnePoints = "0"; private static String playerTwoPoints = "0";

    /**
     * Wins of the players.
     */
    private static String playerOneWins = "0"; private static String playerTwoWins = "0";

    /**
     * Draws of the players.
     */
    private static String playerOneDraws = "0"; private static String playerTwoDraws = "0";

    /**
     * Played games of the players.
     */
    private static String playerOnePlayed = "1"; private static String playerTwoPlayed = "1";

    /**
     * Sets the first player's name.
     * @param player1Name is the first players name
     */
    public static void setName1(final String player1Name) {
        playerOneName = player1Name;
    }

    /**
     * Sets the second player's name.
     * @param player2Name is the second players name
     */
    public static void setName2(final String player2Name) {
        playerTwoName = player2Name;
    }

    /**
     * Sets the first player's points.
     * @param player1Points is the first players points
     */
    public static void setPoints1(final String player1Points) {
        playerOnePoints = player1Points;
    }

    /**
     * Sets the second player's points.
     * @param player2Points is the second players points
     */
    public static void setPoints2(final String player2Points) {
        playerTwoPoints = player2Points;
    }

    /**
     * Sets the first player's wins.
     * @param player1Wins is the first player's wins
     */
    public static void setWins1(final String player1Wins) {
        playerOneWins = player1Wins;
    }

    /**
     * Sets the second player's wins.
     * @param player2Wins is the second player's wins
     */
    public static void setWins2(final String player2Wins) {
        playerTwoWins = player2Wins;
    }

    /**
     * Sets the first player's draws.
     * @param player1draw is the first player's draws
     */
    public static void setDraws1(final String player1draw) {
        playerOneDraws = player1draw;
    }

    /**
     * Sets the second player's draws.
     * @param player2draw is the second player's draws
     */
    public static void setDraws2(final String player2draw) {
        playerTwoDraws = player2draw;
    }

    /**
     * Reads and set up the players data.
     * @param args Any arguments to be included in a string representation of this event.
     * @throws TransformerException if an exceptional condition occurred during the transformation process.
     * @throws ParserConfigurationException indicates a serious configuration error.
     */
    public static void main(final String[] args) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element players = doc.createElement("players");
        doc.appendChild(players);
        Element player = doc.createElement("player");
        player.setAttribute("name", playerOneName);
        players.appendChild(player);

        Element matchesplayed = doc.createElement("matchesplayed");
        matchesplayed.setTextContent(playerOnePlayed);
        player.appendChild(matchesplayed);
        Element matcheswon1 = doc.createElement("matcheswon");
        matcheswon1.setTextContent(playerTwoWins);
        player.appendChild(matcheswon1);
        Element matchesdraw1 = doc.createElement("matchesdraw");
        matchesdraw1.setTextContent(playerOneDraws);
        player.appendChild(matchesdraw1);
        Element points = doc.createElement("points");

        int pontok1;
        Logger.info("Player one's matches won:", matcheswon1.getTextContent());
        if (Integer.parseInt(matcheswon1.getTextContent()) != 0 || Integer.parseInt(matchesdraw1.getTextContent()) != 0) {
           pontok1 = (Integer.parseInt(matcheswon1.getTextContent()) * 5 + Integer.parseInt(matchesdraw1.getTextContent()) * 3);
        } else {
            pontok1 = 0;
        }
        int ennyi1 = Integer.parseInt(playerOnePoints);
        pontok1 = pontok1 + ennyi1;
        String pontokk1 = Integer.toString(pontok1);
        points.setTextContent(pontokk1);
        player.appendChild(points);

        Element player2 = doc.createElement("player");
        player2.setAttribute("name", playerTwoName);
        players.appendChild(player2);

        Element matchesplayed2 = doc.createElement("matchesplayed");
        matchesplayed2.setTextContent(playerTwoPlayed);
        player2.appendChild(matchesplayed2);

        Element matcheswon2 = doc.createElement("matcheswon");
        matcheswon2.setTextContent(playerOneWins);
        player2.appendChild(matcheswon2);

        Element matchesdraw2 = doc.createElement("matchesdraw");
        matchesdraw2.setTextContent(playerTwoDraws);
        player2.appendChild(matchesdraw2);

        Element points2 = doc.createElement("points");
        player2.appendChild(points2);

        int pontok2;
        Logger.info("Player two's matches won:", matcheswon2.getTextContent());
        if (Integer.parseInt(matcheswon2.getTextContent()) != 0 || Integer.parseInt(matchesdraw2.getTextContent()) != 0) {
           pontok2 = (Integer.parseInt(matcheswon2.getTextContent()) * 5 + Integer.parseInt(matchesdraw2.getTextContent()) * 3);
        } else {
            pontok2 = 0;
        }
        int ennyi2 = Integer.parseInt(playerTwoPoints);
        pontok2 = pontok2 + ennyi2;
        String pontokk2 = Integer.toString(pontok2);
        points2.setTextContent(pontokk2);

        Logger.info(playerOneName);
        Logger.info(playerTwoName);
        Logger.info(playerOnePoints);
        Logger.info(playerTwoPoints);
        Logger.info(playerOneWins);
        Logger.info(playerTwoWins);
        Logger.info(matcheswon1.getTextContent());

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult file = new StreamResult(new File("src/main/java/model/output.xml"));

        transformer.transform(source, file);
    }
}
