package client;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class Client extends JFrame{

	static private final int LARGEUR_FENETRE = 500;
	static private final int HAUTEUR_FENETRE = 500;
	static private final int POSITION_FENETRE_X = 0;
	static private final int POSITION_FENETRE_Y = 0;
	static private final int DELAI_AFFICHAGE_FORMES = 500;
	static private final String DEFAULT_PORT_NUMBER = "10000";
	static private final String DEFAULT_NUMBER_OF_SHAPES = "15"; 
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuFile = new JMenu("F I L E ");
	private JMenuItem menuItemImport = new  JMenuItem("I M P O R T ");
	
	private JPanel panel;
	
	
	/**Constructeur du client. Crée entre autres l'interface Graphique.
	 * 
	 * Contient les actions listener.
	 */
	public Client() {
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Application ClientForme");
		
		creerMenu();
		
		panel = new JPanel();
		getContentPane().add(panel);		
		
		menuItemImport.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {		
				
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
		client.setResizable(false);
	}
	
	/**
	 * Crée les différents menus déroulants de l'inteface swing
	 */
	private void creerMenu() {	
		menuBar.add(menuFile);
		menuFile.add(menuItemImport);
		setJMenuBar(menuBar);	
	}
	
	/**
	 * redéfinition de la méthode paint.
	 * 
	 * On ajoute les formes contenue dans la liste de forme dans tabFormes sur la fenêtre.
	 */
	public void paint(Graphics g) {	
		super.paint(g);
	}	
}
