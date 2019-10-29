package c3461288_Coursework_Masters.Pannels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;


public class CodePannel extends JPanel{
	public JTextArea jta;

	
	public CodePannel() {
		// TODO Auto-generated constructor stub
		
		setBackground(Color.green);
		jta = new JTextArea();
		jta.setLineWrap(true);
		jta.setBackground(Color.white);
		jta.setPreferredSize(new Dimension(300,400));
		add(jta);
		
	}
}
