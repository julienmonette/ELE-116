package analyseur;

import javax.xml.soap.Node;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import arbre.Livre;
import arbre.Noeud;

public class MyContentHandler implements ContentHandler {

	
	Noeud noeud = new Noeud();
	private String prevXMLTag;
	
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		
		String s = new String(arg0,arg1,arg2);	
		// Ici on accède contenu (texte) du fichier xml
		if(prevXMLTag.equals("titre_livre")) 
			noeud.getLastLivre().setTitle(s);	
		
		else if(prevXMLTag.equals("auteur"))
			noeud.getLastLivre().setAuteur(s);	
		
		else if(prevXMLTag.equals("titre_chapitre"))
			noeud.getLastLivre().getLastChapitre().setTitre(s);
		
		else if(prevXMLTag.equals("paragraphe"))
			noeud.getLastLivre().getLastChapitre().getLastParapgraphe().setText(s);
		
		prevXMLTag = "";
	}

	
	@Override
	public void startElement(String arg0, String XMLTag, String arg2, Attributes arg3) throws SAXException {
		
		switch(XMLTag){
		case "livre":
			noeud.addLivre();
			break;
			
		case "chapitre":
			noeud.getLastLivre().addChapitre();
			break;	
			
		case "paragraphe":
			noeud.getLastLivre().getLastChapitre().addParagraphe();
		}
		
		prevXMLTag = XMLTag;
	}
	
	public Noeud getNoeud() {
		return noeud;
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
