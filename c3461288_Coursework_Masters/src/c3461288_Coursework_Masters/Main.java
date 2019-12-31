package c3461288_Coursework_Masters;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Commands.Circle;
import Commands.Clear;
import Commands.Command;
import Commands.DrawTo;
import Commands.MoveTo;
import Commands.RectangleCommand;
import Commands.Reset;
import Commands.Triangle;
import Commands.Factories.CommandFactory;
import Parsers.CommandParser;
import c3461288_Coursework_Masters.Pannels.CanvasPannel;
import c3461288_Coursework_Masters.Pannels.CodePannel;
import c3461288_Coursework_Masters.Pannels.CommandPannel;
import c3461288_Coursework_Masters.Pannels.ConsolePannel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Create the Jframe 
		
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setMinimumSize(new Dimension(850, 580));
		jframe.setMaximumSize(new Dimension(850, 580));
		jframe.setLayout(new BorderLayout());

		
		// Create the Jmenu bar and add 2 sub menu Items
		
		JMenuBar jmbar = new JMenuBar();

		JMenu File = new JMenu("File");
		jmbar.add(File);

		JMenuItem Load = new JMenuItem("Load");
		JMenuItem Save = new JMenuItem("Save");

		File.add(Load);
		File.add(Save);

		
		// Create the pannels for the applications
		
		
		CodePannel codepannel = new CodePannel();
		CommandPannel commandpannel = codepannel.commandPannel;
		CanvasPannel canvaspannel = new CanvasPannel(500, 500);

		ConsolePannel consolepannel = new ConsolePannel();

		JScrollPane canvasscroller = new JScrollPane(canvaspannel);

		JTabbedPane canvastab = new JTabbedPane();
		canvastab.addTab("Image", canvasscroller);

		JTabbedPane codeareapane = new JTabbedPane();
		codeareapane.addTab("CodeArea", codepannel);

		jframe.add(codeareapane, BorderLayout.WEST);
		jframe.add(consolepannel, BorderLayout.SOUTH);
		jframe.add(canvastab, BorderLayout.CENTER);
		jframe.add(jmbar, BorderLayout.NORTH);
		jframe.setVisible(true);

		
		// Create the CommandParser object which will call
		// the Validation method to validate user input

		CommandParser parser = new CommandParser();

		
		
		// Create an Array of all the commands
		String[] commandstrings = new String[] { "circle", "clear", "drawto", "moveto", "rectangle", "reset",
				"triangle"};

		// Create and ArrayList to hold all the command objects
		
		ArrayList<Command> commands = new ArrayList<Command>();

		// Create the commands factory and return the correct commands
		// depending on the strings entered.

		CommandFactory commandFactory = new CommandFactory(canvaspannel);

		for (String command : commandstrings) {
			Command commandobject = commandFactory.GetCommand(command);
			commands.add(commandobject);
		}
		
		
		
		// Create an Action Listener for the Save Button
		
		Save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Create the file Chooser Object 
				
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Where Would You Like To Save?");
				chooser.addChoosableFileFilter(new FileNameExtensionFilter(".txt", ".txt"));
				
				//Launch the chooser and save the path to the String path
				// if approved is selected.
				
				if (chooser.showSaveDialog(null) == chooser.APPROVE_OPTION) {

					String path = chooser.getSelectedFile().getAbsolutePath();
					path = path.concat(".txt");
				
					try {
						
						// create a new file with the given path

						java.io.File file = new java.io.File(path);

						// write the userinput to the file 
						
						FileOutputStream fileOutputStream = new FileOutputStream(file);
						fileOutputStream.write(codepannel.jta.getText().toString().getBytes());
						fileOutputStream.close();

					} catch (Exception ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}

				}

			}
		});

		//Create the action Listner for the Load Button

		Load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Open up a file explorer

				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Please Choose a File to Load");
				jfc.addChoosableFileFilter(new FileNameExtensionFilter(".txt", ".txt"));

				// open the jfc and run code if its been approved

				if (jfc.showOpenDialog(null) == jfc.APPROVE_OPTION) {

					// get the file path

					String filepath = jfc.getSelectedFile().getAbsolutePath();

					// get the contents of the file

					try {
						String content = Files.readString(Paths.get(filepath));

						// set the codearea to contain the code

						codepannel.jta.setText(content);

					} catch (IOException e1) {

						// TODO Auto-generated catch block
						e1.printStackTrace();

					}

				}

			}
		});

		// Add an action listener for the JButton
		
		commandpannel.jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				// get the code from the JText Area
				String Code = codepannel.jta.getText().toLowerCase();

				// get the code from the JText
				String SingleCommand = commandpannel.jtf.getText().toString().toLowerCase();

				// check if the command text field is equal to run if so run the big block if
				// not run the command line

				if (SingleCommand.contentEquals("run")) {

					// Pass the code to the parser to break down the code line by line
					Boolean Commandsarevalid = parser.ValidCommands(Code, commands, consolepannel.jta);
					if (Commandsarevalid) {

						System.out.println("The Code is valid well done!");

					} else {

						System.out.println("Code is Invalid, please check it");

					}

				} else {

					Boolean Commandsarevalid = parser.ValidCommands(SingleCommand, commands, consolepannel.jta);

					if (Commandsarevalid) {

						System.out.println("The Code is valid well done!");

					} else {

						

					}

				}

			}
		});
	}

}
