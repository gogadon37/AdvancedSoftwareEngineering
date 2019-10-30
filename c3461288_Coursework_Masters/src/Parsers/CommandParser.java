package Parsers;

public class CommandParser {

	public Boolean ValidCommands(String command) {

		String[] commands = command.split("\\r?\\n|\\r");

		// Check each command Line By Line
		for (String singleline : commands) {

			// Check that the command is a valid command

			if (IsValidCommand(singleline)) {

				// The command is valid so check that the parameters are
				

				if (command.trim().equals("reset") || command.trim().equals("clear")) {

					return true;

				} else {

					// only check the parameters if the code is not a reset or clear command

					if (ParamatersValid(singleline)) {

						System.out.println("Paramaters are Valid");

					} else {

						return false;
					}

				}

			} else {

				System.out.println("Command is NOT valid");
				return false;
			}

		}

		return true;

	}

	private Boolean ParamatersValid(String command) {

		// Remove all the spaces
		command = Removeaditionalspace(command);

		// Split the command into sections
		String[] Commandname = Splitcommand(command);

		// Check that the command has the appropriate number of parameters for the
		// command and that
		// they are integers

		switch (Commandname[0]) {

		// Call the check parameter method passing in the expected number of parameters
		// and the string array

		case "moveto":
			if (checktheparamaters(2, Commandname)) {
				return true;
			} else {
				return false;
			}
		case "circle":
			if (checktheparamaters(1, Commandname)) {
				return true;
			} else {
				return false;
			}
		case "triangle":
			if (checktheparamaters(3, Commandname)) {
				return true;
			} else {
				return false;
			}
		case "rectangle":
			if (checktheparamaters(2, Commandname)) {
				return true;
			} else {
				return false;
			}
		case "drawto":
			if (checktheparamaters(2, Commandname)) {
				return true;
			} else {
				return false;
			}

		default:
			return false;
		}

	}

	private Boolean IsValidCommand(String command) {

		// check that the command is not empty space

		if (command.trim().length() == 0) {

			return false;

		}

		command = Removeaditionalspace(command);

		// check if the command entered is one of the single commands "run", "reset",
		// "clear"

		if (command.trim().equals("reset") || command.trim().equals("clear")) {

			return true;

		} else {

			// split the command by each space this will result in the name followed by each
			// parameter

			String[] Commandname = Splitcommand(command);

			// Check the first parameter against the commands

			switch (Commandname[0]) {

			case "moveto":
				return true;
			case "drawto":
				return true;
			case "rectangle":
				return true;
			case "circle":
				return true;
			case "triangle":
				return true;

			default:
				return false; // Not a Valid Command

			}
		}
	}

	private boolean checktheparamaters(int numberexpected, String[] commandarray) {

		// check that the number of parameters contained in the string array is equal to
		// that passed +1
		if (commandarray.length != (numberexpected + 1)) {

			System.out.println("Paramater error");
			return false; // To many parameters.

		} else if (numberexpected > 1) {

			

			// check for the comer after the first and subsequent parameters when required.

			for (int x = 1; x <= (numberexpected - 1); x++) {

				// get the last value from the parameter
				// This should be a comer

				String lastchar = commandarray[x].substring(commandarray[x].length() - 1);
				

				// if no comer is inserted then the syntax is incorrect

				if (!lastchar.equals(",")) {

					System.out.println("Syntax Error, Please ensure , are used where appropriate");
					return false;

				} else {

					// there is a comer so now we need to remove it for the next step to
					// check if it can be parsed.

					commandarray[x] = commandarray[x].substring(0, (commandarray[x].length() - 1));

				}

			}

		}

		for (int x = 1; x <= numberexpected; x++) {

			try {

				Integer.parseInt(commandarray[x]);

			} catch (Exception e) {

				// could not be parsed to an integer

				System.out.println("could not be parsed");
				return false;

			}

		}

		// all parameters can be parsed to integers

		return true;

	}

	private String Removeaditionalspace(String command) {

		// {EXTRA FEATURE}//
		// Remove all extra white space so that the editor allows multiple spaces
		// between parameters etc

		return command.replaceAll("\\s+", " ").trim();

	}

	private String[] Splitcommand(String command) {

		return command.split(" ");

	}

}
