import java.awt.Graphics;
import java.awt.geom.RectangularShape;
import java.util.StringTokenizer;


public class TabFormes {
	

	private int tableLength = 10;
	
	private int i;
	private int size = 0;
	private RectangularShape table[];
	
	private Forme  tab[] = new Forme[tableLength];
		
	
	public TabFormes(){
		i = 0;
	}
	
	public void add(String string) {
		int seq;
		int x1=0;
		int x2=0;
		int y1=0;
		int y2=0;
		
		Forme forme;
		String typeForme;
		StringTokenizer line = new StringTokenizer(string);	
		
		seq = Integer.parseInt(line.nextToken());

		typeForme = line.nextToken();
		System.out.println(seq);
		System.out.println(typeForme);
		
		x1 = Integer.parseInt(line.nextToken());
		x2 = Integer.parseInt(line.nextToken());
		y1 = Integer.parseInt(line.nextToken());
		if(!typeForme.equals("<CERCLE>")) {	y2 = Integer.parseInt(line.nextToken());}
		
		
		
		if(typeForme.equals("<CARRE>")) {
			forme = new Carre(x1,x2,y1,y2);
		}
		else if (typeForme.equals("<CERCLE>")) {
			forme = new Cercle(x1,x2,y1);
		}
		else if (typeForme.equals("<RECTANGLE>")) {
			forme = new Rectangle(x1,x2,y1,y2);
		}
		else if (typeForme.equals("<LIGNE>")) {
			forme = new Ligne(x1,x2,y1,y2);
		}
		else {
			forme = new Ovale(x1,x2,y1,y2);
		}	
		add(forme);
	}
	
	private void add(Forme forme) {
		tab[i] = forme;
		i++;
		if(i == tableLength) {
			i = 0;
		}
		
		if(size != tableLength) {
			size++;
		}
		
		
	}
	
	
	private Forme getForme(int i) {
		return this.tab[i];
	}
	
	private int getSize() {
		return this.size;
	}
	
	
	public void dessinerTabForme(Graphics g) {
		int j;
		 
		for( j = 0; j < getSize(); j++) {
			tab[j].dessine(g);
		}	
	}

	
	//public int getTableLength() {return tableLength;}
/*	
	TabFormes(){
		table=new RectangularShape[this.tableLength];
	}
	
	TabFormes(int length){
		
		tableLength = length;
	    table=new RectangularShape[this.tableLength];
	}
*/	
	

	
	
	
	

}
