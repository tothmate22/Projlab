package zuzmara.model;

import java.util.List;

import zuzmara.enums.Epulet;
import zuzmara.model.fejek.HanyoFej;
import zuzmara.model.fejek.JegtoroFej;
import zuzmara.model.fejek.Sarkanyfej;
import zuzmara.model.fejek.SoproFej;
import zuzmara.model.fejek.ZuzalekFej;

/**
 * Az Utszakasz osztály egy útszakaszt reprezentál az úttesten, amelyen járművek közlekedhetnek. 
 * Felelősség: az osztály tárolja a hó és jég mennyiségét, a rajta közlekedő járművet, valamint az épület típusát, amely az útszakasz mentén található. 
 * Az Utszakasz osztály felelős a járművek mozgatásáért és az időjárási viszonyok frissítéséért is.
 */
public class Utszakasz {
    protected String id;
    protected int ho;
    protected int jeg;
    protected boolean zuzottKo;
    protected int havonAthaladt;
    protected Jarmu kozlekedoJarmu;
    protected Jarmu felrehuzodottJarmu;
    protected Uttest szuloUttest;
    protected Epulet epulet;
    
    /**
     * Konstruktor.
     * @param szuloUttest az a Uttest, amelyhez ez az Utszakasz tartozik
     * @param epuletTipus az épület típusa, amely az útszakasz mentén van ("MEGALLO", "HAZ", "MUNKAHELY", "BAZIS", "URES")  
     * @param id az útszakasz egyedi azonosítója
    */
    public Utszakasz(Uttest szuloUttest, String epuletTipus, String id) {
        this.id = id;
        ho = 0;
        jeg = 0;
        zuzottKo = false;
        havonAthaladt = 0;
        kozlekedoJarmu = null;
        felrehuzodottJarmu = null;
        this.szuloUttest = szuloUttest;
        this.epulet = Epulet.valueOf(epuletTipus);
        Skeleton.nyit("Utszakasz <<create>>");
        Skeleton.zar("Utszakasz létrehozva");
    }

        /**
     * Egy jármű belép az útszakaszra.
     * @param jarmu a jármű, amely belep az útszakaszra
     */
    public void belep(Jarmu jarmu) {
        kozlekedoJarmu = jarmu;
        if (ho > 0) {
            havonAthaladt++;
        }
    }

    /**
     * Megpróbálja az előtte lévő útszakaszra vinni a járművet az útszakaszon.
     * @return true, ha a jármű sikeresen előre haladt, false egyébként
     */
    public boolean jarmutElore(Utszakasz kovetkezo) {
        if (kovetkezo != null && !kovetkezo.foglaltE()) {
            kovetkezo.belep(kozlekedoJarmu);
            kozlekedoJarmu = null;
            return true;
        }
        return false;
    }


    /**
     * Megpróbálja a járművet jobbra vinni az útszakaszon.
     * @return true, ha a jármű sikeresen jobbra változott, false egyébként
     */
    public boolean jarmuSavotvalt_jobbra() {
        return jarmutElore(getSzakaszJobbbra());
    }

    /**
     * Megpróbálja a járművet balra vinni az útszakaszon.
     * @return true, ha a jármű sikeresen balra változott, false egyébként
     */
    public boolean jarmuSavotvalt_balra() {
        return jarmutElore(getSzakaszBalra());
    }

    private Utszakasz getSzakaszJobbbra() {
        List<Utszakasz> jelenlegiSav = szuloUttest.getSzakaszSavja(this);
        int szakaszIndex = jelenlegiSav.indexOf(this);
        
        List<List<Utszakasz>> savok = szuloUttest.getSavok();
        int jelenlegiSavIndex = savok.indexOf(jelenlegiSav);

        if (jelenlegiSavIndex < savok.size() - 1) {
            List<Utszakasz> jobbSav = savok.get(jelenlegiSavIndex + 1);
            return jobbSav.get(szakaszIndex);            
        }
        return null;
    }

    private Utszakasz getSzakaszBalra() {
        List<Utszakasz> jelenlegiSav = szuloUttest.getSzakaszSavja(this);
        int szakaszIndex = jelenlegiSav.indexOf(this);
        
        List<List<Utszakasz>> savok = szuloUttest.getSavok();
        int jelenlegiSavIndex = savok.indexOf(jelenlegiSav);

        if (jelenlegiSavIndex > 0) {
            List<Utszakasz> balSav = savok.get(jelenlegiSavIndex - 1);
            return balSav.get(szakaszIndex);            
        }
        return null;
    }

    /**
     * Megnézi, hogy van-e jármű az útszakaszon.
     * @return true, ha van jármű az útszakaszon, false egyébként
     */
    public boolean foglaltE() {
        return kozlekedoJarmu != null;
    }

    /**
     * Megpróbálja a járművet félrehúzni az útszakaszon, ha az meghibásodott.
     * @return true, ha a jármű sikeresen félrehúzva, false egyébként
     */
    public boolean jarmuFelrehuzodik() {
        if (felrehuzodottJarmu == null) {
            felrehuzodottJarmu = kozlekedoJarmu;
            kozlekedoJarmu = null;
            return true;
        }
        return false;
    }

    /**
     * Visszaadja az előtte lévő útszakaszt.
     * @return az előtte lévő útszakasz, vagy null, ha nincs
     */
    public Utszakasz getSzakaszElore() {
        List<Utszakasz> jelenlegiSav = szuloUttest.getSzakaszSavja(this);
        int szakaszIndex = jelenlegiSav.indexOf(this);
        
        if (szakaszIndex < jelenlegiSav.size() - 1) {
            return jelenlegiSav.get(szakaszIndex + 1);            
        }
        return null;
    }

    /**
     * Frissíti az időjárási viszonyokat az útszakaszon.
     */
    public void idojarasFrissites() {
        ho += 10;
        zuzottKo = false;
        if (havonAthaladt >= 15) {
            jeg += (int) Math.floor(ho / 5);
            havonAthaladt = 0;
            ho = 0;
        }
    }



    /**
     * Letakarítja az útszakaszt a hókotró takarító fejének megfelelően.
     * @param f a hókotró éppen használt takarító feje
     * @return true, ha történt takarítás, false egyébként
     */
    public boolean letakaritas(Fej f) {
        // 1. SarkanyFej: Felolvasztja a havat és a jeget
        if (f instanceof Sarkanyfej) {
            if (ho > 0 || jeg > 0) {
                ho = 0;
                jeg = 0;
                return true;
            }
        }

        // 3. JegtoroFej: A jeget feltöri, ami hóként marad az úton
        if (f instanceof JegtoroFej) {
            if (jeg > 0) {
                ho = ho + jeg;
                jeg = 0;
                return true;
            }
        }

        // 4. SoproFej: Áttolja a havat a szomszédos sávba
        if (f instanceof SoproFej) {
            if (ho > 0) {
                Utszakasz szomszedJobbra = getSzakaszJobbbra();
                if (szomszedJobbra != null) {
                    szomszedJobbra.addHo(ho); // Áttolja a havat a szomszéd sávba 
                }
                ho = 0;
                return true;
            }
        }

        // 5. HanyoFej: Az út szélére dobja a havat
        if (f instanceof HanyoFej) {
            if (ho > 0) {
                ho = 0;
                return true;
            }
        }

        // 6. ZuzalekFej: Csúszásmentesítő réteget hoz létre
        if (f instanceof ZuzalekFej) {
            if (!zuzottKo) {
                zuzottKo = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Megnézi, hogy milyen típusú épület van az útszakasz mentén.
     * @return az épület típusa
     */
    public Epulet getEpulet() {
        return epulet;
    }

    /**
     * Beállítja az épület típusát az útszakasz mentén.
     * @param epulet az épület típusa
     */
    public void setEpulet(Epulet epulet) {
        this.epulet = epulet;
    }

    /**
     * Megnézi, hogy mennyi hó van az útszakaszon.
     * @return a hó mennyisége centiméterben
     */
    public int getHo() {
        return ho;
    }

    /**
     * Beállítja a hó mennyiségét az útszakaszon.
     * @param ho a hó mennyisége centiméterben
     */
    public void setHo(int ho) {
        this.ho = ho;
    }

    /**
     * Megnézi, hogy mennyi jég van az útszakaszon.
     * @return a jég mennyisége centiméterben
     */    public int getJeg() {
        return jeg;
     }

    /**
     * Beállítja a jég mennyiségét az útszakaszon.
     * @param jeg a jég mennyisége centiméterben
     */    
    public void setJeg(int jeg) {
        this.jeg = jeg;
    }

    public boolean zuzottKovesE() {
        return zuzottKo;
    }

    public void setZuzottKovesE(boolean zuzottKo) {
        this.zuzottKo = zuzottKo;
    }
    

    public Jarmu getFelrehuzodottJarmu() {
        return felrehuzodottJarmu;
    }

    /**
     * Megnézi, hogy melyik jármű közlekedik az útszakaszon.
     * @return a jármű, amely közlekedik az útszakaszon, vagy null, ha nincs jármű
     */
    public Jarmu getKozlekedoJarmu() {
        return kozlekedoJarmu;
    }

    /**
     * Beállítja a járművet, amely közlekedik az útszakaszon.
     * @param kozlekedoJarmu a jármű, amely közlekedik az útszakaszon
     */
    public void setKozlekedoJarmu(Jarmu kozlekedoJarmu) {
        this.kozlekedoJarmu = kozlekedoJarmu;
    }

    /**
     * Visszaadja az útszakasz egyedi azonosítóját.
     * @return az útszakasz egyedi azonosítója
     */
    public String getId() {
        return id;
    }

    public String getInfo() {
        return (id + " útszakasz adatai: " + "hó=" + ho + " jég=" + jeg + " zúzottköves=" + zuzottKo + " havon áthaladt=" + havonAthaladt + " közlekedő jármű=" + "kozlekedoJarmu.getId()" + " félrehúzódott jármű=" + "felrehuzodottJarmu.getId()" + "szülő úttest=" + szuloUttest.getId() + " épület=" + epulet.toString());
    }

    /**
     * Hó hozzáadása az útszakaszhoz (pl. söprésnél használatos).
     * @param hoMennyiseg a hozzáadandó hó mennyisége
     */
    public void addHo(int hoMennyiseg) {
        this.ho += hoMennyiseg;
    }
}

