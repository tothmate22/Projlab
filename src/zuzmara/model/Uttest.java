package zuzmara.model;
import java.util.*;

public class Uttest {
    private List<List<Utszakasz>> savok = new ArrayList<>();

    public Uttest() {
    }

    // uthossz: egy savban hány szakasz legyen generálva
    // savszam: hány sav legyen generálva
    public Uttest(int uthossz, int savszam) {
        for (int i = 0; i < savszam; i++) {
            List<Utszakasz> sav = new ArrayList<>();
            for (int j = 0; j < uthossz; j++) {
                sav.add(new Utszakasz(this, null));
            }
            savok.add(sav);
        }
        Skeleton.nyit("Uttest <<create>>");
        Skeleton.zar("Uttest létrehozva");
    }

    public Uttest(List<List<Utszakasz>> savok) {
        this.savok = savok;
        Skeleton.nyit("Uttest <<create>>");
        Skeleton.zar("Uttest létrehozva");
    }


    public boolean balraSorolasLehetseges(Utszakasz jelenlegi) {
        Skeleton.nyit("Uttest.balraSorolasLehetseges()");
        Skeleton.zar("Uttest.balraSorolasLehetseges() visszater: " + true);
        return true;
    }

    public boolean jobbraSorolasLehetseges(Utszakasz jelenlegi) {
        Skeleton.nyit("Uttest.jobbraSorolasLehetseges()");
        Skeleton.zar("Uttest.jobbraSorolasLehetseges() visszater: " + true);
        return true;
    }
}
