package Had;

import Mapa.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class Had {
    private HadHlava hlava;
    private Mapa m;
    int Score = 0;
    public ArrayList<Boolean> smer= new ArrayList<Boolean>(); //dolava,doprava,hore,dole
    private ArrayList<Boolean> kopiaSmeru= new ArrayList<Boolean>();
    private char pposmer; //predposledny smer
    private int zivot = 3;
    private int dlzka = 1;  // realne 5 pocitame s nulou ta je hlava
    private LinkedList<CastHada> fifoCastiHada = new LinkedList<CastHada>();
    int castLen = 0;




    public Had(Mapa mapa){
        for(int i = 0;i<3;i++){smer.add(false);}
        smer.add(true); //zaciatocny smer je dole
        //todo zautomatizovat
        kopiaSmeru.add(false);kopiaSmeru.add(false);kopiaSmeru.add(false);kopiaSmeru.add(true);
        this.m = mapa;
        this.hlava = new HadHlava(10,10, 'd');
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
                if (m.jeOvocie(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget()) == 1){
                    Score+=10;
                    System.out.println("Score: " + Score);
                }
                if (m.jeOvocie(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget()) == 2){
                    Score+=20;
                    System.out.println("Score: " + Score);
                }
                if (m.jeOvocie(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget()) == 3){
                    Score+=50;
                    System.out.println("Score: " + Score);
                }

                m.zrusOvocie(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget());
                m.generujOvocia();
                zrusOvociaHad();
            }
            else {
                zivot--;
                if (m.jeOvocie(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget())== 4){
                    Score = Score - 40;
                    System.out.println("Score: " + Score);
                }
                System.out.println("Stratil si zivot");
                m.zrusOvocie(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget());
                m.generujOvocia();
                zrusOvociaHad();
            }
        }
    }

    public void resetHada(){
        this.hlava = new HadHlava(10,10, 'd');
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

    public void kontPrek(){
        if(m.jeStena(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget())
                || m.jePrekazka(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget())){
            smrtiacaProcedura();
        }
    }

    private ArrayList kopirujSmer(){
        ArrayList kopia = new ArrayList();
        for(Boolean bit : smer){
            kopia.add(bit);
        }
        return kopia;
    }
    private int kontKopieSmeru(){
        for(int i = 0; i < 4; i++){
            if(kopiaSmeru.get(i) != smer.get(i)){
                return 1;
            }
        }
    return 0;
    }

    private void smrtiacaProcedura(){
        m.zrusVsetkyOvocia();
        zivotMinus();
        resetHada();
        m.generujOvocia();
        zrusOvociaHad();
    }

    /**
     * Kontrola prechodu cez sameho seba(telo hada)
     */
    public void kontPrechoduCSB(char smer){
        if(smer == 'l') {
            for (CastHada cast : fifoCastiHada) {
                if (cast.poz.CastHadaXget() == hlava.poz.CastHadaXget()-1
                    && cast.poz.CastHadaYget() == hlava.poz.CastHadaYget()){
                    System.out.println("vlavo do seba");
                    smrtiacaProcedura();
                }
            }
        }
        if(smer == 'r') {
            for (CastHada cast : fifoCastiHada) {
                if (cast.poz.CastHadaXget() == hlava.poz.CastHadaXget()+1
                        && cast.poz.CastHadaYget() == hlava.poz.CastHadaYget()){
                    System.out.println("vpravo doseba");
                    smrtiacaProcedura();
                }
            }
        }
        if(smer == 'u') {
            for (CastHada cast : fifoCastiHada) {
                if (cast.poz.CastHadaXget() == hlava.poz.CastHadaXget()
                        && cast.poz.CastHadaYget() == hlava.poz.CastHadaYget()-1){
                    System.out.println("hore do seba");
                    smrtiacaProcedura();
                }
            }
        }
        if(smer == 'd') {
            for (CastHada cast : fifoCastiHada) {
                if (cast.poz.CastHadaXget() == hlava.poz.CastHadaXget()+1
                        && cast.poz.CastHadaYget() == hlava.poz.CastHadaYget()+1){
                    System.out.println("dole do seba");
                    smrtiacaProcedura();
                }
            }
        }
    }

    public void pohybHada(){
        kontrolaOvocia();
        if(smer.get(0)) {
            //System.out.println("stlacene dolava");
            kontPrechoduCSB('l');
            hlava.poz.CastHadaXset(hlava.poz.CastHadaXget()-1);
            if(kontKopieSmeru() == 1){
                if(hlava.orientacia == 'd'){
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget()+1,hlava.poz.CastHadaYget(),'h'));
                    System.out.println("otocil sa dolava ZMENA!!!");
                }
                else{
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget()+1,hlava.poz.CastHadaYget(),'l'));
                    System.out.println("otocil sa dolava");
                }
                kopiaSmeru = kopirujSmer();
            }
            else fifoCastiHada.add(new HadTelo(hlava.poz.CastHadaXget()+1,hlava.poz.CastHadaYget(),'l'));
            kontPrek();
            castLen = castLen+1;
            hlava.orientacia = 'l';
        }
        if(smer.get(1)) {
            //System.out.println("stlacene doprava");
            kontPrechoduCSB('r');
            hlava.poz.CastHadaXset(hlava.poz.CastHadaXget()+1);
            if(kontKopieSmeru() == 1){
                if(hlava.orientacia =='u'){
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget()-1,hlava.poz.CastHadaYget(),'g'));
                    System.out.println("otocil sa do prava ZMENA!!!");
                }
                else{
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget()-1,hlava.poz.CastHadaYget(),'r'));
                    System.out.println("otocil sa doprava");
                }
                kopiaSmeru = kopirujSmer();
            }
            else fifoCastiHada.add(new HadTelo(hlava.poz.CastHadaXget()-1,hlava.poz.CastHadaYget(),'r'));
            kontPrek();
            castLen = castLen+1;
            hlava.orientacia = 'r';
        }
        if(smer.get(2)) {
            //System.out.println("stlacene hore");
            kontPrechoduCSB('u');
            hlava.poz.CastHadaYset(hlava.poz.CastHadaYget()-1);
            if(kontKopieSmeru() == 1){
                if(hlava.orientacia == 'l'){
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()+1,'f'));
                    System.out.println("otocil sa hore ZMENA!!!");
                }
                else{
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()+1,'u'));
                    System.out.println("otocil sa hore");
                }
                kopiaSmeru = kopirujSmer();
            }
            else fifoCastiHada.add(new HadTelo(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()+1,'u'));
            kontPrek();
            castLen = castLen+1;
            hlava.orientacia = 'u';
        }
        if(smer.get(3)) {
            kontPrechoduCSB('d');
            //System.out.println("stlacene dole");
            hlava.poz.CastHadaYset(hlava.poz.CastHadaYget()+1);
            if(kontKopieSmeru() == 1){
                if(hlava.orientacia == 'r'){
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()-1,'e'));
                    System.out.println("otocil sa dole ZMENA!!!");
                }
                else{
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()-1,'d'));
                    System.out.println("otocil sa dole");
                }
                kopiaSmeru = kopirujSmer();
            }
            else fifoCastiHada.add(new HadTelo(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()-1,'d'));
            kontPrek();
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

    /**
     * Vracia zoznam castí hada aj s hlavou
     */
    public LinkedList<CastHada> hadieCasti(){
        LinkedList casti = new LinkedList();
        casti.add(this.hlava);
        for(CastHada c : fifoCastiHada){
            casti.add(c);
        }
        return casti;
    }

    public void zrusOvociaHad(){
        for(CastHada c : fifoCastiHada){
            if(m.jeOvocie(c.poz.CastHadaXget(),c.poz.CastHadaYget()) > 0){
                m.zrusOvocie(c.poz.CastHadaXget(),c.poz.CastHadaYget());
            }
        }
    }

}
