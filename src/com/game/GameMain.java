package com.game;

import javax.swing.*;
import java.awt.*;

public class GameMain extends JFrame {

    public static GameMain ex;

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
                 ex = new GameMain();
                ex.setVisible(true);
            }
        });
    }
}
