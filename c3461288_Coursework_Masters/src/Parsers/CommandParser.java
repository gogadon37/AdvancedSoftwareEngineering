package Parsers;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextArea;

import Commands.Command;
import Ifs.Ifstatement;
import Loops.Loop;
import Variables.Variable;
import exceptions.CommandNotFoundException;
import exceptions.DuplicateVariableException;
import exceptions.IncorrectNumberofParamatersException;
import exceptions.InvalidParamatersException;
import exceptions.NullCommandException;
import exceptions.OpeningTagNotFoundException;
import exceptions.UnclosedTagException;
import exceptions.VariableNotFoundException;

public class CommandParser {

	ArrayList<Command> actualCommands;
	ArrayList<Variable> variables;
	ArrayList<String> filteredcommands;
	ArrayList<Loop> loopsarray;
	ArrayList<Loop> completedloops;
	ArrayList<Ifstatement> ifstatementarray;
	ArrayList<Ifstatement> completedifstatements;
	ArrayList<ArrayList<String>> approvedcommands;
	ArrayList<String> recombinedcommands;
	JTextArea console;
	String[] commandname;
	int numberofloopscreated = 0;

	public Boolean ValidCommands(String writtencommand, ArrayList<Command> commands, JTextArea jta) throws NullCommandException, IncorrectNumberofParamatersException, InvalidParamatersException, OpeningTagNotFoundException, VariableNotFoundException, UnclosedTagException, DuplicateVariableException, CommandNotFoundException {

		console = jta;
		String[] writtencommands = writtencommand.split("\\r?\\n|\\r");
		actualCommands = commands;
		loopsarray = new ArrayList<Loop>();
		completedloops = new ArrayList<Loop>();
		ifstatementarray = new ArrayList<Ifstatement>();
		completedifstatements = new ArrayList<Ifstatement>();
		variables = new ArrayList<Variable>();
		filteredcommands = new ArrayList<String>();
		approvedcommands = new ArrayList<ArrayList<String>>();
		recombinedcommands = new ArrayList<String>();
		ArrayList<String> writtencommandsblanklinesremoved = new ArrayList<String>();
		int counter = -1; 
		int currenthiarachy = -1;
		
		
		// Remove the blank lines:

		for (String s : writtencommands) {

			if (!s.trim().equals("")) {

				writtencommandsblanklinesremoved.add(s);

			}
		}

		// Check that something other than space has been entered

		if (writtencommandsblanklinesremoved.size() == 0) {
			
			
			console.setText(" ERROR: Please enter a command into the terminal");
			throw new NullCommandException("No valid commands once space has been removed");
			
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
//STEP 1: CHECK FOR TAGS AND CREATE APPROPRIATE OBJECTS (METHODS VARIABLES LOOPS AND IFS)
		

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	

		for (String singlecommand : writtencommandsblanklinesremoved) {

			
			counter++;
			String split[] = singlecommand.trim().split(" ");
			
			
			

			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
			//                    Checking for Variables                          //
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//

			if (split[0].equals("var")) {

				String varnameandvalue = (String) singlecommand.substring(3, singlecommand.length()).trim();

				if(split.length != 4) {
					
					console.setText("Incorrect number of paramaters for the command " + singlecommand);
					throw new IncorrectNumberofParamatersException("Incorrect number of paramaters for Command" + singlecommand);
					
				}
				
				
				
				if(!split[2].equals("=")) {
					
					console.setText("Please ensure that the variable command uses the = with spaces for"  + singlecommand);
					throw new InvalidParamatersException("Please ensure that the variable command uses the = with spaces for"  + singlecommand);
				}
				
				
				String[] keypair = varnameandvalue.split("=");

				// check if the KeyPair has two values
				if (keypair.length == 2) {
					int pair;

					// Check that the variable name doesn't already exist int the array
					for (Variable v : variables) {

						if (v.getKey().equals(keypair[0].trim())) {

							console.setText("Error please ensure that all variable names are unique for "  + singlecommand);
							throw new DuplicateVariableException("Error please ensure that all variable names are unique for "  + singlecommand);
						}

					}

					// check if the paramters are refering to a variable

					for (Variable v : variables) {

						if (keypair[1].trim().equals(v.getKey())) {
							keypair[1] = v.getPair() + " ";
						}
					}

					// Check the second value is a valid int

					try {
						pair = Integer.parseInt(keypair[1].trim());

					} catch (Exception e) {

						console.setText("Error the second paramter of a variable should point to a valid int or a valid variable for "  + singlecommand);

						throw new NumberFormatException("Error the second paramter of a variable should point to a valid int or a valid variable for " + singlecommand);
					}

					// create the variable.

					Variable x = new Variable(keypair[0].trim(), pair);
					variables.add(x);

				} else {

					console.setText("Error a variable should have a value split by and = for "  + singlecommand);
					throw new InvalidParamatersException("Error a variable should have a value split by and = for "  + singlecommand);

				}

			} else

			

			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
			//                    Checking for Loops                              //
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
				
				
			if (split[0].trim().equals("loop")) {

				if (split.length > 3) {
					console.setText("A loop should have 3 paramters for "  + singlecommand );
					throw new IncorrectNumberofParamatersException("Error a loop should have 3 paramters for "  + singlecommand);
				} else {

					if (split[1].trim().equals("for")) {

						// 1) check if the value is a variable if so set the value of it to those
						// variables
						for (Variable v : variables) {

							if (split[2].equals(v.getKey())) {
								split[2] = v.getPair() + " ";
							}
						}

						// 2) Parse the value to an int

						try {
							int loops = Integer.parseInt(split[2].trim());
							Loop loop = new Loop(counter, numberofloopscreated);

							System.out.println("loops name " + loop.getName() + " " + counter);

							// System.out.println("loop start " + counter);
							loop.setTimestoloop(loops);

							// add one to the hiarchy
							currenthiarachy++;
							loop.setHiarachy(currenthiarachy);
							loopsarray.add(loop);
							numberofloopscreated++;

						} catch (Exception e) {
							// TODO: handle exception
							console.setText("Error creating loop, please ensure you are using the correct syntax for " +   singlecommand);
							throw new InvalidParamatersException("Error creating loop, please ensure you are using the correct syntax for "  + singlecommand);
						}

					} else {

						console.setText("Please ensure that the second paramter for the loop is for at "  + singlecommand);
						throw new InvalidParamatersException("Please ensure that the second paramter for the loop is for at "  + singlecommand);

					}

				}

			} else

			
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
			//                    Checking for Endloops                           //
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//	
				
			if (split[0].trim().equals("endloop")) {

				if (split.length > 1) {
					console.setText("Please ensure the endloop has no additional paramters for "  + singlecommand);
					throw new IncorrectNumberofParamatersException("Please ensure the endloop has no additional paramters for "  + singlecommand);
				} else {

					currenthiarachy--;

					// check that there is a loop to close

					if (loopsarray.size() == 0) {

						console.setText("Please ensure that a loop has been created for "  + singlecommand);
						throw new OpeningTagNotFoundException("Please ensure that a loop has been created for " + singlecommand);

					}

					// end tag links to last added loop

					loopsarray.get(loopsarray.size() - 1).setEndposition(counter);

					// add the loop to the completed loops array
					completedloops.add(loopsarray.get(loopsarray.size() - 1));

					// remove the loop from the loops array
					loopsarray.remove(loopsarray.size() - 1);

				}

			} else

			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
			//                    Checking for Ifs                                //
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//

			if (split[0].trim().equals("if")) {

				// check if we are dealing with a single or Multi-lined variable

				String[] then = singlecommand.trim().split(" then ", 2);
				

				// if its a single command the then should be array should have two elements the
				// condition and the command

				if (then.length == 2) {
					System.out.println(then[0]);
					System.out.println(then[0].trim().split(" ").length);
					
					if(then[0].trim().split(" ").length != 4 ) {
						
						console.setText("Incorrect number of paramaters, Please ensure that you have spaces between each element for "  + singlecommand);
						throw new IncorrectNumberofParamatersException("Incorrect number of paramaters, Please ensure that you have spaces between each element for "  + singlecommand);
					}
					
					
					
					
					
					// check the paramters

					// remove the if from the front of the first array value
					then[0] = then[0].trim().substring(2).trim();

					System.out.println(then[0]);
					int matchfound = -1;

					
					
					
					// split again
					String[] splitagain = then[0].split("=", 2);

					// check if the users variable against other variables

					for (Variable v : variables) {

						if (v.getKey().equals(splitagain[0].trim())) {

							splitagain[0] = "" + v.getPair();
							matchfound++;
						}

					}

					if (matchfound == -1) {

						// check if the user has entered an int instead

						try {

							Integer.parseInt(splitagain[0].trim());
							

						} catch (Exception e) {

							console.setText("Error the variable in the if statement cannot be found for "  + singlecommand);
							throw new VariableNotFoundException("Error the variable in the if statement cannot be found for "  + singlecommand);

						}

					}

					// check the checkagainst against the variables to find a match

					for (Variable vv : variables) {
						if (vv.getKey().equals(splitagain[1].trim())) {

							splitagain[1] = "" + vv.getPair();
						}
					}

					try {
						Integer.parseInt(splitagain[1].trim());
					

					} catch (Exception e) {
						// TODO: handle exception

						console.setText("please ensure that the comparrison vaule is a valid int or variable for "  + singlecommand);
						throw new VariableNotFoundException("please ensure that the comparrison vaule is a valid int or variable for " +   singlecommand);
					}

					// create the if statement

					Ifstatement i = new Ifstatement(counter);
					i.setEndposition(counter);
					i.setCondition1(Integer.parseInt(splitagain[0].trim()));
					i.setCondition2(Integer.parseInt(splitagain[1].trim()));
					i.setSinglestatementcommands(then[1]);
					completedifstatements.add(i);

				} else {

					// multi-lined ifstatement created

					// remove the if from the start

					String values = singlecommand.trim().substring(2);
					
					System.out.println(values.trim().split(" ").length);
					
					
					if(values.trim().split(" ").length !=3) {
						
						console.setText("Please ensure the paramters are split by spaces for "  + singlecommand);
						throw new InvalidParamatersException("Please ensure the paramters are split by spaces for"  + singlecommand);
					}
					
					String[] value1and2 = values.split("=", 2);

					if (value1and2.length != 2) {

						console.setText(
								"Error please ensure that the if statement has 2 paramters seperated by a condition = for "  + singlecommand);
						throw new IncorrectNumberofParamatersException("Error please ensure that the if statement has 2 paramters seperated by a condition = for " + singlecommand );
					} else {

						// check the first paramter against variables

						for (Variable v : variables) {

							if (v.getKey().trim().equals(value1and2[0].trim())) {

								value1and2[0] = "" + v.getPair();

							}

							if (v.getKey().trim().equals(value1and2[1].trim())) {

								value1and2[1] = "" + v.getPair();

							}

						}

						// parse the ints

						try {

							int value1 = Integer.parseInt(value1and2[0].trim());
							int value2 = Integer.parseInt(value1and2[1].trim());

						} catch (Exception e) {
							// TODO: handle exception

							console.setText("please ensure that the values being compared are valid ints or variables for "  + singlecommand);
							throw new NumberFormatException("please ensure that the values being compared are valid ints or variables for "  + singlecommand);

						}

						Ifstatement i = new Ifstatement(counter);
						i.setCondition1(Integer.parseInt(value1and2[0].trim()));
						i.setCondition2(Integer.parseInt(value1and2[1].trim()));

						ifstatementarray.add(i);

					}

				}

			} else 
				
				//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
				//                    Checking for EndIfs                             //
				//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
				
				if (split[0].trim().equals("endif")) {

				if (split.length > 1) {

					console.setText("please ensure the endif has no additional paramters for"  + singlecommand);
					throw new IncorrectNumberofParamatersException("please ensure the endif has no additional paramters for "  + singlecommand);
				}

				if (ifstatementarray.size() == 0) {

					console.setText("Please ensure that an if statement has been created before attempting to close for "  + singlecommand);
					throw new OpeningTagNotFoundException("Please ensure that an if statement has been created before attempting to close for "  + singlecommand);
				}

				// get the last ifstatementcreated

				Ifstatement ifs = ifstatementarray.get(ifstatementarray.size() - 1);
				ifs.setEndposition(counter);

				ifstatementarray.remove(ifstatementarray.size() - 1);
				completedifstatements.add(ifs);

			}

		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//                          STEP 2: PROCESS THE TAGS (METHODS LOOPS AND IFS)

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if (ifstatementarray.size() != 0) {

			console.setText("please ensure all ifstatements are closed");
			throw new UnclosedTagException("please ensure all ifstatements are closed");
		}

		// execute the if statements

		for (Ifstatement ifs : completedifstatements) {

			if (ifs.getCondition1() == ifs.getCondition2()) {

				if (ifs.getSinglestatementcommands().trim().equals("na")) {

					// keep all code in between the same but set the if start and end to deadcode to
					// be removed

					writtencommandsblanklinesremoved.set(ifs.getStartpostion(), "deadcode");
					writtencommandsblanklinesremoved.set(ifs.getEndposition(), "deadcode");

				} else {

					// the single line if statement passed set the line to contain the command
					writtencommandsblanklinesremoved.set(ifs.getStartpostion(), ifs.getSinglestatementcommands());

				}

			} else {

			

				if (ifs.getSinglestatementcommands().trim().equals("na")) {

					// a multi lined if statement has been found

					int start = ifs.getStartpostion();
					int end = ifs.getEndposition();

					System.out.println("stat : end " + start + end);

					// starting from start change all lines until the end to deadcode

					for (int x = start; x <= end; x++) {

						System.out.println(x);
						writtencommandsblanklinesremoved.set(x, "deadcode");

					}

				} else {

					// the if statement failed so set the line to be deadcode to be removed later

					writtencommandsblanklinesremoved.set(ifs.getStartpostion(), "deadcode");

				}

			}

		}

		// sort the loops by hiarchy followed by name
		// this means that when looped through backwards it will begin with the deepest
		// loop moving backwards
		// and if there are two loops at this level then execute the one which was
		// created first

		Collections.sort(completedloops);

		// Check that all loops that have been created are completed and return false if
		// any loops havent been closed

		if (loopsarray.size() > 0) {

			console.setText("Please ensure all opening loop tags are closed by an endloop tag");
			throw new UnclosedTagException("Please ensure all opening loop tags are closed by an endloop tag");

		}

		int numberofcommandsadded; // holds the number of commands added
		int endoflastloop; // holds the end possision of the last loop

		// PROCESS THE LOOPS //

		for (int x2 = completedloops.size() - 1; x2 >= 0; x2--) {

			numberofcommandsadded = 0; // reset as loops have already been updated for previous commands added.
			Loop loop = completedloops.get(x2); // get the last loop from the completed loops
			int totalloops = loop.getTimestoloop(); // get the number of times the loop is to be looped
			int totalelements = (loop.getEndposition() - loop.getStartpostion()) - 1; // the number of elements between
																						// start and end

			// if total loops is equal to 0 set the lines in-between start of end to
			// deadcode to be filtered out

			if (totalloops == 0) {

				for (int y = 1; y <= totalelements; y++) {

					writtencommandsblanklinesremoved.set((loop.getEndposition() - y), "deadcode");

				}

			}

			// loop for the number of times specified -1 this is because one copy of the
			// code in-between already exists

			for (int x = 1; x < totalloops; x++) {

				System.out.println("total elements" + totalelements);

				for (int x1 = 1; x1 <= totalelements; x1++) {

					// check the user is not trying to create a variable in the loop

					if (writtencommandsblanklinesremoved.get((loop.getEndposition() - x1)).subSequence(0, 3)
							.equals("var") && totalloops > 1) {

						console.setText(
								"A variable with the same name can't be created more than once");
						throw new DuplicateVariableException("A variable with the same name can't be created more than once");

					}

					numberofcommandsadded++;
					System.out.println("adding index of " + (loop.getEndposition() - x1));
					System.out.println("loop " + x + " adding "
							+ writtencommandsblanklinesremoved.get((loop.getEndposition() - x1)));
					writtencommandsblanklinesremoved.add(loop.getEndposition(),
							writtencommandsblanklinesremoved.get((loop.getEndposition() - x1)));

				}

				endoflastloop = loop.getEndposition() + numberofcommandsadded;

				// update the positions of all the loops if commands have been added

				for (Loop l : completedloops) {

					if (l.getStartpostion() > (endoflastloop - numberofcommandsadded)) {

						System.out.println("start updated");
						l.setStartpostion(l.getStartpostion() + numberofcommandsadded);

					}

					if (l.getEndposition() > (endoflastloop - numberofcommandsadded)) {

						System.out.println("end updated");
						l.setEndposition(l.getEndposition() + numberofcommandsadded);

					}

				}

			}

		}

		// remove the loop tags

		// clear the completed loops for the next time the button is clicked

		if (completedloops.size() != 0) {

			completedloops.clear();
			numberofloopscreated = 0;

		}

		// remove all the command tags and deadcode from the array

		for (String singString : writtencommandsblanklinesremoved) {

			System.out.println("final command " + singString);

			String splits[] = singString.trim().split(" ");
			if (splits[0].equals("loop") || splits[0].trim().equals("endloop") || splits[0].equals("var")
					|| splits[0].equals("deadcode")) {

				System.out.println("removing " + splits[0].trim());

			} else {

				filteredcommands.add(singString);
				System.out.println("adding " + splits[0].trim());
			}

		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//STEP 3: CHECK THE COMMANDS AND EXECUTE THEM IF CORRECT

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// check the commands

		for (String single : filteredcommands) {

			try {
				IsValidCommand(single);


				
				
			} catch (CommandNotFoundException e) {
				// TODO Auto-generated catch block
				throw e;
			}
		}

		// execute the commands
		
		String executed = "-----------Commands Executed---------";

		for (ArrayList<String> array : approvedcommands) {

			
			
			for (Command c1 : commands) {

				if (array.get(0).equals(c1.getName())) {

					c1.Runcommand(array);
					executed = executed + "\n" + array.get(0) + " executed";
				}

			}
			
		}
		
		console.setText(executed);
		return true; // All commands executed successfully return true
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//  METHODS

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private Boolean IsValidCommand(String command) throws CommandNotFoundException, IncorrectNumberofParamatersException, InvalidParamatersException {

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

			if(commandname[0].trim().equals("circle")) {
				
			params = new String[1];
			params[0] = commandname[1];
			
			}else if(commandname[0].trim().equals("triangle")) {
				
				params = commandname[1].split(",",5);
				
				
			}
			
			else {
			
			params = commandname[1].split(",",2);
	
			}
			
			
			
		}else{

			params = new String[0];

		}

		// loop through the actual commands and check if
		// the command name entered matches a verified command
		// If a match has been found then check the parameters else
		// its an invalid command return false.

		boolean commandnotfound = true;

		for (Command c : actualCommands) {

			if (commandname[0].trim().equals(c.getName().trim())) {

				if (checktheparamaters(c.getNumOfParams(), params, commandname[0].trim())) {

					commandnotfound = false;
					return true;

				} else {

					throw new InvalidParamatersException("Paramaters are invalid for " + command);
				}

			}

		}

		if (variables.size() > 0 && commandnotfound) {

			String[] possiblevarname;

			// Try to split any paramateters

			try {
				possiblevarname = commandname[1].split(" ");

			} catch (Exception e) {
				
				console.setText("Error incorrect number of paramaters for a variable modifier command for " + command );
				throw new IncorrectNumberofParamatersException("Error incorrect number of paramaters for a variable modifier command for "+ command );
			}
			
			
			// Loop through the variables array and check that name against the command
			// entered

			for (Variable v : variables) {

				// check if the second paramter is an existing variable and update it to
				// represent its value

				for (Variable v1 : variables) {

					try {

						if (v1.getKey().trim().equals(possiblevarname[1].trim())) {

							possiblevarname[1] = "" + v1.getPair();

						}
					} catch (Exception e) {

						if (v1.getKey().trim().equals(possiblevarname[0].trim())) {

							possiblevarname[1] = "" + v1.getPair();

						}

					}

				}

				// if the variable is a variable in the array

				if (v.getKey().trim().equals(commandname[0].trim())) {

					// VARIABLE REASSINGED
					if (possiblevarname[0].trim().equals("=")) {

						try {
							int newvalue = Integer.parseInt(possiblevarname[1].trim());
							v.setPair(newvalue);
							return true;

						} catch (Exception e) {
							// TODO: handle exception
							console.setText("please ensure that the new value is a valid int for " + command);
							throw new InvalidParamatersException("please ensure that the new value is a valid int for " + command);
						}

						// VARIABLE INCREMENTATION
					} else if (possiblevarname[0].trim().equals("++")) {

						if (possiblevarname.length > 1) { // there should only be one value for ++
							console.setText("The ++ should be the only paramater for " + command );
							throw new InvalidParamatersException("please ensure the adjustment is the only paramater for " + command);
						} else {
							v.setPair(v.getPair() + 1);
							return true;
						}

					}
					// VARIABLE DECREMENT
					else if (possiblevarname[0].trim().equals("--")) {

						if (possiblevarname.length > 1) { // there should only be one value for ++
							throw new InvalidParamatersException("please ensure the adjustment is the only paramater for " + command);
							
						} else {
							v.setPair(v.getPair() - 1);
							return true;
						}

					}

					// Addition

					else if (possiblevarname[0].trim().equals("+")) {

						try {
							int newvalue = Integer.parseInt(possiblevarname[1].trim());
							v.setPair(v.getPair() + newvalue);

							return true;

						} catch (Exception e) {
							// TODO: handle exception
							console.setText("please ensure that the new value is a valid int for " + command);
							throw new InvalidParamatersException("please ensure that the new value is a valid int for "+ command );
						}
					}

					// Subtraction
					else if (possiblevarname[0].trim().equals("-")) {

						try {
							int newvalue = Integer.parseInt(possiblevarname[1].trim());
							v.setPair(v.getPair() - newvalue);
							return true;

						} catch (Exception e) {
							// TODO: handle exception
							console.setText("please ensure that the new value is a valid int for "+ command );
							throw new InvalidParamatersException("please ensure that the new value is a valid int for " + command);
						}
					}

					// Multiplication
					else if (possiblevarname[0].trim().equals("*")) {

						try {
							int newvalue = Integer.parseInt(possiblevarname[1].trim());
							v.setPair(v.getPair() * newvalue);
							return true;

						} catch (Exception e) {
							// TODO: handle exception
							console.setText("please ensure that the new value is a valid int for " + command);
							throw new InvalidParamatersException("please ensure that the new value is a valid int for " + command);
						}
					}

					// Devision
					else if (possiblevarname[0].trim().equals("/")) {

						try {
							int newvalue = Integer.parseInt(possiblevarname[1].trim());
							v.setPair(v.getPair() / newvalue);
							return true;

						} catch (Exception e) {
							// TODO: handle exception
							console.setText("please ensure that the new value is a valid int for " + command );
							throw new InvalidParamatersException("please ensure that the new value is a valid int for " + command);
						}
					}

					// PARAMATERS CANNOT BE MATCHED
					else {
						console.setText("the variable modifier cannot be identified for " + command);
						throw new InvalidParamatersException("Paramater not recognised for " + command);

					}

				}

			} // Variable name not found

			console.setText("please that you are refering to a Variable or Command for " + possiblevarname[0].trim());
			throw new CommandNotFoundException("please that you are refering to a Variable or Command for " + possiblevarname[0].trim());
		}

		// Command name not found ... only show this version if no variables have been
		// referenced.
		console.setText("Please make sure you have entered a valid command for " + commandname[0]);
		throw new CommandNotFoundException("Please make sure you have entered a valid command for " + commandname[0]);
		

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private boolean checktheparamaters(int numberexpected, String[] paramarray, String commandname) throws IncorrectNumberofParamatersException, InvalidParamatersException {

		// check that the length of the array holding the command and its
		// variables is equal to the number expected.

		// reset the recombined array to prevent the same command being added over and
		// over.
		recombinedcommands = new ArrayList<String>();

		if (paramarray.length != (numberexpected)) {

			console.setText("ERROR: The command contains the incorrect number of paramaters for " + commandname);
			throw new IncorrectNumberofParamatersException("ERROR: The command contains the incorrect number of paramaters for " + commandname);

		}

		// The array contains the correct number of parameters and they are
		// Separated appropriately so now try to parse them to int's

		for (int x = 0; x < numberexpected; x++) {

			// **********************************************
			// if the parameter is equal to the name of a variable then set the paramater
			// equal to that variables value

			// Loop through the variables array if a match is found then set it

			for (Variable v : variables) {

				if (paramarray[x].trim().equals(v.getKey())) {

					paramarray[x] = v.getPair() + " ";

				}
			}

			// ****************************************

			try {

				Integer.parseInt(paramarray[x].trim());

			} catch (Exception e) {

				console.setText("ERROR: Please make sure the Paramater is a valid Integer for " + commandname);
				throw new InvalidParamatersException("ERROR: Please make sure the Paramater is a valid Integer for " + commandname);
			}

		}

		// The command has passed all checks add it to the
		// recombined the array and add it to the approved commands arraylist.

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
