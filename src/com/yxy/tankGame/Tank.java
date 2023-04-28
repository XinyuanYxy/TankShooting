package com.yxy.tankGame;

import java.util.Vector;

/**
 * @author Chris Yang
 * @version 1.0
 */
public class Tank {
    private int x;
    private int y;
    private int direction;
    private int speed = 1;
    private int scale = 1;
    private boolean isAlive = true; // indicating this tank is crashed or not
    private Vector<Bullet> bullets = new Vector<>();

    public Tank(int x, int y, int scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public void moveUp(){
        y -= speed;
    }
    public void moveRight(){
        x += speed;
    }
    public void moveDown(){
        y += speed;
    }
    public void moveLeft(){
        x -= speed;
    }
    public void shoot(){
        // set where the bullet should start moving
        Bullet newBullet = null;
        switch(getDirection()){
            case 0: // up
                newBullet = new Bullet(getX() + 12 * getScale(), getY() - 20 * getScale(), getScale(),0, 0);
                bullets.add(newBullet);
                break;
            case 1: // down
                newBullet = new Bullet(getX() + 12 * getScale() , getY() + 52 * getScale() , getScale(),1, 0);
                bullets.add(newBullet);
                break;
            case 2: // left
                newBullet = new Bullet(getX() - 20 * getScale(), getY() + 12 * getScale() , getScale(),2, 0);
                bullets.add(newBullet);
                break;
            case 3: // right
                newBullet = new Bullet(getX() + 52 * getScale(), getY() + 12 * getScale() , getScale(),3, 0);
                bullets.add(newBullet);
                break;
        }
        new Thread(newBullet).start();
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    public int getScale() {
        return scale;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setScale(int scale) {
        this.scale = scale;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
