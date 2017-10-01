
public class CommForme extends Comm {
	
	public String forme;
	Comm comm = new Comm();
		
	public CommForme(){
		comm.startConnection();	
	}
	
	public void envoieGET() {
		comm.sendString("GET");
		forme = comm.getString();
	}
		
	public void envoieEND() {
	    comm.endConnection();
	}
	
}
