package MVC;

public class Main {

	public static void main(String[] args) {
	
		PaintModel paintModel = new PaintModel();
		PaintControler paintControler = new PaintControler(paintModel);
		PaintView paintView = new PaintView(paintControler);
	
		// paintModel.addObserver(paintView);
	}

}
