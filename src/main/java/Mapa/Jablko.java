package Mapa;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Jablko extends Ovocie{

    URL url1 = getClass().getResource("jablka/jablko.png");
    Image img1 = new ImageIcon(url1).getImage();

    URL url2 = getClass().getResource("jablka/jablko2.png");
    Image img2 = new ImageIcon(url2).getImage();

    URL url3 = getClass().getResource("jablka/jablko3.png");
    Image img3 = new ImageIcon(url3).getImage();


    public Jablko(int x, int y) {
        super(x, y);
    }

    @Override
    public void obr(Graphics g, int i, int j) {
        Random rand = new Random();
        int cislo = rand.nextInt(3);
        switch (cislo){
            case 0:
                g.drawImage(img1, i * 30, j * 30, null);
                break;
            case 1:
                g.drawImage(img2, i * 30, j * 30, null);
                break;
            case 2:
                g.drawImage(img3, i * 30, j * 30, null);
                break;
        }
    }
}
