package Had;

import java.awt.*;

public abstract class CastHada {
    protected HadPoz poz;
    protected char orientacia = 'd';
    public CastHada(int x, int y, char o){
        this.poz = new HadPoz(x,y);
        this.orientacia = o;
    }
    public abstract void obr(Graphics g, int i, int j, char p);
    public char orientaciaCasti(){
        return this.orientacia;
    }
}
