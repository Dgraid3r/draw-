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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Line2D;

/**
 *
 * @author al
 */
public class MyLine extends MyShapes{
    
    public MyLine(Point pntA, Point pntB, Paint paint, Stroke strk)
    {
        super(pntA, pntB, paint, strk);
    }
    
    @Override
    public void draw(Graphics2D g2d){
        g2d.setPaint(getPaint());
        g2d.setStroke(getStroke());
        g2d.draw(new Line2D.Double((int)(getStartPoint().getX()), (int)(getStartPoint().getY()), (int)(getEndPoint().getX()), (int)(getEndPoint().getY())));
    }
    
}
