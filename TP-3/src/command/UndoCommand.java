package command;

import MVC.PaintModel;

public class UndoCommand implements Command {

	private PaintModel model;
	
	public UndoCommand(PaintModel m) {
		this.model = m;
	}
	
	public void execute() {
		model.undoAction();
	}

}
