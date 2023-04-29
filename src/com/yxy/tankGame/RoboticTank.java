package com.yxy.tankGame;

import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Chris Yang
 * @version 1.0
 */
public class RoboticTank extends Tank implements Runnable{

    public RoboticTank(int x, int y, int scale) {
        super(x, y, scale);
    }
    public void changeDirection(){
        // set random direction
        int newDirection = (int) (Math.random() * 4);
        this.setDirection(newDirection);
    }
    public void moveAlong(){
        // move along within valid area
        // 在移动过程中 发射子弹
        long start = System.currentTimeMillis();
        long end = start + (int) ((Math.random() * 2)) * 1000;
        while (System.currentTimeMillis() < end) {
            switch (getDirection()) {
                case 0:
                    if (getY() - 20 > 0){
                        moveUp();
                    }
                    break;
                case 1:
                    if (getY() + 20 < 950){
                        System.out.println("moved down, current y" + getY());
                        moveDown();
                    }
                    break;
                case 2:
                    if (getX() - 20 > 0){
                        moveLeft();
                    }
                    break;
                case 3:
                    if (getX() + 32 < 950){
                        moveRight();
                    }
                    break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // shoot probability 0.0001
            double prob = ThreadLocalRandom.current().nextDouble();
            if (prob < 0.01) this.changeDirection();
            if (prob < 0.009){
                this.shoot();
            }
        }

    }

    @Override
    public void run() {
        // keep shooting while being alive
        while (isAlive()){
            moveAlong();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
