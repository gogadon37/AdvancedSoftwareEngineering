package tests;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;

import org.junit.jupiter.api.Test;

import Commands.Circle;
import Commands.Clear;
import Commands.DrawTo;
import Commands.MoveTo;
import Commands.RectangleCommand;
import Commands.Reset;
import Commands.Triangle;
import Commands.Factories.CommandFactory;
import c3461288_Coursework_Masters.Pannels.CanvasPannel;
import exceptions.CommandNotFoundException;

class CommandFactoryTest {

	@Test
	void ReturnsCorrectObject() {
		
		// The purpose of this tests are to check whether the commandsfactory
		// class returns an array of commands based on the values entered;
		
		// Lets start by creating a command factory object and passing in a 
		// canvas pannel
		
		CanvasPannel canvaspan = new CanvasPannel(100, 100);
		CommandFactory commandFactory = new CommandFactory(canvaspan);
		

		// Test that the commandfactory returns the correct instance per command type
		// The test will fail if an incorrect type is returned.
		
		try {
			
		assertTrue(commandFactory.GetCommand("circle") instanceof Circle);
		assertTrue(commandFactory.GetCommand("drawto") instanceof DrawTo);
		assertTrue(commandFactory.GetCommand("moveto") instanceof MoveTo);
		assertTrue(commandFactory.GetCommand("rectangle") instanceof RectangleCommand);
		assertTrue(commandFactory.GetCommand("reset") instanceof Reset);
		assertTrue(commandFactory.GetCommand("triangle") instanceof Triangle);
		assertTrue(commandFactory.GetCommand("clear") instanceof Clear);
		} catch (CommandNotFoundException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
