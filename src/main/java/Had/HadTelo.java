package Had;

import Mapa.MiestoNaMape;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HadTelo extends CastHada {
    URL url = getClass().getResource("had_telo.png");
    Image img = new ImageIcon(url).getImage();

    public HadTelo(int x, int y) {
        super(x, y);
    }

    @Override
    public void obr(Graphics g, int i, int j) {
        g.drawImage(img, i*30, j*30, null);
    }
}


