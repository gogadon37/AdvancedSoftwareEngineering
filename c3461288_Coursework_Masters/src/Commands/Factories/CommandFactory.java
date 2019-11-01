package Commands.Factories;


import java.awt.Rectangle;

import Commands.Circle;
import Commands.Clear;
import Commands.Command;
import Commands.DrawTo;
import Commands.MoveTo;
import Commands.RectangleCommand;
import Commands.Reset;
import Commands.Triangle;
import c3461288_Coursework_Masters.Pannels.CanvasPannel;

public class CommandFactory {

	Command command;
	CanvasPannel canvas;

	public CommandFactory(CanvasPannel canvaspan) {
		// TODO Auto-generated constructor stub
		canvas = canvaspan;
	}
	
	
	public Command GetCommand(String Type) {
		// TODO Auto-generated constructor stub

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
			throw new IllegalArgumentException("Unexpected value: " + Type);
		}

		return command;
	}

}