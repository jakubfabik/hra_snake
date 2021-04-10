package GUI;

import javax.swing.*;
import java.awt.*;

public class Okno extends JFrame {

    public Okno(){
        OknoFcia();
        OvladaciePrvky();
    }

    JFrame frame = new JFrame("Snake");
    //Platno
    Platno platno = new Platno();
    //layout v pravo
    JPanel panel2 = new JPanel();

    public void OknoFcia(){
        //nastavenia framu
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void OvladaciePrvky(){
        platno.setBackground(Color.GRAY);
        platno.setPreferredSize(new Dimension(platno.getWidth(),platno.getHeight()));
        //panel2 layout
        panel2.setBackground(Color.CYAN);
        panel2.setPreferredSize(new Dimension(300,600));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        //gridlayovt rre rozlozenie prvkov
        frame.setLayout(new BorderLayout());
        frame.add(platno, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.EAST);
    }





}