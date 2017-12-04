package MVC;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private BufferedImage image = null;
	
	public ImagePanel(BufferedImage image) {	
		this.image = image;
		this.setVisible(true);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
	
}