package Mapa;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Mapa {

    private MiestoNaMape[][] mapa;
    private ArrayList<Ovocie> ovocia;

    public Mapa() {
        this.mapa = new MiestoNaMape[20][20];
        ovocia = new ArrayList<>();
        generujMapu();
        generujPrekazky();
        generujOvocia();
    }

    private void generujMapu(){
        int velkost = 20;
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

    private void generujPrekazky(){
        int velkost = 20;
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
    }

    public void generujOvocia(){
        Random rand = new Random();
        int pocetOvoci = rand.nextInt(4)+1;

        for (int i=0; i < pocetOvoci; i++) {
            int cislo = rand.nextInt(8);
            int x = rand.nextInt(18)+1;
            int y = rand.nextInt(18)+1;
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
    public void kresliOvocia(Graphics g){
        ovocia.forEach((item) -> {
            item.obr(g, item.x, item.y);
        });
    }

    public void kresli(Graphics g){
        int velkost = 20;
        for (int j=0; j<velkost; j++) {
            for (int i=0; i <velkost; i++) {
                mapa[i][j].obr(g, i, j);
            }
        }
    }


    public boolean jeCesta(int x, int y){
        if(mapa[x][y] instanceof Cesta){
            return true;
        }
        return false;
    }
    public boolean jeStena(int x, int y){
        if(mapa[x][y] instanceof Stena){
            return true;
        }
        return false;
    }
    public boolean jePrekazka(int x, int y){
        if(mapa[x][y] instanceof Prekazka){
            return true;
        }
        return false;
    }

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

    public void zrusOvocie(int x, int y){
        ovocia.forEach((item) -> {
            if (item.x == x && item.y == y){
                ovocia.remove(item);
            }
        });
    }
}
