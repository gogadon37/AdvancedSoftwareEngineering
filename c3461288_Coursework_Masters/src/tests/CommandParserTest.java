package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;

import Commands.Command;
import Commands.Factories.CommandFactory;
import Parsers.CommandParser;
import c3461288_Coursework_Masters.Pannels.CanvasPannel;

class CommandParserTest {

	@Test
	void CommandsandParamaters() {

		// We want to test the parser class by entering a string of commands.
		// If the command is valid it should return true. if the command or the
		// Parameters are invalid it should return false.
		// lets start by making two Strings to hold the sample input

		String Incorrectcommand = "movefrom 100, 100";
		String Correctcommand = "moveto 100, 100";
		String Incorrectparamaters = "moveto 100, 100, 100";
		String Correctparamaters = "moveto 100, 100";

		// now lets create an object of the CommandParser.class
		CommandParser commandParser = new CommandParser();

		// the ValidCommands method requires an array of commands which are
		// created by passing strings into the commands factory. It also requires
		// a JTextArea for outputting the commands and a Canvas Panel to draw on

		String[] commandstrings = new String[] { "circle", "clear", "drawto", "moveto", "rectangle", "reset",
				"triangle" };

		ArrayList<Command> commands = new ArrayList<Command>();

		JTextArea jta = new JTextArea();
		CanvasPannel cp = new CanvasPannel(1000, 1000);

		CommandFactory commandFactory = new CommandFactory(cp);

		for (String command : commandstrings) {
			Command commandobject = commandFactory.GetCommand(command);
			commands.add(commandobject);
		}

		// Assert Statements

		// the CorrectCommand String Should return true as both
		// the command and parameters are correct.

		assertTrue(commandParser.ValidCommands(Correctcommand, commands, jta));

		// The IncorrectCommand String should return false as the
		// Command is incorrect although the parameters are correct.

		assertFalse(commandParser.ValidCommands(Incorrectcommand, commands, jta));

		// the CorrectParamaters String should return true as the parameters
		// and the command are correct.

		assertTrue(commandParser.ValidCommands(Correctparamaters, commands, jta));

		// the IncorrectParamaters String should return false as the parameters
		// are incorrect although the command is correct.

		assertFalse(commandParser.ValidCommands(Incorrectparamaters, commands, jta));

	}

	@Test
	void variabletest() {

		// the aim of this test is to check that the value of a variable has been set
		// the idea is that the validate commands method will set the variable where
		// appropriate by creating a variable object and storing it in an array of
		// variable
		// objects. The getVariable will then search the objects and return the correct
		// paramater.

		// the ValidCommands method requires an array of commands which are
		// created by passing strings into the commands factory. It also requires
		// a JTextArea for outputting the commands and a Canvas Panel to draw on

		String[] commandstrings = new String[] { "circle", "clear", "drawto", "moveto", "rectangle", "reset",
				"triangle" };

		ArrayList<Command> commands = new ArrayList<Command>();

		JTextArea jta = new JTextArea();
		CanvasPannel cp = new CanvasPannel(1000, 1000);

		CommandFactory commandFactory = new CommandFactory(cp);

		for (String command : commandstrings) {
			Command commandobject = commandFactory.GetCommand(command);
			commands.add(commandobject);
		}
		// create the user input command to store a variable
		CommandParser commandParser = new CommandParser();

		String userinput = "Count = 1";

		// Test that the command parser accepts the command as a valid variable command
		assertTrue(commandParser.ValidCommands(userinput, commands, jta));

		// Test that the value returned from the get variable command is equal to 1
		assertEquals("1", commandParser.getVariable("Count"));

	}

	@Test
	void loops() {

		// this test is to check that the parser class accepts the loop string as a
		// valid
		// command and when getVariable is called its value will have increased;

		String loopstring = "Loop for 2" + "/n" + "Count +1" + "/n" + "Endloop";

		// the ValidCommands method requires an array of commands which are
		// created by passing strings into the commands factory. It also requires
		// a JTextArea for outputting the commands and a Canvas Panel to draw on

		String[] commandstrings = new String[] { "circle", "clear", "drawto", "moveto", "rectangle", "reset",
				"triangle" };

		ArrayList<Command> commands = new ArrayList<Command>();

		JTextArea jta = new JTextArea();
		CanvasPannel cp = new CanvasPannel(1000, 1000);

		CommandFactory commandFactory = new CommandFactory(cp);

		for (String command : commandstrings) {
			Command commandobject = commandFactory.GetCommand(command);
			commands.add(commandobject);
		}
		
		// create the user input command to store a variable
		CommandParser commandParser = new CommandParser();

		String userinput = "Count = 1";
		commandParser.ValidCommands(userinput, commands, jta); // set the variable
		
		// test that the parser accepts the string as a valid command
		assertTrue(commandParser.ValidCommands(loopstring, commands, jta));
		
		// check that the variable has increased to free (Looped twice adding 1 each time)
		assertEquals("3", commandParser.getVariable("Count"));
		

	}

}
