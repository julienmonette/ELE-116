package command;

import MVC.PaintModel;
import MVC.PaintView;

public class ToGraysScaleCommand implements Command {

	private PaintModel model; // TODO est-ce qu'on ne dervait pas avoior image comme param�tre ?
	
	public ToGraysScaleCommand(PaintModel model) {
		this.model = model;
	}
	
	public void execute() {
		model.toGrayScale();
	}
}
