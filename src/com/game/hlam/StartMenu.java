package com.game.hlam;

import com.game.Board;

import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class StartMenu extends JFrame {

    Image dbImage;
    Graphics dbg;

    static Board b = new Board();

//    Thread board = new Thread(b);
//    Thread p1 = new Thread(b.p1);


    Rectangle startButton = new Rectangle(150,100,100,25);
    Rectangle quitButton = new Rectangle(150,100,100,25);

    private final int B_WIDTH = 1500;
    private final int B_HEIGHT = 1000;


    public StartMenu() {

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        this.setBackground(Color.GRAY);
        this.addKeyListener(new KeyHandler());
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());

    }

    @Override
    public void paint(Graphics g) {
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        draw(dbg);
        g.drawImage(dbImage,0,0,this);
    }

    public void draw(Graphics g) {

        g.setFont(new Font("Arial", Font.BOLD,26));
        g.setColor(Color.WHITE);
        g.drawString("Game", 125,75);
        g.setColor(Color.CYAN);
        g.fillRect(startButton.x, startButton.y,startButton.width, startButton.height);
        g.setFont(new Font("Arial", Font.BOLD,12));
        g.setColor(Color.GRAY);
        g.drawString("Start Game", startButton.x+20,startButton.y+17);
        g.setColor(Color.CYAN);
        g.setColor(Color.PINK);
        g.fillRect(quitButton.x, quitButton.y,quitButton.width, quitButton.height);
        g.setColor(Color.GRAY);
        g.drawString("Quit Game", quitButton.x+20, quitButton.y+17);

        repaint();


    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            add(new Board());
        }
        @Override
        public void keyReleased(KeyEvent e) {
            add(new Board());
        }
    }

    public class MouseHandler extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {

        }
        @Override
        public void mousePressed(MouseEvent e) {

        }
    }
}
