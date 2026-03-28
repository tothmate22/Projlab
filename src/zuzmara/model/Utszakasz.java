package zuzmara.model;

import zuzmara.enums.Epulet;

/**
 * Az Utszakasz osztály egy útszakaszt reprezentál az úttesten, amelyen járművek közlekedhetnek. 
 * Felelősség: az osztály tárolja a hó és jég mennyiségét, a rajta közlekedő járművet, valamint az épület típusát, amely az útszakasz mentén található. 
 * Az Utszakasz osztály felelős a járművek mozgatásáért és az időjárási viszonyok frissítéséért is.
 */
public class Utszakasz {
    protected int ho;
    protected int jeg;
    protected int havonAthaladt;
    protected Jarmu kozlekedoJarmu;
    protected Jarmu felrehuzodottJarmu;
    protected Uttest szuloUttest;
    protected Epulet epulet;

    /**
     * Alapértelmezett konstruktor.
     */
    public Utszakasz() {
        ho = 0;
        jeg = 0;
        havonAthaladt = 0;
        kozlekedoJarmu = null;
        felrehuzodottJarmu = null;
        szuloUttest = null;
        epulet = null;
        Skeleton.nyit("Utszakasz <<create>>");
        Skeleton.zar("Utszakasz létrehozva");
    }
    
    /**
     * Konstruktor.
     * @param szuloUttest az a Uttest, amelyhez ez az Utszakasz tartozik
     * @param epuletTipus az épület típusa, amely az útszakasz mentén van ("MEGALLO", "HAZ", "MUNKAHELY", "BAZIS", "URES")    
    */
    public Utszakasz(Uttest szuloUttest, String epuletTipus) {
        ho = 0;
        jeg = 0;
        havonAthaladt = 0;
        kozlekedoJarmu = null;
        felrehuzodottJarmu = null;
        this.szuloUttest = szuloUttest;
        this.epulet = Epulet.valueOf(epuletTipus);
        Skeleton.nyit("Utszakasz <<create>>");
        Skeleton.zar("Utszakasz létrehozva");
    }

    /**
     * Megpróbálja az előtte lévő útszakaszra vinni a járművet az útszakaszon.
     * @return true, ha a jármű sikeresen előre haladt, false egyébként
     */
    public boolean jarmutElore() {
        Skeleton.nyit("Utszakasz.jarmutElore()");
        // Jármű előre haladása logikája
        Skeleton.zar("Utszakasz.jarmutElore() visszater: " + true);

        return true;
    }

    /**
     * Megpróbálja a járművet jobbra vinni az útszakaszon.
     * @return true, ha a jármű sikeresen jobbra változott, false egyébként
     */
    public boolean jarmuSavotvalt_jobbra() {
        Skeleton.nyit("Utszakasz.jarmuSavotvalt_jobbra()");
        // Jármű savváltása jobbra logikája
        Skeleton.zar("Utszakasz.jarmuSavotvalt_jobbra() visszater: " + false);

        return false;
    }

    /**
     * Megpróbálja a járművet balra vinni az útszakaszon.
     * @return true, ha a jármű sikeresen balra változott, false egyébként
     */
    public boolean jarmuSavotvalt_balra() {
        Skeleton.nyit("Utszakasz.jarmuSavotvalt_balra()");
        // Jármű savváltása balra logikája
        Skeleton.zar("Utszakasz.jarmuSavotvalt_balra() visszater: " + false);

        return false;
    }

    /**
     * Egy jármű belép az útszakaszra.
     * @param jarmu a jármű, amely belep az útszakaszra
     */
    public void belep(Jarmu jarmu) {
        Skeleton.nyit("Utszakasz.belep()");
        kozlekedoJarmu = jarmu;
        if (ho > 0) {
            havonAthaladt++;
        }
        Skeleton.zar("Utszakasz.belep() végrehajtva");
    }

    /**
     * Frissíti az időjárási viszonyokat az útszakaszon.
     */
    public void idojarasFrissites() {
        Skeleton.nyit("Utszakasz.idojarasFrissites()");

        ho += 10;
        if (havonAthaladt >= 15) {
            jeg += (int) Math.floor(ho / 5);
            havonAthaladt = 0;
            ho = 0;
        }
                
        Skeleton.zar("Utszakasz.idojarasFrissites() visszater (ho: " + ho + ", jeg: " + jeg + ", havonAthaladt: " + havonAthaladt + ")");

    }

    /**
     * Megnézi, hogy van-e jármű az útszakaszon.
     * @return true, ha van jármű az útszakaszon, false egyébként
     */
    public boolean foglaltE() {
        Skeleton.nyit("Utszakasz.foglaltE()");
        Skeleton.zar("Utszakasz.foglaltE() visszater: " + (kozlekedoJarmu != null));
        return kozlekedoJarmu != null;
    }

    /**
     * Letakarítja az útszakaszt, eltávolítva a havat és a jeget.
     */
    public void letakaritas() {
        Skeleton.nyit("Utszakasz.letakaritas()");
        // Letakarítás logikája
        Skeleton.zar("Utszakasz.letakaritas() végrehajtva");
    }

    /**
     * Megnézi, hogy milyen típusú épület van az útszakasz mentén.
     * @return az épület típusa
     */
    public Epulet getEpulet() {
        Skeleton.nyit("Utszakasz.getEpulet()");
        Skeleton.zar("Utszakasz.getEpulet() visszater: " + epulet);
        return epulet;
    }

    /**
     * Megnézi, hogy mennyi hó van az útszakaszon.
     * @return a hó mennyisége centiméterben
     */
    public int getHo() {
        Skeleton.nyit("Utszakasz.getHo()");
        Skeleton.zar("Utszakasz.getHo() visszater: " + ho);
        return ho;
    }

    /**
     * Beállítja a hó mennyiségét az útszakaszon.
     * @param ho a hó mennyisége centiméterben
     */
    public void setHo(int ho) {
        Skeleton.nyit("Utszakasz.setHo()");
        Skeleton.zar("Utszakasz.setHo() végrehajtva");
        this.ho = ho;
    }   

    /**
     * Megnézi, hogy melyik jármű közlekedik az útszakaszon.
     * @return a jármű, amely közlekedik az útszakaszon, vagy null, ha nincs jármű
     */
    public Jarmu getKozlekedoJarmu() {
        Skeleton.nyit("Utszakasz.getKozlekedoJarmu()");
        Skeleton.zar("Utszakasz.getKozlekedoJarmu() visszater: " + kozlekedoJarmu);
        return kozlekedoJarmu;
    }

    /**
     * Beállítja a járművet, amely közlekedik az útszakaszon.
     * @param kozlekedoJarmu a jármű, amely közlekedik az útszakaszon
     */
    public void setKozlekedoJarmu(Jarmu kozlekedoJarmu) {
        Skeleton.nyit("Utszakasz.setKozlekedoJarmu()");
        Skeleton.zar("Utszakasz.setKozlekedoJarmu() végrehajtva");
        this.kozlekedoJarmu = kozlekedoJarmu;
    }
}

