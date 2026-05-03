/**
 * Az Alagutszakasz osztály egy speciális típusú Utszakasz, amely alagutat reprezentál az úttesten.
 * Felelősség: az osztály biztosítja, hogy az alagútszakaszon mindig hó és jég nélküli időjárási viszonyok legyenek.
 */
public class Alagutszakasz extends Utszakasz {

        /**
        * Konstruktor.
        * @param szuloUttest az a Uttest, amelyhez ez az Utszakasz tartozik
        * @param epuletTipus az épület típusa, amely az útszakasz mentén van ("MEGALLO", "HAZ", "MUNKAHELY", "BAZIS", "URES")  
        * @param id az útszakasz egyedi azonosítója
        */
    public Alagutszakasz(Uttest szuloUttest, String epuletTipus, String id) {
        super(szuloUttest, epuletTipus, id); 
    }

    /**
     * Hó és jég nélküli időjárási viszonyokat biztosít az alagútszakaszon.
     */
    @Override
    public void idojarasFrissites(Ora ora) {
        super.ho = 0;
        super.jeg = 0;
    }

    @Override
    public String getInfo() {
        return (id + " alagútszakasz adatai: " + 
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
