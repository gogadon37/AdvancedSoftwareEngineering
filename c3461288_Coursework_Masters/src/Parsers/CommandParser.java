package Parsers;

import java.util.ArrayList;
import javax.swing.JTextArea;
import Commands.Command;

public class CommandParser {

	ArrayList<Command> actualCommands;
	ArrayList<ArrayList<String>> approvedcommands;
	ArrayList<String> recombinedcommands;
	JTextArea console;
	String[] commandname;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Boolean ValidCommands(String writtencommand, ArrayList<Command> commands, JTextArea jta) {

		// Assign the variables
		console = jta;
		String[] writtencommands = writtencommand.split("\\r?\\n|\\r");
		actualCommands = commands;
		approvedcommands = new ArrayList<ArrayList<String>>();
		recombinedcommands = new ArrayList<String>();
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

				System.out.println("command not found");
				return false; // if command is not valid return false

			}
		}

		// The approvedcommands arraylist now holds the commands if all were
		// correct. loop through the array until the approved command matches
		// the correct command name execute the command passing in its parameters.

		for (ArrayList<String> array : approvedcommands) {

			System.out.println("array element" + array.get(0));

		}

		for (ArrayList<String> array : approvedcommands) {

			for (Command c : commands) {

				if (array.get(0).equals(c.getName())) {

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

		// split at first instance of a space leaving two values, command and params
		String[] commandname = command.split(" ", 2);

		// split the parameters by commas if there is more than one element in the
		// array.

		String[] params;

		if (commandname.length != 1) {

			params = commandname[1].split(",");

		} else {

			params = new String[0];
		}

		// loop through the actual commands and check if
		// the command name entered matches a verified command
		// If a match has been found then check the parameters else
		// its an invalid command return false.

		for (Command c : actualCommands) {

			if (commandname[0].equals(c.getName())) {

				if (checktheparamaters(c.getNumOfParams(), params, commandname[0])) {

					return true;

				} else {

					return false;
				}
			}

		}

		console.setText(java.time.LocalTime.now() + " ERROR: Command does not exist");
		return false;

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private boolean checktheparamaters(int numberexpected, String[] paramarray, String commandname) {

		// check that the length of the array holding the command and its
		// variables is equal to the number expected.

		// reset the recombined array to prevent the same command being added over and
		// over.
		recombinedcommands = new ArrayList<String>();

		if (paramarray.length != (numberexpected)) {

			console.setText("ERROR: The command contains the incorrect number of paramaters");
			return false;

		}

		// The array contains the correct number of parameters and they are
		// Separated appropriately so now try to parse them to int's

		for (int x = 0; x != numberexpected; x++) {

			try {

				Integer.parseInt(paramarray[x].trim());

			} catch (Exception e) {

				console.setText("ERROR: Please make sure the Paramater is a valid Integer");
				return false;
			}

		}

		// The command has passed all checks add it to the
		// recombined the array and add it to the approved commands arraylist.

		System.out.println("Command name " + commandname);

		recombinedcommands.add(commandname);

		for (int x = 0; x < paramarray.length; x++) {

			recombinedcommands.add(paramarray[x].trim());

		}

		approvedcommands.add(recombinedcommands);
		return true;

	}

	public String getVariable(String string) {
		// TODO Auto-generated method stub
		return "string";
	}

}
