
public class CommForme extends Comm {
	
	public String forme;
	
	public void envoieGET() {
		this.sendString("GET");
		forme = this.getString();
	}
		
	public void envoieEND() {
	    this.endConnection();
	}
	
}
