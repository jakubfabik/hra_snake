package Mapa;

import java.awt.*;
import java.util.Random;
import Had.*;

public class Mapa {

    private MiestoNaMape[][] mapa;

    public Mapa() {
        this.mapa = new MiestoNaMape[20][20];
        generujMapu();
        generujPrekazky();
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
                //Todo podmienky pre kreslenie hada
                /*if(j == had.HadX() & i == had.HadY()) {
                    kresliHada(j, i);
                }*/
            }
        }
    }

    public void kresli(Graphics g){
        int velkost = 20;
        for (int j=0; j<velkost; j++) {
            for (int i=0; i <velkost; i++) {
                mapa[i][j].obr(g, i, j);
            }
        }
    }

   /* public void kresliHada(int x, int y){
        //TODO treba riesit: obluk hada, inicializacne prekrytie prekazky hadom
        if(had.dlzka() == 1){
            mapa[x][y] = new HadHlava();
        }
        if (had.dlzka() == 2) {
            mapa[x][y] = new HadHlava();
            mapa[x][y-1] = new HadKoniec();
        }
        if  (had.dlzka() > 2) {
            mapa[x][y] = new HadHlava();
            for(int i = had.dlzka();i > 1; i--){
                mapa[x][y-i+1] = new HadTelo();
            }
            mapa[x][y-had.dlzka()] = new HadKoniec();
        }
    }*/
}
