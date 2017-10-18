/******************************************************
Cours : ELE116
Session : AUT2017
Groupe : 01
Projet : Laboratoire #1
�tudiant(e)(s) : 	Colin Reid-Lapierre
					Julien Monette
Code(s) perm. : 	REIC11069309
					XXXXXXXXX					
Professeur : Rita Noumeir
Nom du fichier : ClientForme.java
Date cr�ation : 2017-09-11
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



public class ClientForme extends JFrame{

	static private final int LARGEUR_FENETRE = 500;
	static private final int HAUTEUR_FENETRE = 500;
	static private final int POSITION_FENETRE_X = 0;
	static private final int POSITION_FENETRE_Y = 0;
	static private final int DELAI_AFFICHAGE_FORMES = 500;
	static private final String DEFAULT_PORT_NUMBER = "10000";
	static private final String DEFAULT_NUMBER_OF_SHAPES = "2"; 
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuServeur = new JMenu("Serveur");
	private JMenuItem menuItemConnect = new  JMenuItem("Se connecter...");
	private JMenuItem menuItemDisconnect = new  JMenuItem("D�connection");
	private JMenuItem menuItemSetPort = new  JMenuItem("Param�tres serveur");
	
	private JMenu menuCommandes = new JMenu("Commandes");
	private JMenuItem menuItemStart = new  JMenuItem("Start");
	private JMenuItem menuItemStop = new  JMenuItem("Stop");
	private JMenuItem menuItemShapeConfig = new  JMenuItem("Param�tre Forme");
	
	
	public boolean stopButton = false;
	public CommForme commForme = new CommForme();
	public TabFormes tabFormes = new TabFormes();
	
	String forme;
	
	public String localHostPort = DEFAULT_PORT_NUMBER;
	public String numberOfShapes = DEFAULT_NUMBER_OF_SHAPES;
			
	private int shapesDisplayed = 0;
	
	
	public ClientForme() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Application ClientForme");
		
		creerMenu();
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
			
		
		JOptionPane portNumberBox = new JOptionPane();
		JOptionPane wrongPortNumberMessage = new JOptionPane();
		
		
		menuItemConnect.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {
				
				try {
					commForme.startConnection(Integer.parseInt(localHostPort));
					menuItemStart.setEnabled(true);
					menuItemConnect.setEnabled(false);
					menuItemDisconnect.setEnabled(true);
					menuItemSetPort.setEnabled(false);
					
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(panel, "V�rifier le num�ro de port !", "Connection impossible",
							wrongPortNumberMessage.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(panel, "V�rifier le num�ro de port !", "Connection impossible",
							wrongPortNumberMessage.INFORMATION_MESSAGE);
	
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
				stopButton = false;   	
				commencerDessin();	
				
				menuItemStop.setEnabled(true);
				menuItemStart.setEnabled(false);
			}
		});
		
		menuItemStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ) {		 
    			stopButton = true;   
    			
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
					"Nombre de formes � afficher : ", numberOfShapes);
			}
		});		
		
		
		
	}
	
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
	
	
	private void commencerDessin(){

		final SwingWorker swingWorker = new SwingWorker() {

			protected Object doInBackground() throws Exception {
				
				int shapesToDisplay = Integer.parseInt(numberOfShapes);
				shapesDisplayed = 0;
				
				while ( (stopButton != true ) && ( shapesDisplayed < shapesToDisplay )  ) {
					
					try {
						Thread.sleep(DELAI_AFFICHAGE_FORMES);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}					
					commForme.envoieGET();
					forme = commForme.getString();				
					tabFormes.add(forme);
					repaint();
					shapesDisplayed++;	
					
					System.out.println(forme);
				}	
				return null;
			}		
		};
		swingWorker.execute();
	}
	
	
	private void creerMenu() {	
		menuBar.add(menuServeur);
		menuServeur.add(menuItemConnect);
		menuServeur.add(menuItemDisconnect);
		menuServeur.add(menuItemSetPort);
		
		menuBar.add(menuCommandes);
		menuCommandes.add(menuItemStart);
		menuCommandes.add(menuItemStop);
		menuCommandes.add(menuItemShapeConfig);
		setJMenuBar(menuBar);
		
		menuItemDisconnect.setEnabled(false);
		menuItemStop.setEnabled(false);
		menuItemStart.setEnabled(false);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		tabFormes.dessinerTabForme(g);
	}
	
}


