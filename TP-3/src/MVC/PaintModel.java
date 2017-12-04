package MVC;
public class PaintModel implements Observable{

	private Observer observer;
	

	public void notifyObserver() {
		observer.getParameter();
	}
	
	
	/* 
	 * setState recevra les informations relatives �
	 * la fa�on avec laquelle l'image doit �tre affich�
	 * (zoom, position, etc)
	 * Les param�tres sont � d�terminer
	 * setState est appell�e dans les actionListener de
	 * PaintView.java
	 */
	public void setState() {
		// TODO Modification � l'�tat actuel du model
		notifyObserver();
	}

	public void addObserver(Observer observer) {
		this.addObserver(observer);
	}

}
