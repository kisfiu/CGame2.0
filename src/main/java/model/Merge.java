package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

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
import org.xml.sax.SAXException;

import org.pmw.tinylog.Logger;



/**
 * Merging the xml files to create the new database.
 * @author kisfiu
 */
public final class Merge {
    /**
     * This one is just for the checkstyle.
     */
    private Merge() {
    }
     /**
     * Merging. Makes the needed calculations.
     * @return scores
      * @throws TransformerException if an exceptional condition occurred during the transformation process.
      * @throws ParserConfigurationException indicates a serious configuration error.
      * @throws IOException if it reads an xml file that has the wrong structure
      * @throws SAXException for the XML parser
     * */
     public static Map<Integer, Map> calculate() throws TransformerException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setIgnoringComments(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(new File("src/main/java/model/merged.xml"));
        Document doc1 = builder.parse(new File("src/main/java/model/output.xml"));

        NodeList nodes = doc.getElementsByTagName("player");
        NodeList nodes1 = doc1.getElementsByTagName("player");

        int firstNotIn = 0;
        int secondNotIn = 0;
        Map<Integer, Map> scores = new HashMap<>();

        /**
         * Goes through the xml files and compare the output.xml with the existing ones in merged.xml
         * Name by name.
         * If the first player's name already exists in the database then it will give the player the deserved points
         */
        Node nOutput1 = nodes1.item(0);
        Element eOutput1 = (Element) nOutput1;
        String nevOutput1 = eOutput1.getAttribute("name");
        for (int j = 0; j < nodes.getLength(); j = j + 1) {
            Map<String, String> player = new HashMap<>();

            Node nMerged = nodes.item(j);
            Element eMerged = (Element) nMerged;
            String mergedName = eMerged.getAttribute("name");
            Logger.info("The first player is", nevOutput1);
            Logger.info("Merged player", mergedName);
            player.put("name", mergedName);

            if (nevOutput1.equals(mergedName)) {
                Logger.info("Everything works, player 1 is merged.");
                firstNotIn = 1;
                String fromMerged = eMerged.getElementsByTagName("matcheswon").item(0).getTextContent();
                String fromOutput = eOutput1.getElementsByTagName("matcheswon").item(0).getTextContent();
                int itogether = Integer.parseInt(fromOutput) + Integer.parseInt(fromMerged);
                String stogether = Integer.toString(itogether);
                Logger.info(stogether);
                eMerged.getElementsByTagName("matcheswon").item(0).setTextContent(stogether);
                player.put("matcheswon", stogether);

                fromMerged = eMerged.getElementsByTagName("matchesplayed").item(0).getTextContent();
                itogether = Integer.parseInt(fromMerged); itogether += 1;
                stogether = Integer.toString(itogether);
                eMerged.getElementsByTagName("matchesplayed").item(0).setTextContent(stogether);
                player.put("matchesplayed", stogether);

                fromMerged = eMerged.getElementsByTagName("matchesdraw").item(0).getTextContent();
                fromOutput = eOutput1.getElementsByTagName("matchesdraw").item(0).getTextContent();
                itogether = Integer.parseInt(fromOutput) + Integer.parseInt(fromMerged);
                stogether = Integer.toString(itogether);
                eMerged.getElementsByTagName("matchesdraw").item(0).setTextContent(stogether);
                player.put("matchesdraw", stogether);

                fromMerged = eMerged.getElementsByTagName("points").item(0).getTextContent();
                fromOutput = eOutput1.getElementsByTagName("points").item(0).getTextContent();
                itogether = Integer.parseInt(fromOutput) + Integer.parseInt(fromMerged);
                    String meccsek = eMerged.getElementsByTagName("matchesplayed").item(0).getTextContent();
                    String nyeresek = eMerged.getElementsByTagName("matcheswon").item(0).getTextContent();
                    String dontetlenek = eMerged.getElementsByTagName("matchesdraw").item(0).getTextContent();

                    if (meccsek.equalsIgnoreCase("3") || meccsek.equalsIgnoreCase("5") || meccsek.equalsIgnoreCase("15") || meccsek.equalsIgnoreCase("50")
                            ||
                        nyeresek.equals("5") || nyeresek.equals("10") || nyeresek.equals("15") || nyeresek.equals("20")
                            ||
                        dontetlenek.equals("1") || dontetlenek.equals("7") || dontetlenek.equals("11") || dontetlenek.equals("30")) {
                                itogether += 7;
                    }
                stogether = Integer.toString(itogether);
                eMerged.getElementsByTagName("points").item(0).setTextContent(stogether);
                player.put("points", stogether);
                Logger.info("Player 1's points are calculated");
                break;
            } else {
                    Logger.info("Not the same player. Search continues.");
            }
            scores.put(j, player);
        }

        /**
         * Goes through the xml files and compare the output.xml with the existing ones in merged.xml
         * Name by name.
         * If the first player's name already exists in the database then it will give the player the deserved points
        */
        Node nOutPut2 = nodes1.item(1);
        Element eOutput2 = (Element) nOutPut2;
        String nevoutput2 = eOutput2.getAttribute("name");
        for (int j = 0; j < nodes.getLength(); j = j + 1) {
            Node nmerged = nodes.item(j);
            Element emerged = (Element) nmerged;
            String nevmerged = emerged.getAttribute("name");
            Logger.info("Player 2 is:", nevoutput2);
            Logger.info("Merged player is:", nevmerged);

            if (nevoutput2.equals(nevmerged)) {
                Logger.info("Everything works, player 2 is merged.");
                secondNotIn = 1;
                String frommerged = emerged.getElementsByTagName("matcheswon").item(0).getTextContent();
                String fromoutput = eOutput2.getElementsByTagName("matcheswon").item(0).getTextContent();
                int itogether = Integer.parseInt(fromoutput) + Integer.parseInt(frommerged);
                String stogether = Integer.toString(itogether);
                Logger.info(stogether);
                emerged.getElementsByTagName("matcheswon").item(0).setTextContent(stogether);

                frommerged = emerged.getElementsByTagName("matchesplayed").item(0).getTextContent();
                itogether = Integer.parseInt(frommerged); itogether += 1;
                stogether = Integer.toString(itogether);
                emerged.getElementsByTagName("matchesplayed").item(0).setTextContent(stogether);

                frommerged = emerged.getElementsByTagName("matchesdraw").item(0).getTextContent();
                fromoutput = eOutput2.getElementsByTagName("matchesdraw").item(0).getTextContent();
                itogether = Integer.parseInt(fromoutput) + Integer.parseInt(frommerged);
                stogether = Integer.toString(itogether);
                emerged.getElementsByTagName("matchesdraw").item(0).setTextContent(stogether);

                frommerged = emerged.getElementsByTagName("points").item(0).getTextContent();
                fromoutput = eOutput2.getElementsByTagName("points").item(0).getTextContent();
                itogether = Integer.parseInt(fromoutput) + Integer.parseInt(frommerged);
                    String meccsek = emerged.getElementsByTagName("matchesplayed").item(0).getTextContent();
                    String nyeresek = emerged.getElementsByTagName("matcheswon").item(0).getTextContent();
                    String dontetlenek = emerged.getElementsByTagName("matchesdraw").item(0).getTextContent();
                    if (meccsek.equalsIgnoreCase("3") || meccsek.equalsIgnoreCase("5") || meccsek.equalsIgnoreCase("15") || meccsek.equalsIgnoreCase("50")
                            ||
                        nyeresek.equals("5") || nyeresek.equals("10") || nyeresek.equals("15") || nyeresek.equals("20")
                            ||
                        dontetlenek.equals("1") || dontetlenek.equals("7") || dontetlenek.equals("11") || dontetlenek.equals("30")) {
                        itogether += 7;
                    }

                stogether = Integer.toString(itogether);
                emerged.getElementsByTagName("points").item(0).setTextContent(stogether);
                Logger.info("Player 2's points are calculated");
                break;
            } else {
                Logger.info("Not the same player. Search continues.");
            }
        }

        /**
         * If it's a new player, then we just add it to the database.
         */
        if (firstNotIn == 0) {
            Node n = (Node) doc.importNode(nodes1.item(0), true);
            nodes.item(0).getParentNode().appendChild(n);
            Logger.info("Player 1 is a new player. Player one added to the database.");
        }

        if (secondNotIn == 0) {
            Node n = (Node) doc.importNode(nodes1.item(1), true);
            nodes.item(1).getParentNode().appendChild(n);
            Logger.info("Player 2 is a new player. Player two added to the database.");
        }

        Logger.info("Has player one played before? T/F", firstNotIn);
        Logger.info("Has player two played before? T/F", secondNotIn);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
        Writer output = null;
        output = new BufferedWriter(new FileWriter("src/main/java/model/merged.xml"));

        String xmlOutput = result.getWriter().toString();
        output.write(xmlOutput);
        output.close();
        Logger.info("The merge is completed. The database is up-to-date");

        return scores;
    }
}
