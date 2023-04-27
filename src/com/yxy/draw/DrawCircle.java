package com.yxy.draw;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chris Yang
 * @version 1.0
 */
public class DrawCircle extends JFrame{ // 定义画框
    // 定义一个面板
    private MyPanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();
    }
    public DrawCircle(){
        // 初始化面板
        mp = new MyPanel();
        // 把面板放入到窗口（画框）
        this.add(mp);
        // 设置窗口大小
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 点击窗口的叉的时候，程序退出
        this.setVisible(true);
    }
}

/*
 * 可把Graphics g 看作一个画笔
 */

class MyPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 画圆
        g.drawOval(0, 0, 10, 10);
        g.drawLine(10, 10, 100, 10);
        g.drawRect(10,11, 50, 50);

        // 给矩形填充颜色
        g.setColor(Color.blue);
        // 画出矩形并填色
        g.fillRect(50, 50, 100, 100);

        // 画image
        // 1. 获取图片资源:
        // 图片要放在out中同名的包文件下，当作根目录
//        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/hi.png"));
//        g.drawImage(image, 10, 10, 175, 222, this);
        // 化字符串, 写字
        g.setColor(Color.BLACK);
        g.setFont(new Font("New York Times", Font.BOLD, 50));
        g.drawString("hello",100, 100);


    }
}