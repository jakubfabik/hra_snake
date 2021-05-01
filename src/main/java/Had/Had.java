package Had;
import Mapa.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class Had {
    private HadHlava hlava;
    private Mapa m;
    public ArrayList<Boolean> smer= new ArrayList<Boolean>(); //dolava,doprava,hore,dole
    private int zivot = 3;
    private int dlzka = 1;  // realne 5 pocitame s nulou ta je hlava
    private int score = 0;
    private LinkedList<CastHada> fifoCastiHada = new LinkedList<CastHada>();
    int castLen = 0;




    public Had(Mapa mapa){
        for(int i = 0;i<3;i++){smer.add(false);}
        smer.add(true); //zaciatocny smer je dole
        this.m = mapa;
        this.hlava = new HadHlava(10,10);
        //poleHad.add(new HadHlava(10,10));
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

    public void kontrolaOvocia(){
        if((m.jeOvocie(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()) == 0)){}
        else {
            if((m.jeOvocie(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()) < 4)){
                dlzka++;
                m.zrusOvocie(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget());
                m.generujOvocia();
            }
            else {
                zivot--;
                System.out.println("Stratil si zivot");
                m.zrusOvocie(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget());
                m.generujOvocia();
            }
        }
    }
    public void resetHada(){
        this.hlava = new HadHlava(10,10);
        this.dlzka = 1;
        System.out.println("Stratil si zivot");
        while(castLen !=0){
            fifoCastiHada.pop();
            castLen --;
        }
    }

    public int kontrolaKoncaHry(){
        if(zivot < 1) {
            System.out.println("Had zomrel!!!");
            zivot--;
            return 0;
        }
        else return 1;
    }

    public void kontrolaPrekazky(){
        if(m.jeStena(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget())
                || m.jePrekazka(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget())){
            m.zrusVsetkyOvocia();
            zivotMinus();
            resetHada();
            m.generujOvocia();
        }

    }
    public void pohybHada(){
        kontrolaPrekazky();
        kontrolaOvocia();
        if(smer.get(0)) {
            //System.out.println("stlacene dolava");
            hlava.poz.CastHadaXset(hlava.poz.CastHadaXget()-1);
            fifoCastiHada.add(new HadGulicka(hlava.poz.CastHadaXget()+1,hlava.poz.CastHadaYget()));
            castLen = castLen+1;
            hlava.orientacia = 'l';
        }
        if(smer.get(1)) {
            //System.out.println("stlacene doprava");
            hlava.poz.CastHadaXset(hlava.poz.CastHadaXget()+1);
            fifoCastiHada.add(new HadGulicka(hlava.poz.CastHadaXget()-1,hlava.poz.CastHadaYget()));
            castLen = castLen+1;
            hlava.orientacia = 'r';
        }
        if(smer.get(2)) {
            //System.out.println("stlacene hore");
            hlava.poz.CastHadaYset(hlava.poz.CastHadaYget()-1);
            fifoCastiHada.add(new HadGulicka(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()+1));
            castLen = castLen+1;
            hlava.orientacia = 'u';
        }
        if(smer.get(3)) {
            //System.out.println("stlacene dole");
            hlava.poz.CastHadaYset(hlava.poz.CastHadaYget()+1);
            fifoCastiHada.add(new HadGulicka(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()-1));
            castLen = castLen+1;
            hlava.orientacia = 'd';
        }
        /**
         * ak sa dlzka rovna poctu guliciek vyhod z radu prvy vlozeny
         */
        if(castLen == dlzka){
            fifoCastiHada.pop();
            castLen --;
        }
    }
    public void kresli(Graphics g){
        hlava.obr(g,hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget(),hlava.orientaciaCasti());
        fifoCastiHada.forEach((item) -> {
            item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti());
        });
    }
}
