

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

    @Override
    public String getInfo() {
        return (id + " hidszakasz adatai: " + 
            "\n hó=" + ho + 
            "\n jég=" + jeg + 
            "\n zúzottköves=" + zuzottKo + 
            "\n havon áthaladt=" + havonAthaladt + 
            "\n közlekedő jármű=" + (kozlekedoJarmu != null ? kozlekedoJarmu.getId() : "nincs") + 
            "\n félrehúzódott jármű=" + (felrehuzodottJarmu != null ? felrehuzodottJarmu.getId() : "nincs") + 
            "\n szülő úttest=" + szuloUttest.getId() + 
            "\n épület=" + epulet.toString());
    }

}