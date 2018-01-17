package com.game.hlam;

import com.game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class StartPage extends JPanel implements ActionListener {

    private final int B_WIDTH = 1500;
    private final int B_HEIGHT = 1000;
    private boolean stageGame;
    private Timer timer;
    private final int DELAY = 15;


    public StartPage(){

     initStart();
    }

    private void initStart() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        stageGame = true;


        timer = new Timer(DELAY, this);
        timer.start();

    }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (stageGame) {

                drawPage(g);

            } else {
                add(new Board());
            }

        }

        private void drawPage(Graphics g) {
            String msg =  "START GAME  -  click ENTER";

            Font small = new Font("Helvetica", Font.BOLD, 50);
            FontMetrics fm = getFontMetrics(small);
            g.drawImage(new ImageIcon("fone.jpg").getImage(), 0, 0, 1500, 1000, this);
            g.setColor(Color.black);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                    B_HEIGHT / 2);

            JButton button = new JButton("PLAY");
            button.doClick();
            this.add(button);
            button.addActionListener(this);

        }



    @Override
    public void actionPerformed(ActionEvent e) {
        add(new Board());
        repaint();
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                add(new Board());
            }
        }
    }
}
