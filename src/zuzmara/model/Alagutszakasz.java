package zuzmara.model;


/**
 * Az Alagutszakasz osztály egy speciális típusú Utszakasz, amely alagutat reprezentál az úttesten.
 * Felelősség: az osztály biztosítja, hogy az alagútszakaszon mindig hó és jég nélküli időjárási viszonyok legyenek.
 */
public class Alagutszakasz extends Utszakasz {

    /**
     * Alapértelmezett konstruktor.
     */
    public Alagutszakasz() {
        super();
        Skeleton.nyit("Alagutszakasz <<create>>");
        Skeleton.zar("Alagutszakasz létrehozva");   
    }

    /**
     * Konstruktor.
     * @param szuloUttest az a Uttest, amelyhez ez az Utszakasz tartozik
     */
    public Alagutszakasz(Uttest szuloUttest) {
        super(szuloUttest, "URES");
        Skeleton.nyit("Alagutszakasz <<create>>");
        Skeleton.zar("Alagutszakasz létrehozva");
    }

    /**
     * Hó és jég nélküli időjárási viszonyokat biztosít az alagútszakaszon.
     */
    @Override
    public void idojarasFrissites() {
        Skeleton.nyit("Alagutszakasz.idojarasFrissites()");
        super.ho = 0;
        super.jeg = 0;
        super.havonAthaladt = 0;
        Skeleton.zar("Alagutszakasz.idojarasFrissites() visszater (ho: " + ho + ", jeg: " + jeg + ", havonAthaladt: " + havonAthaladt + ")");
    }
}
