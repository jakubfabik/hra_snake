package GUI;

import Had.Had;
import Mapa.Mapa;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Okno extends JFrame {
    String menoHraca;
    Timer casovac;

    public Okno(String meno){
        this.menoHraca = meno;
        this.casovac = new Timer();
        OvladaciePrvky();
        bocnyPanel();
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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        JLabel label6 = new JLabel();
        JLabel score = new JLabel();
        JLabel pohybNadpis = new JLabel();
        JLabel pohyb = new JLabel();
        JLabel pohyb2 = new JLabel();
        label1.setText("<html><h1>Meno: " + menoHraca + "</h1></html>");
        label2.setText("<html><h1>Legenda </h1></html>");
        label3.setText("Jablko: +10 bodov");
        label4.setText("Hruška: +20 bodov");
        label5.setText("Banán: +50 bodov");
        label6.setText("Huba: -40 bodov");
        pohybNadpis.setText("<html><h2>Pohyb hada šípkami</h2></html>");
        pohyb.setText("↑ hore, ↓ dole");
        pohyb2.setText("→ do prava, ← do lava");
        casovac.schedule(new TimerTask() {
            @Override
            public void run() {
                score.setText("<html><h2>Score:" + h.getSkore() + "</h2></html>");
            }
        }, 0, 500);
        //label2.setBounds(0, 20, 200, 50);
        panel2.add(label1);
        panel2.add(label2);
        panel2.add(label3);
        panel2.add(label4);
        panel2.add(label5);
        panel2.add(label6);
        panel2.add(pohybNadpis);
        panel2.add(pohyb);
        panel2.add(pohyb2);
        panel2.add(score);
    }

    public void zavriHracieOkno(){
        frame.dispose();
        casovac.cancel();
    }
}