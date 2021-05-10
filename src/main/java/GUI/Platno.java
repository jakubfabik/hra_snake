package GUI;

import Had.Had;
import Mapa.Mapa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Platno extends JPanel implements ActionListener {
    Mapa m;
    Had h;
    public Platno(Mapa m, Had h){
        timer.start();
        addKeyListener(new TAdapter());
        setFocusable(true);
        this.m = m;
        this.h = h;
    }

    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    Timer timer = new Timer(500,this);



    private int WIDTH = 600;
    private int HEIGHT = 630;

    public int getWidth(){
        return WIDTH;
    }

    public int getHeight(){
        return HEIGHT;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d =   (Graphics2D) g.create();
        m.kresli(g);
        m.kresliOvocia(g);
        h.kresli(g);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
                h.dolava();

            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
                h.doprava();

            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
                h.hore();

            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                h.dole();
                leftDirection = false;

            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        h.pohybHada();
        repaint();
    }
}
