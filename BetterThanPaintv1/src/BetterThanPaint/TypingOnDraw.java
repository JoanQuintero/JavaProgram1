package BetterThanPaint;

import java.awt.Font;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TypingOnDraw extends PanelofDraw implements KeyListener {
	
	
	private Font font;
	private FontMetrics fm; // Calculates how big the fonts are
	private int fontSize;
	public static final String FONT_NAME = "serif";
	public static final int FONT_STYLE = Font.ITALIC;
	
	public TypingOnDraw() { // Constructor
		super(); //Rule#1 call your parent super
		fontSize = PanelofDraw.MEDIUM;
		font = new Font(FONT_NAME, FONT_STYLE, fontSize);
		fm = getFontMetrics(font);
		addKeyListener(this);
		
		//listen for keystrokes...
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		String s = String.valueOf(e.getKeyChar());
		Graphics2D g = (Graphics2D)getGraphics();
		font = new Font(FONT_NAME, FONT_STYLE, super.getDrawingSize());
		fm = getFontMetrics(font);
		g.setFont(font);
		g.setColor(getColor());
		
		int M = 0;
		
		if (getLatestX()+fm.stringWidth(s) > 400) {
		
			g.drawString(s, M+fm.stringWidth(s), getLatestY()+fm.stringWidth(s)*2);
			record(M+fm.stringWidth(s), getLatestY()+fm.stringWidth(s));
		}
		else
		{
		g.drawString(s, getLatestX()+fm.stringWidth(s), getLatestY());
		record(getLatestX()+fm.stringWidth(s), getLatestY());
		}
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
