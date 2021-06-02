package Had;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HadHlava extends CastHada {


    /**
     *HadHlava rozširujúca triedu časť vsebe uchováva aktuálnu,
     * pozíziu začiatku hada.
     */
    public HadHlava(int x, int y, char o) {
        super(x, y, o);
    }

    @Override
    public void obr(Graphics g, int i, int j, char o) {
        //softverove otocenie
        /*
        final double rads = Math.toRadians(90);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));

        BufferedImage image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image,rotatedImage);
        */
        super.orientacia = o;
        URL url = getClass().getResource("had_"+super.orientacia+".png");
        Image img = new ImageIcon(url).getImage();
        g.drawImage(img, i*30, j*30, null);

    }


}


