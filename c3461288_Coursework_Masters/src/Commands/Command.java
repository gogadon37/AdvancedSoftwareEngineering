package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public abstract class Command{
	
	// abstract methods all children must include
	
	abstract public void Runcommand(ArrayList<String> array);
	
	
	private String name = "";
	private int numOfParams = 0;
	CanvasPannel canvas;
	
	// public methods
	
	public CanvasPannel getCanvas() {
		return canvas;
	}
	public void setCanvas(CanvasPannel canvas) {
		this.canvas = canvas;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfParams() {
		return numOfParams;
	}

	public void setNumOfParams(int numOfParams) {
		this.numOfParams = numOfParams;
	}

	
	
	
	

}
