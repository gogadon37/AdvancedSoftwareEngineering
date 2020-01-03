package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class Reset extends Command{

	static Reset singleinstance = null;
	
	private Reset(String syntax, int paramsnumber, CanvasPannel canvas) {
		// TODO Auto-generated constructor stub
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
	}
	
	// return a singleton object if one is already created else create one.
	public static Reset getInstance(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		if(singleinstance == null) {
			
			singleinstance = new Reset(syntax, paramsnumber, canvas);
			System.out.println("new instance created for " + syntax);
			
		}else {
			
			System.out.println("Using existing Instance for " + syntax);
		}
		
		
		return singleinstance;
	}
	
	
	public void Runcommand(ArrayList<String> Array) {
		// TODO Auto-generated method stub
		canvas.resetxy();
		System.out.println("reset executed");
	}

}
