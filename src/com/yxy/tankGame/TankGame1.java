package com.yxy.tankGame;

import javax.swing.*;

/**
 * @author Chris Yang
 * @version 1.0
 */
public class TankGame1 extends JFrame {
    // 定义Panel
    MyPanel mp = null;
    public static void main(String[] args) {
        new TankGame1();
    }
    public TankGame1(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
