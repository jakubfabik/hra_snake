package Had;

import Mapa.*;
import GUI.*;

public class Had {
    private HadPoz poz;

    public Had(){
        this.poz = new HadPoz(10,10);
    }
    public int HadX(){return poz.HadX();}
    public int HadY(){return poz.HadY();}

}
