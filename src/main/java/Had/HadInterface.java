package Had;

import java.io.IOException;

public interface HadInterface {
    void zivotPlus();
    void zivotMinus();
    void dlzkaPlus();
    int dlzka();
    int zivoty();
    void hore();
    void dole();
    void doprava();
    void dolava();
    void kontrolaOvocia();
    void resetHada();
    boolean kontrolaKoncaHry() throws IOException;
    void koniecHry() throws IOException;
    int getSkore();

}
