package MVC;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import command.OpenImageCommand;
import command.ToGraysScaleCommand;
import command.TranslationCommand;
import command.UndoCommand;
import command.ZoomInCommand;
import command.ZoomOutCommand;

public class PaintView extends JFrame implements Observer, MouseWheelListener, MouseListener, MouseMotionListener {

	static private final int WINDOW_WIDTH = 600;
	static private final int WINDOW_HEIGHT = 600;
	static private final int DEFAULT_WINDOW_X = 0;
	static private final int DEFAULT_WINDOW_Y = 0;
	static private final String WINDOW_TITLE = "Viewer";
	static private final double TRANSLATION_FACTOR = 0.5;	
	
	private PaintControler control;
	private PaintModel model;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenu menuAction = new JMenu("Action");
	private JMenuItem menuItemOuvrir = new JMenuItem("Ouvrir");
	private JMenuItem menuItemSauvegarder = new JMenuItem("Sauvegarder");
	private JMenuItem menuItemQuitter = new JMenuItem("Quitter");
	private JMenuItem menuItemToGrayScale = new JMenuItem("Noir et blanc");
	private JMenuItem menuItemZoomOut = new JMenuItem("Zoom -");
	private JMenuItem menuItemZoomIn = new JMenuItem("Zoom +");
	private JMenuItem menuItemUndo = new JMenuItem("Undo");

	public Container contentPane=getContentPane();
	
	private boolean isDragged = false; 
	private Point oldMousePosition;
	private Point currentMousePosition;

	// Image State
	private int imageXPos = 0;
	private int imageYPos = 0;
	private double imageZoom;
	private BufferedImage image = null;
	
	public PaintView(PaintControler control, PaintModel model) {
		
		this.control = control;
		this.model = model;
		model.addObserver(this);
		
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(DEFAULT_WINDOW_X, DEFAULT_WINDOW_Y);
		MyPanel pane = new MyPanel();
		setContentPane(pane);
		createMenuBar();
		setVisible(true);
		
		menuItemOuvrir.addActionListener(new OuvrirListener());
		menuItemSauvegarder.addActionListener(new SauvegardeListener());
		menuItemQuitter.addActionListener(new QuitterListener());
		menuItemToGrayScale.addActionListener(new ToGrayScaleListener()); 
		menuItemZoomIn.addActionListener(new ZoomInListener());
		menuItemZoomOut.addActionListener(new ZoomOutListener());
		menuItemUndo.addActionListener(new UndoListener());

		pane.addMouseMotionListener(this);
		pane.addMouseListener(this);
		pane.addMouseWheelListener(this);
	}
	
	private void createMenuBar() {
		menuBar.add(menuFichier);
		menuBar.add(menuAction);
		menuFichier.add(menuItemOuvrir);
		menuFichier.add(menuItemSauvegarder);
		menuFichier.add(menuItemQuitter);
		menuAction.add(menuItemUndo);
		//menuAction.add(menuItemToGrayScale);
		menuAction.add(menuItemZoomIn);
		menuAction.add(menuItemZoomOut);
		setJMenuBar(menuBar);
		
		menuItemSauvegarder.setEnabled(false);
		menuItemToGrayScale.setEnabled(false);
		menuItemUndo.setEnabled(false);
		menuItemZoomIn.setEnabled(false);
		menuItemZoomOut.setEnabled(false);	
	}

	public void getParameter() {
		this.image = model.getImage();
		this.imageZoom = model.getZoom();
		this.imageXPos = model.getXPos(); 
		this.imageYPos = model.getYPos(); 		
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
	    		BufferedImage newimage = (BufferedImage) ImageIO.read(imageFile);
		    	control.addCommand(new OpenImageCommand(newimage, model));
		    	ImagePanel panel=new ImagePanel(newimage);
		    	setSize(newimage.getWidth(),newimage.getHeight());
		    	contentPane.add(panel);
				
				menuItemUndo.setEnabled(true);
				menuItemZoomIn.setEnabled(true);
				menuItemZoomOut.setEnabled(true);
		    	menuItemSauvegarder.setEnabled(true);
				menuItemToGrayScale.setEnabled(true);

		    	repaint();	
	
			} catch (IOException e1) { e1.printStackTrace();}
	    }           
	}
	
	private class UndoListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			control.addCommand( new UndoCommand(model));
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

	public void mousePressed(MouseEvent e) {
		oldMousePosition = e.getPoint(); 
		isDragged = true;
	}

	public void mouseReleased(MouseEvent e) {
		currentMousePosition = e.getPoint();
		if(isDragged) {
			int xTranslation = (int) (TRANSLATION_FACTOR*(currentMousePosition.getX() - oldMousePosition.getX()));
			int yTranslation = (int) (TRANSLATION_FACTOR*(currentMousePosition.getY() - oldMousePosition.getY()));
			control.addCommand(new TranslationCommand(model, xTranslation, yTranslation));
		}
		isDragged = false;
	}
	
	public void mouseWheelMoved(MouseWheelEvent w) {
		if(w.getWheelRotation() < 0) {
			control.addCommand( new ZoomOutCommand(model));
		}
		else {
			control.addCommand( new ZoomInCommand(model));
		}
	}
	
	public class MyPanel extends JPanel{
	
		public void paintComponent(Graphics gd) {
			Graphics2D g = (Graphics2D) gd;
			AffineTransform modif = new AffineTransform();
		    modif.scale(imageZoom, imageZoom);
		    modif.translate(imageXPos,imageYPos);
	        g.drawImage(image, modif, null);
	        g.dispose();
		}             
	}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e)   {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e)  {}

}



