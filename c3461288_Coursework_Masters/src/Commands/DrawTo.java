package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;


public class DrawTo extends Command {

	public DrawTo(String syntax, int paramsnumber, CanvasPannel canvas) {
		// TODO Auto-generated constructor stub
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
	}
	
	


	public void Runcommand(ArrayList<String> array) {
		System.out.println("drawto executed");
		canvas.drawto(Integer.parseInt(array.get(1)), Integer.parseInt(array.get(2)));
		
	}

}
