package MVC;
public class PaintModel implements Observable{

	private Observer observer;
	

	public void notifyObserver() {
		observer.getParameter();
	}
	
	
	/* 
	 * setState recevra les informations relatives à
	 * la façon avec laquelle l'image doit être affiché
	 * (zoom, position, etc)
	 * Les paramètres sont à déterminer
	 * setState est appellée dans les actionListener de
	 * PaintView.java
	 */
	public void setState() {
		// TODO Modification à l'état actuel du model
		notifyObserver();
	}

	public void addObserver(Observer observer) {
		this.addObserver(observer);
	}

}
