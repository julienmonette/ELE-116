package analyseur;

import java.io.IOException;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import arbre.Bibliotheque;


public class Parser {

	XMLReader reader; 
	
	public void parseXMLFile(String XLMFilename, MyContentHandler XMLHandler) {
		try{
			reader = XMLReaderFactory.createXMLReader();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		reader.setContentHandler(XMLHandler);
		reader.setErrorHandler(new MyErrorHandler());
		try {
			reader.parse(XLMFilename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
