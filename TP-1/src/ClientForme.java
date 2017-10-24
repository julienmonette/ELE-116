/******************************************************
Cours : ELE116
Session : AUT2017
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : 	Colin Reid-Lapierre
					Julien Monette
Code(s) perm. : 	REIC11069309
					XXXXXXXXX					
Professeur : Rita Noumeir
Nom du fichier : ClientForme.java
Date création : 2017-09-11
Date dern. modif. 2017-10-18

*******************************************************
Historique des modifications
*******************************************************
2017-09-11 Version initiale
*******************************************************/


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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.util.concurrent.Future;
import javax.swing.JOptionPane;

/**
 * Classe de l'application ClientForme
 * 
 * Recoit des formes du serveur "ServeurForme" pour ensuite les afficher sur l'interface utilisateur.
 * 
 * @author Colin Reid-Lapierre, Julien Monette
 */
public class ClientForme extends JFrame{

	static private final int LARGEUR_FENETRE = 500;
	static private final int HAUTEUR_FENETRE = 500;
	static private final int POSITION_FENETRE_X = 0;
	static private final int POSITION_FENETRE_Y = 0;
	static private final int DELAI_AFFICHAGE_FORMES = 500;
	static private final String DEFAULT_PORT_NUMBER = "10000";
	static private final String DEFAULT_NUMBER_OF_SHAPES = "15"; 
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuServeur = new JMenu("Serveur");
	private JMenuItem menuItemConnect = new  JMenuItem("Se connecter...");
	private JMenuItem menuItemDisconnect = new  JMenuItem("Déconnection");
	private JMenuItem menuItemSetPort = new  JMenuItem("Paramètres Serveur");
	
	private JMenu menuCommandes = new JMenu("Formes");
	private JMenuItem menuItemStart = new  JMenuItem("Démarrer");
	private JMenuItem menuItemPause = new JMenuItem("Pause");
	private JMenuItem menuItemStop = new  JMenuItem("Arrêt");
	private JMenuItem menuItemShapeConfig = new  JMenuItem("Paramètres Forme");
	
	public boolean isStopped = false;
	public boolean isPaused =  false;
	
	public CommForme commForme = new CommForme();
	public TabFormes tabFormes = new TabFormes();
	
	public String forme;
	
	public String localHostPort = DEFAULT_PORT_NUMBER;
	public String numberOfShapes = DEFAULT_NUMBER_OF_SHAPES;
	public int shapesDisplayed = 0;
	
	private JPanel panel;
	
	private int mundo = 0;
	
	
	/**Constructeur du Client forme. Crée entre autres l'interface Graphique.
	 * 
	 * Contient les actions listener.
	 */
	public ClientForme() {
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Application ClientForme");
		
		creerMenu();
		
		panel = new JPanel();
		getContentPane().add(panel);		
		
		// JOptionPane portNumberBox = new JOptionPane();
		//JOptionPane wrongPortNumberMessage = new JOptionPane();	
		
		menuItemConnect.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {
				
				try {
					commForme.startConnection(Integer.parseInt(localHostPort));
					menuItemStart.setEnabled(true);
					menuItemConnect.setEnabled(false);
					menuItemDisconnect.setEnabled(true);
					menuItemSetPort.setEnabled(false);
					
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(panel,"Numéro de port incorrect. ( Par défaut : 10000 )",
							"Connection à ServeurForme impossible",
							JOptionPane.WARNING_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(panel,"Vérifier que ServeurForme est actif.",
							"Connection à ServeurForme impossible",
							JOptionPane.WARNING_MESSAGE);
				}
	
			}
		});

		menuItemDisconnect.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {				 
				commForme.envoieEND();				
				menuItemConnect.setEnabled(true);
				menuItemDisconnect.setEnabled(false);
				menuItemStop.setEnabled(false);
    			menuItemStart.setEnabled(false);
    			menuItemSetPort.setEnabled(true);
				
			}
		});	
		
		menuItemStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ) {	 			
				isStopped = false;
				isPaused  = false;
				drawShapes();	
				
				menuItemPause.setEnabled(true);
				menuItemStart.setEnabled(false);
				menuItemStop.setEnabled(true);
			}
		});
		
		
		menuItemPause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ) {	 			
				isPaused = true;
				
				menuItemStart.setEnabled(true);
				menuItemPause.setEnabled(false);
			}
		});
		
		
		menuItemStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ) {		 
    			isStopped = true;   
    			
    			menuItemStop.setEnabled(false);
    			menuItemStart.setEnabled(true);
			}
		});
		
		menuItemSetPort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ) {		 
				
				localHostPort = JOptionPane.showInputDialog(
						"Port de ServeurForme : ", localHostPort);
			}
		});		
		
		menuItemShapeConfig.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ) {		 
    		
				numberOfShapes = JOptionPane.showInputDialog(
						"Nombre de formes à afficher : ", numberOfShapes);
				try{
					Integer.parseInt(numberOfShapes);
				}
				catch(NumberFormatException e3) {
					JOptionPane.showMessageDialog(panel, "Format incorrect", "erreur",
							JOptionPane.INFORMATION_MESSAGE);
					numberOfShapes = DEFAULT_NUMBER_OF_SHAPES;
				}
			}
		});		
				
	}
	
	/** Méthode Main de ClienForme
	 * 
	 * @param args
	 */
	public static void main(String args[] ) {
	    
		//Redefine outpout port
		//Log created with date 
		String filename = new String(new SimpleDateFormat("yyyy-MM-dd - HHmmss").format(new Date()));
		
		FileOutputStream f = null;
		try { f = new FileOutputStream(filename + "-LOG.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(new PrintStream(f));		
	
		
		ClientForme clientForme = new ClientForme();
		clientForme.setSize(LARGEUR_FENETRE,HAUTEUR_FENETRE);
		clientForme.setVisible(true);
		clientForme.setLocation(POSITION_FENETRE_X,POSITION_FENETRE_Y);
		clientForme.setResizable(false);
	}
	
	/**
	 *  Dessine le nombre de formes désirés sur l'interface
	 *  
	 */
	private void drawShapes(){

		final SwingWorker swingWorker = new SwingWorker() {
		
			/*
			 * On utilise doInBackground pour que le reste du programme puisse continuer
			 * de fonctionner en même temps.
			 */
			protected Object doInBackground() throws Exception {
				
				int shapesToDisplay = Integer.parseInt(numberOfShapes);
				shapesDisplayed = 0;
				while ( !isStopped  && ( shapesDisplayed < shapesToDisplay )  ) {
					try {
						Thread.sleep(DELAI_AFFICHAGE_FORMES);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					if(!isPaused) {
						commForme.envoieGET();
						forme = commForme.getString();	
						tabFormes.add(forme);
						repaint();
						shapesDisplayed++;	
						
						System.out.println(forme);	
					}
				}	
				commForme.endConnection();
				menuItemConnect.setEnabled(true);
				menuItemDisconnect.setEnabled(false);
				menuItemStop.setEnabled(false);
				menuItemPause.setEnabled(false);
    			menuItemStart.setEnabled(false);
    			menuItemSetPort.setEnabled(true);
				
    			return null;
			}		
		};
		swingWorker.execute();
	}
	
	/**
	 * Crée les différents menus déroulants de l'inteface swing
	 */
	private void creerMenu() {	
		menuBar.add(menuServeur);
		menuServeur.add(menuItemConnect);
		menuServeur.add(menuItemDisconnect);
		menuServeur.add(menuItemSetPort);
		
		menuBar.add(menuCommandes);
		menuCommandes.add(menuItemStart);
		menuCommandes.add(menuItemPause);
		menuCommandes.add(menuItemStop);
		menuCommandes.add(menuItemShapeConfig);
		setJMenuBar(menuBar);
		
		menuItemPause.setEnabled(false);
		menuItemDisconnect.setEnabled(false);
		menuItemStop.setEnabled(false);
		menuItemStart.setEnabled(false);
	}
	
	/**
	 * redéfinition de la méthode paint.
	 * 
	 * On ajoute les formes contenue dans la liste de forme dans tabFormes sur la fenêtre.
	 */
	public void paint(Graphics g) {	
		super.paint(g);
		tabFormes.dessinerTabForme(g);
		super.setJMenuBar(menuBar);
	}
	
}


