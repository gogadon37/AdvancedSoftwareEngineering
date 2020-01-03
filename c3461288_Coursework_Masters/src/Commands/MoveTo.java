package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class MoveTo extends Command{
	
	static MoveTo singleinstance = null;

	private MoveTo(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);

	}
	

	
	public void Runcommand(ArrayList<String> array) {
		// TODO Auto-generated method stub
		canvas.setXY(Integer.parseInt(array.get(1)),Integer.parseInt(array.get(2)));
		System.out.println("moveto Executed");
	}
	
	// return a singleton object if one is already created else create one.	
	public static MoveTo getInstance(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		if(singleinstance == null) {
			
			singleinstance = new MoveTo(syntax, paramsnumber, canvas);
			System.out.println("new instance created for " + syntax);
			
		}else {
			
			System.out.println("Using existing Instance for " + syntax);
		}
		
		
		return singleinstance;
	}
	
	

}
