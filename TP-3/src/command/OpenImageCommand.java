package command;

import java.awt.image.BufferedImage;

import MVC.PaintModel;
import MVC.PaintView;

public class OpenImageCommand implements Command{

	private BufferedImage image;
	private PaintModel model;
	
	public OpenImageCommand(BufferedImage image, PaintModel model) {
		this.image = image;
		this.model = model;
	}
	
	public void execute() {
		model.OpenImage(image);
	}
}
