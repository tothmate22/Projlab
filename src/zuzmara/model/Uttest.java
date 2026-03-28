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
    }

    public Uttest(List<List<Utszakasz>> savok) {
        this.savok = savok;
    }


    public boolean balraSorolasLehetseges(Utszakasz jelenlegi) {
        return false;
    }

    public boolean jobbraSorolasLehetseges(Utszakasz jelenlegi) {
        return false;
    }
}
