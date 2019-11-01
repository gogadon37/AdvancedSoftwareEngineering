package Commands;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class Reset extends Command{

	
	public Reset(String syntax, int paramsnumber, CanvasPannel canvas) {
		// TODO Auto-generated constructor stub
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
	}
	

	
	public void Runcommand(String[] array) {
		// TODO Auto-generated method stub
		canvas.resetxy();
		System.out.println("reset executed");
	}

}
