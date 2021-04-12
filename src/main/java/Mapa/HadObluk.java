package Mapa;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HadObluk extends MiestoNaMape{
    URL url = getClass().getResource("had_obluk.png");
    Image img = new ImageIcon(url).getImage();

    @Override
    public void obr(Graphics g, int i, int j) {
        g.drawImage(img, i*30, j*30, null);
    }
}

