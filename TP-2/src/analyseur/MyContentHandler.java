/******************************************************
Cours : ELE116
Session : AUT2017
Groupe : 01
Projet : Laboratoire #2
�tudiant(e)(s) : 	Colin Reid-Lapierre
					Julien Monette
Code(s) perm. : 	REIC11069309
					MONJ28079501					
Professeur : Rita Noumeir
Nom du fichier : MyContentHandler.java
Date cr�ation : 2017-10-31
Date dern. modif. 2017-11-14

*******************************************************
Historique des modifications
*******************************************************
2017-11-14 Version initiale
*******************************************************/
package analyseur;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import arbre.Bibliotheque;

/**
 * Content Handler pour la lecture du fichier XML.
 * Lit un fichier xml en rempli un arbre de donn�es avec ce qu'il contient.
 * 
 * @author Colin Reid-Lapierre
 */
public class MyContentHandler implements ContentHandler {

	private String prevXMLTag;
	private Bibliotheque bibliotheque = new Bibliotheque();


	/**
	 * Reccueil le text d'une section du fichier XML. �crit l'information recceuillie dans l'arbre de donn�
	 * en fonction des balise XML dans lesquel se trouve le texte.
	 */
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		
		String s = new String(arg0,arg1,arg2);	
		
		// Ici on acc�de contenu (texte) du fichier xml
		if(prevXMLTag.equals("titre_livre")) 
			bibliotheque.getLastLivre().setTitle(s);	
		
		else if(prevXMLTag.equals("auteur"))
			bibliotheque.getLastLivre().setAuteur(s);	
		
		else if(prevXMLTag.equals("titre_chapitre"))
			bibliotheque.getLastLivre().getLastChapitre().setTitre(s);
		
		else if(prevXMLTag.equals("paragraphe"))
			bibliotheque.getLastLivre().getLastChapitre().getLastParapgraphe().setText(s);
		
		prevXMLTag = "";
	}

	/**
	 *  Ajoute les �l�ments de l'arbre de donn�es en fonction des balise XML rencontr� dans le fichier
	 *  @param XMLTag : Balise XML renontr�e dans le fichier xml
	 */
	public void startElement(String arg0, String XMLTag, String arg2, Attributes arg3) throws SAXException {
		
		switch(XMLTag){
		case "livre":
			bibliotheque.addLivre();
			break;
			
		case "chapitre":
			bibliotheque.getLastLivre().addChapitre();
			break;	
			
		case "paragraphe":
			bibliotheque.getLastLivre().getLastChapitre().addParagraphe();
		}
		
		prevXMLTag = XMLTag;
	}
	

	/**
	 * Retourne la l'arbre de donn�es
	 * @return La bibliotheque
	 */
	public Bibliotheque getBibliotheque() {
		return bibliotheque;
	}
	
	
	/*
	 * M�thodes non utilis�es par le handler
	 */
	public void endDocument() throws SAXException {}
	public void endElement(String arg0, String arg1, String arg2) throws SAXException {}
	public void endPrefixMapping(String arg0) throws SAXException {}
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {}
	public void processingInstruction(String arg0, String arg1) throws SAXException {}
	public void setDocumentLocator(Locator arg0) {}
	public void skippedEntity(String arg0) throws SAXException {}
	public void startDocument() throws SAXException {}
	public void startPrefixMapping(String arg0, String arg1) throws SAXException {}
}
