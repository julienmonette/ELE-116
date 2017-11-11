package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import arbre.Noeud;

public class Client extends JFrame{

	static private final int LARGEUR_FENETRE = 500;
	static private final int HAUTEUR_FENETRE = 500;
	static private final int POSITION_FENETRE_X = 0;
	static private final int POSITION_FENETRE_Y = 0;
	static private final String NOM_FICHIER_HTML = "testHTML.html";
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFile = new JMenu("F I L E ");
	private JMenuItem menuItemImport = new  JMenuItem("I M P O R T ");
	private JMenuItem menuItemDisplayTitles= new  JMenuItem("D I S P L A Y   T I T L E S");
	private JMenuItem menuItemDisplayAll = new  JMenuItem("D I S P L A Y   A L L");
	private JPanel panel;
	private JEditorPane editor;
	
	Noeud noeud = new Noeud();
	Parser parser = new Parser();
	
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
				noeud = XMLHandler.getNoeud();		
				
				logTree();			
			}
		});	
		
		menuItemDisplayAll.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {				
				try {
					editor = new JEditorPane();
					editor.setEditable(false);
					java.net.URL htmlfile = Client.class.getResource("testHTML.html");
					editor.setPage(htmlfile);
			
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				JScrollPane scrollPane = new JScrollPane(editor);

				//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
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
		menuItemDisplayTitles.setEnabled(false);
		menuItemDisplayAll.setEnabled(true);	
	}
	
	/**
	 * redéfinition de la méthode paint.
	 * 
	 * On ajoute les formes contenue dans la liste de forme dans tabFormes sur la fenêtre.
	 */
	public void paint(Graphics g) {	
		super.paint(g);
	}	
	
	
	public void logTree() {
		
		int iLivre;
		int iChapitre;
		int iPara;
	
		PrintStream console = System.out;
		
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
	    System.out.println("NB Livre     : " + noeud.getNbLivre());	
	    System.out.println("NB Chapitres : " + noeud.getLastLivre().getNbChapitre());
	    System.out.println("");
	    System.out.println("------------------------------------------------------------------------------------------");
	    System.out.println("");
	    
	    for(iLivre = 0; iLivre < noeud.getNbLivre(); iLivre++) {
	    	System.out.println("LIVRE No."+iLivre);
	    	System.out.println("");
	    	System.out.println("	Titre  : "+noeud.getLivre(iLivre).getTitle());
	    	System.out.println("	Auteur : "+noeud.getLivre(iLivre).getTitle());
	    	System.out.println("");
	    	for(iChapitre = 0; iChapitre < noeud.getLivre(iLivre).getNbChapitre(); iChapitre++) {
	    		System.out.println("	CHAPITRE No."+iChapitre);
	    		System.out.println("");
	    		System.out.println("		Titre : "+noeud.getLivre(iLivre).getChapitre(iChapitre).getTitre());
	    		System.out.println("");
	    		for(iPara =0; iPara < noeud.getLivre(iLivre).getChapitre(iChapitre).getNbParagraphes(); iPara++) {
	    			System.out.println("		  "+
	    					noeud.getLivre(iLivre).getChapitre(iChapitre).getParagraphe(iPara).getText());
	    		}		
	    		System.out.println("		");
	    	}
	    	
	    	
	    }
	    System.out.println("//////////////////////////////////////////////////////////////////////////////////////////");
	    System.setOut(console);
	    
	}
	
}





