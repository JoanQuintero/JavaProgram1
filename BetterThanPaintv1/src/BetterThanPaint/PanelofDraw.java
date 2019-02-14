package BetterThanPaint;

import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.Component;

import java.awt.font.*;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class PanelofDraw extends JPanel implements MouseListener,MouseMotionListener, KeyListener{

	public static final int EXTRA_SMALL = 10;
	public static final int SMALL = 25;
	public static final int MEDIUM = 50;
	public static final int LARGE = 75;
	public static final int EXTRA_LARGE = 100;
	public static final int DEFAULT = 35;
	public static final int NO_SIZE = DEFAULT;
	
	public static final String ARIAL = "Arial";
	public static final String COURIER =  "Courier";
	public static final String SANS_SERIF = "Sans Serif";
	
	public static final String [] FontTypes = {ARIAL, COURIER, SANS_SERIF};
	
	public static final String SMALLtext = "Small";
	public static final String MEDIUMtext = "Medium";
	public static final String LARGEtext = "Large";
	
	public static final String [] FontSize = {SMALLtext, MEDIUMtext, LARGEtext};
	
	private static final String NOTHING = "none";
	public static final String KeyFieldON = "Enable Keyboard"; // Keyboard try #1
	public static final String KeyFieldOFF = "Disable Keyboard";
	public static final String [] Keyboarding = {KeyFieldON, KeyFieldOFF};
	
	//public static final String SHAPETITLE = "Choose a Shape";
	public static final String NO_SHAPE = "none";
	public static final String CIRCLE = "circle";
	public static final String SQUARE = "square";
	public static final String FLOWER = "flower";
	public static final String [] shapeNames = {/*SHAPETITLE,*/ NO_SHAPE, CIRCLE, SQUARE, FLOWER};
	//public static final int [] sizeNames = {EXTRA_SMALL, SMALL, MEDIUM, LARGE, EXTRA_LARGE};

	//public static final String SIZETITLE = "Choose a Size";
	public static final String EXTRA_SMALL2 = "extra small";
	public static final String SMALL2 = "small";
	public static final String MEDIUM2 = "medium";
	public static final String LARGE2 = "large";
	public static final String EXTRA_LARGE2 = "extra large";
	public static final String [] sizerNames = {/*SIZETITLE,*/ EXTRA_SMALL2, SMALL2, MEDIUM2, LARGE2, EXTRA_LARGE2};
	
	
	
	private int xStart=0, yStart=0;
	private int size;
	private Color color;
	private String shape;
	private String keyboard;
    private int x;
    private int y;
	
	public PanelofDraw(){
		setBackground(Color.WHITE);
		color = Color.ORANGE;//default
		size = LARGE;
		shape = NO_SHAPE;
		//keyboard = NOTHING;
//		Font font1 = new Font("SansSerif", Font.BOLD, 20);
//		Font font2 = new Font("Arial", Font.BOLD, 10);
//		Font font3 = new Font("Courier", Font.BOLD, 50);
		
		addMouseListener(this);//make the JPanel listen for mouse events
		addMouseMotionListener(this);//make the JPanel listen for MORE mouse events
	}
	
//	private void keyboard(int x, int y) {
//		
//		
//		switch(keyboard) {
//		case KeyFieldON:
//			JTextField Keyster = new JTextField();
//			break;
//		case KeyFieldOFF:
//			break;
//		default:
//			keyboard = NOTHING;
//			break;
//		}
//		
//		
//	}
	private void drawShape(int x, int y){
		//Graphics g = getGraphics();
		Graphics2D g = (Graphics2D)getGraphics();
//		Graphics2D MyOwn = (Graphics2D)getGraphics();
		g.setColor(color);
		switch(shape){
		case CIRCLE:
			g.drawOval(x - size/2, y - size/2, size, size);
			//g.drawOval(x, y, size, size);//incorrect drawing
			break;
		case SQUARE:
			g.fillRect(x - size/2, y - size/2, size, size);
			break;
		case FLOWER:
			//size = 15;
			g.fillOval(x - size/2, y - (size -size/3), size, size);
			g.fillOval(x - size/2, y + size/3, size, size); 
			g.fillOval(x - size/8, y - size/6, size, size); 
			g.fillOval(x - size, y - size/6, size, size);
			g.setColor(color.YELLOW);
			g.fillOval(x - size/2, y - size/6, size, size); 
			
			//size = size+10;
			//g.setColor(color.GREEN);
			//g.drawLine(x, y+size/8, x, y+size/2);
			
			//MyOwn.drawArc(x, y, size, size, size - 5, size + 20);
			//MyOwn.rotate(Math.random(),x,y);
			//MyOwn.rotate(LARGE);
			//MyOwn.rotate(Math.random(), x, y);
			//((Graphics2D)g).rotate(angle, centerX, centerY);
			//g.drawOval(x + size/2, y + size/2, size, size);
			//g.drawOval(x + size/3, y + size/3, size, size); 
			//g.drawOval(x + size/6, y + size/6, size, size); 
			//g.drawOval(x + size, y + size, size, size);
			//g.drawOval(x - (size + size/2), y - (size + size/2), size, size);
			//g.drawOval(x - (size + size/3), y - (size + size/3), size, size);
			//g.drawOval(x - (size + size/4), y - (size + size/4), size, size);
			break;
			
		default:
			shape = NO_SHAPE;
			g.setStroke(new BasicStroke(size/8));
			g.drawLine(x,y,x,y);//hahahaha just a dot
		}
		g.dispose();
	}
	protected void record(int x, int y){
		xStart = x;
		yStart = y;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
        int xEnd = e.getX();
        int yEnd = e.getY();
		drawShape(x, y);
		
		record(e.getX(), e.getY());
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		record(e.getX(),e.getY()); //store the x,y
	}
	@Override
	public void mouseReleased(MouseEvent e) {
//		int xEnd = e.getX();
//		int yEnd = e.getY();
//		Graphics g = getGraphics();
//		g.setColor(color);
//		g.drawLine(xStart, yStart, xEnd, yEnd);
//		g.dispose();	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		requestFocus();
        record(e.getX(), e.getY());
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		int xStart = e.getX();
		int yStart = e.getY();
		int xEnd = e.getX();
		int yEnd = e.getY();
		drawShape(xStart, yStart);
		
		Graphics2D g = (Graphics2D)getGraphics();
		g.setStroke(new BasicStroke(size/10));
		g.setColor(color);
		
		//g.drawLine(xStart, yStart, xEnd, yEnd);
		drawShape(xStart, yStart);
		g.dispose();
		record(xEnd,yEnd); //store the x,y
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		record(e.getX(), e.getY());
		
	}
	public Color getColor(){
		return color;
	}
    public int getDrawingSize() {
    	return size;
    }
    
	protected void setColor(Color c){
		color = c;
	}
	protected void setShape(String theShape){
		if(theShape.equalsIgnoreCase(CIRCLE)){
			shape = CIRCLE;
		}
		else if(theShape.equalsIgnoreCase(SQUARE)){
			shape = SQUARE;
		}
		
		else if(theShape.equalsIgnoreCase(FLOWER)){
			shape = FLOWER;
		}
		else{
			shape = NO_SHAPE;
			System.out.println("invalid shape was entered "+ theShape);
		}
	}
	
	protected void setFont(String FontTypes) {
		if (FontTypes.equalsIgnoreCase(ARIAL)) {
			Font font1 = new Font("SansSerif", Font.BOLD, 20);
		}
		else if (FontTypes.equalsIgnoreCase(COURIER)) {
			Font font2 = new Font("Arial", Font.BOLD, 10);
		}
		else if (FontTypes.equalsIgnoreCase(SANS_SERIF)){
			Font font3 = new Font("Courier", Font.BOLD, 50);
		}
	}
	
	 protected void setSize(String sizersNames){
		// EXTRA_SMALL, SMALL, MEDIUM, LARGE, EXTRA_LARGE
		//EXTRA_SMALL2, SMALL2, MEDIUM2, LARGE2, EXTRA_LARGE2
		/*if (sizersNames.equalsIgnoreCase(TITLE)){
			 */
		if (sizersNames.equalsIgnoreCase(EXTRA_SMALL2)) {
			size = EXTRA_SMALL;
		}
		else if (sizersNames.equalsIgnoreCase(SMALL2)) {
			size = SMALL;
		}
		else if (sizersNames.equalsIgnoreCase(MEDIUM2)) {
			size = MEDIUM;
		}
		else if (sizersNames.equalsIgnoreCase(LARGE2)) {
			size = LARGE;
		}
		else if (sizersNames.equalsIgnoreCase(EXTRA_LARGE2)) {
			size = EXTRA_LARGE;
		}
		else {
			size = DEFAULT;
			System.out.println("Size not selected "+ sizerNames);
		} 
		
	} 
	 
//	 protected void enableKeyboard(String Keyboarding) {
//		 
//		 if (Keyboarding.equalsIgnoreCase(KeyFieldON)) {
//			 keyboard = KeyFieldON;
//			 JTextField Keyster = new JTextField();
//		 }
//		 else if (Keyboarding.equalsIgnoreCase(KeyFieldOFF)) {
//			 keyboard = KeyFieldOFF;
//		 }
//		 else {
//			 keyboard = NOTHING;
//			 System.out.println("Keyboard not enable");
//		 }
//	 }
	 
     protected int getLatestX() {
     	return xStart;
     }
     
     protected int getLatestY() {
     	return yStart;
     }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
		 

	 
	
	// My creation:
	
	
	
	
	
	
	
	
	
	
}
