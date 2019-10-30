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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.filechooser.FileNameExtensionFilter;

import Parsers.CommandParser;
import c3461288_Coursework_Masters.Pannels.CanvasPannel;
import c3461288_Coursework_Masters.Pannels.CodePannel;
import c3461288_Coursework_Masters.Pannels.CommandPannel;


public class Main{

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		
		
		
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setMinimumSize(new Dimension(800,474));
		jframe.setLayout(new BorderLayout());
		
		JMenuBar jmbar = new JMenuBar();
		
		
		JMenu File = new JMenu("File");
		jmbar.add(File);
		
		JMenuItem Load = new JMenuItem("Load");
		JMenuItem Save = new JMenuItem("Save");
		
		File.add(Load);
		File.add(Save);
		
		
		CodePannel codepannel = new CodePannel();
		CommandPannel commandpannel = new CommandPannel();
		CanvasPannel canvaspannel = new CanvasPannel();
		canvaspannel.setMaximumSize(new Dimension(400,400));
	
		jframe.add(codepannel, BorderLayout.WEST);
		jframe.add(commandpannel, BorderLayout.PAGE_END);
		jframe.add(canvaspannel, BorderLayout.CENTER);
		jframe.add(jmbar, BorderLayout.NORTH);
		jframe.setVisible(true);
		
		CommandParser parser = new CommandParser();

		Save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Where Would You Like To Save?");
				chooser.addChoosableFileFilter(new FileNameExtensionFilter(".txt", ".txt"));
				
				if(chooser.showSaveDialog(null) == chooser.APPROVE_OPTION) {
					
					String path = chooser.getSelectedFile().getAbsolutePath();
					path = path.concat(".txt");
					String name = chooser.getSelectedFile().getName();
					
					
					System.out.println(path);
					
					try {
						
						java.io.File file = new java.io.File(path);
						
						//create a fos and write the string into a binary array
						
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
		
		
		
		
		
		
		
		
		
		// Action Listners //
		
		Load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Open up a file explorer
				
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Please Choose a File to Load");
				jfc.addChoosableFileFilter(new FileNameExtensionFilter(".txt", ".txt"));
				
			
				// open the jfc and run code if its been approved 
				
				if(jfc.showOpenDialog(null) == jfc.APPROVE_OPTION) {
					
					
					
					
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		commandpannel.jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// get the code from the JText Area
				String Code = codepannel.jta.getText().toLowerCase();
				
				// get the code from the JText
				String SingleCommand = commandpannel.jtf.getText().toString().toLowerCase();
				
				
				
				// check if the command text field is equal to run if so run the big block if not 
				// run the command line
				
				
				if(SingleCommand.contentEquals("run")) {
					
					// Pass the code to the parser to break down the code line by line
					Boolean Commandsarevalid = parser.ValidCommands(Code);
					if(Commandsarevalid) {
						
						System.out.println("The Code is valid well done!");
						
					}else {
						
						System.out.println("Code is Invalid, please check it");
						
					}
					
				}else {
					
					
					Boolean Commandsarevalid = parser.ValidCommands(SingleCommand);
					if(Commandsarevalid) {
						
						System.out.println("The Code is valid well done!");
						
					}else {
						
						System.out.println("Code is Invalid, please check it");
						
					}
					
					
					
					
					
					
				}
				
				
				
				
				
				
				
			}
		});				
	}

	


}
