package copypasta;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OperationColler implements Strategie {

	public BufferedImage Operation(BufferedImage image) {
		
		Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor))
		{
		  try
		  {
		    return (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
		  }
		  catch (UnsupportedFlavorException e)
		  {
		    // handle this as desired
		    e.printStackTrace();
		  }
		  catch (IOException e)
		  {
		    // handle this as desired
		    e.printStackTrace();
		  }
		}
		else
		{
		  System.err.println("OperationColler: ce n'etait as une image!");
		}
		return null;
	}
		
}
