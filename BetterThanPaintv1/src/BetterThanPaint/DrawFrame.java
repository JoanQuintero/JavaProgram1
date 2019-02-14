// Joan S. Quintero
// CMP 342 - EM Sofiano
// Project 1

/* 
 * Credits: 
 * Cardlayout design: https://www.thoughtco.com/cardlayout-example-program-2033962
 * Only learned CardLayout structure based on that design
 * 
 * setFont help: https://www.programcreek.com/java-api-examples/?class=javax.swing.JTextField&method=setFont
 * I only got the .setFont(f) from here
 * 
 * Creating new font: https://www.dreamincode.net/forums/topic/142986-change-the-font-size-used-in-a-textfield/
 * note: I only got the Font f1 = new font from here
 * 
 * Clearing field: https://stackoverflow.com/questions/45662015/formatting-jtextfields-using-another-class
 * note: I only got the setText("") from stackover flow
 * and learned how to make my TextField as a member variable of my fontSelectionPanel  
 */


package BetterThanPaint;
import java.awt.EventQueue;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;

public class DrawFrame extends JFrame {
JFrame guiFrame;
CardLayout cards;
JPanel cardPanel;


private TypingOnDraw drawingPanel1;

private Color textColor;


public static void main(String[] args) {

EventQueue.invokeLater(new Runnable()
{
@Override
public void run()
{
new DrawFrame();
}
});
}
public DrawFrame()
{
guiFrame = new JFrame();

guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
guiFrame.setTitle("Better than Paint");
guiFrame.setSize(900,600);

guiFrame.setLocationRelativeTo(null);
guiFrame.setLayout(new BorderLayout());

Border outline = BorderFactory.createLineBorder(Color.BLACK);

JPanel tabsPanel = new JPanel();
tabsPanel.setBorder(outline);

JButton switchCards = new JButton("Click to Paint or Type");
switchCards.setBackground(new Color(59, 89, 182));
switchCards.setForeground(Color.BLACK);
switchCards.setActionCommand("Click to Paint or Type");
switchCards.addActionListener(new ActionListener()


{
@Override
public void actionPerformed(ActionEvent event)
{
cards.next(cardPanel);
}
});

drawingPanel1 = new TypingOnDraw();
drawingPanel1.setBorder(BorderFactory.createTitledBorder("Drawing Field"));

JTextField KeyboardField = new JTextField();
KeyboardField.setBorder(BorderFactory.createTitledBorder("TextField"));
KeyboardField.requestFocusInWindow();
textColor = Color.BLUE;
KeyboardField.setForeground(textColor);

ShapeSelectionPanel shapeSelectionPanel = new ShapeSelectionPanel();
shapeSelectionPanel.setBackground(Color.CYAN);
shapeSelectionPanel.setBorder(BorderFactory.createTitledBorder("Choose Shape"));

SizeSelectionPanel sizeSelectionPanel = new SizeSelectionPanel();
sizeSelectionPanel.setBackground(Color.YELLOW);
sizeSelectionPanel.setBorder(BorderFactory.createTitledBorder("Choose Size"));

ColorChooserPanel colorChooserJP = new ColorChooserPanel();
colorChooserJP.setBackground(Color.GREEN);
colorChooserJP.setBorder(BorderFactory.createLineBorder(Color.BLACK));

TextColorChooserPanel textColorJP = new TextColorChooserPanel();
textColorJP.setBackground(Color.GREEN);
textColorJP.setBorder(BorderFactory.createLineBorder(Color.BLACK));


tabsPanel.add(switchCards);
guiFrame.add(tabsPanel,BorderLayout.NORTH);

cards = new CardLayout();

cardPanel = new JPanel();
cardPanel.setLayout(cards);

cards.show(cardPanel,"Fields");

JPanel westJP = new JPanel();
westJP.setLayout(new GridLayout(1,2));
westJP.add(shapeSelectionPanel);
westJP.add(sizeSelectionPanel);

JPanel firstCard = new JPanel();
firstCard.setLayout(new BorderLayout());
firstCard.add(westJP, BorderLayout.WEST);
firstCard.add(drawingPanel1, BorderLayout.CENTER);
//firstCard.add(colorChooserJP, BorderLayout.SOUTH);
firstCard.setBackground(Color.GREEN);

ImageIcon jbImg = new ImageIcon("images/rip.jpg");
Image tempImage = jbImg.getImage();
jbImg = new ImageIcon(tempImage.getScaledInstance(280,  400, Image.SCALE_FAST));
JButton jb = new JButton(jbImg);
//firstCard.add(jb, BorderLayout.EAST);
jb.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		firstCard.removeAll();
		ImageIcon Ghostly = new ImageIcon("images/ghost.gif");
		Image tempImage = Ghostly.getImage();
		Ghostly = new ImageIcon(tempImage.getScaledInstance(280,  400, Image.SCALE_FAST));
		JButton jb = new JButton(Ghostly);
		firstCard.repaint();
		firstCard.add(jb, BorderLayout.CENTER);
		
	
	}
	
});


JPanel secondCard = new JPanel();
secondCard.setLayout(new BorderLayout());

JButton clear = new JButton("Clear Text");

clear.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent l) { 
		KeyboardField.setText("");
	}
});

FontSelectionPanel fontSelectionPanel = new FontSelectionPanel(KeyboardField);
fontSelectionPanel.setBorder(BorderFactory.createTitledBorder("Choose Font"));

FontSizePanel fontSizePanel = new FontSizePanel(KeyboardField);
fontSizePanel.setBorder(BorderFactory.createTitledBorder("Font Size"));

JPanel theFontsPanel = new JPanel();
theFontsPanel.setLayout(new GridLayout(1,2));

theFontsPanel.add(fontSelectionPanel);
theFontsPanel.add(fontSizePanel);

secondCard.add(clear, BorderLayout.EAST);
secondCard.add(KeyboardField, BorderLayout.CENTER);
secondCard.add(textColorJP, BorderLayout.SOUTH);
secondCard.add(theFontsPanel, BorderLayout.WEST);
secondCard.setBackground(Color.YELLOW);

cardPanel.add(firstCard, "");
cardPanel.add(secondCard, "");
guiFrame.add(tabsPanel,BorderLayout.NORTH);
guiFrame.add(cardPanel,BorderLayout.CENTER);
guiFrame.setVisible(true);
}

private void addButton(Container parent, String name)
{
JButton but = new JButton(name);
but.setActionCommand(name);
parent.add(but);
}

private class ShapeSelectionPanel extends JPanel implements ActionListener{
	private JRadioButton [] radBtnArr;
	private ButtonGroup radBtnGroup;
	private final int NUM_SHAPES = PanelofDraw.shapeNames.length;
	
	public ShapeSelectionPanel(){
		radBtnGroup = new ButtonGroup();
		radBtnArr = new JRadioButton[NUM_SHAPES];
		setLayout(new GridLayout(NUM_SHAPES,1));
		for(int i=0; i<radBtnArr.length; i++){
			radBtnArr[i] = new JRadioButton(PanelofDraw.shapeNames[i]);
			radBtnArr[i].setActionCommand(PanelofDraw.shapeNames[i]);
			radBtnArr[i].addActionListener(this);
			radBtnGroup.add(radBtnArr[i]);
			add(radBtnArr[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actCmd = e.getActionCommand();
		drawingPanel1.setShape(actCmd);
	}}

private class SizeSelectionPanel extends JPanel implements ActionListener{
	private JRadioButton[] radBtnArr;
	private ButtonGroup radBtnGroup;
	private final int NUM_SIZES = PanelofDraw.sizerNames.length;
	
	public SizeSelectionPanel(){
		radBtnGroup = new ButtonGroup();
		radBtnArr = new JRadioButton[NUM_SIZES];
		setLayout(new GridLayout(NUM_SIZES,1));
		for(int i=0; i<radBtnArr.length; i++){
			radBtnArr[i] = new JRadioButton(PanelofDraw.sizerNames[i]);
			radBtnArr[i].setActionCommand(PanelofDraw.sizerNames[i]);
			radBtnArr[i].addActionListener(this);
			radBtnGroup.add(radBtnArr[i]);
			add(radBtnArr[i]);
		}
		System.out.println("total num of sizes "+ NUM_SIZES);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actCmd = e.getActionCommand();
		drawingPanel1.setSize(actCmd);
	}}

public class FontSelectionPanel extends JPanel implements ActionListener{
	private JRadioButton[] radBtnArr;
	private ButtonGroup radBtnGroup;
	private final int NUM_FONTS = PanelofDraw.FontTypes.length;

    private JTextField KeyboardField;
	
	public FontSelectionPanel(JTextField KeyboardField){
		this.KeyboardField = KeyboardField;
		
		radBtnGroup = new ButtonGroup();
		radBtnArr = new JRadioButton[NUM_FONTS];
		setLayout(new GridLayout(NUM_FONTS,1));
		for(int i=0; i<radBtnArr.length; i++){
			radBtnArr[i] = new JRadioButton(PanelofDraw.FontTypes[i]);
			radBtnArr[i].setActionCommand(PanelofDraw.FontTypes[i]);
			radBtnArr[i].addActionListener(this);
			radBtnGroup.add(radBtnArr[i]);
			add(radBtnArr[i]);
			}
		
		System.out.println("total num of sizes "+ NUM_FONTS);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actCmd = e.getActionCommand();
		Font font1 = new Font("Arial", Font.BOLD, 15);
		Font font2 = new Font("Courier", Font.BOLD, 15);
		Font font3 = new Font("SansSerif", Font.ITALIC, 16);
		
		for(int i=0; i<radBtnArr.length; i++){
			if (actCmd.equalsIgnoreCase(PanelofDraw.FontTypes[0])) {
				KeyboardField.setFont(font1);
			}
			else if (actCmd.equalsIgnoreCase(PanelofDraw.FontTypes[1])) {
				KeyboardField.setFont(font2);
			}
			else if (actCmd.equalsIgnoreCase(PanelofDraw.FontTypes[2])){
				KeyboardField.setFont(font3);
			}
					}	}   }

public class FontSizePanel extends JPanel implements ActionListener{
	private JRadioButton[] radBtnArr;
	private ButtonGroup radBtnGroup;
	private final int NUM_FSIZE = PanelofDraw.FontSize.length;
    private JTextField KeyboardField;
	
	public FontSizePanel(JTextField KeyboardField){
		this.KeyboardField = KeyboardField;
		
		radBtnGroup = new ButtonGroup();
		radBtnArr = new JRadioButton[NUM_FSIZE];
		setLayout(new GridLayout(NUM_FSIZE,1));
		for(int i=0; i<radBtnArr.length; i++){
			radBtnArr[i] = new JRadioButton(PanelofDraw.FontSize[i]);
			radBtnArr[i].setActionCommand(PanelofDraw.FontSize[i]);
			radBtnArr[i].addActionListener(this);
			radBtnGroup.add(radBtnArr[i]);
			add(radBtnArr[i]);
			
			}
		System.out.println("total num of sizes "+ NUM_FSIZE);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actCmd = e.getActionCommand();
		Font size1 = new Font("Arial", Font.BOLD, 12);
		Font size2 = new Font("Arial", Font.BOLD, 50);
		Font size3 = new Font("Arial", Font.BOLD, 100);
		
		for(int i=0; i<radBtnArr.length; i++){
				if (actCmd.equalsIgnoreCase(PanelofDraw.FontSize[0])) {
					KeyboardField.setFont(size1);

				}
				else if (actCmd.equalsIgnoreCase(PanelofDraw.FontSize[1])) {
					KeyboardField.setFont(size2);
				}
				else if (actCmd.equalsIgnoreCase(PanelofDraw.FontSize[2])){
					KeyboardField.setFont(size3);
				} }	} }

private class ColorChooserPanel extends JPanel implements ActionListener{
	private ImageIcon CCimg = new ImageIcon("images/choosecol.jpeg");
	
	
	private JButton jb;
	private JColorChooser colChooser;
	public ColorChooserPanel(){
		colChooser = new JColorChooser();
		Image tempImage = CCimg.getImage();
		CCimg = new ImageIcon(tempImage.getScaledInstance(200,  200, Image.SCALE_FAST));
		jb = new JButton(CCimg);
		//jb = new JButton("choose a color");
		jb.addActionListener(this);
		add(jb);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Color chosenColor = 
				colChooser.showDialog(null, 
						"Choose Color", 
						drawingPanel1.getColor());
		drawingPanel1.setColor(chosenColor);
		
	} }

private class TextColorChooserPanel extends JPanel implements ActionListener{
	private JButton jb;
	private JColorChooser colChooser;
	public TextColorChooserPanel(){
		colChooser = new JColorChooser();
		jb = new JButton("choose a color");
		jb.addActionListener(this);
		add(jb);}
		
		private JTextField KeyboardField;
		
//		public TextColorChooserPanel(JTextField KeyboardField){
//			
//			    this.KeyboardField = KeyboardField;
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//JButton btnClicked = (JButton) e.getSource();
		Color chosenColor = 
				JColorChooser.showDialog(null, 
						"Choose Color", getColor());
		
		System.out.println("inside actperf "+chosenColor);
		
		String how = KeyboardField.getText();
		//how.setColor(Color.RED);
		
	
		//KeyboardField.setForeground(getColor());
		
		
//		Color chosenColor = 
//				JColorChooser.showDialog(null, 
//						"Choose Color", getColor());
//		
//		System.out.println("inside actperf "+chosenColor);
//		//textColor = Color.RED;
//		//KeyboardField.setForeground(chosenColor);
////		KeyboardField.repaint();
//		if (ColorClicked.equalsIgnoreCase(PanelofDraw.FontSize[0])) {
//			KeyboardField.setForeground(chosenColor));
	}

	private Color getColor() {
		return null;
	} }

}
