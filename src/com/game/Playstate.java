package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Playstate extends JPanel implements ActionListener {

    private Button button1;
    private Button button2;

    private final int B_WIDTH = 1500;
    private final int B_HEIGHT = 1000;

    public Playstate(){

            addMouseListener(new TAdapter());
            setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
            this.setBackground(Color.GRAY);
            button1 = new Button(this, "Start Game", 250, 100, 100, 40);
            button2 = new Button(this, "Exit", 250, 200, 100, 40);
    }

    public  void update(){}

    @Override
    public void paint(Graphics g) {
      render(g);
    }

    public void render(Graphics g) {
        button1.render(g);
        button2.render(g);
    }

    private class TAdapter extends MouseAdapter {

        //эти методы реагируют на мышку
        @Override
        public void mousePressed(MouseEvent e) {
            button1.mousePressed(e);
            button2.mousePressed(e);
        }

        public void mouseReleased(MouseEvent e) {
            button1.mouseReleased(e);
            button2.mouseReleased(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button1) {

            //вот здесь не получается перейти к самой игре, кидает ошибку

            add(new Board());
        }
        if (e.getSource() == button2) {
            System.exit(0);
        }
    }
}
