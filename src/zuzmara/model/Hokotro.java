package zuzmara.model;

import java.util.ArrayList;
import java.util.LinkedList;
//import zuzmara.model.fejek.*;
import java.util.List;

import zuzmara.model.fejek.Sarkanyfej;
import zuzmara.model.fejek.SoproFej;
import zuzmara.model.Takarito;

/**
 * A hókotró osztály felelős a hókotró megvalósításáért
 * A játékos irányítja, az irányítást a Jarmu osztály valósítja meg
 * A Jarmu osztályból származik, így a Jarmu osztályban megvalósított metódusokat használja
 * Tartoznak hozzá fejek, pénzt keres és vásárolni tud, valamint ezeket a fejeket használni
 */
public class Hokotro extends Jarmu{
    private LinkedList<Fej> fejek; //A hókotróhoz tartozó fejek listája
    private int eletero;    //A hókotró életereje
    private Fej aktualisFej; //Az aktuálisan használt fej
    private Takarito takarito; //A hókotróhoz tartozó takarító
    
    /**
     * Konstruktor, létrehozza a hókotró objektumot
     * alapértelmezetten egy söprőfejjel indul, és 300 életerővel, 
     * a fejek listáját is létrehozza és hozzáadja az első fejet
     */
    public Hokotro() {
        super(null);
        Skeleton.getInstance().nyit("Hokotro <<create>>");
        this.fejek = new LinkedList<>();
        fejek.add(new SoproFej());
        this.eletero = 300;
        this.aktualisFej = fejek.get(0);
        Skeleton.getInstance().zar("Hokotro letrejott.");
    }

    /**
     * fejvásárlás után ez adja hozzá a listához a fejet
     * @param fej a hozzáadni kívánt fej
     */
    public void addFej(Fej fej){
        if(fej!=null && !fejek.contains(fej)){
            fejek.addLast(fej);
        }
    }

    /**
     * ha elfogyott egy fej életereje, ezzel a metódussal távolítja el a listából
     * @param fej a eltávolítani kívánt fej
     */
    public void removeFej(Fej fej){
        Skeleton.nyit("Hokotro.removeFej(sf1)");
        if(fejek.contains(fej)){
            fejek.remove(fej);
        }
        Skeleton.zar("Hokotro.removeFej() visszater");
    }

    /**
     * A hókotró kopását megvalósító metódus
     */
    public void kopas(){
        Skeleton.nyit("Hokotro.kopas()");
        this.eletero--;
        Skeleton.zar("Hokotro.kopas() visszater");
    }

    /**
     * minden letakarított útszakaszra az adott pénzmennyiséget rendel a játékoshoz
     */
    public void penztKeres(){
        Skeleton.nyit("Hokotro.penztKeres()");
        takarito.penztKap(50);
        Skeleton.zar("Hokotro.penztKeres() visszater");
    }

    /**
     * Vásárlást megvalósító metódus
     */
    public void vasarol(){}

    /**
     * vált a listában következő fejre
     */
    public void cserelFej(){
        Skeleton.nyit("Hokotro.cserelFej()");
        aktualisFej = fejek.get(fejek.indexOf(aktualisFej)+1);
        Skeleton.zar("Hokotro.cserelFej() visszater");
    }

    /**
     * jelenlegi fej beállítása
     * @param sf1
     */
    public void setAktualisFej(Sarkanyfej sf1) {
        this.aktualisFej = sf1;
    }

    public void takarit(Utszakasz cel) {
        Skeleton.nyit("Hokotro.takarit(Utszakasz)");
        if (aktualisFej != null) {
            aktualisFej.takarit(cel);
            if (aktualisFej.getEletero() <= 0) {
                removeFej(aktualisFej);
                cserelFej();
            }
        }
        Skeleton.zar("Hokotro.takarit() visszater");
    }

    /**
     * A hokotro halad a megadott utszakasz fele.
     * Ha a celszakasz szabad, raleephet es takarit.
     */
    @Override
    public void halad(Utszakasz cel) {
        Skeleton.getInstance().nyit("Hokotro.halad(u1)");
        boolean belephet = this.pozicio.jarmutElore(cel);
        if (belephet) {
            cel.belep(this);
            if (aktualisFej != null) {
                takarit(cel);
            }
            kopas();
            penztKeres();
        }
        Skeleton.getInstance().zar("Hokotro.halad() visszater");
    }

    public void setTakarito(Takarito takarito) {
        this.takarito = takarito;
    }
}
