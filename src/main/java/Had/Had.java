package Had;

import Mapa.*;
import GUI.*;

public class Had {
    private HadPoz poz;
    private int zivot = 3;
    private int dlzka = 8;

    public Had(){
        this.poz = new HadPoz(10,10);
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


}
