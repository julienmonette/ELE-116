import java.awt.geom.RectangularShape;

public class TabFormes {
	
	private int tableLength = 10;
	private RectangularShape table[];
	
	

	public int getTableLength() {return tableLength;}
	
	
	
	TabFormes()
	{
		table=new RectangularShape[this.tableLength];
	}
	TabFormes(int length)
	{
		tableLength = length;
		table=new RectangularShape[this.tableLength];
	}
	
	
	private int getSize() {
		return this.tableLength;
	}
	
	

}
