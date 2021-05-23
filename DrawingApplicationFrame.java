/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5;

/**
 *
 * @author CSGO
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author acv
 */
public class DrawingApplicationFrame extends JFrame
{
	private JPanel lineO;
	private JPanel lineT;
	private JPanel topPanel;
	private JButton undoP;
	private JButton clearP;
	private JLabel shapeN;
	private JComboBox shapeP;
	private JCheckBox filledP;
	private JCheckBox gradientP;
	private JButton colorOneP;
	private JButton colorTwoP;
	private JTextField lineWidthP;
	private JTextField strokeDashLengthP;
	private JLabel lineW;
	private JLabel strokeDashLength;
	private JCheckBox lineTypeP;
	private JPanel drawPanel;
	private JLabel statusBarP;
	private JColorChooser colorChooseOne;
	private JColorChooser colorChooseTwo;
    private ArrayList<MyShapes> shapes;
    private MyShapes currentShape;
    private Color color1;
    private Color color2;
    private Stroke stroke1;
    private Paint paint1;
    private boolean fill1;
    //= new ArrayList<MyShapes>();
    

  
    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame()
    {
    	super("Drawing Application");
    	
        // add widgets to panels
    	lineO = new JPanel();
        
        // firstLine widgets
    	undoP = new JButton("Undo");
    	clearP = new JButton("Clear");
    	shapeN = new JLabel("Shape:");
    	shapeP = new JComboBox();
    	shapeP.addItem("Line");
    	shapeP.addItem("Oval");
    	shapeP.addItem("Rectangle");
    	filledP = new JCheckBox("Filled");
    	lineO.add(undoP);
    	lineO.add(clearP);
    	lineO.add(shapeN);
    	lineO.add(shapeP);
    	lineO.add(filledP);
        // secondLine widgets
    	lineT = new JPanel();
    	gradientP = new JCheckBox("Use Gradient");
    	colorOneP = new JButton("1st Color...");
    	colorTwoP = new JButton("2nd Color...");
    	lineW = new JLabel("Line Width: ");
    	lineWidthP = new JTextField("10");
    	strokeDashLength = new JLabel("Dash Length: ");
    	strokeDashLengthP = new JTextField("15");
    	lineTypeP = new JCheckBox("Dashed");
    	lineT.add(gradientP);
    	lineT.add(colorOneP);
    	lineT.add(colorTwoP);
    	lineT.add(lineW);
    	lineT.add(lineWidthP);
    	lineT.add(strokeDashLength);
    	lineT.add(strokeDashLengthP);
    	lineT.add(lineTypeP);
        // add top panel of two panels
    	topPanel = new JPanel();
    	//drawPanel = new JPanel();
    	statusBarP = new JLabel();
    	statusBarP.setText("Coordinates will show up here when the mouse is moved");
    	statusBarP.setBackground(Color.BLACK);
    	
    	DrawPanel draw = new DrawPanel();
    	shapes = new ArrayList<MyShapes>();
        // add topPanel to North, drawPanel to Center, and statusLabel to South
    	topPanel.setLayout(new BorderLayout());
    	topPanel.add(lineO, BorderLayout.NORTH);
    	topPanel.add(lineT, BorderLayout.SOUTH);
    	draw.setBackground(Color.WHITE);
    	statusBarP.setVisible(true);
        add(topPanel, BorderLayout.NORTH);
        add(draw, BorderLayout.CENTER);
        add(statusBarP, BorderLayout.SOUTH);
        
      //  undoButtonHandler h1 = new undoButtonHandler();
        undoP.addActionListener(
        		new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent event)
        			{
        				shapes.remove(shapes.size() - 1);
        				repaint();
        			}
        		});
        clearP.addActionListener(
        		new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent event)
        			{
        				shapes.clear();
        				repaint();
        			}
        		});
        
        
        colorOneP.addActionListener(
        		new ActionListener()
        		{
					@Override
					public void actionPerformed(ActionEvent event)
					{
						color1 = JColorChooser.showDialog(DrawingApplicationFrame.this, "Choose a color", color1);
						if(color1 == null)
							color1 = Color.BLACK;
					}
        		});
        colorTwoP.addActionListener(
        		new ActionListener()
        		{
					@Override
					public void actionPerformed(ActionEvent event)
					{
						color2 = JColorChooser.showDialog(DrawingApplicationFrame.this, "Choose a color", color2);
						if(color2 == null)
							color2 = Color.LIGHT_GRAY;
					}
        		});
        
        //add listeners and event handlers
    }
   
    
    // Create event handlers, if needed
    //theoretically we only need event handlers for the buttons; not for each component in the myshapes hierarchy
    //buttons for undo, clear, colorOne, colorTwo
    //for the checkboxes(gradient, dashed, filled) and use the boolean isSelected method
    //if the gradient is set, do a new setPaint for the current shapes
    //if filled is checked, use boundedshapes to draw it
    // Create a private inner class for the DrawPanel.
    //don't need handlers for non buttons because we don't need to worry about any of that current information until the user clicks on it
    //2 listeners and eventhandlers for the top 2 lines
    
    private class DrawPanel extends JPanel
    {

        public DrawPanel()
        {
        	
        	
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            //loop through and draw each shape in the shapes arraylist
            MouseHandler handler = new MouseHandler();
            addMouseListener(handler);
            addMouseMotionListener(handler);
            for(int i = 0; i < shapes.size(); i++)
            {
            	shapes.get(i).draw(g2d);
            	repaint();
            }
            //loop through arraylist and for each shape, we're going to call shape.draw and passing g2d
            //and java will figure out what shape it is and draw it
            
        }


        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {

            public void mousePressed(MouseEvent event)
            {
            	//get start coordinates
            	int curX = event.getX();
            	int curY = event.getY();
            	Paint paint;
            	//create a paint variable and set it based on the variables in the top 2 lines
            	if(gradientP.isSelected())
            	{
            		paint = new GradientPaint(0, 0, color1, 50, 50, color2, true);
            	}
            	else
            	{
            		paint = color1;
            	}
            	Stroke stroke;
                if (lineTypeP.isSelected())
                {
                	float lineWidth = Float.valueOf(lineWidthP.getText());
                	float temp = Float.valueOf(strokeDashLengthP.getText());
                	float[] dashLength = new float[1];
                	dashLength[0] = temp;
                	//we add temp into dashLength as the current value in the one element float array
                    stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashLength, 0);
                } else
                {
                	float lineWidth = Float.valueOf(lineWidthP.getText());
                	stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                }
                
                boolean fill;
               
                Point start = new Point(event.getX(), event.getY());
                if(filledP.isSelected())
                {
                	fill = true;
                }
                else
                {
                	fill = false;
                }
                fill1 = fill;
                String str = (String) shapeP.getSelectedItem();
                if(str == "Line")
                {
                	currentShape = new MyLine(start, start, paint, stroke);
                	shapes.add(currentShape);
                }
                else
                if(str == "Oval")
                {
                	currentShape = new MyOval(start, start, paint, stroke, fill);
                	shapes.add(currentShape);
                }
                else
                if(str == "Rectangle")
                {
                	currentShape = new MyRectangle(start, start, paint, stroke, fill);
                	shapes.add(currentShape);
                }
            	//filled variable
            	//create a myLine, myRectange, or myOval and make it into a variable of mySHapes type called currentshape
            	//everytime we create a shape we add it to arraylist
            	//start point for shape is set to where mouse currently is and set end point to same place as well
                String str1 = "(" + event.getX() + "," + event.getY();
            	statusBarP.setText(str1);
            	repaint();
            }

            public void mouseReleased(MouseEvent event)
            {
            	//update endpoint of latest thing in mousearray
            	Point current = new Point(event.getX(), event.getY());
            	shapes.get(shapes.size() - 1).setEndPoint(current);
            	
            	String str = "(" + event.getX() + "," + event.getY();
            	statusBarP.setText(str);
            	repaint();
            }

            @Override
            public void mouseDragged(MouseEvent event)
            {
            	Point current = new Point(event.getX(), event.getY());
            	shapes.get(shapes.size() - 1).setEndPoint(current);
            	String str1 = "(" + event.getX() + "," + event.getY();
            	statusBarP.setText(str1);
            	repaint();
        
            }

            @Override
            public void mouseMoved(MouseEvent event)
            {	
            	String str = "(" + event.getX() + "," + event.getY();
            	statusBarP.setText(str);
            	repaint();
            	//update the statuslabel in all of these with a get, gety and set the current text of that status label to where we are
            }
        }

    }
}
