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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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

import command.OpenImageCommand;
import command.ToGraysScaleCommand;
import command.ZoomInCommand;
import command.ZoomOutCommand;

public class PaintView extends JFrame implements Observer, MouseWheelListener{

	static private final int WINDOW_WIDTH = 600;
	static private final int WINDOW_HEIGHT = 600;
	static private final int DEFAULT_WINDOW_X = 0;
	static private final int DEFAULT_WINDOW_Y = 0;
	static private final String WINDOW_TITLE = "Viewer";
	
	private double imageZoom;
	private BufferedImage image = null;
	
	private PaintControler control;
	private PaintModel model;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenuItem menuItemOuvrir = new JMenuItem("Ouvrir");
	private JMenuItem menuItemSauvegarder = new JMenuItem("Sauvegarder");
	private JMenuItem menuItemToGrayScale = new JMenuItem("Noir et blanc");
	private JMenuItem menuItemQuitter = new JMenuItem("Quitter");
	private JMenuItem menuItemZoomOut = new JMenuItem("Zoom -");
	private JMenuItem menuItemZoomIn = new JMenuItem("Zoom +");
	public Container contentPane=getContentPane();
	
	private ImagePanel imagePanel;
	private MouseListener mouselistener;
	
	
	public PaintView(PaintControler control, PaintModel model) {
		
		
		this.control = control;
		this.model = model;
		
		model.addObserver(this);
		
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(DEFAULT_WINDOW_X, DEFAULT_WINDOW_Y);
		Panneau pane = new Panneau();
		setContentPane(pane);
		createMenuBar();
		setVisible(true);
		
		menuItemOuvrir.addActionListener(new OuvrirListener());
		menuItemSauvegarder.addActionListener(new SauvegardeListener());
		menuItemQuitter.addActionListener(new QuitterListener());
		menuItemToGrayScale.addActionListener(new ToGrayScaleListener()); 
		menuItemZoomIn.addActionListener(new ZoomInListener());
		menuItemZoomOut.addActionListener(new ZoomOutListener());
		pane.addMouseWheelListener(this);
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
		this.image = model.getImage();
		this.imageZoom = model.getZoom();
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
				BufferedImage newimage = ImageIO.read(imageFile);
		    	control.addCommand(new OpenImageCommand(newimage, model));
		    	ImagePanel panel=new ImagePanel(newimage);
		    	setSize(newimage.getWidth(),newimage.getHeight());
		    	contentPane.add(panel);
		    	repaint();
	
			} catch (IOException e1) { e1.printStackTrace();}
	    }           
	}
	
	private class ToGrayScaleListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {	   
			control.addCommand(new ToGraysScaleCommand(model));
		}
	}
	
	
	private class QuitterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}           
	}
	
	private class ZoomInListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			control.addCommand(new ZoomInCommand(model));
		}
	}
	
	private class ZoomOutListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			control.addCommand(new ZoomOutCommand(model));
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent w) {
		if(w.getWheelRotation() < 0) {
			control.addCommand( new ZoomOutCommand(model));
		}
		else {
			control.addCommand( new ZoomInCommand(model));
		}
	}
	
	
	public class Panneau extends JPanel{
	
			public void paintComponent(Graphics gd) {
				
				Graphics2D g = (Graphics2D) gd;
				getParameter();
		        AffineTransform modif = new AffineTransform();
		        modif.scale(imageZoom, imageZoom);
		        g.drawImage(image, modif, null);
		         
		        //On libère un peu de mémoire histoire de laisser le GC tranquille un peu plus longtemps
		        g.dispose();
				g.drawImage(image,0,0,this);
			}               
	}


	


	
}



