package zuzmara.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A Palya osztály tárolja a játékban szereplő útszakaszokat és járműveket.
 * Nem kezel bemenetet és nem futtat játéklogikát, csak az objektumok
 * nyilvántartásáért és név alapján történő visszakereséséért felelős.
 */
public class Palya {

    /** A kereszteződések azonosító szerinti tárolása*/
    private Map<String, Keresztezodes> keresztezodesek;

    /** Az úttestek azonosító szerinti tárolása*/
    private Map<String, Uttest> uttestek;

    /** Az útszakaszok azonosító szerinti tárolása. */
    private Map<String, Utszakasz> utszakaszok;

    /** A járművek azonosító szerinti tárolása. */
    private Map<String, Jarmu> jarmuvek;

    /**
     * Létrehoz egy üres pályát.
     */
    public Palya() {
        this.utszakaszok = new HashMap<>();
        this.jarmuvek = new HashMap<>();
        this.keresztezodesek = new HashMap<>();
        this.uttestek = new HashMap<>();
    }

    /**
     * Hozzáad egy útszakaszt a pályához.
     *
     * @param id az útszakasz azonosítója
     * @param u az eltárolandó útszakasz
     */
    public void addUtszakasz(String id, Utszakasz u) {
        utszakaszok.put(id, u);
    }

    /**
     * Visszaad egy útszakaszt azonosító alapján.
     *
     * @param id az útszakasz azonosítója
     * @return a keresett útszakasz, vagy null, ha nincs ilyen
     */
    public Utszakasz getUtszakasz(String id) {
        return utszakaszok.get(id);
    }

    /**
     * Hozzáad egy járművet a pályához.
     *
     * @param id a jármű azonosítója
     * @param j az eltárolandó jármű
     */
    public void addJarmu(String id, Jarmu j) {
        jarmuvek.put(id, j);
    }

    /**
     * Visszaad egy járművet azonosító alapján.
     *
     * @param id a jármű azonosítója
     * @return a keresett jármű, vagy null, ha nincs ilyen
     */
    public Jarmu getJarmu(String id) {
        return jarmuvek.get(id);
    }
}
