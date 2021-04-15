package Mapa;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Jablko extends Ovocie{

    URL url = getClass().getResource("jablko.png");
    Image img = new ImageIcon(url).getImage();

    public Jablko(int x, int y) {
        super(x, y);
    }

    @Override
    public void obr(Graphics g, int i, int j) {
        g.drawImage(img, i*30, j*30, null);
    }
}