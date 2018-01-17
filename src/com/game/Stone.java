package com.game;

public class Stone extends Sprite {


    public Stone(int x, int y) {
        super(x, y);

        initStone();
    }

    private void initStone() {

        loadImage("stone.png");
        getImageDimensions();
    }

    public void move() {

        y += 1;
    }
}
