package command;

import MVC.PaintModel;

public class ListeCommandesCommand implements Command{
private PaintModel model;	
	
	
	public ListeCommandesCommand(PaintModel model) {
		this.model = model;
	}
	
	/*
	public void ZoomIn(BufferedImage image)
	{
		this.image = image;
	}*/
	
	public void execute() {
		model.printCommandes();;
	}

}
