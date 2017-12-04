import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private BufferedImage image;
	private ImagePanel imagePanel;
	
	private PaintControler control;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenuItem menuItemOuvrir = new JMenuItem("Ouvrir");
	private JMenuItem menuItemSauvegarder = new JMenuItem("Sauvegarder");
	private JMenuItem menuItemQuitter = new JMenuItem("Quitter");
	Container contentPane=getContentPane();
	
	public PaintView(PaintControler control) {
			
		this.control = control;
		
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(DEFAULT_WINDOW_X, DEFAULT_WINDOW_Y);
		
		
		createMenuBar();
		
		setVisible(true);
		
		menuItemOuvrir.addActionListener(new OuvrirListener());
		menuItemSauvegarder.addActionListener(new SauvegardeListener());
		menuItemQuitter.addActionListener(new QuitterListener());
    	
	}
	
	private void createMenuBar() {
		menuBar.add(menuFichier);
		menuFichier.add(menuItemOuvrir);
		menuFichier.add(menuItemSauvegarder);
		menuFichier.add(menuItemQuitter);
		setJMenuBar(menuBar);
	}


	public void getParameter() {
		// TODO On fait une demande au model pour obtenir les nouveaux param�tres de l'image
	}
	
	class SauvegardeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}           
	}
	
	class OuvrirListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(null);
			File imageFile =  fileChooser.getSelectedFile();
	    	try {
				image = ImageIO.read(imageFile);
			} catch (IOException e1) {}
	    	
	    	ImagePanel panel=new ImagePanel(image);
	    	setSize(image.getWidth(),image.getHeight());
	    	contentPane.add(panel);
	    }           
	}
	
	class QuitterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}           
	}
}



