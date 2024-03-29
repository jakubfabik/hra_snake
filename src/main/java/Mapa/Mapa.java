package Mapa;

import java.awt.*;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Mapa {

    private MiestoNaMape[][] mapa;
    private LinkedList<Ovocie> ovocia;
    private int velkost = 20;
    private int pozPortX = 9;
    private int pozPortY = 9;

    public Mapa() {
        this.mapa = new MiestoNaMape[20][20];
        ovocia = new LinkedList<>();
        nahodnaMapa();
    }

    /**
     * Metóda vygeneruje náhodnú mapu s dierou z ktorej had výjde po prejdení cez portál
     */
    public void kresliPortal() {
        this.mapa = new MiestoNaMape[20][20];
        ovocia = new LinkedList<>();
        nahodnaMapa();
        mapa[10][10] = new Diera();     //diera zkadial vychadza had
        //otvorPortal();
    }

    public void kresliBludisko(){
        this.mapa = new MiestoNaMape[20][20];
        nacitajBludisko();
    }
    /**
     * Metóda vygeneruje ohraničenie mapy
     */
    private void generujMapu(){
        for (int j=0; j<velkost; j++){
            for (int i=0; i<velkost; i++){
                if(i==0 || j==0 || i==velkost-1 || j==velkost-1) {
                    mapa[i][j] = new Stena();
                }else{
                    mapa[i][j] = new Cesta();
                }
            }
        }
    }

    /**
     * Metóda načíta bludisko zo súboru
     * a vytvorí z neho mapu
     */
    private void nacitajBludisko(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("Mapa/bludisko");
        Scanner sc = new Scanner(inputStream);
        int sirka = 20;
        int vyska = 20;
        int y=0;
        do {
            String riadok = sc.nextLine();
            for(int x=0; x<sirka;x++){
                if (riadok.charAt(x) == '0') {
                    mapa[x][y] = new Stena();
                }else if(riadok.charAt(x) == '1'){
                    mapa[x][y] = new Cesta();
                }else if(riadok.charAt(x) == '2'){
                    mapa[x][y] = new Portal();
                }else {
                    mapa[x][y] = new Prekazka();
                }
            }
            y++;
        }while(y<vyska);
        zrusVsetkyOvocia();
    }

    /**
     * Metóda vygeneruje náhodný počet prekážok na náhodných miestach
     */
    private void generujPrekazky(){
        Random rand = new Random();
        for (int j=1; j<velkost-1; j++) {
            for (int i=1; i < velkost-1; i++) {
                int cislo = rand.nextInt(10);
                switch (cislo) {
                    case 0:
                        mapa[j][i] = new Prekazka();
                        break;
                    default:
                        mapa[j][i] = new Cesta();
                }
            }
        }
        spawn();
    }
    /**
     * Metóda generuje ovocia, od 1 po 4
     * Najväčšiu šancu vygeneravnia má Jablko
     * potom Hruška
     * Banán a Huba majú rovnakú šancu
     */
    public void generujOvocia(){
        Random rand = new Random();
        int pocetOvoci = rand.nextInt(3)+1;

        for (int i=0; i < pocetOvoci; i++) {
            int cislo = rand.nextInt(8);
            int x;
            int y;
            do {
                x = rand.nextInt(18) + 1;
                y = rand.nextInt(18) + 1;
            }while(mapa[x][y] instanceof Stena || mapa[x][y] instanceof Prekazka || mapa[x][y] instanceof Kamen);
            switch (cislo) {
                case 0:
                    ovocia.add(new Huba(x,y));
                    break;
                case 1:
                    ovocia.add(new Banan(x,y));
                    break;
                case 2:
                    ovocia.add(new Hruska(x,y));
                    break;
                case 3:
                    ovocia.add(new Hruska(x,y));
                    break;
                default:
                    ovocia.add(new Jablko(x,y));
            }
        }
    }

    /**
     * Pomocná metóda, aby sa pri mieste objavenia hada nevygenerovali prekážky
     */
    public void spawn(){
        int x,y;
        for(x = 8;x < 12; x++ ){
            for(y = 8; y < 12; y++){
                mapa[x][y] = new Cesta();
            }
        }
    }


    public void nahodnaMapa(){
        generujMapu();
        generujPrekazky();
        generujOvocia();
    }

    /**
     * Metóda prejde celý zoznam ovocia a vykreslí textúru
     */
    public void kresliOvocia(Graphics g){
        ovocia.forEach((item) -> {
            item.obr(g, item.x, item.y);
        });
    }

    /**
     * Metóda vykreslí textúru podľa pozície na mape
     */
    public void kresli(Graphics g){
        for (int j=0; j<velkost; j++) {
            for (int i=0; i <velkost; i++) {
                mapa[i][j].obr(g, i, j);
            }
        }
    }

    /**
     * Metóda otvorí portál na náhodnej pozícii po dosiahnutí určítého skóre
     */
    public void otvorPortal(){
        if(mapa[pozPortX][pozPortY]instanceof Cesta) {
            Random rand = new Random();
            pozPortX = rand.nextInt(velkost - 2) + 1;
            pozPortY = rand.nextInt(velkost - 2) + 1;
            mapa[pozPortX][pozPortY] = new Portal();
        }
    }

    /**
     * Metóda slúži pre hada na zistenie portál
     * x - pozícia hlavy hada
     * y - pozícia hlavy hada
     */
    public boolean jePortal(int x, int y){
        if(mapa[x][y] instanceof Portal){
            return true;
        }
        return false;
    }

    /**
     * Metóda slúži pre hada na zistenie Steny
     * Stena - ohraničenie mapy
     * x - pozícia hlavy hada
     * y - pozícia hlavy hada
     */
    public boolean jeStena(int x, int y){
        if(mapa[x][y] instanceof Stena){
            return true;
        }
        return false;
    }

    /**
     * Metóda slúži pre hada na zistenie prekážky
     * prekážka - nachádzajú sa vo vnútri mapy
     * x - pozícia hlavy hada
     * y - pozícia hlavy hada
     */
    public boolean jePrekazka(int x, int y){
        if(mapa[x][y] instanceof Prekazka){
            return true;
        }
        return false;
    }

    /**
     * Metóda vráti číslo od 1 po 4 podľa druhu ovocia
     * ak nie je ovocie na pozícii x y (parametre funkcie) vráti 0
     * Každý druh ovocia pridáva alebo odoberá iné skóre
     * Jablko +10
     * Hruska +20
     * Banan +50
     * Huba -40
     */
    public int jeOvocie(int x, int y){
        for (Ovocie item : ovocia) {
            if (item.x == x && item.y == y) {
                if(item instanceof Jablko){
                    return 1;
                }
                if(item instanceof Hruska){
                    return 2;
                }
                if (item instanceof Banan){
                    return 3;
                }
                if (item instanceof Huba){
                    return 4;
                }
            }
        }
        return 0;
    }

    /**
     * Metóda zruší ovocie na danej pozícii
     * Slúži na vymazanie ovocia, ktoré had akurát zjedol
     * @param x - x-ová pozícia hlavy hada
     * @param y - y-ová pozícia hlavy hada
     */
    public void zrusOvocie(int x, int y){
        for(int i=0; i < ovocia.size(); i++){
            if(ovocia.get(i).x == x && ovocia.get(i).y == y){
                ovocia.remove(i);
            }
        }
    }

    /**
     * Metóda zruší všetky ovocia
     * Nastaví spájaný zoznam ovocia na nový LinkedList
     */
    public void zrusVsetkyOvocia(){
        ovocia = new LinkedList<>();
    }
}
