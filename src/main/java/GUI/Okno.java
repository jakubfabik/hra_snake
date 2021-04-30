package GUI;

import Had.Had;
import Mapa.Mapa;
import javax.swing.*;
import java.awt.*;

public class Okno extends JFrame {
    String menoHraca;
    public Okno(String meno){
        this.menoHraca = meno;
        OvladaciePrvky();
        OknoFcia();
    }
    Mapa m = new Mapa();
    Had h = new Had(m);
    JFrame frame = new JFrame("Snake");
    //Platno
    Platno platno = new Platno(m,h);
    //layout v pravo
    JPanel panel2 = new JPanel();

    public void OknoFcia(){
        //nastavenia framu
        frame.setSize(900, 630);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void OvladaciePrvky(){
        platno.setPreferredSize(new Dimension(platno.getWidth(),platno.getHeight()));
        //panel2 layout
        panel2.setPreferredSize(new Dimension(300,600));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));

        JLabel meno = new JLabel();
        meno.setText("Meno:"+menoHraca);
        panel2.add(meno);

        //gridlayovt rre rozlozenie prvkov
        frame.setLayout(new BorderLayout());
        frame.add(platno, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.EAST);
    }





}