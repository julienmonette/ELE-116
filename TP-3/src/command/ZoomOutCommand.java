package command;

import MVC.PaintModel;
import MVC.PaintView;

public class ZoomOutCommand implements Command{

	private PaintModel model;
	
	public ZoomOutCommand(PaintModel model) {
		this.model= model;
	}
	
	public void execute() {
		model.zoomOUT();
	}

}
