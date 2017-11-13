package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.parsers.*;

import analyseur.MyContentHandler;
import analyseur.Parser;
import arbre.Bibliotheque;
import visitor.Visitor;
import visitor.VisitorPrintAll;
import visitor.VisitorPrintTITLE;

public class Client extends JFrame{

	static private final int LARGEUR_FENETRE = 500;
	static private final int HAUTEUR_FENETRE = 500;
	static private final int POSITION_FENETRE_X = 0;
	static private final int POSITION_FENETRE_Y = 0;
	static private final String HTML_FILE_NAME = "unFichierHTML.html";
	static private final String HTML_FILE_DIR_NAME = "html";
	static private final PrintStream console = System.out;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFile = new JMenu("F I L E ");
	private JMenuItem menuItemImport = new  JMenuItem("I M P O R T ");
	private JMenuItem menuItemDisplayTitles= new  JMenuItem("D I S P L A Y   T I T L E S");
	private JMenuItem menuItemDisplayAll = new  JMenuItem("D I S P L A Y   A L L");
	private JEditorPane editor;
	
	public FileOutputStream HTMLFile = null;
	public Bibliotheque bibliotheque = new Bibliotheque();
	public Visitor visitorPrintAll;
	public Visitor visitorPrintTitle;

	Parser parser = new Parser();
	public PrintStream pout=null;
	public String htmlFilePath;
	
	/**Constructeur du client. Crée entre autres l'interface Graphique.
	 * 
	 * Contient les actions listener.
	 */
	public Client() {
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Application");
		
		creerMenu();
		
		menuItemImport.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {		
				
				MyContentHandler XMLHandler = new MyContentHandler();
				parser.parseXMLFile("monLivre.xml",XMLHandler);	
				bibliotheque = XMLHandler.getBibliotheque();		
				
				logTree();			
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
	
	
	/** main Client
	 * 
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
		menuItemDisplayTitles.setEnabled(true);
		menuItemDisplayAll.setEnabled(true);	
	}
	
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
		visitorPrintTitle = new VisitorPrintTITLE(pout);

		pout.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2 Final//EN\">");
		pout.println("<html>");
		pout.println("<body>");
	}
	
	public void closeHTMLFile() {
		pout.println("</body>");
		pout.println("</html>");
		pout.close();
	}	
	
	/**
	 * redéfinition de la méthode paint.
	 * 
	 */
	public void paint(Graphics g) {	
		super.paint(g);
	}	
	
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
	    System.setOut(console);
	    
	}
	
}




