package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Playstate extends JPanel implements ActionListener {

    private Button button1 = new Button(this, "Start Game", 600, 250, 300, 80);
    private Button button2 = new Button(this, "Exit", 600, 350, 300, 80);
    private ArrayList<Nuts> nuts;
    private boolean state;
    private final int B_WIDTH = 1500;
    private final int B_HEIGHT = 1000;
    String msg = "Game Over";
    String msg1 = "You won!:)";
    Font small = new Font("Helvetica", Font.BOLD, 50);



    public Playstate(){

            addMouseListener(new TAdapter());
            setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    }

    public Playstate(ArrayList<Nuts> n, boolean st) {

        this.nuts = n;
        this.state = st;
        addMouseListener(new TAdapter());
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

    }

    @Override
    public void paint(Graphics g) {
      render(g);
    }

    public void render(Graphics g) {
        //прорисовка фона
        g.drawImage(new ImageIcon("fone.jpg").getImage(), 0, 0, 1500, 1000, this);

        if (state){
            FontMetrics fm = getFontMetrics(small);
            g.setColor(Color.black);
            g.setFont(small);

            //если все орехи собраны прорисовываем, что игрок выиграл или проиграл
            if (nuts.size()!=0) {
                g.drawImage(new ImageIcon("finish.png").getImage(), 650, 500, 196, 265, this);
                g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                        B_HEIGHT / 2);

                Playstate playstate = new Playstate();
                add(playstate);
                playstate.requestFocus();
                GameMain.ex.validate();
            }
            else {
                g.drawImage(new ImageIcon("scrnut.png").getImage(), 650, 500, 237, 215, this);
                g.drawString(msg1, (B_WIDTH - fm.stringWidth(msg1)) / 2,
                        B_HEIGHT / 2);
            }

        }
            button1.render(g);
            button2.render(g);

    }

    //обработка событий мышки
    private class TAdapter extends MouseAdapter {

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

    //проверка на какую кнопку нажал игрок
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button1) {

            Board board = new Board();
            add(board);
            board.requestFocus();
            GameMain.ex.validate();
        }
        if (e.getSource() == button2) {
            System.exit(0);
        }
    }
}
