package zuzmara.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    /** A játékosok azonosító szerinti tárolása. */
    private Map<String, Jatekos> jatekosok;

    /** A hókotróik azonosító szerinti tárolása. */
    private Map<String, Hokotro> hokotrok;

    /** A fejek azonosító szerinti tárolása. */
    private Map<String, Fej> fejek;

    /** Az automatentők azonosító szerinti tárolása. */
    private Map<String, Automento> automatentok;

    /** Véletlenszám generátor. */
    private Random random;

    /**
     * Létrehoz egy üres pályát.
     */
    public Palya() {
        this.utszakaszok = new HashMap<>();
        this.jarmuvek = new HashMap<>();
        this.keresztezodesek = new HashMap<>();
        this.uttestek = new HashMap<>();
        this.jatekosok = new HashMap<>();
        this.hokotrok = new HashMap<>();
        this.fejek = new HashMap<>();
        this.automatentok = new HashMap<>();
        this.random = null;
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

    /**
     * Hozzáad egy játékost a pályához.
     *
     * @param id a játékos azonosítója
     * @param j az eltárolandó játékos
     */
    public void addJatekos(String id, Jatekos j) {
        jatekosok.put(id, j);
    }

    /**
     * Visszaad egy játékost azonosító alapján.
     *
     * @param id a játékos azonosítója
     * @return a keresett játékos, vagy null, ha nincs ilyen
     */
    public Jatekos getJatekos(String id) {
        return jatekosok.get(id);
    }

    /**
     * Hozzáad egy hókotró járművet a pályához.
     *
     * @param id a hókotró azonosítója
     * @param h az eltárolandó hókotró
     */
    public void addHokotro(String id, Hokotro h) {
        hokotrok.put(id, h);
    }

    /**
     * Visszaad egy hókotró járművet azonosító alapján.
     *
     * @param id a hókotró azonosítója
     * @return a keresett hókotró, vagy null, ha nincs ilyen
     */
    public Hokotro getHokotro(String id) {
        return hokotrok.get(id);
    }

    /**
     * Hozzáad egy fejat a pályához.
     *
     * @param id a fej azonosítója
     * @param f az eltárolandó fej
     */
    public void addFej(String id, Fej f) {
        fejek.put(id, f);
    }

    /**
     * Visszaad egy fejat azonosító alapján.
     *
     * @param id a fej azonosítója
     * @return a keresett fej, vagy null, ha nincs ilyen
     */
    public Fej getFej(String id) {
        return fejek.get(id);
    }

    /**
     * Hozzáad egy úttestet a pályához.
     *
     * @param id az úttest azonosítója
     * @param u az eltárolandó úttest
     */
    public void addUttest(String id, Uttest u) {
        uttestek.put(id, u);
    }

    /**
     * Visszaad egy úttestet azonosító alapján.
     *
     * @param id az úttest azonosítója
     * @return a keresett úttest, vagy null, ha nincs ilyen
     */
    public Uttest getUttest(String id) {
        return uttestek.get(id);
    }

    /**
     * Hozzáad egy kereszteződést a pályához.
     *
     * @param id a kereszteződés azonosítója
     * @param k az eltárolandó kereszteződés
     */
    public void addKeresztezodes(String id, Keresztezodes k) {
        keresztezodesek.put(id, k);
    }

    /**
     * Visszaad egy kereszteződést azonosító alapján.
     *
     * @param id a kereszteződés azonosítója
     * @return a keresett kereszteződés, vagy null, ha nincs ilyen
     */
    public Keresztezodes getKeresztezodes(String id) {
        return keresztezodesek.get(id);
    }

    /**
     * Hozzáad egy automatentőt a pályához.
     *
     * @param id az automatento azonosítója
     * @param am az eltárolandó automatento
     */
    public void addAutomento(String id, Automento am) {
        automatentok.put(id, am);
    }

    /**
     * Visszaad egy automatentőt azonosító alapján.
     *
     * @param id az automatento azonosítója
     * @return a keresett automatento, vagy null, ha nincs ilyen
     */
    public Automento getAutomento(String id) {
        return automatentok.get(id);
    }

    /**
     * Beállítja a véletlenszám generátort.
     *
     * @param random a használandó Random objektum
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     * Visszaadja a véletlenszám generátort.
     *
     * @return a Random objektum, vagy null, ha nincs beállítva
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Visszaad egy objektumot név alapján, amely implementálja az IInfos interfészt.
     * Ez lehet útszakasz, jármű, játékos, vagy más info-szolgáltató objektum.
     *
     * @param nev az objektum neve
     * @return az IInfos interfészt implementáló objektum, vagy null, ha nincs ilyen
     */
    public IInfos getInfos(String nev) {
        // Próbáljuk meg úszakaszként keresni
        Utszakasz u = getUtszakasz(nev);
        if (u != null) return u;

        // Próbáljuk meg járműként keresni
        Jarmu j = getJarmu(nev);
        if (j != null && j instanceof IInfos) return (IInfos) j;

        // Próbáljuk meg játékosként keresni
        Jatekos jat = getJatekos(nev);
        if (jat != null && jat instanceof IInfos) return (IInfos) jat;

        // Próbáljuk meg hókotróként keresni
        Hokotro h = getHokotro(nev);
        if (h != null && h instanceof IInfos) return (IInfos) h;

        return null;
    }
