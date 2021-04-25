package GUI;

import Had.Had;
import Mapa.Mapa;

import javax.swing.*;
import java.awt.*;

public class Okno extends JFrame {

    public Okno(){
        OknoFcia();
        OvladaciePrvky();
        bocnyPanel();
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
        //gridlayovt rre rozlozenie prvkov
        frame.setLayout(new BorderLayout());
        frame.add(platno, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.EAST);
    }

    public void bocnyPanel(){
        JLabel label2 = new JLabel();
        label2.setText("<html><h1>Score: </h1></html>");
        label2.setBounds(0, 20, 200, 50);
        panel2.add(label2);
    }




}