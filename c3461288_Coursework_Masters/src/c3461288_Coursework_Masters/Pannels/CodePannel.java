package c3461288_Coursework_Masters.Pannels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;


public class CodePannel extends JPanel{
	public JTextArea jta;
	public CommandPannel commandPannel;

	
	public CodePannel() {
		// TODO Auto-generated constructor stub
		
		jta = new JTextArea();
		jta.setLineWrap(true);
		jta.setBackground(Color.white);
		JScrollPane jsp = new JScrollPane(jta);
		jsp.setPreferredSize(new Dimension(300,650));
	
		 commandPannel = new CommandPannel();
		 
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
		
		
		add(jsp);
		add(commandPannel);
		
		
	}
}
