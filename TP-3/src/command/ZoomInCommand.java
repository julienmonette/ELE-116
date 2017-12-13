package command;

import java.awt.image.BufferedImage;

public class ZoomInCommand implements Command{

	private BufferedImage image;	
	
	
	public void ZoomIn(BufferedImage image)
	{
		this.image = image;
	}
	
	@Override
	public void execute() {
		//image.zoomIN();
		
	}

}
