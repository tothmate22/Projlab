package zuzmara.model;

public class Hidszakasz extends Utszakasz {
    public Hidszakasz() {
        super();
        Skeleton.nyit("Hidszakasz <<create>>");
        Skeleton.zar("Hidszakasz létrehozva");  
    }

    public Hidszakasz(Uttest szuloUttest) {
        super(szuloUttest, "URES");
        Skeleton.nyit("Hidszakasz <<create>>");
        Skeleton.zar("Hidszakasz létrehozva");
    }


}