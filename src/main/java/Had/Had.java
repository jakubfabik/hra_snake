package Had;

import Mapa.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class Had {
    private HadHlava hlava;
    private Mapa m;
    public ArrayList<Boolean> smer= new ArrayList<Boolean>(); //dolava,doprava,hore,dole
    private int zivot = 3;
    private int dlzka = 4;  // realne 5 pocitame s nulou ta je hlava
    private LinkedList<CastHada> fifoCastiHada = new LinkedList<CastHada>();
    int castLen = 0;




    public Had(Mapa mapa){
        for(int i = 0;i<3;i++){smer.add(false);}
        smer.add(true); //zaciatocny smer je dole
        this.m = mapa;
        this.hlava = new HadHlava(10,10);
        //poleHad.add(new HadHlava(10,10));
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

        //dole
        if(smer.get(3)){
            System.out.println("stlacene dole");
            hlava.poz.CastHadaYset(hlava.poz.CastHadaYget()+1);
            fifoCastiHada.add(new HadGulicka(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()-1));
            castLen = castLen+1;
        }
        if(smer.get(2)){
            System.out.println("stlacene hore");
            hlava.poz.CastHadaYset(hlava.poz.CastHadaYget()-1);
            fifoCastiHada.add(new HadGulicka(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()+1));
            castLen = castLen+1;
        }
        if(smer.get(1)){
            System.out.println("stlacene doprava");
            hlava.poz.CastHadaXset(hlava.poz.CastHadaXget()+1);
            fifoCastiHada.add(new HadGulicka(hlava.poz.CastHadaXget()-1,hlava.poz.CastHadaYget()));
            castLen = castLen+1;
        }
        if(smer.get(0)){

            System.out.println("stlacene dolava");
            hlava.poz.CastHadaXset(hlava.poz.CastHadaXget()-1);
            fifoCastiHada.add(new HadGulicka(hlava.poz.CastHadaXget()+1,hlava.poz.CastHadaYget()));
            castLen = castLen+1;
        }
    }
    public void kresli(Graphics g){

        if(smer.get(0)) {
            hlava.orientacia = 'l';
            hlava.obr(g,hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget(),hlava.orientaciaCasti());
            fifoCastiHada.forEach((item) -> {
                item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti());
            });
        }
        if(smer.get(1)) {
            hlava.orientacia = 'r';
            hlava.obr(g,hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget(),hlava.orientaciaCasti());
            fifoCastiHada.forEach((item) -> {
                    item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti());

            });

        }
        if(smer.get(2)) {
            hlava.orientacia = 'u';
            hlava.obr(g,hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget(),hlava.orientaciaCasti());
            fifoCastiHada.forEach((item) -> {
                item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti());
            });
        }
        if(smer.get(3)) {
            hlava.orientacia = 'd';
            hlava.obr(g,hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget(),hlava.orientaciaCasti());
            fifoCastiHada.forEach((item) -> {
                item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti());
            });
        }
        /**
         * ak sa dlzka rovna poctu guliciek vyhod z radu prvy vlozeny
         */
        if(castLen == dlzka){
            fifoCastiHada.pop();
            castLen --;
        }
    }
}
