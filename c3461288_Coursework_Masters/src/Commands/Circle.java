package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class Circle extends Command{

	static Circle singleinstance = null;
	
	private Circle(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
		
	}
	

	public void Runcommand(ArrayList<String> array) {
		
		
		System.out.println("Circle executed");
		canvas.drawcircle(Integer.parseInt(array.get(1)));
		
	}


	// return a singleton object if one is already created else create one.
	
	public static Circle getInstance(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		if(singleinstance == null) {
			
			singleinstance = new Circle(syntax, paramsnumber, canvas);
			System.out.println("new instance created for " + syntax);
			
		}else {
			
			System.out.println("Using existing Instance for " + syntax);
		}
		
		
		return singleinstance;
	}


	





	
}
