package Commands;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public abstract class Command{
	
	public  void Runcommand(String[] array) {
		System.out.println("run command");
	}
	
	private String name = "";
	private int numOfParams = 0;
	CanvasPannel canvas;
	
	
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
