package com.yxy.tankGame;

/**
 * @author Chris Yang
 * @version 1.0
 */
public class Bullet implements Runnable{
    private int x;
    private int y;
    private int direction = 0;
    private int speed = 2;
    private int scale = 1;
    private boolean isAlive = true;
    private int type;

    public Bullet(int x, int y, int scale, int direction, int type) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.direction = direction;
        this.type = type;

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        // keep moving as long as this thread is alive
        while (isAlive){
            // let bullet sleep for 60ms
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            switch (direction){
                case 0: // up
                    y -= speed;
                    break;
                case 1: // down
                    y += speed;
                    break;
                case 2: // left
                    x -= speed;
                    break;
                case 3: // right
                    x += speed;
                    break;
            }

            if (x <= 0 || x >= 1000 || y <= 0 || y >= 1000){
                isAlive = false;
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
