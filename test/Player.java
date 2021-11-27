/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;


    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint; //ballPoint=(300,430), height=10, width=150, container: (0,0), width=600, height=450
        moveAmount = 0;
        playerFace = makeRectangle(width, height); //starting point=(225,430), height=10, width=150
        min = container.x + (width / 2); //min=75
        //TODO change width to playerWidth
        max = min + container.width - width; //max=525

    }

    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * This method returns true if the ball touches the player, i.e.
     * the bottom of the ball and the centre of the ball touches the player
     *
     * @param b
     * @return
     */
    //TODO delete b.getPosition() / b.down cuz just need only 1 ?
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    /**
     * This method is just to set the initial position?
     */
    //TODO change method name?
    public void move(){

        double x = ballPoint.getX() + moveAmount;
        //if x < 75 or x > 525, can't move player anymore, just return
        //TODO: change max or min name to..?
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        //use x and getX just to diff double and integer?
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    public void stop(){
        moveAmount = 0;
    }

    public Shape getPlayerFace(){
        return  playerFace;
    }

    //This method is just being used in ballReset to reset the ball the player to ori place
    //TODO can just use move method?
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
