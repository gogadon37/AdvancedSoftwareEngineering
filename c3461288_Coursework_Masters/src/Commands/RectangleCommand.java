package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class RectangleCommand extends Command {

	static RectangleCommand singleinstance = null;
	
	private RectangleCommand(String syntax, int paramsnumber, CanvasPannel canvas) {
		// TODO Auto-generated constructor stub
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
		
	}
	
	// return a singleton object if one is already created else create one.
	
	public static RectangleCommand getInstance(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		if(singleinstance == null) {
			
			singleinstance = new RectangleCommand(syntax, paramsnumber, canvas);
			System.out.println("new instance created for " + syntax);
			
		}else {
			
			System.out.println("Using existing Instance for " + syntax);
		}
		
		
		return singleinstance;
	}
	
	
	

	public void Runcommand(ArrayList<String> array) {
		// TODO Auto-generated method stub
		canvas.drawrectangle(Integer.parseInt(array.get(1)) ,Integer.parseInt(array.get(2)) );
		System.out.println("rectangle executed");
	}
	
	
	
}
