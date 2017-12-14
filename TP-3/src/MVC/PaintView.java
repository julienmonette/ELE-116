package MVC;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
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
import command.ZoomInCommand;
import command.ZoomOutCommand;

public class PaintView extends JFrame implements Observer {

	static private final int WINDOW_WIDTH = 600;
	static private final int WINDOW_HEIGHT = 600;
	static private final int DEFAULT_WINDOW_X = 0;
	static private final int DEFAULT_WINDOW_Y = 0;
	static private final String WINDOW_TITLE = "Viewer";
	
	private static final double ZOOMFACTOR = 0.1;
	
	private BufferedImage image;
	private ImagePanel imagePanel;
	
	private PaintControler control;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenuItem menuItemOuvrir = new JMenuItem("Ouvrir");
	private JMenuItem menuItemSauvegarder = new JMenuItem("Sauvegarder");
	private JMenuItem menuItemToGrayScale = new JMenuItem("Noir et blanc");
	private JMenuItem menuItemQuitter = new JMenuItem("Quitter");
	private JMenuItem menuItemZoomOut = new JMenuItem("Zoom -");
	private JMenuItem menuItemZoomIn = new JMenuItem("Zoom +");
	public Container contentPane=getContentPane();
	
	
	
	private double zoom = 1;
	
	
	
	public PaintView(PaintControler control) {
			
		this.control = control;
		
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(DEFAULT_WINDOW_X, DEFAULT_WINDOW_Y);
		
		setContentPane(new Panneau());
		createMenuBar();
		
		setVisible(true);
		
		menuItemOuvrir.addActionListener(new OuvrirListener());
		menuItemSauvegarder.addActionListener(new SauvegardeListener());
		menuItemQuitter.addActionListener(new QuitterListener());
		menuItemToGrayScale.addActionListener(new ToGrayScaleListener()); 
		menuItemZoomIn.addActionListener(new ZoomInListener());
		menuItemZoomOut.addActionListener(new ZoomOutListener());
    	
	}
	
	private void createMenuBar() {
		menuBar.add(menuFichier);
		menuFichier.add(menuItemOuvrir);
		menuFichier.add(menuItemSauvegarder);
		menuFichier.add(menuItemToGrayScale);
		menuFichier.add(menuItemQuitter);
		menuFichier.add(menuItemZoomIn);
		menuFichier.add(menuItemZoomOut);
		setJMenuBar(menuBar);
	}

	public void getParameter() {
		// TODO On fait une demande au model pour obtenir les nouveaux paramï¿½tres de l'image
	}
	
	public void zoomIN()
	{
		zoom += ZOOMFACTOR;
		repaint();
	}
	public void zoomOUT()
	{
		zoom -= ZOOMFACTOR;
		repaint();
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
	    repaint();
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
	
	private class ZoomInListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ZoomInCommand zoomInCommand = new ZoomInCommand(PaintView.this);
			zoomInCommand.execute();
		}
	}
	
	private class ZoomOutListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ZoomOutCommand zoomOutCommand = new ZoomOutCommand(PaintView.this);
			zoomOutCommand.execute();
		}
	}
	
	
	public class Panneau extends JPanel{
	
			public void paintComponent(Graphics gd) {
				
				Graphics2D g = (Graphics2D) gd;
		        
		        AffineTransform t = new AffineTransform();
		        //Ici je centre l'image
		        //float currentImgWidth = img.getWidth()*zoom, currentImgHeight = img.getHeight()*zoom;
		       // t.translate(width/2-currentImgWidth/2, height/2-currentImgHeight/2);
		        //J'applique le "scale"
		        t.scale(zoom, zoom);
		        //Et j'affiche en utilisant la transformation
		        g.drawImage(image, t, null);
		         
		        //On libère un peu de mémoire histoire de laisser le GC tranquille un peu plus longtemps
		        g.dispose();
				
				
				System.out.println("Je suis exécutée !"); 
				g.drawImage(image,0,0,this);
			}               
	}
	
}



