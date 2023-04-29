package com.yxy.tankGame;

/**
 * @author Chris Yang
 * @version 1.0
 */
public class Bomb {
    int x, y;
    int lifeTime = 6;
    boolean isAlive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void reduceLifeTime(){ // to show different image
        if (lifeTime > 0){
            lifeTime --;
        }else {
            isAlive = false;
        }

    }
}

