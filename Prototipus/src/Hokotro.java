

import java.util.ArrayList;
import java.util.LinkedList;
//import zuzmara.model.fejek.*;
import java.util.List;

/**
 * A hókotró osztály felelős a hókotró megvalósításáért
 * A játékos irányítja, az irányítást a Jarmu osztály valósítja meg
 * A Jarmu osztályból származik, így a Jarmu osztályban megvalósított metódusokat használja
 * Tartoznak hozzá fejek, pénzt keres és vásárolni tud, valamint ezeket a fejeket használni
 */
public class Hokotro extends Jarmu implements ILepheto, IInfo{
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
        this.fejek = new LinkedList<>();
        fejek.add(new SoproFej("Alap SoproFej"));
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
            if(aktualisFej == null){
                aktualisFej = fej;
            }
        }
    }

    /**
     * ha elfogyott egy fej életereje, ezzel a metódussal távolítja el a listából
     * @param fej a eltávolítani kívánt fej
     */
    public void removeFej(Fej fej){
        if(fejek.contains(fej)){
            fejek.remove(fej);
            if(aktualisFej == fej){
                aktualisFej = fejek.isEmpty() ? null : fejek.getFirst();
            }
        }
    }


    /**
     * vált a listában következő fejre
     */
    public void cserelFej(){
        if(fejek.isEmpty()){
            aktualisFej = null;
            return;
        }
        int currentIndex = fejek.indexOf(aktualisFej);
        int nextIndex = (currentIndex + 1) % fejek.size();
        aktualisFej = fejek.get(nextIndex);
    }

    /**
     * A hókotró kopását megvalósító metódus
     */
    public void kopas(){
        this.eletero--;
        if(aktualisFej != null){
            aktualisFej.kopas();
        }
    }

    /**
     * minden letakarított útszakaszra az adott pénzmennyiséget rendel a játékoshoz
     */
    public void penztKeres(){
        if(takarito != null){
            takarito.penztKap(50);
        }
    }

    /**
     * Meghívja az aktualis fej takarito metodusat
     * @param cel a letakaritando utszakasz
     */
    public void takarit(Utszakasz cel) {
        if (aktualisFej != null) {
            aktualisFej.takarit(cel);
            if (aktualisFej.getEletero() <= 0) {
                removeFej(aktualisFej);
                cserelFej();
            }
        }
    }


    /**
     * jelenlegi fej beállítása
     * @param sf1
     */
    public void setAktualisFej(Fej sf1) {
        this.aktualisFej = sf1;
    }

    @Override
    public Jarmu getJarmu() {
        return this;
    }

    /**
     * A hokotro halad a megadott utszakasz fele.
     * Ha a celszakasz szabad, raleephet es takarit.
     */
    @Override
    public void halad(Utszakasz cel) {
        
        boolean belephet = this.pozicio.jarmutElore(cel);
        if (belephet) {
            boolean voltSzennyezodes = cel.ho > 0 || cel.jeg > 0;
            if (aktualisFej != null) {
                takarit(cel);
            }
            kopas();
            if (voltSzennyezodes) {
                penztKeres();
            }
        }
    }

    public String getInfo(){
        String fejNev = (aktualisFej != null) ? aktualisFej.getClass().getSimpleName() : "Nincs fej";
        return "Hókotró adatai: pozicio=" + (pozicio != null ? pozicio.getId() : "null") +
               ", eletero=" + eletero +
               ", aktualisFej=" + fejNev +
               ", fejek szama=" + fejek.size();
    }

    public void setTakarito(Takarito takarito) {
        this.takarito = takarito;
    }

    /**
     * Visszaadja a hókotróhoz tartozó takarítót.
     * * @return A takarító, vagy null ha nincs.
     */
    public Takarito getTakarito() {
        return this.takarito;
    }

    @Override
    public void idoEltelt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'idoEltelt'");
    }
}
