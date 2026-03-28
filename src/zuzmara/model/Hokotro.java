package zuzmara.model;

import java.util.ArrayList;
import java.util.LinkedList;
//import zuzmara.model.fejek.*;
import java.util.List;

import zuzmara.model.fejek.Sarkanyfej;
import zuzmara.model.fejek.SoproFej;

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

    
    /**
     * Konstruktor, létrehozza a hókotró objektumot
     * alapértelmezetten egy söprőfejjel indul, és 300 életerővel, 
     * a fejek listáját is létrehozza és hozzáadja az első fejet
     */
    public Hokotro() {
        this.fejek = new LinkedList<>();
        fejek.add(new SoproFej());
        this.eletero = 300;
        this.aktualisFej = fejek.get(0);
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
        if(fejek.contains(fej)){
            fejek.remove(fej);
        }
    }

    /**
     * A hókotró kopását megvalósító metódus
     */
    public void kopas(){}

    /**
     * minden letakarított útszakaszra az adott pénzmennyiséget rendel a játékoshoz
     */
    public void penztKeres(){}

    /**
     * Vásárlást megvalósító metódus
     */
    public void vasarol(){}

    /**
     * vált a listában következő fejre
     */
    public void cserelFej(){
        aktualisFej = fejek.get(fejek.indexOf(aktualisFej)+1);
    }

    /**
     * jelenlegi fej beállítása
     * @param sf1
     */
    public void setAktualisFej(Sarkanyfej sf1) {
        this.aktualisFej = sf1;
    }
}
