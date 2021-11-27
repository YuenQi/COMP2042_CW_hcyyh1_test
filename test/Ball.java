package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center; //center=(300,430)

        up = new Point2D.Double(); //up=(0.0,0.0)
        down = new Point2D.Double(); //down=(0.0,0.0)
        left = new Point2D.Double(); //left=(0.0,0.0)
        right = new Point2D.Double(); //right=(0.0,0.0)

        //TODO maybe can call setPoints method or sth
        up.setLocation(center.getX(),center.getY()-(radiusB / 2)); //up=(300.0,425.0)
        down.setLocation(center.getX(),center.getY()+(radiusB / 2)); //down=(300.0,435.0)

        left.setLocation(center.getX()-(radiusA /2),center.getY()); //left=(295,430.0)
        right.setLocation(center.getX()+(radiusA /2),center.getY()); //right=(305.0,430.0)


        ballFace = makeBall(center,radiusA,radiusB); //ballFace (rectangle starting point = (295,425), width=10, height=10)
        this.border = border;
        this.inner  = inner;

        //TODO change the speed so that the speed maintain when the user closes the window, skip level
        speedX = 0;
        speedY = 0;
    }

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp; //preserve ori ballFace
    }

    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    public void setXSpeed(int s){
        speedX = s;
    }

    public void setYSpeed(int s){
        speedY = s;
    }

    public void reverseX(){
        speedX *= -1;
    }

    public void reverseY(){
        speedY *= -1;
    }

    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }

    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }


}
