package Parsers;

import java.util.ArrayList;
import javax.swing.JTextArea;
import Commands.Command;
import Variables.Variable;

public class CommandParser {

	ArrayList<Command> actualCommands;
	ArrayList<Variable> variables;
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
		variables = new ArrayList<Variable>();
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
		int currentindex = -1;
		for (String singleline : writtencommandsblanklinesremoved) {

			currentindex++;

			// ***********************************************************************************
			// check if first 3 chars are the var keyword
			// but dont run this segment if the lines length is
			// less than 3 chars as it will not say var and cause a
			// bug with the next conditon

			if (singleline.length() >= 3 && singleline.trim().subSequence(0, 3).equals("var")) {

				// Handle the variable creation

				// trim the first 3 chars off
				String varnameandvalue = (String) singleline.substring(3, singleline.length()).trim();

				// split by equals //
				String[] keypair = varnameandvalue.split("=");

				// check if the KeyPair has two values
				if (keypair.length == 2) {
					int pair;
					// Check the second value is a valid int

					try {
						pair = Integer.parseInt(keypair[1].trim());

					} catch (Exception e) {

						console.setText("Error a variables value should be a valid int");
						System.out.println(keypair[1]);
						return false;
					}

					// create the variable.

					Variable x = new Variable(keypair[0].trim(), pair);
					variables.add(x);

				} else {

					console.setText("Error a variable should have a value split by and =");
					return false;

				}

			} else if (!IsValidCommand(singleline)) {

				
					return false;


				} 

			}
		

		// ********************************************************************************************

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
					console.setText(java.time.LocalTime.now() + " ERROR: Command does not exist");
					return false;
				}
				
// **************************************************************************************
// **************************************************************************************
// **************************************************************************************
				
			}else if (variables.size() > 0) {

				String[] possiblevarname;
				
				// Try to split any paramateters 
				
				try {
					possiblevarname = commandname[1].split(" ");
					
				}catch (Exception e) {
				
					// if theres no paramaters than it is not a valid command 
					
					console.setText(java.time.LocalTime.now() + " ERROR: Please ensure the variable has a paramater");
					return false;
				}
					
					
				
					// Loop through the variables array and check that name against the command entered

						for (Variable v : variables) {
							
							if (v.getKey().trim().equals(commandname[0].trim())) {

					
								// VARIABLE REASSINGED
								if (possiblevarname[0].trim().equals("=")) {

									try {
										int newvalue = Integer.parseInt(possiblevarname[1].trim());
										v.setPair(newvalue);
										return true;
										
									} catch (Exception e) {
										// TODO: handle exception
										console.setText("please ensure that the new value is a valid int");
										return false;
									}

							
								// VARIABLE INCREMENTATION
								} else if (possiblevarname[0].trim().equals("++")) {
									
									if(possiblevarname.length>1) { // there should only be one value for ++
										console.setText("The ++ should be the only paramater");
										return false;
									}else {
									v.setPair(v.getPair() + 1);
									return true;	
									}

								}
								// VARIABLE DECREMENT
								else if (possiblevarname[0].trim().equals("--")) {
									
									if(possiblevarname.length>1) { // there should only be one value for ++
										console.setText("The -- should be the only paramater");
										return false;
									}else {
									v.setPair(v.getPair() - 1);
									return true;	
									}

								}
								

								// Addition
								
								else if(possiblevarname[0].trim().equals("+")) {
									
									try {
										int newvalue = Integer.parseInt(possiblevarname[1].trim());
										v.setPair( v.getPair() + newvalue);
										return true;
										
									} catch (Exception e) {
										// TODO: handle exception
										console.setText("please ensure that the new value is a valid int");
										return false;
									}
								}
								
								// Subtraction
								else if(possiblevarname[0].trim().equals("-")) {
									
									try {
										int newvalue = Integer.parseInt(possiblevarname[1].trim());
										v.setPair( v.getPair() - newvalue);
										return true;
										
									} catch (Exception e) {
										// TODO: handle exception
										console.setText("please ensure that the new value is a valid int");
										return false;
									}
								}
								
								// Multiplication
								else if(possiblevarname[0].trim().equals("*")) {
									
									try {
										int newvalue = Integer.parseInt(possiblevarname[1].trim());
										v.setPair( v.getPair() * newvalue);
										return true;
										
									} catch (Exception e) {
										// TODO: handle exception
										console.setText("please ensure that the new value is a valid int");
										return false;
									}
								}
								
								
								// Devision
								else if(possiblevarname[0].trim().equals("/")) {
									
									try {
										int newvalue = Integer.parseInt(possiblevarname[1].trim());
										v.setPair( v.getPair() / newvalue);
										return true;
										
									} catch (Exception e) {
										// TODO: handle exception
										console.setText("please ensure that the new value is a valid int");
										return false;
									}
								}
								
								// PARAMATERS CANNOT BE MATCHED 
								else {
									console.setText("please check your paramters");
									return false;
									
								}
								
							}
							
							//Variable name not found
							console.setText("please that you are refering to a Variable or Command");
							return false;
						}
					}
					

		}
		
		// Command name not found ... only show this version if no variables have been referenced.
		console.setText("Please make sure you have entered a valid command");
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

			// **********************************************
			// if the parameter is equal to the name of a variable then set the paramater
			// equal to that variables value

			// Loop through the variables array if a match is found then set it

			for (Variable v : variables) {

				if (paramarray[x].equals(v.getKey())) {

					paramarray[x] = v.getPair() + " ";

				}
			}

			// ****************************************

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
