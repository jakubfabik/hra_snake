package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KoniecHryObrazovka extends JFrame{

    JFrame frame = new JFrame("Snake");
    String meno = UvodnaObrazovka.okno.menoHraca;
    Okno okno = UvodnaObrazovka.okno;
    int skore;

    public KoniecHryObrazovka(int skore){
        okno.zavriHracieOkno();
        this.skore = skore;
        OvladaciePrvky();
        OknoFcia();
    }

    public void OknoFcia(){
        //nastavenia framu
        frame.setSize(700, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public void OvladaciePrvky(){

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setBackground(Color.ORANGE);
        JLabel nadpis = new JLabel();
        nadpis.setText("KONIEC HRY!");
        nadpis.setFont(new Font("Serif", Font.BOLD, 20));
        nadpis.setForeground(Color.BLACK);
        north.add(nadpis);

        JPanel south = new JPanel();
        south.setBackground(Color.ORANGE);
        JButton ukonciť = new JButton("Ukončiť");
        ukonciť.setBackground(Color.RED);
        ukonciť.setForeground(Color.BLACK);
        JButton odznova = new JButton("Začať odznova");
        odznova.setBackground(Color.GREEN);
        odznova.setForeground(Color.BLACK);
        south.add(ukonciť);
        south.add(odznova);

        ukonciť.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        odznova.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UvodnaObrazovka obrazovka = new UvodnaObrazovka();
            }
        });

        JPanel center = new JPanel();
        center.setBackground(Color.YELLOW);
        center.setLayout(new GridLayout(0,4));
        JLabel labelMeno = new JLabel();
        labelMeno.setText("Meno: ");
        labelMeno.setFont(new Font("Serif", Font.BOLD, 20));
        labelMeno.setForeground(Color.BLACK);
        labelMeno.setHorizontalAlignment(JTextField.RIGHT);
        center.add(labelMeno);

        JLabel menoHraca = new JLabel();
        menoHraca.setText(""+meno);
        menoHraca.setFont(new Font("Serif", Font.BOLD, 20));
        menoHraca.setForeground(Color.BLACK);
        menoHraca.setHorizontalAlignment(JTextField.LEFT);
        center.add(menoHraca);

        JLabel labelSkore = new JLabel();
        labelSkore.setText("Skore: ");
        labelSkore.setFont(new Font("Serif", Font.BOLD, 20));
        labelSkore.setForeground(Color.BLACK);
        labelSkore.setHorizontalAlignment(JTextField.RIGHT);
        center.add(labelSkore);

        JLabel skoreHraca = new JLabel();
        skoreHraca.setText(""+skore);
        skoreHraca.setFont(new Font("Serif", Font.BOLD, 20));
        skoreHraca.setForeground(Color.BLACK);
        skoreHraca.setHorizontalAlignment(JTextField.LEFT);
        center.add(skoreHraca);


        panel.add(north, BorderLayout.NORTH);
        panel.add(south, BorderLayout.SOUTH);
        panel.add(center, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
    }
}