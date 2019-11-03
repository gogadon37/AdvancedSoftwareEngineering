package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class Circle extends Command{

	public Circle(String syntax, int paramsnumber, CanvasPannel canvas) {
		// TODO Auto-generated constructor stub
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
	}
	
	



	public void Runcommand(ArrayList<String> array) {
		
		
		System.out.println("Circle executed");
		canvas.drawcircle(Integer.parseInt(array.get(1)));
		
	}





	
}
