package copypasta;

import java.awt.*;
import java.awt.image.*;
import java.awt.datatransfer.*;
import java.io.*;

public class CopiePP implements ClipboardOwner {
	
	private class TransferableImage implements Transferable{
		
		Image i;
		
		public TransferableImage(Image I) {
			this.i = I;
		}

		@Override
		public Image getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

			if (flavor.equals(DataFlavor.imageFlavor) && i!=null) {
				return i;
			}else throw new UnsupportedFlavorException(flavor);
			
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			DataFlavor[] flavors = new DataFlavor[ 1 ];
            flavors[ 0 ] = DataFlavor.imageFlavor;
            return flavors;
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			DataFlavor[] flavors = getTransferDataFlavors();
	        for ( int i = 0; i < flavors.length; i++ ) {
	        	if ( flavor.equals( flavors[ i ] ) ) {
	        		return true;
	               }
	            }
	             
	            return false;			
	    }
		
	}
	
	
	public BufferedImage CopierImage(BufferedImage image) {
		
		TransferableImage Im = new TransferableImage(image);
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        c.setContents( Im, this );		
		
		return null;
	}


	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
        System.out.println( "Perdu la possession du presse-papier!" );		
	}
	
	
}
