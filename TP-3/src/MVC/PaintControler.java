package MVC;
import command.Command;

public class PaintControler {

	// TODO : Gestionnaire de commande ?
	
	private PaintModel model; 
	
	public PaintControler(PaintModel mod) {
		this.model = mod;
	}
	
	public void addCommand(Command command) {
		command.execute();
	}
	
}
