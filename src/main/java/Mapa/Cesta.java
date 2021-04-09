package Mapa;

import java.awt.*;

public class Cesta extends MiestoNaMape{
    @Override
    public void obr(Graphics g, int i, int j) {
        g.setColor(Color.GREEN);
        g.fillRect(i*30,j*30, 30,30);
    }
}
