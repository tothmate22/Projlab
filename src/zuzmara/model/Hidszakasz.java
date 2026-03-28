package zuzmara.model;

/**
 * A Hidszakasz osztály egy speciális típusú Utszakasz, amely hidat reprezentál az úttesten.
 */
public class Hidszakasz extends Utszakasz {

    /**
     * Alapértelmezett konstruktor.
     */
    public Hidszakasz() {
        super();
        Skeleton.nyit("Hidszakasz <<create>>");
        Skeleton.zar("Hidszakasz létrehozva");  
    }

    /**
     * Konstruktor.
     * @param szuloUttest az a Uttest, amelyhez ez az Utszakasz tartozik
     */
    public Hidszakasz(Uttest szuloUttest) {
        super(szuloUttest, "URES");
        Skeleton.nyit("Hidszakasz <<create>>");
        Skeleton.zar("Hidszakasz létrehozva");
    }


}