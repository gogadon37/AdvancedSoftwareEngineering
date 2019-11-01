package c3461288_Coursework_Masters.Pannels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;



public class ConsolePannel extends JPanel{
	
	public JTextArea jta;
	public JTabbedPane jTabbedPane = new JTabbedPane();
	

	public ConsolePannel() {
		
	jta = new JTextArea();
	jta.setLineWrap(true);
	jta.setBackground(Color.white);
	JScrollPane jsp = new JScrollPane(jta);
	jsp.setPreferredSize(new Dimension(800, 80));
	
	jTabbedPane.addTab("Console", jsp);
	

	
	
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	

	add(jTabbedPane);
	}
	
	
}
