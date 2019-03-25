package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.xml.sax.SAXException;

import model.Merge;
import view.ToplistController;

public class TestHighScores {

	@Test
	public void playersNotNull() throws TransformerException, ParserConfigurationException, IOException, SAXException {
		Map<Integer, Map> playersWithScores = Merge.szamolj();
		
		assertNotEquals(null, playersWithScores.get(0).get("name"));
	}
}
