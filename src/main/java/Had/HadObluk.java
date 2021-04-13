package Had;

import Mapa.MiestoNaMape;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HadObluk extends CastHada {
    URL url = getClass().getResource("had_obluk.png");
    Image img = new ImageIcon(url).getImage();

    public HadObluk(int x, int y) {
        super(x, y);
    }

    @Override
    public void obr(Graphics g, int i, int j) {
        g.drawImage(img, i*30, j*30, null);
    }
}

