package analyseur;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class MyContentHandler implements ContentHandler {

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		// Ici on acc�de contenu (texte) du fichier xml
		System.out.println(new String(arg0,arg1,arg2));
		// TODO Ajouter le contenu dans la section data du noeud correcpondant
	}

	
	@Override
	public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {
		// Ici on acc�de au tag du fichier xml
		System.out.println(arg1);
		
		// TODO Ajouter le noeud correspondand dans l'arbre
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String arg0, String arg1) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException {
		
	}


	@Override
	public void startPrefixMapping(String arg0, String arg1) throws SAXException {

		
	}

}
