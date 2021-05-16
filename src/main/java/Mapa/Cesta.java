package Mapa;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Cesta extends MiestoNaMape{
    URL url = getClass().getResource("cesta.png");
    Image img = new ImageIcon(url).getImage();


    @Override
    public void obr(Graphics g, int i, int j) {
        g.drawImage(img, i*30, j*30, null);
    }

}
