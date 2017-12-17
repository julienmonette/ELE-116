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
Nom du fichier : Client.java
Date création : 2017-10-31
Date dern. modif. 2017-11-14

*******************************************************
Historique des modifications
*******************************************************
2017-11-14 Version initiale
*******************************************************/


package client;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import analyseur.MyContentHandler;
import analyseur.Parser;
import arbre.Bibliotheque;
import visitor.Visitor;
import visitor.VisitorPrintAll;
import visitor.VisitorPrintTitles;


/**
 * Classe de l'application XML READER.
 * 
 * Permet de lire un fichier XML et d'afficher soit l'entièreté du contenu,
 * soit seulement le titre du livre et des chapitres.
 * 
 * @author Colin Reid-Lapierre et Julien Monette
 */
public class Client extends JFrame{

	static private final int LARGEUR_FENETRE = 500;
	static private final int HAUTEUR_FENETRE = 500;
	static private final int POSITION_FENETRE_X = 0;
	static private final int POSITION_FENETRE_Y = 0;
	static private final String HTML_FILE_NAME = "unFichierHTML.html";
	static private final String HTML_FILE_DIR_NAME = "html";
	static private final PrintStream CONSOLE = System.out;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFile = new JMenu("File");
	private JMenuItem menuItemImport = new  JMenuItem("Import");
	private JMenuItem menuItemDisplayTitles= new  JMenuItem("Display Titles");
	private JMenuItem menuItemDisplayAll = new  JMenuItem("Display Books");
	private JEditorPane editor;
	
	private Bibliotheque bibliotheque = new Bibliotheque();
	private Visitor visitorPrintAll;
	private Visitor visitorPrintTitle;

	private Parser parser = new Parser();
	private PrintStream pout=null;
	private String htmlFilePath;
	
	/**Constructeur du client. Crée entre autres l'interface Graphique.
	 * 
	 * Contient les actions listener.
	 */
	public Client() {
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("XML READER");
		
		creerMenu();
		
		menuItemImport.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {		
				
				String xmlFilename = null;
				xmlFilename = JOptionPane.showInputDialog("Entrer le nom du fichier .xml", xmlFilename);
			
				MyContentHandler XMLHandler = new MyContentHandler();
				try {
					parser.parseXMLFile(xmlFilename,XMLHandler);
					bibliotheque = XMLHandler.getBibliotheque();			
					menuItemDisplayAll.setEnabled(true);
					menuItemDisplayTitles.setEnabled(true);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(editor,"Veuillez respecter le format \"nomDuFichier.xml\" "
							+ "\nLe fichier doit être dans le même repertoire que l'exécutable.",
							"Fichier XML introuvable",
							JOptionPane.WARNING_MESSAGE);
				}	
		
				
				//logTree();
			}
		});	
		
		menuItemDisplayAll.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {	
				
				openHTMLFile();
				bibliotheque.accept(visitorPrintAll);
				closeHTMLFile();
			
				try {		
					editor = new JEditorPane();
					editor.setEditable(false);
					java.net.URL htmlfile = new URL("file:///"+htmlFilePath+"/"+HTML_FILE_NAME);
					editor.setPage(htmlfile);
				} catch (IOException e1) {		
					e1.printStackTrace();
				}		
				
				JScrollPane scrollPane = new JScrollPane(editor);
				
				getContentPane().add(scrollPane,BorderLayout.CENTER);
				setSize(LARGEUR_FENETRE,HAUTEUR_FENETRE);
				setVisible(true);
				setLocation(POSITION_FENETRE_X,POSITION_FENETRE_Y);
			}
		});	
		
		menuItemDisplayTitles.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {	
				
				openHTMLFile();
				bibliotheque.accept(visitorPrintTitle);
				closeHTMLFile();
			
				try {		
					editor = new JEditorPane();
					editor.setEditable(false);
					java.net.URL htmlfile = new URL("file:///"+htmlFilePath+"/"+HTML_FILE_NAME);
					editor.setPage(htmlfile);
				} catch (IOException e1) {		
					e1.printStackTrace();
				}		
				
				JScrollPane scrollPane = new JScrollPane(editor);
				
				getContentPane().add(scrollPane,BorderLayout.CENTER);
				setSize(LARGEUR_FENETRE,HAUTEUR_FENETRE);
				setVisible(true);
				setLocation(POSITION_FENETRE_X,POSITION_FENETRE_Y);
			}
		});	
		
	}
	
	
	/**
	 * Main du Client (Application) 
	 * @param args
	 */
	public static void main(String args[] ) {  
		
		Client client = new Client();
		client.setSize(LARGEUR_FENETRE,HAUTEUR_FENETRE);
		client.setVisible(true);
		client.setLocation(POSITION_FENETRE_X,POSITION_FENETRE_Y);
	}
	
	/**
	 * Crée les différents menus déroulants de l'inteface swing
	 */
	private void creerMenu() {	
		menuBar.add(menuFile);
		menuFile.add(menuItemImport);
		menuFile.add(menuItemDisplayTitles);
		menuFile.add(menuItemDisplayAll);
		setJMenuBar(menuBar);		
		menuItemDisplayTitles.setEnabled(false);
		menuItemDisplayAll.setEnabled(false);	
	}
	
	/**
	 *  Crée le repertoire du fichier html
	 *  Cree le fichier html et ajoute l'en-tête de ce dernier.
	 *  
	 */
	public void openHTMLFile() {
		
		File directory = new File(HTML_FILE_DIR_NAME);
		htmlFilePath = directory.getAbsolutePath();
		directory.mkdir();
		
		try {	
			pout = new PrintStream(new BufferedOutputStream(new FileOutputStream(HTML_FILE_DIR_NAME+"/"+HTML_FILE_NAME)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		visitorPrintAll = new VisitorPrintAll(pout);
		visitorPrintTitle = new VisitorPrintTitles(pout);

		pout.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2 Final//EN\">");
		pout.println("<html>");
		pout.println("<body>");
	}
	
	
	/**
	 * Finalise le fichier html et ferme ce dernier.
	 */
	public void closeHTMLFile() {
		pout.println("</body>");
		pout.println("</html>");
		pout.close();
	}	
	
	/**
	 * Redéfinition de la méthode paint.
	 * 
	 */
	public void paint(Graphics g) {	
		super.paint(g);
	}	
	

	/**
	 * Fonction pour validation. Génère logtree.txt et y écrit le livre du fichier xml importé
	 */
	public void logTree() {
		
		int iLivre;
		int iChapitre;
		int iPara;
		
		FileOutputStream f = null;
		try { f = new FileOutputStream("logTree.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(new PrintStream(f));	
		
		System.out.println("//////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("//									L O G 												//");		
	    System.out.println("//////////////////////////////////////////////////////////////////////////////////////////");
	    System.out.println("");
	    System.out.println("NB Livre     : " + bibliotheque.getNbLivre());	
	    System.out.println("NB Chapitres : " + bibliotheque.getLastLivre().getNbChapitre());
	    System.out.println("");
	    System.out.println("------------------------------------------------------------------------------------------");
	    System.out.println("");
	    
	    for(iLivre = 0; iLivre < bibliotheque.getNbLivre(); iLivre++) {
	    	System.out.println("LIVRE No."+iLivre);
	    	System.out.println("");
	    	System.out.println("	Titre  : "+bibliotheque.getLivre(iLivre).getTitle());
	    	System.out.println("	Auteur : "+bibliotheque.getLivre(iLivre).getTitle());
	    	System.out.println("");
	    	for(iChapitre = 0; iChapitre < bibliotheque.getLivre(iLivre).getNbChapitre(); iChapitre++) {
	    		System.out.println("	CHAPITRE No."+iChapitre);
	    		System.out.println("");
	    		System.out.println("		Titre : "+bibliotheque.getLivre(iLivre).getChapitre(iChapitre).getTitre());
	    		System.out.println("");
	    		for(iPara =0; iPara < bibliotheque.getLivre(iLivre).getChapitre(iChapitre).getNbParagraphes(); iPara++) {
	    			System.out.println("		  "+
	    					bibliotheque.getLivre(iLivre).getChapitre(iChapitre).getParagraphe(iPara).getText());
	    		}		
	    		System.out.println("		");
	    	}
	    }
	    System.out.println("//////////////////////////////////////////////////////////////////////////////////////////");
	    System.setOut(CONSOLE);   
	}
}




