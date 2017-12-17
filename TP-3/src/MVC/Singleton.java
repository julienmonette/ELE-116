package MVC;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
	
	private static final Singleton INSTANCE = new Singleton();
		//Pour pas que l'on puisse instancier de l'exterieur
	private List<String> Commandes = new ArrayList<String>();
	private String listeCommandes;
	
	private Singleton() {}
	
	
	public static Singleton getInstance() {		
		return INSTANCE;
		
	}

	public void ajouterCommande(String commande) {
		Commandes.add(commande);
	}
	
	public String listeDesCommandes() {
		for(String commande : Commandes) {
			listeCommandes += "\n" + commande;
		}
		return listeCommandes;
	}
}
