package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class Clear extends Command{

	public Clear(String syntax, int paramsnumber, CanvasPannel canvas) {

		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);

	}
	

	public void Runcommand(ArrayList<String> array) {
		// TODO Auto-generated method stub
		System.out.println("Clear Executed");
		canvas.clear();
	}

}
