package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class RectangleCommand extends Command {

	public RectangleCommand(String syntax, int paramsnumber, CanvasPannel canvas) {
		// TODO Auto-generated constructor stub
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
		
	}

	public void Runcommand(ArrayList<String> array) {
		// TODO Auto-generated method stub
		canvas.drawrectangle(Integer.parseInt(array.get(1)) ,Integer.parseInt(array.get(2)) );
		System.out.println("rectangle executed");
	}
	
	
	
}
