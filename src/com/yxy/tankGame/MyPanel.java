package com.yxy.tankGame;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chris Yang
 * @version 1.0
 */
public class MyPanel extends JPanel {
    MyTank myTank;


    public MyPanel() {
        myTank = new MyTank(30, 30);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0, 1000, 1000);
        // draw myTank
        drawTank(myTank.getX(), myTank.getY(), g, 0, 0, 1);
        // face right
        drawTank(100, 100, g, 3, 1, 2);
        // face down
        drawTank(200, 200, g, 1, 1, 2);
        // face left
        drawTank(300, 300, g, 2, 1, 2);


    }

    /**
     *
     * @param x
     * @param y
     * @param g
     * @param direction
     * @param type
     * @param scale
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type, int scale){
        switch (type){
            case 0: // player's tank
                g.setColor(Color.CYAN);
                break;
            case 1: // robotic tank
                g.setColor(Color.yellow);
                break;
        }
        // 0 up, 1 down, 2 left, 3 right
        switch (direction){
            case 0:
                // wheels: width 6, height 32
                // left wheel
                g.fill3DRect(x, y, 6 * scale, 32 * scale, false);
                // body: width: 12, height: 22
                g.fill3DRect(x + 6 * scale, y + 5 * scale, 12 * scale, 22 * scale, false);
                // draw left corner of the oval
                g.fillOval(x + 6 * scale,y + 8 * scale,12 * scale,12 * scale);
                // right wheel
                g.fill3DRect(x + 18 * scale,y,6 * scale ,32 * scale ,false);
                // pipeline
                g.drawLine(x + 12 * scale,y - 20 * scale,x + 12 * scale, y + 26 * scale);
                break;
            case 1:
                // left wheel
                g.fill3DRect(x, y, 6 * scale, 32 * scale, false);
                // body: width: 12, height: 22
                g.fill3DRect(x + 6 * scale, y + 5 * scale, 12 * scale, 22 * scale, false);
                // draw left corner of the oval
                g.fillOval(x + 6 * scale,y + 12 * scale,12 * scale,12 * scale);
                // right wheel
                g.fill3DRect(x + 18 * scale,y,6 * scale ,32 * scale ,false);
                // pipeline
                g.drawLine(x + 12 * scale,y + 47 * scale,x + 12 * scale, y + 27 * scale);
                break;
            case 2: // left
                // left wheel
                g.fill3DRect(x, y,  32 * scale, 6 * scale, false);
                // body: width: 12, height: 22
                g.fill3DRect(x + 5 * scale, y + 6 * scale, 22 * scale, 12 * scale, false);
                // draw left corner of the oval
                g.fillOval(y + 8 * scale, x + 6 * scale,12 * scale,12 * scale);
                // right wheel
                g.fill3DRect(x ,y + 18 * scale,32 * scale, 6 * scale  ,false);
                // pipeline
                g.drawLine(x - 10 * scale,y + 12 * scale,x + 18 * scale, y + 12 * scale);
                break;
            case 3: // right
                // left wheel
                g.fill3DRect(x, y,  32 * scale, 6 * scale, false);
                // body: width: 12, height: 22
                g.fill3DRect(x + 5 * scale, y + 6 * scale, 22 * scale, 12 * scale, false);
                // draw left corner of the oval
                g.fillOval(y + 12 * scale, x + 6 * scale,12 * scale,12 * scale);
                // right wheel
                g.fill3DRect(x ,y + 18 * scale,32 * scale, 6 * scale  ,false);
                // pipeline
                g.drawLine(x + 42 * scale,y + 12 * scale,x + 18 * scale, y + 12 * scale);
                break;
        }


    }
}
