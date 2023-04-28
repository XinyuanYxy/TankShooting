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
        mp = new MyPanel(3);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.addKeyListener(mp); // 画框监听画板的动静
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
