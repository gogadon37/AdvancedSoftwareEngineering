package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class Clear extends Command{

	static Clear singleinstance = null;
	
	private Clear(String syntax, int paramsnumber, CanvasPannel canvas) {

		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);

	}
	
	// return a singleton object if one is already created else create one.

	public static Clear getInstance(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		if(singleinstance == null) {
			
			singleinstance = new Clear(syntax, paramsnumber, canvas);
			System.out.println("new instance created for " + syntax);
			
		}else {
			
			System.out.println("Using existing Instance for " + syntax);
		}
		
		
		return singleinstance;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	public void Runcommand(ArrayList<String> array) {
		// TODO Auto-generated method stub
		System.out.println("Clear Executed");
		canvas.clear();
	}

}
