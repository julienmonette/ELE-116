import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

public class ClientForme extends JFrame{

	static private final int LARGEUR_FENETRE = 500;
	static private final int HAUTEUR_FENETRE = 500;
	static private final int POSITION_FENETRE_X = 0;
	static private final int POSITION_FENETRE_Y = 0;

	
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuServeur = new JMenu("Serveur");
	private JMenuItem menuItemConnect = new  JMenuItem("Se connecter...");
	private JMenuItem menuItemDisconnect = new  JMenuItem("Déconnection");
	private JMenu menuCommandes = new JMenu("Commandes");
	private JMenuItem menuItemStart = new  JMenuItem("Start");
	private JMenuItem menuItemStop = new  JMenuItem("Stop");
	
	public boolean stopButton = false;
	public CommForme commForme = new CommForme();
	public TabFormes tabFormes = new TabFormes();
	
	String mundo_string;
	
	public ClientForme() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Application ClientForme");
		
		creerMenu();
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		
		menuItemConnect.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {
				commForme.startConnection();
			}
		});

		menuItemDisconnect.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e ) {				 
				commForme.envoieEND();		
			}
		});	
		
		menuItemStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ) {	 			
				stopButton = false;   	
				commencerDessin();												
			}
		});
		
		menuItemStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ) {		 
    				stopButton = true;   				
			}
		});
		
	}
	
	public static void main(String args[] ) {
	    
		//Redefine outpout port
		//Log created with date 
		String filename = new String(new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()));
		
		FileOutputStream f = null;
		try { f = new FileOutputStream(filename + "-LOG.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(new PrintStream(f));
		
		
		
		ClientForme clientforme = new ClientForme();
		
		clientforme.setSize(LARGEUR_FENETRE,HAUTEUR_FENETRE);
		clientforme.setVisible(true);
		clientforme.setLocation(POSITION_FENETRE_X,POSITION_FENETRE_Y);

	}
	
	
	private void commencerDessin(){
		
		final SwingWorker wingWorker = new SwingWorker() {


			protected Object doInBackground() throws Exception {
				
				while ( stopButton != true ) {
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}	
									
					commForme.envoieGET();
					mundo_string = commForme.getString();
					// ICI ON LOG
					System.out.println(mundo_string);
					tabFormes.add(mundo_string);
					repaint();
				}
				
				return null;
			}		
		};
		
		wingWorker.execute();

	}
	
	
	
	private void creerMenu() {
		
		menuBar.add(menuServeur);

		menuServeur.add(menuItemConnect);
		menuServeur.add(menuItemDisconnect);
		
		menuBar.add(menuCommandes);
		menuCommandes.add(menuItemStart);
		menuCommandes.add(menuItemStop);
		
		
		setJMenuBar(menuBar);
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		tabFormes.dessinerTabForme(g);
	}
	
	
}


