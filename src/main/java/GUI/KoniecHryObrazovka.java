package GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class KoniecHryObrazovka extends JFrame{
    String menaZoSub = "<html>";
    String skoreZoSub ="<html>";
    JFrame frame = new JFrame("Snake");
    String meno = UvodnaObrazovka.okno.menoHraca;
    Okno okno = UvodnaObrazovka.okno;
    int skore;
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    ValueComparator bvc = new ValueComparator(map);
    TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);


    public KoniecHryObrazovka(int skore) throws IOException {
        okno.zavriHracieOkno();
        this.skore = skore;
        NacitajSkoreSuboru(skore);
        OvladaciePrvky();
        OknoFcia();

    }

    private void NacitajSkoreSuboru(int skore) throws IOException {
        String riadok = "";
        Integer hodnota = null, i = 0;
        String inputLine;
        String skoreTXT = System.getProperty("user.dir") + "/src/main/resources/GUI/skore";
        try (FileWriter f = new FileWriter(skoreTXT, true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {

            if(skore == 0) p.println(meno.substring(0,3) + " "+ "00" + skore);
            else if(skore < 100) p.println(meno.substring(0,3) + " "+ "0" + skore);
            else p.println(meno.substring(0,3) + " "+ skore);


        } catch (IOException x) {
            x.printStackTrace();
        }
        String meno1 = meno;


        File myObj = new File(System.getProperty("user.dir") + "/src/main/resources/GUI/skore");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            riadok = myReader.nextLine();
            String meno = riadok.substring(0, 3);
            hodnota = Integer.parseInt(riadok.substring(4, 7));
            if(meno.equals(meno1)){
                if(hodnota < skore){
                    map.put(meno,skore);
                }
                if(hodnota > skore){
                    map.put(meno,hodnota);

                }
            }
            else map.put(meno,hodnota);
        }
        sorted_map.putAll(map);
        for (Map.Entry<String,Integer> entry : sorted_map.entrySet()) {
            if(i<5) {                                                   //beriem iba prvych 5 skore
                menaZoSub += "" + entry.getKey() + "<br>";
                skoreZoSub += "" + entry.getValue() + "<br>";
                i++;
            }
        }
        menaZoSub += "<h1>Vaše skóre</h1></html>";
        skoreZoSub += "<h1>:" +skore+ "</h1></html>";
        myReader.close();
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
        labelMeno.setFont(new Font("Serif", Font.BOLD, 20));
        labelMeno.setForeground(Color.BLACK);
        labelMeno.setHorizontalAlignment(JTextField.RIGHT);
        center.add(labelMeno);

        JLabel menoHraca = new JLabel();
        menoHraca.setText(menaZoSub);
        menoHraca.setFont(new Font("Serif", Font.BOLD, 20));
        menoHraca.setForeground(Color.BLACK);
        menoHraca.setHorizontalAlignment(JTextField.LEFT);
        center.add(menoHraca);

        JLabel labelSkore = new JLabel();
        labelSkore.setFont(new Font("Serif", Font.BOLD, 20));
        labelSkore.setForeground(Color.BLACK);
        labelSkore.setHorizontalAlignment(JTextField.RIGHT);
        center.add(labelSkore);

        JLabel skoreHraca = new JLabel();
        skoreHraca.setText(skoreZoSub);
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