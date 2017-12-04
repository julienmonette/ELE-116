package MVC;

public class Singleton {
	
	private static Singleton instance = null;
	
	private Singleton() {
		//Pour pas que l'on puisse instancier de l'exterieur
	}
	
	public static Singleton getInstance() {
		if (instance == null) instance = new Singleton();
			
		return instance;
		
	}

}
