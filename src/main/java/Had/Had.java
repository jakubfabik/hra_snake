package Had;

import Mapa.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class Had {
    private HadPoz poz;
    private Mapa m;
    public ArrayList<Boolean> smer= new ArrayList<Boolean>(); //dolava,doprava,hore,dole
    private int zivot = 3;
    private int dlzka = 4;
    private ArrayList<CastHada> poleHad = new ArrayList<CastHada>();




    public Had(Mapa mapa){
        for(int i = 0;i<3;i++){smer.add(false);}
        smer.add(true); //zaciatocny smer je dole
        this.poz = new HadPoz(10,10);
        this.m = mapa;
        poleHad.add(new HadHlava(10,10));
        poleHad.add(new HadTelo(10,9));
        poleHad.add(new HadTelo(10,8));
        poleHad.add(new HadTelo(10,7));
        poleHad.add(new HadKoniec(10,6));
    }

    public void hore() {
        for (int i = 0; i < 4; i++) {
            smer.set(i, false);
            if(i == 2) smer.set(i,true);
        }
    }
    public void dole() {
        for (int i = 0; i < 4; i++) {
            smer.set(i, false);
            if(i == 3) smer.set(i,true);
        }
    }
    public void doprava() {
        for (int i = 0; i < 4; i++) {
            smer.set(i, false);
            if(i == 1) smer.set(i,true);
        }
    }
    public void dolava() {
        for (int i = 0; i < 4; i++) {
            smer.set(i, false);
            if(i == 0) smer.set(i,true);
        }
    }


    public int ZaciatokHadaX(){return poz.CastHadaXget();}
    public int ZaciatokHadaY(){return poz.CastHadaYget();}

    public void zivotPlus(){
        this.zivot++;
    }
    public void zivotMinus(){
        this.zivot--;
    }
    public void dlzkaPlus(){
        this.dlzka++;
    }

    public int dlzka(){return this.dlzka;}
    public int zivoty(){return this.zivot;}

    public void pohni(){
        int castLen = 0;
        //dole
        if(smer.get(3)){
            System.out.println("stlacene dole");
            for (CastHada item : poleHad) {
                if(castLen == 0){
                    item.poz.CastHadaYset(item.poz.CastHadaYget() + 1);
                }
                else{
                    if(item.poz.CastHadaXget() != poleHad.get(0).poz.CastHadaXget()){
                        item.poz.CastHadaXset(item.poz.CastHadaXget() - 1);
                    }
                    else{
                        item.poz.CastHadaYset(item.poz.CastHadaYget() + 1);
                        item.orientacia = 'd';
                    }
                }
                castLen += 1;
            }
        }
        if(smer.get(2)){
            System.out.println("stlacene hore");
            for (CastHada item : poleHad) {
                if(castLen == 0){
                    item.poz.CastHadaYset(item.poz.CastHadaYget() - 1);
                }
                else{
                    if(item.poz.CastHadaXget() != poleHad.get(0).poz.CastHadaXget()){
                        item.poz.CastHadaXset(item.poz.CastHadaXget() + 1);
                    }
                    else{
                        item.poz.CastHadaYset(item.poz.CastHadaYget() - 1);
                        item.orientacia = 'u';
                    }
                }
                castLen += 1;
            }
        }
        if(smer.get(1)){
            System.out.println("stlacene doprava");
            for (CastHada item : poleHad) {
                if(castLen == 0){
                    item.poz.CastHadaXset(item.poz.CastHadaXget() + 1);
                }
                else{
                    if(item.poz.CastHadaYget() != poleHad.get(0).poz.CastHadaYget()){
                        item.poz.CastHadaYset(item.poz.CastHadaYget() + 1);
                    }
                    else{
                        item.poz.CastHadaXset(item.poz.CastHadaXget() + 1);
                        item.orientacia = 'r';
                    }
                }

                castLen += 1;
            }
        }
        if(smer.get(0)){

            System.out.println("stlacene dolava");
            for (CastHada item : poleHad) {
                if(castLen == 0){
                    item.poz.CastHadaXset(item.poz.CastHadaXget() - 1);
                }
                else{
                    if(item.poz.CastHadaYget() != poleHad.get(0).poz.CastHadaYget()){
                        item.poz.CastHadaYset(item.poz.CastHadaYget() - 1);
                    }
                    else{
                        item.poz.CastHadaXset(item.poz.CastHadaXget() - 1);
                        item.orientacia = 'l';
                    }
                }
                castLen += 1;
            }
        }
    }
    public void kresli(Graphics g){
        /**
         * reakcia na posun dole
         */
        if(smer.get(3)) {
            poleHad.get(0).orientacia = 'd';
            poleHad.forEach((item) -> {
                item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti() );
            });
        }
        /**
         * reakcia na posun doprava
         */
        if(smer.get(0)) {
            poleHad.get(0).orientacia = 'l';
            poleHad.forEach((item) -> {
                item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti());
            });
        }
        if(smer.get(1)) {
            poleHad.get(0).orientacia = 'r';
            poleHad.forEach((item) -> {
                    item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti());

            });

        }
        if(smer.get(2)) {
            poleHad.get(0).orientacia = 'u';
            poleHad.forEach((item) -> {
                item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti());
            });
        }
        if(smer.get(3)) {
            poleHad.get(0).orientacia = 'd';
            poleHad.forEach((item) -> {
                item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti());
            });
        }
    }

}
