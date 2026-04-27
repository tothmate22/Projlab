package zuzmara.model;

/**
 * A Hidszakasz osztály egy speciális típusú Utszakasz, amely hidat reprezentál az úttesten.
 */
public class Hidszakasz extends Utszakasz {

    /**
     * Konstruktor.
     * @param szuloUttest
     * @param epuletTipus
     * @param id
     */
    public Hidszakasz(Uttest szuloUttest, String epuletTipus, String id) {
        super(szuloUttest, epuletTipus, id);
        
    }


}