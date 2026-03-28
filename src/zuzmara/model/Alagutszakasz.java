package zuzmara.model;

public class Alagutszakasz extends Utszakasz {
    public Alagutszakasz() {
        super();
        Skeleton.nyit("Alagutszakasz <<create>>");
        Skeleton.zar("Alagutszakasz létrehozva");   
    }

    public Alagutszakasz(Uttest szuloUttest) {
        super(szuloUttest, "URES");
        Skeleton.nyit("Alagutszakasz <<create>>");
        Skeleton.zar("Alagutszakasz létrehozva");
    }

    @Override
    public void idojarasFrissites() {
        Skeleton.nyit("Alagutszakasz.idojarasFrissites()");
        super.ho = 0;
        super.jeg = 0;
        super.havonAthaladt = 0;
        Skeleton.zar("Alagutszakasz.idojarasFrissites() visszater (ho: " + ho + ", jeg: " + jeg + ", havonAthaladt: " + havonAthaladt + ")");
    }
}
