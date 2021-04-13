package Had;

import Mapa.*;
import GUI.*;

import java.awt.*;
import java.util.LinkedList;

public class Had {
    private HadPoz poz;
    private Mapa m;
    private int zivot = 3;
    private int dlzka = 8;
    private LinkedList<CastHada> h = new LinkedList<>();

    public Had(Mapa mapa){
        this.poz = new HadPoz(10,10);
        this.m = mapa;
    }

    public int HadX(){return poz.HadX();}
    public int HadY(){return poz.HadY();}

    public void zivotPlus(){
        this.zivot++;
    }
    public void zivotMinus(){
        this.zivot--;
    }
    public void dlzkaPlus(){
        this.dlzka++;
    }
    public void dlzkaMinus(){
        if(this.dlzka >= 1) {
            this.dlzka--;
        }
        else{
            System.out.println("had zomrel koniec hry");
        }
    }
    public int dlzka(){return this.dlzka;}
    public int zivoty(){return this.zivot;}

    public void kresli(Graphics g){
        h.forEach((item) -> {
            item.obr(g,item.p.HadX(), item.p.HadY());
        });
    }
}
