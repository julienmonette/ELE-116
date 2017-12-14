package command;

import MVC.PaintModel;
import MVC.PaintView;

public class ZoomInCommand implements Command{

	private PaintModel model;	
	
	
	public ZoomInCommand(PaintModel model) {
		this.model = model;
	}
	
	/*
	public void ZoomIn(BufferedImage image)
	{
		this.image = image;
	}*/
	
	public void execute() {
		model.zoomIN();
	}

}
