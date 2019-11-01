package Commands;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class Triangle extends Command {

	public Triangle(String syntax, int paramsnumber, CanvasPannel canvas) {
		// TODO Auto-generated constructor stub
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
	}

	public void Runcommand(String[] array) {
		// TODO Auto-generated method stub
		System.out.println("triangle executed");
		canvas.drawtriangle(Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]));
	}

}
