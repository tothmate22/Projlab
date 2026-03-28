package zuzmara.model;

/**
 * A jatekosok (peldaul a buszvezeto vagy a takarito) kozos, absztrakt ososztalya. 
 * O tarolja a megszerzett vagyont (egyenleget) es biztositja a penzmozgas alapveto logikajat.
 */
public abstract class Jatekos {

    /**
     * A jatekos azonosito neve.
     */
    protected String nev;

    /**
     * A jatekos altal osszegyujtott vagyon. Kezdeti erteke 0. A cel az 1500 elerese.
     */
    protected int egyenleg;

    /**
     * A Jatekos konstruktora.
     * Beallitja a nevet es az alapertelmezett 0 egyenleget.
     * @param nev A jatekos neve
     */
    public Jatekos(String nev) {
        this.nev = nev;
        this.egyenleg = 0;
        // Itt sincs Skeleton.nyit/zar, hogy a Buszvezeto <<create>> hivasa 
        // egyertelmubb es tisztabb maradjon a konzolon.
    }

    /**
     * Noveli az egyenleg attributum erteket a kapott osszeggel.
     * @param osszeg A hozzaadando penzmennyiseg
     */
    public void penztKap(int osszeg) {
        Skeleton.getInstance().nyit("Jatekos.penztKap(" + osszeg + ")");
        
        this.egyenleg += osszeg;
        System.out.println("    [A jatekos kapott " + osszeg + " penzt. Uj egyenleg: " + this.egyenleg + "]");
        
        Skeleton.getInstance().zar("Jatekos.penztKap() visszater");
    }

    /**
     * Visszaadja a jatekos aktualis vagyonat.
     * @return A jatekos egyenlege
     */
    public int getEgyenleg() {
        Skeleton.getInstance().nyit("Jatekos.getEgyenleg()");
        Skeleton.getInstance().zar("Jatekos.getEgyenleg() -> " + this.egyenleg);
        return this.egyenleg;
    }
}
