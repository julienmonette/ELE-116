package command;

import MVC.PaintView;

public class ZoomInCommand implements Command{

	private PaintView image;	
	
	
	public ZoomInCommand(PaintView image) {
		this.image = image;
	}
	
	/*
	public void ZoomIn(BufferedImage image)
	{
		this.image = image;
	}*/
	
	public void execute() {
		image.zoomIN();
	}

}
