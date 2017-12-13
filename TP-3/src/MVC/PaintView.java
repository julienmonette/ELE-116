package MVC;
import java.awt.BorderLayout;
import java.awt.Color;
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

import command.ToGraysScaleCommand;

public class PaintView extends JFrame implements Observer {

	static private final int WINDOW_WIDTH = 600;
	static private final int WINDOW_HEIGHT = 600;
	static private final int DEFAULT_WINDOW_X = 0;
	static private final int DEFAULT_WINDOW_Y = 0;
	static private final String WINDOW_TITLE = "Viewer";
	
	private static final double ZOOMFACTOR = 0.01;
	
	private BufferedImage image;
	private ImagePanel imagePanel;
	
	private PaintControler control;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenuItem menuItemOuvrir = new JMenuItem("Ouvrir");
	private JMenuItem menuItemSauvegarder = new JMenuItem("Sauvegarder");
	private JMenuItem menuItemToGrayScale = new JMenuItem("Noir et blanc");
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
		menuItemToGrayScale.addActionListener(new ToGrayScaleListener()); 
    	
	}
	
	private void createMenuBar() {
		menuBar.add(menuFichier);
		menuFichier.add(menuItemOuvrir);
		menuFichier.add(menuItemSauvegarder);
		menuFichier.add(menuItemToGrayScale);
		menuFichier.add(menuItemQuitter);
		setJMenuBar(menuBar);
	}

	public void getParameter() {
		// TODO On fait une demande au model pour obtenir les nouveaux param�tres de l'image
	}
	
	public void zoomIN()
	{
		//image = image.getScaledInstance(image.getWidth()*(-1)*ZOOMFACTOR, image.getHeight()*ZOOMFACTOR, hints)
		image = (BufferedImage)image.getScaledInstance((int)(image.getWidth()*(1-ZOOMFACTOR)), (int)(image.getHeight()*(1-ZOOMFACTOR)), image.SCALE_SMOOTH);
	}
	public void zoomOUT()
	{
		//image = image.getScaledInstance(image.getWidth()*ZOOMFACTOR, height, hints)
		image = (BufferedImage)image.getScaledInstance((int)(image.getWidth()*(ZOOMFACTOR)), (int)(image.getHeight()*(ZOOMFACTOR)), image.SCALE_SMOOTH);
	}
	
	public  void toGrayScale() {
		for( int i = 0; i < image.getWidth(); ++i ){
			for( int j = 0; j < image.getHeight(); ++j ) {
				Color color = new Color(image.getRGB(i,j));
				int gray = (color.getRed() + color.getGreen() + color.getBlue() )/3;
				Color grayPixel = new Color(gray, gray, gray);
				image.setRGB(i,j,grayPixel.getRGB());
			}
		}
		repaint();
	}
	
	
	private class SauvegardeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}           
	}
	
	private class OuvrirListener implements ActionListener{
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
	
	private class ToGrayScaleListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		   ToGraysScaleCommand mundoCommand = new ToGraysScaleCommand(PaintView.this);
		   mundoCommand.execute();
		}
	}
	
	
	private class QuitterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}           
	}

}



