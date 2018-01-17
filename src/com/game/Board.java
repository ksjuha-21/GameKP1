package com.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Squirrel squirrel;
    private ArrayList<Nuts> nuts;
    private boolean ingame;
    private ArrayList<Stone> ms;
    private final int ISQUIRREL_X = 700;
    private final int ISQUIRREL_Y = 780;
    private final int B_WIDTH = 1500;
    private final int B_HEIGHT = 1000;
    private final int DELAY = 15;

    //координаты орехов
    private final int[][] npos = {
            {0, 0}, {600,-300}, {300, -600}, {900, -900},
            {1400, -1200}, {1200, -1500}/*, {100, -1800},
            {700, -2100}, {400, -2400}, {1300, -2700},
            {1000, -3000}, {500, -3300}, {200, -3600},
            {1100, -3900}, {800, -4200}, {1400, -4500},
            {300, -4800}, {0, -5100}, {900, -5400},
            {600, -5700}, {1400, -6000}, {1200, -6300},
            {400, -6600}, {100, -6900}, {1000, -7200},
            {700, -7500}, {1300, -7800}, {200, -8100}*/
    };

    //координаты камней
    private final int[][] mpos = {
            {150, 0}, {500,-300}, {900, -600}, {1400, -900}
    };
    public Board() {

        initBoard();
    }



    public void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);

        ingame = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        squirrel = new Squirrel(ISQUIRREL_X, ISQUIRREL_Y);

        initNuts();
        initStone();

        timer = new Timer(DELAY, this);
        timer.start();
    }


    public void initNuts() {
        nuts = new ArrayList<>();

        //создаем список орехов
        for (int[] p : npos) {
            nuts.add(new Nuts(p[0], p[1]));
        }
    }

    public void initStone() {
        ms = new ArrayList<>();
        //создаем список камней
        for (int[] m : mpos) {
            ms.add(new Stone(m[0], m[1]));
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
        //рисуе фон
        g.drawImage(new ImageIcon("fone.jpg").getImage(), 0, 0, 1500, 1000, this);

        //рисуем белку
        if (squirrel.isVisible()) {
            g.drawImage(squirrel.getImage(), squirrel.getX(), squirrel.getY(),
                    this);
        }

        //рисуем камни
        for (Stone m : ms) {
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }

        //рисуем орехи
        for (Nuts a : nuts) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }

        //рисуем запись сколько осталось орехов
        g.setColor(Color.WHITE);
        Font small = new Font("Helvetica", Font.BOLD, 25);
        g.setFont(small);
        g.drawString("NUTS LEFT: " + nuts.size(), 1300, 30);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        String msg1 = "You won!:)";

        Font small = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fm = getFontMetrics(small);
        g.drawImage(new ImageIcon("fone.jpg").getImage(), 0, 0, 1500, 1000, this);
        g.setColor(Color.black);
        g.setFont(small);
        if (nuts.size()!=0) {
            g.drawImage(new ImageIcon("finish.png").getImage(), 650, 500, 196, 265, this);
            g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                    B_HEIGHT / 2);
        }
        else {
            g.drawImage(new ImageIcon("scrnut.png").getImage(), 650, 500, 237, 215, this);
            g.drawString(msg1, (B_WIDTH - fm.stringWidth(msg1)) / 2,
                    B_HEIGHT / 2);
        }

    }

    //этот метод каждый раз вызывается таймером, за счет него предметы движутся
    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateSquirrel();
        updateStone();
        updateNuts();

        //проверяем столкновения орехов и камней с белкой
        checkCollisions();

        repaint();
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }

    private void updateSquirrel() {

        if (squirrel.isVisible()) {
            squirrel.move();
        }
    }

    private void updateStone() {

        for (int i = 0; i < ms.size(); i++) {

            Stone m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateNuts() {

        if (nuts.isEmpty()) {

            ingame = false;
            return;
        }

        for (int i = 0; i < nuts.size(); i++) {

            Nuts a = nuts.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                nuts.remove(i);
            }
        }
    }

    //проверяем столкновения
    public void checkCollisions() {

        Rectangle r3 = squirrel.getBounds();

        for (Nuts nuts : nuts) {
            Rectangle r2 = nuts.getBounds();

            if (r3.intersects(r2)) {
                squirrel.setVisible(true);
                nuts.setVisible(false);

            }

            if (nuts.getY()==1000) {
                ingame = false;
            }
        }

            for (Stone m : ms) {

                Rectangle r1 = m.getBounds();

                if (r3.intersects(r1)) {
                    m.setVisible(false);
                    squirrel.setVisible(false);
                    ingame = false;
                }
            }

    }

    private class TAdapter extends KeyAdapter {

        //эти методы реагируют на кнопки из клавиатуры, за счет них управляем белкой
        @Override
        public void keyReleased(KeyEvent e) {
            squirrel.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            squirrel.keyPressed(e);
        }
    }
}
