package Had;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HadHlava extends CastHada {
    URL url = getClass().getResource("had.png");
    Image img = new ImageIcon(url).getImage();

    public HadHlava(int x, int y) {
        super(x, y);
    }

    @Override
    public void obr(Graphics g, int i, int j) {
        g.drawImage(img, i*30, j*30, null);
    }
}


