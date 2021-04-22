package Had;

import Mapa.MiestoNaMape;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HadGulicka extends CastHada {

    public HadGulicka(int x, int y) {
        super(x, y);
    }

    @Override
    public void obr(Graphics g, int i, int j, char o) {
        super.orientacia = o;
        URL url = getClass().getResource("gulicka.png");
        Image img = new ImageIcon(url).getImage();
        g.drawImage(img, i*30, j*30, null);
    }

}


