package c3461288_Coursework_Masters.Pannels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CanvasPannel extends JPanel {
	
	int positionx;
	int positiony;
	int imagewidth;
	int imageheight;
	
	BufferedImage bi;
	public BufferedImage getBi() {
		return bi;
	}





	Graphics g;
	
	public CanvasPannel(int width, int height) {
		// TODO Auto-generated constructor stub
		imagewidth = width;
		imageheight = height;
		
		
		setPreferredSize(new Dimension(imagewidth,imageheight));
		setBackground(new Color(220,220,220));
		
		
		
		//create a buffered image
		
		bi = new BufferedImage(imagewidth, imageheight, BufferedImage.TYPE_INT_RGB);
		g = bi.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.green);	
	
	}
	
	
	public void setXY(int x, int y) {
		positionx = x;
		positiony = y;
	}
	
	
	//resets to top left hand corner
	
    public void resetxy() {
		positionx = 0;
		positiony = 0;
	}
	
    
	public void drawrectangle(int width, int height) {
	
		g.drawRect(positionx, positiony, width, height);
		repaint();
		
	}
	
	public void drawcircle(int radius) {
		
		g.drawOval(positionx, positiony, (radius*2), (radius*2));
		repaint();
		
		
	}
	
	public void drawtriangle(int base, int adj, int hyp) {
		
		g.drawLine(positionx, positiony, (positionx+base), positiony);
		g.drawLine((positionx+base), positiony, (positionx+base), (positiony+hyp));
		g.drawLine((positionx+base), (positiony+hyp), positionx, positiony);
		repaint();
		
	
		
	}
	
	
	public void drawto(int x , int y) {
		g.drawLine(positionx, positiony, (positionx + x), (positiony + y));
		repaint();
	}
	
	
	public void clear(){
		
	g.setColor(Color.black);
	g.fillRect(0, 0, imagewidth,imageheight);
	g.setColor(Color.green);
	repaint();
	
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(bi, 0,0, null);
	}
}
