package copypasta;

import java.awt.image.BufferedImage;

public class OperationCopier implements Strategie {

	public BufferedImage Operation(BufferedImage image) {
		CopiePP ci = new CopiePP();
		ci.CopierImage(image);
		return null;
	}
	
}
