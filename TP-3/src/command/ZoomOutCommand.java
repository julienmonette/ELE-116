package command;

import MVC.PaintView;

public class ZoomOutCommand implements Command{

	private PaintView image;
	
	public ZoomOutCommand(PaintView image) {
		this.image = image;
	}
	
	public void execute() {
		image.zoomOUT();
	}

}
