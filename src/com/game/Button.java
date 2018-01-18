package com.game;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Button {

    private int x;
    private int y;
    private int width;
    private int height;

    private boolean enabled;
    private boolean pressed;

    private String text;
    private final Font font = new Font("Courier", Font.ITALIC,35);
    private ActionListener listener;

    public Button(ActionListener listener, String text, int x, int y, int width, int height) {
        this.listener = listener;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        enabled = true;
    }

    //прорисовка кнопки
    public void render(Graphics g) {
        if (pressed) {
            g.setColor(new Color(200, 188, 40));
        } else {
            g.setColor(new Color(200, 188, 40));
        }

        if(enabled) {
            g.fillRect(x,y,width,height);
            g.setFont(font);
            g.setColor(new Color(67, 140, 70));
            int stringWidth = g.getFontMetrics().stringWidth(text);
            g.drawString(text, x + width / 2 - stringWidth / 2, y + height / 2);
        }
    }

    private boolean isPressed(int x, int y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    public void mousePressed(MouseEvent e) {
        if (isPressed(e.getX(), e.getY())) {
            pressed = true;
        }
    }

    public void mouseReleased(MouseEvent e) {

        if (pressed && enabled)  {
            listener.actionPerformed(new ActionEvent(this));
            pressed = false;
        }
    }


}
