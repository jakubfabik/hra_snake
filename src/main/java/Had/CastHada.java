package Had;

import java.awt.*;

public abstract class CastHada {
    protected HadPoz p;
    public CastHada(int x, int y){
        p = new HadPoz(x,y);
    }
    public abstract void obr(Graphics g, int i, int j);
}
