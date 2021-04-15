package Mapa;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Banan extends Ovocie{

    URL url = getClass().getResource("banan.png");
    Image img = new ImageIcon(url).getImage();

    public Banan(int x, int y) {
        super(x, y);
    }

    @Override
    public void obr(Graphics g, int i, int j) {
        g.drawImage(img, i*30, j*30, null);
    }
}
