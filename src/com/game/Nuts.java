package com.game;

public class Nuts extends Sprite {

    public Nuts(int x, int y) {
        super(x, y);
        initNuts();
    }

    private void initNuts() {

        loadImage("nuts.png");
        getImageDimensions();
    }

    public void move() {
        y += 2;
    }
}
