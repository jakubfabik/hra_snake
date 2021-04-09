package Mapa;

import java.util.Random;

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
                        mapa[i][j] = new Stena();
                        break;
                    default:
                        mapa[i][j] = new Cesta();
                }
            }
        }
    }

    public void vypis(){
        int velkost = 20;
        for (int j=0; j<velkost; j++) {
            for (int i=0; i < velkost; i++) {
                System.out.print(mapa[i][j].vrat());
            }
            System.out.println();
        }
        System.out.println();
    }


}
