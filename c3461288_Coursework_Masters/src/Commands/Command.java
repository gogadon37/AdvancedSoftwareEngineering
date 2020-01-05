package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;


/**
 * The class contains all the methods subclassess will inherit.
 * 
 * @author George Lloyd-Anderson
 * @version 1.0
 * @since 04/01/20
 * 

 */


public abstract class Command{
	
	// abstract methods all children must include
	/**
	 * The abstract method all subclasses must override.
	 * @param array Contains the command and its parameters which the subclass will then execute
	 */
	abstract public void Runcommand(ArrayList<String> array);
	

	private String name = "";
	private int numOfParams = 0;
	CanvasPannel canvas;
	
	// public methods
	/**
	 * The method returns the canvas object associated with the class.
	 * @return returns the CanvasPannel object
	 */
	public CanvasPannel getCanvas() {
		return canvas;
	}
	
	/**
	 * Sets the CanvasPannel Object.
	 * @param canvas The object the calling class wants to set as the CanvasPannel.
	 */
	public void setCanvas(CanvasPannel canvas) {
		this.canvas = canvas;
	}
	
	/**
	 * Returns the name associated with the object.
	 * @return the name of the object
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name associated with the object with the String passed by the calling class.
	 * @param name the name passed by the calling class.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the number of parameters expected by commandobject for the calling class.
	 * @return returns the number of parameters expected by the command object.
	 */
	public int getNumOfParams() {
		return numOfParams;
	}

	
	/**
	 * Sets the number of parameters expected for the object with the int passed by the calling class.
	 * @param numOfParams the int passed by the calling class holding the expected number of parameters for the object.
	 */
	public void setNumOfParams(int numOfParams) {
		this.numOfParams = numOfParams;
	}

	
	
	
	

}
