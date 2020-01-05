package Commands;

import java.util.ArrayList;

import c3461288_Coursework_Masters.Pannels.CanvasPannel;

/**
 * The Class creates a singleton object of itself and passes it when getInstance is called it also contains methods to run the command.
 * 
 * @author George Lloyd-Anderson
 * @version 1.0
 * @since 04/01/20
 * 

 */

public class Circle extends Command{

	static Circle singleinstance = null;
	
	/**
	 * The constructor sets the name, number of parameters and the canvas for the object.
	 * 
	 * @param syntax The name of the parameter i.e its syntax.
	 * @param paramsnumber The number of parameters the command should have.
	 * @param canvas The canvas where the object is to draw to.
	 */
	
	private Circle(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		setName(syntax);
		setNumOfParams(paramsnumber);
		setCanvas(canvas);
		
	}
	
/**
 *  The run command method executes the command via the canvas.
 *  @param array contains the command and parameters entered by the user
 */
   public void Runcommand(ArrayList<String> array) {
		
		
		System.out.println("Circle executed");
		canvas.drawcircle(Integer.parseInt(array.get(1)));
		
	}


	// return a singleton object if one is already created else create one.
   
	/** The getInstance method returns single instance object and if it doesn't exist yet it creates it.
	 * 
	 *@param syntax The name of the parameter i.e its syntax.
	 * @param paramsnumber The number of parameters the command should have.
	 * @param canvas The canvas where the object is to draw to.
	 * @return the singleinstance of the object
	 */
   
	public static Circle getInstance(String syntax, int paramsnumber, CanvasPannel canvas) {
		
		if(singleinstance == null) {
			
			singleinstance = new Circle(syntax, paramsnumber, canvas);
			System.out.println("new instance created for " + syntax);
			
		}else {
			
			System.out.println("Using existing Instance for " + syntax);
		}
		
		
		return singleinstance;
	}


	





	
}
