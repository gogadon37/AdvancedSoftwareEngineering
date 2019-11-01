package Parsers;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JTextArea;

import Commands.Command;

public class CommandParser {

	ArrayList<Command> actualCommands;
	ArrayList<String[]> approvedcommands;
	JTextArea console;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Boolean ValidCommands(String writtencommand, ArrayList<Command> commands, JTextArea jta) {

		// Assign the variables
		console= jta;
		String[] writtencommands = writtencommand.split("\\r?\\n|\\r");
		actualCommands = commands;
		approvedcommands = new ArrayList<String[]>();
		ArrayList<String> writtencommandsblanklinesremoved = new ArrayList<String>();

		// loop through written commands if the line isn't blank add it
		// to writtencommandsblanklinesremoved.

		for (String s : writtencommands) {

			if (!s.trim().equals("")) {

			writtencommandsblanklinesremoved.add(s);
			
			} 
		}

		// Check if there are any commands after empty lines have been removed
		// return false if it is not the case.

		if (writtencommandsblanklinesremoved.size() == 0) {

			console.setText(" ERROR: Please enter a command into the terminal");
			return false;

		}

		// loop through the writtencommandsblanklinesremoved array
		// running the IsValidCommand method for each of its strings

		for (String singleline : writtencommandsblanklinesremoved) {

			if (!IsValidCommand(singleline)) {

				return false; // if command is not valid return false

			}
		}

		// The approvedcommands arraylist now holds the commands if all were
		// correct. loop through the array until the approved command matches
		// the correct command name execute the command passing in its parameters.

		for (String[] array : approvedcommands) {

			for (Command c : commands) {

				if (array[0].equals(c.getName())) {

					c.Runcommand(array);

				}

			}
		}

		return true; // All commands executed successfully return true

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private Boolean IsValidCommand(String command) {

		// Remove additional spaces from the command. This allows
		// for the user to use spaces to organise their code in the editor

		command = command.replaceAll("\\s+", " ").trim();

		// split the command by each remaining space. This should give
		// an array containing the command name and any parameters.

		String[] Commandname = command.split(" ");

		// loop through the actual commands and check if
		// the command name entered matches a verified command
		// If a match has been found then check the parameters else
		// its an invalid command return false.

		for (Command c : actualCommands) {

			if (Commandname[0].equals(c.getName())) {

				if (checktheparamaters(c.getNumOfParams(), Commandname)) {

					return true;

				}else {
					
					
					return false;
				}
			}

		}
		
		
		
		console.setText( java.time.LocalTime.now() + " ERROR: Command does not exist");
		return false;

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private boolean checktheparamaters(int numberexpected, String[] commandarray) {

		// check that the length of the array holding the command and its
		// variables is equal to the number expected + 1. The +1 takes into
		// account that the first parameter of the array will not be a parameter.

		if (commandarray.length != (numberexpected + 1)) {

			console.setText("ERROR: The command contains the incorrect number of paramaters");
			return false;

		}

		// working with the syntax from the assignment specification all parameters
		// unless its the last or only parameter should be directly followed by a comma
		// with no space following it.

		else if (numberexpected > 1) {

			for (int x = 1; x <= (numberexpected - 1); x++) {

				String lastchar = commandarray[x].substring(commandarray[x].length() - 1);

				if (!lastchar.equals(",")) {

					console.setText("ERROR: Syntax Error, Please ensure commas are used where appropriate");
					return false;

				} else {

					// a comma has been found but now we need to remove it before we try to parse it
					// to and int
					
					commandarray[x] = commandarray[x].substring(0, (commandarray[x].length() - 1));

				}

			}

		}

		// The array contains the correct number of parameters and they are
		// Separated appropriately so now try to parse them to int's
		
		
		for (int x = 1; x <= numberexpected; x++) {

			try {

				Integer.parseInt(commandarray[x]);

			} catch (Exception e) {

				console.setText("ERROR: Please make sure the Paramater is a valid Integer");
				return false;
			}

		}


		// The command has passed all checks add it to the
		// approved commands arraylist.
	  
		approvedcommands.add(commandarray);
		return true;

	}

}
