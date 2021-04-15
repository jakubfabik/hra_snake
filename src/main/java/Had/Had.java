package Had;

import Mapa.*;
import java.awt.*;
import java.util.ArrayList;



public class Had {
    private HadPoz poz;
    private Mapa m;
    public ArrayList<Boolean> smer= new ArrayList<Boolean>(); //dolava,doprava,hore,dole
    private int zivot = 3;
    private int dlzka = 8;
    private ArrayList<CastHada> h = new ArrayList<CastHada>();


    public Had(Mapa mapa){
        for(int i = 0;i<3;i++){smer.add(false);}
        smer.add(true); //zaciatocny smer je dole
        this.poz = new HadPoz(10,10);
        this.m = mapa;
        h.add(new HadHlava(10,10));
        h.add(new HadTelo(10,9));
        h.add(new HadKoniec(10,8));
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
        //dole
        if(smer.get(3)){
            System.out.println("stlacene dole");
            h.forEach((item) -> {
                item.p.CastHadaYset(item.p.CastHadaYget()+1);
            });
        }
        if(smer.get(2)){
            System.out.println("stlacene hore");
            h.forEach((item) -> {
                item.p.CastHadaYset(item.p.CastHadaYget()-1);
            });
        }
        if(smer.get(1)){
            System.out.println("stlacene doprava");
            h.forEach((item) -> {
                item.p.CastHadaXset(item.p.CastHadaXget()+1);
            });
        }
        if(smer.get(0)){
            System.out.println("stlacene dolava");
            h.forEach((item) -> {
                item.p.CastHadaXset(item.p.CastHadaXget()-1);
            });
        }
    }
    public void kresli(Graphics g){
        h.forEach((item) -> {
            item.obr(g,item.p.CastHadaXget(), item.p.CastHadaYget());
        });
    }

}
