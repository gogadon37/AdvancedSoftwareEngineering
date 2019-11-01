package c3461288_Coursework_Masters.Pannels;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CommandPannel extends JPanel{

	public JTextField jtf;
	public JButton jb;
	
	public CommandPannel() {
		// TODO Auto-generated constructor stub
		
		
		jtf = new JTextField();
		jb = new JButton("submit");
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS) );
		
		add(jtf);
		add(jb);
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
