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
Nom du fichier : MyErrorHandler.java
Date création : 2017-10-31
Date dern. modif. 2017-11-14

*******************************************************
Historique des modifications
*******************************************************
2017-11-14 Version initiale
*******************************************************/

package analyseur;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * ErrorHandler. Handle les erreur reccueillies par la parser. n'est pas utilisé dans ce projet.
 * @author Colin RL
 */
public class MyErrorHandler implements ErrorHandler {

	public void error(SAXParseException arg0) throws SAXException {}

	public void fatalError(SAXParseException arg0) throws SAXException {}

	public void warning(SAXParseException arg0) throws SAXException {}

}
