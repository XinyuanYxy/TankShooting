package com.yxy.tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Chris Yang
 * @version 1.0
 */

// in order to have bullet move along its direction,
// MyPanel should be in a thread.
public class MyPanel extends JPanel implements KeyListener, Runnable {
    MyTank myTank;
    Vector<Bullet> myBullets;
    Vector<RoboticTank> robots = new Vector<>();
    Vector<Bomb> bombs = new Vector<>(); // add a bomb when a tank hit by another
    Image image1;
    Image image2;
    private int NumOfRobots;


    public MyPanel(int n) {
        NumOfRobots = n;
        myTank = new MyTank(30, 30, 1);
        myBullets = myTank.getBullets();
        myTank.setSpeed(4);
        for (int i = 1; i <= NumOfRobots; i ++) {
            int x = 0, y = 0;
            while (true){
                x = (int) (ThreadLocalRandom.current().nextDouble() * 950);
                y = (int) (ThreadLocalRandom.current().nextDouble() * 950);
                if ( (Math.abs(x - myTank.getX()) > 100) || (Math.abs(y - myTank.getY()) > 100)){
                    break;
                }
            }
            RoboticTank roboticTank = new RoboticTank(x, y, 1);
            roboticTank.setDirection((int) (Math.random() * 4));
            robots.add(roboticTank);
            new Thread(roboticTank).start();
        }

        // initialized image
        try{
            image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb1.png"));
            image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb.png"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0, 1000, 1000);
        // draw player's tank
        if (myTank.isAlive()){
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0, myTank.getScale());
            if (myTank.getBullets().size() > 0){
                // draw bullets for player's tank
                drawBullets(myBullets, g);
            }

        }
        // draw robots tank
        for (int i = 0; i < robots.size(); i++) {
            RoboticTank roboticTank = robots.get(i);
            if (roboticTank.isAlive()){
                drawTank(roboticTank.getX(),roboticTank.getY(), g, roboticTank.getDirection(),1,roboticTank.getScale());
                // draw robots' bullets
                if (roboticTank.getBullets().size() > 0){
                    drawBullets(roboticTank.getBullets(), g);
                }
            }else {
                robots.remove(roboticTank);
            }

        }
        // draw bombs if exist
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            // may have different images to show bomb life stage
            if (bomb.lifeTime > 3){
                g.drawImage(image1, bomb.x, bomb.y, 80, 80, this);
            }else {
                g.drawImage(image2, bomb.x, bomb.y, 80, 80, this);
            }
            if (bomb.lifeTime <= 0){
                bombs.remove(bomb);
            }
            bomb.reduceLifeTime();
        }




    }
    public void drawBullets(Vector<Bullet> bullets, Graphics g){
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (bullet.isAlive()){
                drawBullet(bullet,g);
            }else{
                bullets.remove(bullet);
            }
        }

    }


    public void drawBullet(Bullet bullet, Graphics g){
        switch (bullet.getType()){
            case 0: // player's tank
                g.setColor(Color.CYAN);
                break;
            case 1: // robotic tank
                g.setColor(Color.yellow);
                break;
        }
        switch (bullet.getDirection()){
            case 0:
            case 1:
                g.fillOval(bullet.getX() - 2 * bullet.getScale(), bullet.getY() , 4 * bullet.getScale(), 8 * bullet.getScale());
                break;
            case 2:
            case 3:
                g.fillOval(bullet.getX(), bullet.getY() - 2 * bullet.getScale(), 8 * bullet.getScale(), 4 * bullet.getScale());
                break;


        }

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
                g.drawLine(x + 12 * scale,y - 20 * scale,x + 12 * scale, y + 8 * scale);
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
                g.drawLine(x + 12 * scale,y + 52 * scale,x + 12 * scale, y + 24 * scale);
                break;
            case 2: // left
                // left wheel
                g.fill3DRect(x, y,  32 * scale, 6 * scale, false);
                // body: width: 12, height: 22
                g.fill3DRect(x + 5 * scale, y + 6 * scale, 22 * scale, 12 * scale, false);
                // draw left corner of the oval
                g.fillOval(x + 8 * scale, y + 6 * scale,12 * scale,12 * scale);
                // right wheel
                g.fill3DRect(x ,y + 18 * scale,32 * scale, 6 * scale  ,false);
                // pipeline
                g.drawLine(x - 20 * scale,y + 12 * scale,x + 18 * scale, y + 12 * scale);
                break;
            case 3: // right
                // left wheel
                g.fill3DRect(x, y,  32 * scale, 6 * scale, false);
                // body: width: 12, height: 22
                g.fill3DRect(x + 5 * scale, y + 6 * scale, 22 * scale, 12 * scale, false);
                // draw left corner of the oval
                g.fillOval(x + 12 * scale, y + 6 * scale,12 * scale,12 * scale);
                // right wheel
                g.fill3DRect(x ,y + 18 * scale,32 * scale, 6 * scale  ,false);
                // pipeline
                g.drawLine(x + 52 * scale,y + 12 * scale,x + 18 * scale, y + 12 * scale);
                break;
        }


    }
    // check if our bullet hit a robot or not
    private void hitTank(Bullet bullet, Tank tank){
        switch (tank.getDirection()){
            case 0:
            case 1:
                if (bullet.getX() >= tank.getX() && bullet.getX() <= tank.getX() + 24 && bullet.getY() >= tank.getY() &&
                        bullet.getY() <= tank.getY() + 32){
                    tank.setAlive(false);
                    bullet.setAlive(false);
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                }
                break;
            case 2:
            case 3:
                if (bullet.getX() >= tank.getX() && bullet.getX() <= tank.getX() + 32 && bullet.getY() >= tank.getY() &&
                        bullet.getY() <= tank.getY() + 24){
                    tank.setAlive(false);
                    bullet.setAlive(false);
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                }
                break;
        }
    }
    public void hitRobots(){
        for (int j = 0; j < robots.size(); j++) {
            RoboticTank robot = robots.get(j);
            for (int i = 0; i < myBullets.size(); i++) {
                Bullet bullet = myBullets.get(i);
                if (bullet.isAlive()){
                    hitTank(bullet,robot);
                }
            }

        }
    }
    private void hitPlayer() {
        // iterator every robot, and their bullets
        for (int i = 0; i < robots.size(); i++) {
            RoboticTank robot = robots.get(i);
            Vector<Bullet> bullets = robot.getBullets();
            for (int j = 0; j < bullets.size(); j++) {
                hitTank(bullets.get(j), myTank);
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // move within valid area
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                myTank.setDirection(0);
                if (myTank.getY() - 20 > 0){
                    myTank.moveUp();
                }
                break;
            case KeyEvent.VK_S:
                myTank.setDirection(1);
                if (myTank.getY() + 52 < 950){
                    myTank.moveDown();
                }
                break;
            case KeyEvent.VK_A:
                myTank.setDirection(2);
                if (myTank.getX() - 20 > 0){
                    myTank.moveLeft();
                }
                break;
            case KeyEvent.VK_D:
                myTank.setDirection(3);
                if (myTank.getX() + 52 < 950){
                    myTank.moveRight();
                }
                break;
            case KeyEvent.VK_J:
                myTank.shoot();
                break;



        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() { // repaint every other 100ms to make the bullet move along
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // run hitTank method
            // make sure we have one bullet hit one of the tanks
            hitRobots();
            hitPlayer();
            this.repaint();
        }
    }


}
