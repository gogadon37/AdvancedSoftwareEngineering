package Parsers;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextArea;

import Commands.Command;
import Loops.Loop;
import Variables.Variable;

public class CommandParser {

	ArrayList<Command> actualCommands;
	ArrayList<Variable> variables;
	ArrayList <String> filteredcommands;
	ArrayList<Loop> loopsarray;
	ArrayList<Loop> completedloops;
	ArrayList<ArrayList<String>> approvedcommands;
	ArrayList<String> recombinedcommands;
	JTextArea console;
	String[] commandname;
	int numberofloopscreated = 0;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Boolean ValidCommands(String writtencommand, ArrayList<Command> commands, JTextArea jta) {

		// Assign the variables
		console = jta;
		String[] writtencommands = writtencommand.split("\\r?\\n|\\r");
		actualCommands = commands;
		loopsarray = new ArrayList<Loop>();
		completedloops =  new ArrayList<Loop>();
		variables = new ArrayList<Variable>();
		filteredcommands = new ArrayList<String>();
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

		
	
		
			
		
//LOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPS 
		
		
		int counter = -1; // holds the number of times the loop has occured 
		int currenthiarachy = -1;
		
		
		
		
		// INSIDE LOOP OF WRITTENCOMMANDSBLANKLINESREMOVED
		// for every command in entered by the user check the following things
		//
		// 1) check if the user is creating a variable and create the variable
		//
		//          - check variable doesn't exist already
		//			- check the if the parameter is referring to another variable and replace it with the variables value
		//			- check that the parameter is a valid int
		//			- create a variable object and add it to the variables arraylist
		//
		// 2) check if the user is creating a loop
		//
		// 			- check the parameters and use values of variables if required
		// 			- check that the parameters are split by a for
		//			- check the paramter is a valid int
		//			- create a loop object and insert its starting postion
		//			- add the loop to the loops array and increase number of loops created
		// 
		// 3) check if the user is ending a loop
		//
		//  		- The end loop tag links to the last created loop
		//			- get the last created loop and set its end to the current position
		//			- add this last created loop to a completed loops array
		//          - remove the last created loop from the loops array
		
	
		
		
	
		
		ArrayList<Integer> positionofendlooptags = new ArrayList<Integer>();
	
		
		for(String singlecommand : writtencommandsblanklinesremoved) {
			
			counter++;
			String split[] = singlecommand.trim().split(" ");
			
			
			
			// ***********************************************************************************
						// check if first 3 chars are the var keyword
						// but dont run this segment if the lines length is
						// less than 3 chars as it will not say var and cause a
						// bug with the next conditon
			

						if (split[0].equals("var")) {

							String varnameandvalue = (String) singlecommand.substring(3, singlecommand.length()).trim();

							// split by equals //
							String[] keypair = varnameandvalue.split("=");

							// check if the KeyPair has two values
							if (keypair.length == 2) {
								int pair;
								
								// Check that the variable name doesn't already exist int the array
								for(Variable v : variables) {
									
									if(v.getKey().equals(keypair[0].trim())) {
										
										console.setText("Error please ensure that all variable names are unique");
										return false;
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

									console.setText("Error a variables value should be a valid int");
									
									return false;
								}

								// create the variable.

								Variable x = new Variable(keypair[0].trim(), pair);
								variables.add(x);

							} else {

								console.setText("Error a variable should have a value split by and =");
								return false;

							}

						}else
			
			
			
			// --------------------------------------------------------------------------------------------------------------------------------------
			
			if (split[0].trim().equals("loop")){
			 
				if(split.length>3){ console.setText("A loop should have 3 paramters"); return false;}else {
					
						if(split[1].trim().equals("for")) {
						
							
								//  1) check if the value is a variable if so set the value of it to those variables
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
									
									//System.out.println("loop start " + counter);
									loop.setTimestoloop(loops);
									
									// add one to the hiarchy
									currenthiarachy ++;
									loop.setHiarachy(currenthiarachy);
									loopsarray.add(loop);
									numberofloopscreated++;
									
									
									
									
									
									
									
								} catch (Exception e) {
									// TODO: handle exception
								}
							
						
						}else {
					
							console.setText("Please ensure that the second paramter for the loop is for");
							return false;
					
						}
	
				}
					
			}
			
			// --------------------------------------------------------------------------------------------------------------------------------------
			
			if (split[0].trim().equals("endloop")) {
				
					if(split.length > 1) {console.setText("Please ensure the endloop has no additional paramters"); return false;} else {
					
						currenthiarachy --;
				
							
								// check that there is a loop to close
					
								if(loopsarray.size() == 0) {
							
										console.setText("Please ensure that a loop has been created");
										return false;
							
								}
						
	
								
								// end tag links to last added loop
								
								loopsarray.get(loopsarray.size()-1).setEndposition(counter);
								
								
								// add the loop to the completed loops array
								completedloops.add(loopsarray.get(loopsarray.size()-1));
								
								
								// remove the loop from the loops array
								loopsarray.remove(loopsarray.size()-1);
								
								
							
								
							}
		
						}
					
				
			}
		
		
		
		
			
		
		
		
		
		
		
		
		
		
		
		
		
		// sort the loops by hiarchy followed by name 
		// this means that when looped through backwards it will begin with the deepest loop moving backwards
		// and if there are two loops at this level then execute the one which was created first
		
		Collections.sort(completedloops);

		

		// Check that all loops that have been created are completed and return false if any loops havent been closed
	
		if(loopsarray.size()>0) {
			
			console.setText("Please ensure all opening loop tags are closed by an endloop tag");
			return false;
			
		}
		
		
		
		
		int numberofcommandsadded; // holds the number of commands added
		int endoflastloop;         // holds the end possision of the last loop
		
		
		// PROCESS THE LOOPS //
		
		
		for(int x2 = completedloops.size()-1; x2 >=0; x2--) {
			
			numberofcommandsadded = 0; // reset as loops have already been updated for previous commands added.
			Loop loop = completedloops.get(x2); // get the last loop from the completed loops
			int totalloops = loop.getTimestoloop();   // get the number of times the loop is to be looped
			int totalelements = (loop.getEndposition()-loop.getStartpostion())-1; // the number of elements between start and end
			
	
			
				   // if total loops is equal to 0 set the lines in-between start of end to deadcode to be filtered out
	
			
				if(totalloops == 0) {
					
					for(int y = 1; y<=totalelements; y++) {
						
						writtencommandsblanklinesremoved.set((loop.getEndposition()-y), "deadcode");
						
					}
					
				}
					
			
			
			
			
					// loop for the number of times specified -1 this is because one copy of the code in-between already exists

					for(int x = 1; x< totalloops; x++) {
					
					
						System.out.println("total elements" + totalelements);
						
						for(int x1 = 1;  x1<= totalelements; x1++) {
							
							
							
							// check the user is not trying to create a variable in the loop
							
							if(writtencommandsblanklinesremoved.get((loop.getEndposition()-x1)).subSequence(0, 3).equals("var") && totalloops > 1) {
								
								console.setText("A variable with the same name can't be created more than once try moving it outside the array");
								return false;
								
							}
							
							
							numberofcommandsadded++;
							System.out.println("adding index of " + (loop.getEndposition()-x1));
							System.out.println("loop "+ x + " adding " + writtencommandsblanklinesremoved.get((loop.getEndposition()-x1)));
							writtencommandsblanklinesremoved.add(loop.getEndposition(), writtencommandsblanklinesremoved.get((loop.getEndposition()-x1)));
							
							
					}
						
						endoflastloop = loop.getEndposition()+numberofcommandsadded;
						
						
						
						// update the positions of all the loops if commands have been added
						
						for(Loop l :completedloops) {
							
						
							if(l.getStartpostion() > (endoflastloop - numberofcommandsadded)) {
								
								System.out.println("start updated");
								l.setStartpostion(l.getStartpostion() + numberofcommandsadded);
								
							}
							
							if(l.getEndposition() > (endoflastloop - numberofcommandsadded)) {
								
								System.out.println("end updated");
								l.setEndposition(l.getEndposition() + numberofcommandsadded);
								
							} 
							
							
						}		
		
			}
			
				
		}
		
		// remove the loop tags

		// clear the completed loops for the next time the button is clicked
		
		if(completedloops.size() != 0) {
	
			completedloops.clear();
			numberofloopscreated = 0;
		
		}
		
		
		
		
		// remove all the command tags from the array
		
		
		for(String singString : writtencommandsblanklinesremoved) {
			
			System.out.println("final command " + singString);
			
			String splits[] = singString.trim().split(" ");
			if(splits[0].trim().equals("loop") || splits[0].trim().equals("endloop") || splits[0].equals("var") || splits[0].equals("deadcode")){
				
				System.out.println("removing " + splits[0].trim());
				
				
			}else {
				
				filteredcommands.add(singString);
				System.out.println("adding " + splits[0].trim());
			}
			
			
		}
		

		

		
		
//LOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPSLOOPS 
		
		// check all paramters 
		
		for (String single : filteredcommands) {
			
			
			
			if(!IsValidCommand(single)) {
				
				return false;
				
			}
			
			
		}
		
	
		
		
		
		



		// ********************************************************************************************

		// The approvedcommands arraylist now holds the commands if all were
		// correct. loop through the array until the approved command matches
		// the correct command name execute the command passing in its parameters.

	

		for (ArrayList<String> array : approvedcommands) {

			
			
			for (Command c1 : commands) {

				if (array.get(0).equals(c1.getName())) {
					
					
					c1.Runcommand(array);

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
		
		
		boolean commandnotfound = true;
		
		for (Command c : actualCommands) {

			
			
			if (commandname[0].trim().equals(c.getName())) {

				if (checktheparamaters(c.getNumOfParams(), params, commandname[0].trim())) {
					
					commandnotfound = false;
					return true;

				} else {
					
					return false;
				}


			} 

		}
		
		
		if (variables.size() > 0 && commandnotfound) {

		
			String[] possiblevarname;

			// Try to split any paramateters

			try {
				possiblevarname = commandname[1].split(" ");

			} catch (Exception e) {

				// if theres no paramaters than it is not a valid command

				console.setText(java.time.LocalTime.now() + " ERROR: Please ensure the variable has a paramater");
				return false;
			}

			// Loop through the variables array and check that name against the command
			// entered

			for (Variable v : variables) {
				
				

				// check if the second paramter is an existing variable and update it to represent its value
				
				
				for(Variable v1 : variables) {
					
				
					
					if(v1.getKey().trim().equals(possiblevarname[1].trim())) {
						
					
						
						possiblevarname[1] = ""+ v1.getPair();
					
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
							console.setText("please ensure that the new value is a valid int");
							return false;
						}

						// VARIABLE INCREMENTATION
					} else if (possiblevarname[0].trim().equals("++")) {

						if (possiblevarname.length > 1) { // there should only be one value for ++
							console.setText("The ++ should be the only paramater");
							return false;
						} else {
							v.setPair(v.getPair() + 1);
							return true;
						}

					}
					// VARIABLE DECREMENT
					else if (possiblevarname[0].trim().equals("--")) {

						if (possiblevarname.length > 1) { // there should only be one value for ++
							console.setText("The -- should be the only paramater");
							return false;
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
							console.setText("please ensure that the new value is a valid int");
							return false;
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
							console.setText("please ensure that the new value is a valid int");
							return false;
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
							console.setText("please ensure that the new value is a valid int");
							return false;
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

			
			}	// Variable name not found
			
			console.setText("please that you are refering to a Variable or Command" + possiblevarname[0].trim());
			return false;
		}
	

			

		// Command name not found ... only show this version if no variables have been
		// referenced.
		console.setText("Please make sure you have entered a valid command"  + commandname[0]);
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

				console.setText("ERROR: Please make sure the Paramater is a valid Integer");
				return false;
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
