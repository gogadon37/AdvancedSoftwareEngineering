package Commands;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class MoveTo extends Command{

	public MoveTo(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);

	}
	
	public void Runcommand(String[] array) {
		// TODO Auto-generated method stub
		canvas.setXY(Integer.parseInt(array[1]),Integer.parseInt(array[2]));
		System.out.println("moveto Executed");
	}
	
	
	
	

}
