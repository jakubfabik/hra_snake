package Had;

import GUI.KoniecHryObrazovka;
import Mapa.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class Had implements HadInterface {
    private HadHlava hlava;
    private Mapa m;
    private int[] skpp = {1,30,80,100,130,200,300,1000}; //skore pre portal, index 0 je poradie skora, od 1 su pozadovane skora
    private int skore = 0;
    private boolean zomrel = false;     //premenna sluzi aby had po smrti nevyliezol na prekazku
    private Smery smer;
    private Smery kopiaSmeru;
    private int zivot = 2;
    private int dlzka = 2;  // informaticky pocet len(3)
    private LinkedList<CastHada> fifoCastiHada = new LinkedList<CastHada>();
    private boolean koniec = false;
    private int castLen = 0;
    boolean jeBludisko = false;

    public Had(Mapa mapa){
        smer = Smery.DOLE; //zaciatocny smer je dole
        kopiaSmeru = Smery.DOLE;
        this.m = mapa;
        this.hlava = new HadHlava(10, 10, 'd');
        //poleHad.add(new HadHlava(10,10));
    }

    /**
     * Settery na smer
     * potrebujem mať settery ako public pre plátno
     */
    public void hore() {
        this.smer = Smery.HORE;
    }
    public void dole() {
        this.smer = Smery.DOLE;
    }
    public void doprava() {
        this.smer = Smery.DOPRAVA;
    }
    public void dolava() {
        this.smer = Smery.DOLAVA;
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
                    skore+=10;
                    System.out.println("Score: " + skore);
                }
                if (m.jeOvocie(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget()) == 2){
                    skore+=20;
                    System.out.println("Score: " + skore);
                }
                if (m.jeOvocie(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget()) == 3){
                    skore+=50;
                    System.out.println("Score: " + skore);
                }
                m.zrusOvocie(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget());
                zrusOvociaHad();
                if(!jeBludisko) {
                    m.generujOvocia();
                }
            }
            else {
                if (m.jeOvocie(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget())== 4){
                    if(skore-40 < 0){
                        skore = 0;
                    }else{
                        skore = skore - 40;
                    }
                }
                m.zrusOvocie(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget());
                zrusOvociaHad();
                if(!jeBludisko) {
                    m.generujOvocia();
                }
            }
        }
    }

    public void resetHada(){
        if(jeBludisko){
            m.zrusVsetkyOvocia();
            this.hlava = new HadHlava(1,1, 'd');
        }else {
            this.hlava = new HadHlava(10,10, 'd');
        }
        this.dlzka = 1;
        kontrolaKoncaHry();
        while(castLen !=0){
            fifoCastiHada.pop();
            castLen --;
        }
        zomrel = true;
    }


    public boolean kontrolaKoncaHry(){
        if(zivot < 0) {
            System.out.println("Koniec hry had zomrel!!!");
            zivotMinus();
            return true;
        }
        return false;
    }

    /**
     * Metóda bola vytvorená aby sa objekt z triedy plátno mohol správne
     * zachovať na koniec hry.
     */
    public void koniecHry() throws IOException {
        if(!koniec) {KoniecHryObrazovka obr = new KoniecHryObrazovka(skore);
            this.koniec = true;
        }
    }

    private void kontPrek(){
        if(m.jeStena(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget())
                || m.jePrekazka(hlava.poz.CastHadaXget(), hlava.poz.CastHadaYget())){
            smrtiacaProcedura();
        }
    }

    /**
     * Kópiu smeru používam na zistenie zmeny smeru a uchovanie informácie o poslednom smere.
     * Túto pomocnú metódu potrebujem pre správny pohyb hada v smere hod. ručičiek a opačnom.
     */
    private int kontKopieSmeru(){
        if(this.smer != this.kopiaSmeru){
            return 1;
        }
    return 0;
    }

    public int getSkore(){
        return this.skore;
    }

    /**
     * Rieši čo sa má udiať keď had narazí na prekážku.
     */
    public void smrtiacaProcedura(){
        m.zrusVsetkyOvocia();
        zivotMinus();
        resetHada();
        if(!jeBludisko) {
            m.generujOvocia();
            m.spawn();
        }
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

    private void kontrolaPoralu(){
        int dlzka = this.dlzka;
        if(m.jePortal(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget())){
            if(skore >= skpp[0] && !jeBludisko){
                jeBludisko = true;
                hlava.poz.CastHadaXset(1);
                hlava.poz.CastHadaYset(1);
                m.kresliBludisko();
            }else {
                jeBludisko = false;
                m.kresliPortal();
            }
            resetHada();
            this.dlzka = dlzka;
        }
    }

    /**
     * Urobí pohyb v smere, ktorý je uložený v enum premennej this.smer .
     */
    public void pohybHada(){
        if(getSkore() >= skpp[skpp[0]]){
            m.otvorPortal();        //zobraz portal
            skpp[0]++;              //zvysi limit na dosiahnutie otvorenia dalsieho
        }
        kontrolaOvocia();
        kontrolaPoralu();
        if(this.smer == Smery.DOLAVA) {
            //System.out.println("stlacene dolava");
            kontPrechoduCSB('l');
            hlava.poz.CastHadaXset(hlava.poz.CastHadaXget()-1);
            if(kontKopieSmeru() == 1){      //kontrola či nedošlo k zmene smeru oproti smeru posledného posunu
                //tu nastávajú iba dve možnosti pohybu buď v smere hodinových ručičiek alebo opačne
                if(hlava.orientacia == 'd'){
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget()+1,hlava.poz.CastHadaYget(),'h'));
                    //System.out.println("otocil sa dolava ZMENA!!!");
                }
                else{
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget()+1,hlava.poz.CastHadaYget(),'l'));
                    //System.out.println("otocil sa dolava");
                }
                this.kopiaSmeru = this.smer;
            }
            else fifoCastiHada.add(new HadTelo(hlava.poz.CastHadaXget()+1,hlava.poz.CastHadaYget(),'l'));
            kontPrek();
            castLen = castLen+1;
            hlava.orientacia = 'l';
        }
        if(this.smer == Smery.DOPRAVA) {
            //System.out.println("stlacene doprava");
            kontPrechoduCSB('r');
            hlava.poz.CastHadaXset(hlava.poz.CastHadaXget()+1);
            if(kontKopieSmeru() == 1){
                if(hlava.orientacia =='u'){
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget()-1,hlava.poz.CastHadaYget(),'g'));
                    //System.out.println("otocil sa do prava ZMENA!!!");
                }
                else{
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget()-1,hlava.poz.CastHadaYget(),'r'));
                    //System.out.println("otocil sa doprava");
                }
                this.kopiaSmeru = this.smer;
            }
            else fifoCastiHada.add(new HadTelo(hlava.poz.CastHadaXget()-1,hlava.poz.CastHadaYget(),'r'));
            kontPrek();
            castLen = castLen+1;
            hlava.orientacia = 'r';
        }
        if(this.smer == Smery.HORE) {
            //System.out.println("stlacene hore");
            kontPrechoduCSB('u');
            hlava.poz.CastHadaYset(hlava.poz.CastHadaYget()-1);
            if(kontKopieSmeru() == 1){
                if(hlava.orientacia == 'l'){
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()+1,'f'));
                    //System.out.println("otocil sa hore ZMENA!!!");
                }
                else{
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()+1,'u'));
                    //System.out.println("otocil sa hore");
                }
                this.kopiaSmeru = this.smer;
            }
            else fifoCastiHada.add(new HadTelo(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()+1,'u'));
            kontPrek();
            castLen = castLen+1;
            hlava.orientacia = 'u';
        }
        if(this.smer == Smery.DOLE) {
            kontPrechoduCSB('d');
            //System.out.println("stlacene dole");
            hlava.poz.CastHadaYset(hlava.poz.CastHadaYget()+1);
            if(kontKopieSmeru() == 1){
                if(hlava.orientacia == 'r'){
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()-1,'e'));
                    //System.out.println("otocil sa dole ZMENA!!!");
                }
                else{
                    fifoCastiHada.add(new HadObluk(hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget()-1,'d'));
                    //System.out.println("otocil sa dole");
                }
                this.kopiaSmeru = this.smer;
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
        if(zomrel == true){     // FIX bez tohto posledna cast hada zostane po smrti chvilu na ploche
            dlzkaPlus();
            zomrel = false;
        }
    }

    /**
     * Pomocná metóda ktorá rozlišuje medzi ukončeniami hada, oblúk alebo telo.
     */
    private CastHada koniecHada(CastHada item){
        if(item instanceof HadTelo){
            return new HadTeloKoniec(item.poz.CastHadaXget(), item.poz.CastHadaYget(),item.orientaciaCasti());
        }
        else if(item instanceof HadObluk){
            return new HadOblukKoniec(item.poz.CastHadaXget(), item.poz.CastHadaYget(),item.orientaciaCasti());
        }
        else return item;
    }

    /**
     * Pripaja graficky adapter na vykreslenie casti hada.
     */
    public void kresli(Graphics g){
        int c = 0;
        hlava.obr(g,hlava.poz.CastHadaXget(),hlava.poz.CastHadaYget(),hlava.orientaciaCasti());
        for (CastHada item : fifoCastiHada) {
            c++;
            if(c == 1){   //posledny clanok v hadovi
                //System.out.println(item.getClass().getName());
                koniecHada(item);
                CastHada koniec = koniecHada(item);
                koniec.obr(g, koniec.poz.CastHadaXget(), koniec.poz.CastHadaYget(), item.orientaciaCasti());
            }
            else {
                item.obr(g, item.poz.CastHadaXget(), item.poz.CastHadaYget(), item.orientaciaCasti());
            }
        }
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

    /**
     * Pomocna funkcia pre triedu mapa
     */
    public void zrusOvociaHad(){
        for(CastHada c : fifoCastiHada){
            if(m.jeOvocie(c.poz.CastHadaXget(),c.poz.CastHadaYget()) > 0){
                m.zrusOvocie(c.poz.CastHadaXget(),c.poz.CastHadaYget());
            }
        }
    }
}
