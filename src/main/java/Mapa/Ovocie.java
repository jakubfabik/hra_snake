package Mapa;

import java.awt.*;

public abstract class Ovocie {
    protected int x;
    protected int y;

    public Ovocie(int x, int y){
        this.x = x;
        this.y = y;
    }
    public abstract void obr(Graphics g, int i, int j);
}
