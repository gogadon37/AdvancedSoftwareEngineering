package c3461288_Coursework_Masters;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;



import Parsers.CommandParser;
import c3461288_Coursework_Masters.Pannels.CanvasPannel;
import c3461288_Coursework_Masters.Pannels.CodePannel;
import c3461288_Coursework_Masters.Pannels.CommandPannel;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setMinimumSize(new Dimension(800,474));
		jframe.setLayout(new BorderLayout());
		
		CodePannel codepannel = new CodePannel();
		CommandPannel commandpannel = new CommandPannel();
		CanvasPannel canvaspannel = new CanvasPannel();
		canvaspannel.setMaximumSize(new Dimension(400,400));
	
		jframe.add(codepannel, BorderLayout.WEST);
		jframe.add(commandpannel, BorderLayout.PAGE_END);
		jframe.add(canvaspannel, BorderLayout.CENTER);
		
		jframe.setVisible(true);
		
		CommandParser parser = new CommandParser();
		
		
		
		
		commandpannel.jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// get the code from the JText Area
				String Code = codepannel.jta.getText();
				
				// Pass the code to the parser to break down the code line by line
				parser.ReturnCode(Code);
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
	}

}
