package GUI;

import Had.Had;
import Mapa.Mapa;

import java.awt.*;

public class Hra {

    Mapa m = new Mapa();
    Had h = new Had(m);

    public void kresli(Graphics g){
        m.kresli(g);
        h.kresli(g);
    }
}
