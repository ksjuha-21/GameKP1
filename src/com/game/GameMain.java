package com.game;

import javax.swing.*;
import java.awt.*;

public class GameMain extends JFrame {

    private boolean state;

    public GameMain() {

        initUI();
    }

    private void initUI() {

        add(new Playstate());

        setResizable(false);
        pack();

        setTitle("Game Squirrel");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameMain ex = new GameMain();
                ex.setVisible(true);
            }
        });
    }
}
