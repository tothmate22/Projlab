package zuzmara.model;
import java.util.*;

/**
 * Az Uttest osztály egy úttestet reprezentál, amely több savból is állhat.
 * Felelősség: az osztály tárolja az úttest szakaszait, és biztosítja a járművek mozgatását az úttesten, valamint a sávváltás lehetőségét. Az Uttest osztály felelős a járművek helyzetének frissítéséért és a sávváltás logikájáért is.
 */
public class Uttest {
    private List<List<Utszakasz>> savok = new ArrayList<>();

    /**
     * Alapértelmezett konstruktor, amely létrehozza az üres úttestet.
     */
    public Uttest() {
        Skeleton.nyit("Uttest <<create>>");
        Skeleton.zar("Uttest létrehozva");
    }
    /**
     * Konstruktor, amely létrehozza az úttestet a megadott paraméterek alapján.
     * @param uthossz egy savban hány szakasz legyen generálva
     * @param savszam hány sav legyen generálva
     */
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

    /**
     * Konstruktor, amely létrehozza az úttestet a megadott szakaszokkal.
     * @param savok az úttest szakaszait tartalmazó lista, ahol minden belső lista egy savot reprezentál
     */
    public Uttest(List<List<Utszakasz>> savok) {
        this.savok = savok;
        Skeleton.nyit("Uttest <<create>>");
        Skeleton.zar("Uttest létrehozva");
    }

    /**
     * Megpróbálja a járművet jobbra vinni az útszakaszon.
     * @param jelenlegi az a Utszakasz, amelyen a jármű jelenleg tartózkodik
     * @return true, ha a jármű sikeresen jobbra haladt, false egyébként
     */
    public boolean balraSorolasLehetseges(Utszakasz jelenlegi) {
        Skeleton.nyit("Uttest.balraSorolasLehetseges()");
        Skeleton.zar("Uttest.balraSorolasLehetseges() visszater: " + true);
        return true;
    }

    /**
     * Megpróbálja a járművet jobbra vinni az útszakaszon.
     * @param jelenlegi az a Utszakasz, amelyen a jármű jelenleg tartózkodik
     * @return true, ha a jármű sikeresen jobbra haladt, false egyébként
     */
    public boolean jobbraSorolasLehetseges(Utszakasz jelenlegi) {
        Skeleton.nyit("Uttest.jobbraSorolasLehetseges()");
        Skeleton.zar("Uttest.jobbraSorolasLehetseges() visszater: " + true);
        return true;
    }

    /**
     * Megpróbálja a járművet előre vinni az útszakaszon.
     * @param jelenlegi az az Utszakasz, amelyen a jármű jelenleg tartózkodik
     * @return true, ha a jármű sikeresen előre haladt, false egyébként
     */
    public boolean jarmutElore(Utszakasz jelenlegi) {
        Skeleton.nyit("Uttest.jarmutElore()");
        Skeleton.zar("Uttest.jarmutElore() visszater: " + true);
        return true;
    }
}
