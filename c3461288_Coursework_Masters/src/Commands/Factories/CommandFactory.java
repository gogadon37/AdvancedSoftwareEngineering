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

public class CommandFactory {

	Command command;
	CanvasPannel canvas;

	public CommandFactory(CanvasPannel canvaspan) {
		// set the canvas equal to the canvas pannel paramater passed in the constructor.
		canvas = canvaspan;
	}
	
	
	public Command GetCommand(String Type) throws CommandNotFoundException {
		
		// Return the correct Command subclass object depending on the String which has been passed into 
		// the method.

		switch (Type) {

		// Circle
		case "circle":
			command = new Circle("circle", 1, canvas);
			break;

		// Clear
		case "clear":
			command = new Clear("clear", 0, canvas);
			break;

		// DrawTo
		case "drawto":
			command = new DrawTo("drawto", 2, canvas);
			break;

		// MoveTo
		case "moveto":
			command = new MoveTo("moveto", 2, canvas);
			break;

		// Rectangle
		case "rectangle":
			command = new RectangleCommand("rectangle", 2, canvas);
			break;

		// Reset
		case "reset":
			command = new Reset("reset", 0, canvas);
			break;
			
			// Reset
		case "triangle":
			command = new Triangle("triangle", 3, canvas);
			break;
			
		
		default:
			throw new CommandNotFoundException("CommandObject not found  " + Type);
		}

		return command;
	}

}
