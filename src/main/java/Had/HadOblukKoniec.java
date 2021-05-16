package Had;

import Mapa.MiestoNaMape;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HadOblukKoniec extends CastHada {


    public HadOblukKoniec(int x, int y, char o) {
        super(x, y, o);
        super.orientacia = o;
    }

    @Override
    public void obr(Graphics g, int i, int j, char o) {
        super.orientacia = o;
        URL url = getClass().getResource("had_obluk_koniec_"+super.orientacia+".png");
        Image img = new ImageIcon(url).getImage();
        g.drawImage(img, i*30, j*30, null);
    }


}

