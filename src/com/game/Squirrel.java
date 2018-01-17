package com.game;

import java.awt.event.KeyEvent;

public class Squirrel extends Sprite {

    private int dx;
    private int speed = 3;

    public Squirrel(int x, int y) {
        super(x, y);

        initSquirrel();
    }

    private void initSquirrel() {

        loadImage("squirrel.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;

        if (x < 1) {
            x = 1;
        }

    }


    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx  -=speed;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx  +=speed;
        }

    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

    }

}
