package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;



public class Color extends Command {

static private Color singleinstance;	

	private Color(String syntax, int paramsnumber, CanvasPannel canvas) {
		// TODO Auto-generated constructor stub
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
		
	}


	@Override
	public void Runcommand(ArrayList<String> array) {
		// TODO Auto-generated method stub
		
		canvas.color();
	}

	
	public static Color getInstance (String syntax, int paramsnumber, CanvasPannel canvas) {
		
		if(singleinstance == null) {
			
			singleinstance = new Color(syntax, paramsnumber, canvas);
			System.out.println("new instance created for " + syntax);
			return singleinstance;
			
			
		}else {
			System.out.println("Using existing Instance for " + syntax);
			return singleinstance;
		}
		
		
		
		
		
	}
	
}
