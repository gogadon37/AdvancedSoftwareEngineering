package Commands;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class RectangleCommand extends Command {

	public RectangleCommand(String syntax, int paramsnumber, CanvasPannel canvas) {
		// TODO Auto-generated constructor stub
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
		
	}

	public void Runcommand(String[] array) {
		// TODO Auto-generated method stub
		canvas.drawrectangle(Integer.parseInt(array[1]) ,Integer.parseInt(array[2]) );
		System.out.println("rectangle executed");
	}
	
	
	
}
