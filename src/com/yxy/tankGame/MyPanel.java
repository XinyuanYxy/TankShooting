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
        drawTank(100, 100, g, 0, 1, 2);


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
            case 0: // 自己的tank
                g.setColor(Color.CYAN);
                break;
            case 1: // 敌人tank
                g.setColor(Color.yellow);
                break;
        }

        switch (direction){
            case 0: // face upward
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
                g.drawLine(x + 12 * scale,y - 20,x + 12 * scale, y + 26);
        }


    }
}
