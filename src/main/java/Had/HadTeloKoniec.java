package Had;

import Mapa.MiestoNaMape;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HadTeloKoniec extends CastHada {

    public HadTeloKoniec(int x, int y, char o) {
        super(x, y, o);
    }

    @Override
    public void obr(Graphics g, int i, int j,char o) {
        super.orientacia = o;
        URL url = getClass().getResource("had_koniec_"+super.orientacia+".png");
        Image img = new ImageIcon(url).getImage();
        g.drawImage(img, i*30, j*30, null);
    }


}


