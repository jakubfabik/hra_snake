package GUI;

import Mapa.Mapa;

import javax.swing.*;
import java.awt.*;

public class Platno extends JPanel {

    Mapa m = new Mapa();
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
        m.kresli(g);
    }
}
