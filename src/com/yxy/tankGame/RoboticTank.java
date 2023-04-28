package com.yxy.tankGame;

/**
 * @author Chris Yang
 * @version 1.0
 */
public class RoboticTank extends Tank implements Runnable{

    public RoboticTank(int x, int y, int scale) {
        super(x, y, scale);
    }

    @Override
    public void run() {
        // keep shooting while being alive
        while (isAlive()){
            try {
                int time = (int) (Math.ceil(Math.random() * 3) + 3);
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.shoot();
        }
    }




}
