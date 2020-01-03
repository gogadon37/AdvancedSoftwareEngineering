package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;


public class DrawTo extends Command {

	static DrawTo singleinstance = null;
	
	private DrawTo(String syntax, int paramsnumber, CanvasPannel canvas) {
		// TODO Auto-generated constructor stub
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
	}
	
	// return a singleton object if one is already created else create one.
	
	public static DrawTo getInstance(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		if(singleinstance == null) {
			
			singleinstance = new DrawTo(syntax, paramsnumber, canvas);
			System.out.println("new instance created for " + syntax);
			
		}else {
			
			System.out.println("Using existing Instance for " + syntax);
		}
		
		
		return singleinstance;
	}


	public void Runcommand(ArrayList<String> array) {
		System.out.println("drawto executed");
		canvas.drawto(Integer.parseInt(array.get(1)), Integer.parseInt(array.get(2)));
		
	}

}
