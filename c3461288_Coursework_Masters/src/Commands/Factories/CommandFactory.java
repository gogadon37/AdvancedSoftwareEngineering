package Commands.Factories;

import Commands.Circle;
import Commands.Clear;
import Commands.Command;
import Commands.DrawTo;
import Commands.MoveTo;
import Commands.RectangleCommand;
import Commands.Reset;
import Commands.Triangle;
import c3461288_Coursework_Masters.Pannels.CanvasPannel;
import exceptions.CommandNotFoundException;
/**
	 * The class returns the appropriate subclass of Command depending on what the user requests.
	 * 
	 * @author George Lloyd-Anderson
	 * @version 1.0
	 * @since 04/01/20
	 * 

	 */

public class CommandFactory {

	Command command;
	CanvasPannel canvas;

/**
 * The constructor class takes a canvas pannel which is passed to the objects being created, this allows them to execute their run methods when required.
 * @param canvaspan conatians the canvaspannel the command is to drawto.
 */
	
	public CommandFactory(CanvasPannel canvaspan) {
		
		// set the canvas equal to the canvas pannel paramater passed in the constructor.
		canvas = canvaspan;
	}
	
	/**
	 * The GetCommand method compares the value the user entered against the command names and returns the correct subclass of the Command class.
	 * 
	 * @param Type holds the requested type requested by the calling class.
	 * @return returns the appropriate subclass of the Command object depending on what's requested.
	 * @throws CommandNotFoundException thrown when the user requests a command which doesen't exist.
	 */
	
	public Command GetCommand(String Type) throws CommandNotFoundException {
		
		// Return the correct Command subclass object depending on the String which has been passed into 
		// the method.

		switch (Type) {

		// Circle
		case "circle":
			command = Circle.getInstance("circle", 1, canvas);
			break;

		// Clear
		case "clear":
			command = Clear.getInstance("clear", 0, canvas);
			break;

		// DrawTo
		case "drawto":
			command = DrawTo.getInstance("drawto", 2, canvas);
			break;

		// MoveTo
		case "moveto":
			command = MoveTo.getInstance("moveto", 2, canvas);
			break;

		// Rectangle
		case "rectangle":
			command = RectangleCommand.getInstance("rectangle", 2, canvas);
			break;

		// Reset
		case "reset":
			command = Reset.getInstance("reset", 0, canvas);
			break;
			
			// Reset
		case "triangle":
			command = Triangle.getInstance("triangle", 3, canvas);
			break;
			
		
		default:
			throw new CommandNotFoundException("CommandObject not found  " + Type);
		}

		return command;
	}

}
