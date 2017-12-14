package command;

import MVC.PaintModel;
import MVC.PaintView;

public class ToGraysScaleCommand implements Command {

	private PaintModel model;
	
	public ToGraysScaleCommand(PaintModel model) {
		this.model = model;
	}
	
	public void execute() {
		model.toGrayScale();
	}
	
}
