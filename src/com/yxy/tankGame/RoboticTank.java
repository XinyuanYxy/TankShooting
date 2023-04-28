package com.yxy.tankGame;

/**
 * @author Chris Yang
 * @version 1.0
 */
public class RoboticTank extends Tank implements Runnable{
    private boolean isAlive = true; // indicating this robotic tank is crashed or not
    public RoboticTank(int x, int y, int scale) {
        super(x, y, scale);
    }

    @Override
    public void run() {
        // keep shooting while being alive
        while (isAlive){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.shoot();
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


}
