/******************************************************
Cours : ELE116
Session : AUT2017
Groupe : 01
Projet : Laboratoire #2
Étudiant(e)(s) : 	Colin Reid-Lapierre
					Julien Monette
Code(s) perm. : 	REIC11069309
					MONJ28079501					
Professeur : Rita Noumeir
Nom du fichier : Parser.java
Date création : 2017-10-31
Date dern. modif. 2017-11-14

*******************************************************
Historique des modifications
*******************************************************
2017-11-14 Version initiale
*******************************************************/

package analyseur;

import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Ouvre et lit le fichier xml. Envoie l'imformation reccueillies à MyContentHandler.
 * 
 * @author Colin Reid-Lapierre
 */
public class Parser {

	XMLReader reader; 
	
	/**
	 * Ouvre et  et lit le fichier "XMLFilename" et envoie le contenu au XMLHandler
	 * @param XLMFilename : fichier xml à lire
	 * @param XMLHandler : Handler auquel le contenu sera envoyé.
	 */
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
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

}
