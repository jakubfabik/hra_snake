package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.Okno;


public class UvodnaObrazovka extends JFrame{

    JFrame frame = new JFrame("Snake");
    static JSpinner s1, s2, s3;

    public UvodnaObrazovka(){
        OvladaciePrvky();
        OknoFcia();
    }

    public void OknoFcia(){
        //nastavenia framu
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public void OvladaciePrvky(){

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setBackground(Color.DARK_GRAY);
        JLabel nadpis = new JLabel();
        nadpis.setText("Vyber si meno:");
        nadpis.setFont(new Font("Serif", Font.BOLD, 20));
        nadpis.setForeground(Color.WHITE);
        north.add(nadpis);

        JPanel south = new JPanel();
        south.setBackground(Color.DARK_GRAY);
        JButton tlacidlo = new JButton("Potvrdit");
        tlacidlo.setBackground(Color.WHITE);
        tlacidlo.setForeground(Color.BLACK);
        south.add(tlacidlo);
        tlacidlo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Okno okno = new Okno();
                String meno = s1.getValue() + " " + s2.getValue() + " " + s3.getValue();
            }
        });

        JPanel center = new JPanel();
        center.setBackground(Color.DARK_GRAY);
        center.setLayout(new GridLayout(0,3));
        String abeceda[] = {"A", "B", "C", "D", "E","F", "G",
                "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };
        s1 = new JSpinner(new SpinnerListModel(abeceda));
        s1.setFont(new Font("Serif", Font.BOLD, 400));
        ((JSpinner.DefaultEditor) s1.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) s1.getEditor()).getTextField().setBackground(Color.GRAY);
        ((JSpinner.DefaultEditor) s1.getEditor()).getTextField().setForeground(Color.WHITE);
        center.add(s1);

        s2 = new JSpinner(new SpinnerListModel(abeceda));
        s2.setFont(new Font("Serif", Font.BOLD, 400));
        ((JSpinner.DefaultEditor) s2.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) s2.getEditor()).getTextField().setBackground(Color.GRAY);
        ((JSpinner.DefaultEditor) s2.getEditor()).getTextField().setForeground(Color.WHITE);
        center.add(s2, BorderLayout.CENTER);

        s3 = new JSpinner(new SpinnerListModel(abeceda));
        s3.setFont(new Font("Serif", Font.BOLD, 400));
        ((JSpinner.DefaultEditor) s3.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) s3.getEditor()).getTextField().setBackground(Color.GRAY);
        ((JSpinner.DefaultEditor) s3.getEditor()).getTextField().setForeground(Color.WHITE);
        center.add(s3, BorderLayout.WEST);

        panel.add(north, BorderLayout.NORTH);
        panel.add(south, BorderLayout.SOUTH);
        panel.add(center, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
    }
}
