package GUI;

import javax.swing.*;
import java.awt.*;

public class Platno extends JPanel {

    Hra h = new Hra();

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
        h.kresli(g);
    }
}
