import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PaintView extends JFrame implements Observer {

	static private final int WINDOW_WIDTH = 600;
	static private final int WINDOW_HEIGHT = 600;
	static private final int DEFAULT_WINDOW_X = 0;
	static private final int DEFAULT_WINDOW_Y = 0;
	static private final String WINDOW_TITLE = "Viewer";
	
	private PaintControler control;
	private JPanel panel = new JPanel();
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenuItem menuItemOuvrir = new JMenuItem("Ouvrir");
	private JMenuItem menuItemSauvegarder = new JMenuItem("Sauvegarder");
	
	public PaintView(PaintControler control) {
			
		this.control = control;
		
		getContentPane().add(panel);
		
		setVisible(true);
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(DEFAULT_WINDOW_X, DEFAULT_WINDOW_Y);
		
		createMenuBar();	
	
	}
	

	
	private void createMenuBar() {
		menuBar.add(menuFichier);
		menuFichier.add(menuItemOuvrir);
		menuFichier.add(menuItemSauvegarder);
		setJMenuBar(menuBar);
	}


	public void getParameter() {
		// TODO On fait une demande au model pour obtenir les nouveaux paramètres de l'image
	}
	
	class SauvegardeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO 	
		}           
	}
	
	
	class OuvrirListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      // TODO 
	    }           
	}



	
	 
}
