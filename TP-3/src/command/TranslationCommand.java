package command;

import MVC.PaintModel;
import MVC.PaintView;

public class TranslationCommand implements Command {

	private PaintModel model;
	private int xTranslation;
	private int yTranslation;
	
	public TranslationCommand(PaintModel model, int x , int y) {
		this.model = model;
		this.xTranslation = x;
		this.yTranslation = y;
	}
	
	public void execute() {
		model.applyTranslation(xTranslation,yTranslation);
	}

}
