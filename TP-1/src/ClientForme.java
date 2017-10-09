import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import com.sun.corba.se.pept.transport.Connection;


public class ClientForme extends JFrame{

	static private final int LARGEUR_FENETRE = 500;
	static private final int HAUTEUR_FENETRE = 500;
	static private final int POSITION_FENETRE_X = 0;
	static private final int POSITION_FENETRE_Y = 0;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuServeur = new JMenu("Serveur");
	private JMenuItem menuItemConnect = new  JMenuItem("Se connecter...");
	private JMenuItem menuItemDisconnect = new  JMenuItem("D�connection");
	
	public CommForme commForme = new CommForme();
	
	public ClientForme() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Application ClientForme");
		
		creerMenu();
		
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
	}
	

	
	public static void main(String args[] ) {
	    	
		ClientForme clientforme = new ClientForme();
			
		clientforme.setSize(LARGEUR_FENETRE,HAUTEUR_FENETRE);
		clientforme.setVisible(true);
		clientforme.setLocation(POSITION_FENETRE_X,POSITION_FENETRE_Y);

	}
	


	private void creerMenu() {
		
		menuBar.add(menuServeur);
		menuServeur.add(menuItemConnect);
		menuServeur.add(menuItemDisconnect);
		setJMenuBar(menuBar);
	}
}

