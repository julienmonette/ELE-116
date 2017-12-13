package command;

import MVC.PaintView;

public class ToGraysScaleCommand implements Command {

	private PaintView image;
	
	public ToGraysScaleCommand(PaintView image) {
		this.image = image;
	}
	
	public void execute() {
		image.toGrayScale();
	}
	
}
