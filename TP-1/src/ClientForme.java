import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
	private JMenuItem menuItemDisconnect = new  JMenuItem("Déconnection");
	private JMenu menuCommandes = new JMenu("Commandes");
	private JMenuItem menuItemGET = new  JMenuItem("GET");
	
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
		
		menuItemGET.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ) {		 
    			commForme.envoieGET();
    			mundo_string = commForme.getString();
    			System.out.println(mundo_string);
    			tabFormes.add(mundo_string);
    			repaint();	
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
		
		menuBar.add(menuCommandes);
		menuCommandes.add(menuItemGET);
		
		setJMenuBar(menuBar);
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		tabFormes.dessinerTabForme(g);
	}
	
	
}

