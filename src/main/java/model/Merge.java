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

public class Merge {
	
	
	public static Map<Integer, Map> szamolj() throws TransformerException, ParserConfigurationException, IOException, SAXException 
    {
//	ClassLoader classloader = getClass().getClassLoader();
	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();   
	domFactory.setIgnoringComments(true);  
	DocumentBuilder builder = domFactory.newDocumentBuilder();   
	Document doc = builder.parse(new File("src/main/java/model/merged.xml"));   
	Document doc1 = builder.parse(new File("src/main/java/model/output.xml"));   

	NodeList nodes = doc.getElementsByTagName("player");  

	NodeList nodes1 = doc1.getElementsByTagName("player");

	
	int elsonem = 0;
	int masodiknem = 0;
		
	Map<Integer,Map> scores = new HashMap<>();

	Node Noutput1 = nodes1.item(0);
	Element Eoutput1 = (Element) Noutput1;
	String nevoutput1 = Eoutput1.getAttribute("name");
	for(int j=0; j<nodes.getLength(); j=j+1)
	{
		Map<String, String> player = new HashMap<>();
		
		Node Nmerged = nodes.item(j);
		Element Emerged = (Element) Nmerged;
        String nevmerged = Emerged.getAttribute("name");
		System.out.println(nevoutput1);
		System.out.println(nevmerged);
		
		player.put("name", nevmerged);
		
		if(nevoutput1.equals(nevmerged))
		{
			System.out.println("______________takarmany");
			elsonem = 1;
			String frommerged = Emerged.getElementsByTagName("matcheswon").item(0).getTextContent();
			String fromoutput = Eoutput1.getElementsByTagName("matcheswon").item(0).getTextContent();
			int Itogether = Integer.parseInt(fromoutput) + Integer.parseInt(frommerged);
			String Stogether = Integer.toString(Itogether);
			System.out.println(Stogether);
			Emerged.getElementsByTagName("matcheswon").item(0).setTextContent(Stogether);
			player.put("matcheswon", Stogether);
			
			frommerged = Emerged.getElementsByTagName("matchesplayed").item(0).getTextContent();
			Itogether = Integer.parseInt(frommerged); Itogether += 1;  
			Stogether = Integer.toString(Itogether);
			Emerged.getElementsByTagName("matchesplayed").item(0).setTextContent(Stogether);
			player.put("matchesplayed", Stogether);
			
			frommerged = Emerged.getElementsByTagName("matchesdraw").item(0).getTextContent();
			fromoutput = Eoutput1.getElementsByTagName("matchesdraw").item(0).getTextContent();
			Itogether = Integer.parseInt(fromoutput) + Integer.parseInt(frommerged);
			Stogether = Integer.toString(Itogether);
			Emerged.getElementsByTagName("matchesdraw").item(0).setTextContent(Stogether);
			player.put("matchesdraw", Stogether);

			frommerged = Emerged.getElementsByTagName("points").item(0).getTextContent();
			fromoutput = Eoutput1.getElementsByTagName("points").item(0).getTextContent();
			Itogether = Integer.parseInt(fromoutput) + Integer.parseInt(frommerged);
				String meccsek = Emerged.getElementsByTagName("matchesplayed").item(0).getTextContent();
				String nyeresek = Emerged.getElementsByTagName("matcheswon").item(0).getTextContent();
				String dontetlenek = Emerged.getElementsByTagName("matchesdraw").item(0).getTextContent();
				if (meccsek.equalsIgnoreCase("3") || meccsek.equalsIgnoreCase("5") || meccsek.equalsIgnoreCase("15") || meccsek.equalsIgnoreCase("50") 
						||
					nyeresek.equals("5") || nyeresek.equals("10") || nyeresek.equals("15") || nyeresek.equals("20")
						||
					dontetlenek.equals("1") || dontetlenek.equals("7") || dontetlenek.equals("11") || dontetlenek.equals("30"))
				{
					Itogether += 7;
				}
			Stogether = Integer.toString(Itogether);
			Emerged.getElementsByTagName("points").item(0).setTextContent(Stogether);		
			player.put("points", Stogether);
			break;
		}
			else 
			{
				System.out.println("--------------------megvagy");
			}
		scores.put(j, player);
	}
		 

	Node Noutput2 = nodes1.item(1);
	Element Eoutput2 = (Element) Noutput2;
	String nevoutput2 = Eoutput2.getAttribute("name");
	for(int j=0; j<nodes.getLength(); j=j+1)
	{
		Node Nmerged = nodes.item(j);
		Element Emerged = (Element) Nmerged;
        String nevmerged = Emerged.getAttribute("name");
		System.out.println(nevoutput2);
		System.out.println(nevmerged);
		
		if(nevoutput2.equals(nevmerged))
		{
			System.out.println("______________takarmany");
			masodiknem = 1;
			String frommerged = Emerged.getElementsByTagName("matcheswon").item(0).getTextContent();
			String fromoutput = Eoutput2.getElementsByTagName("matcheswon").item(0).getTextContent();
			int Itogether = Integer.parseInt(fromoutput) + Integer.parseInt(frommerged);
			String Stogether = Integer.toString(Itogether);
			System.out.println(Stogether);
			Emerged.getElementsByTagName("matcheswon").item(0).setTextContent(Stogether);
			
			frommerged = Emerged.getElementsByTagName("matchesplayed").item(0).getTextContent();
			Itogether = Integer.parseInt(frommerged); Itogether += 1;  
			Stogether = Integer.toString(Itogether);
			Emerged.getElementsByTagName("matchesplayed").item(0).setTextContent(Stogether);
			
			frommerged = Emerged.getElementsByTagName("matchesdraw").item(0).getTextContent();
			fromoutput = Eoutput2.getElementsByTagName("matchesdraw").item(0).getTextContent();
			Itogether = Integer.parseInt(fromoutput) + Integer.parseInt(frommerged);
			Stogether = Integer.toString(Itogether);
			Emerged.getElementsByTagName("matchesdraw").item(0).setTextContent(Stogether);

			frommerged = Emerged.getElementsByTagName("points").item(0).getTextContent();
			fromoutput = Eoutput2.getElementsByTagName("points").item(0).getTextContent();
			Itogether = Integer.parseInt(fromoutput) + Integer.parseInt(frommerged);
				String meccsek = Emerged.getElementsByTagName("matchesplayed").item(0).getTextContent();
				String nyeresek = Emerged.getElementsByTagName("matcheswon").item(0).getTextContent();
				String dontetlenek = Emerged.getElementsByTagName("matchesdraw").item(0).getTextContent();
				if (meccsek.equalsIgnoreCase("3") || meccsek.equalsIgnoreCase("5") || meccsek.equalsIgnoreCase("15") || meccsek.equalsIgnoreCase("50") 
						||
					nyeresek.equals("5") || nyeresek.equals("10") || nyeresek.equals("15") || nyeresek.equals("20")
						||
					dontetlenek.equals("1") || dontetlenek.equals("7") || dontetlenek.equals("11") || dontetlenek.equals("30"))
				{
					Itogether += 7;
				}
			Stogether = Integer.toString(Itogether);
			Emerged.getElementsByTagName("points").item(0).setTextContent(Stogether);		
			break;
		}
			else 
			{
				System.out.println("--------------------megvagy");
			}
	}
		
		
		
		if(elsonem == 0)
		{
			Node n= (Node) doc.importNode(nodes1.item(0), true);  
			nodes.item(0).getParentNode().appendChild(n);
		}
		if(masodiknem == 0)
		{
			Node n= (Node) doc.importNode(nodes1.item(1), true);  
			nodes.item(1).getParentNode().appendChild(n);
		}
		
		System.out.println(elsonem);
		System.out.println(masodiknem);			
		

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
	System.out.println("merge complete");
	
	
	return scores;
    }
	
	

	
	
	
	
	
	

//	String probalunk = Emerged.getElementsByTagName("matchesplayed").item(0).getTextContent();
//	if (probalunk.equalsIgnoreCase("3"))
//	{
//		Itogether += 7;
//	}
//
// 
//	public String hajatszott(String n)
//	{
//		Node Nmerged = nodes.item(j);
//		Element Emerged = (Element) Nmerged;
//		String probalunk = Emerged.getElementsByTagName("matchesplayed").item(0).getTextContent();
//		String asd = "0";
//		return asd; 
//	}




}