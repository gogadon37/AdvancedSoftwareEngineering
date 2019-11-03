package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class MoveTo extends Command{

	public MoveTo(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);

	}
	
	public void Runcommand(ArrayList<String> array) {
		// TODO Auto-generated method stub
		canvas.setXY(Integer.parseInt(array.get(1)),Integer.parseInt(array.get(2)));
		System.out.println("moveto Executed");
	}
	
	
	
	

}
