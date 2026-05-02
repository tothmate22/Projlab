

import java.util.List;


/**
 * Az Utszakasz osztály egy útszakaszt reprezentál az úttesten, amelyen járművek közlekedhetnek. 
 * Felelősség: az osztály tárolja a hó és jég mennyiségét, a rajta közlekedő járművet, valamint az épület típusát, amely az útszakasz mentén található. 
 * Az Utszakasz osztály felelős a járművek mozgatásáért és az időjárási viszonyok frissítéséért is.
 */
public class Utszakasz implements IInfo {
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
        return jarmutElore(getSzakaszJobbra());
    }

    /**
     * Megpróbálja a járművet balra vinni az útszakaszon.
     * @return true, ha a jármű sikeresen balra változott, false egyébként
     */
    public boolean jarmuSavotvalt_balra() {
        return jarmutElore(getSzakaszBalra());
    }

    public Utszakasz getSzakaszJobbra() {
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

    public Utszakasz getSzakaszBalra() {
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
     * A hó nő a ticksPerSnowCm beállítás alapján, de csak akkor esik, ha nincs jármű az útszakaszon.
     * A jég úgy nő, hogy amikor a hó >= 4, akkor jeg = floor((ho - 1) / 2)
     * @param ora az Óra, amely a ticksPerSnowCm és currentTime értékeket tartalmazza
     */
    public void idojarasFrissites(Ora ora) {
        // Ha van jármű az útszakaszon, nem esik hó (de a meglévő marad)
        if (kozlekedoJarmu != null) {
            return;
        }
        
        // Hó növekedése az Ora ticksPerSnowCm értéke alapján
        int ticksPerSnowCm = ora.getTicksPerSnowCm();
        int currentTime = ora.getCurrentTime();
        
        // Minden ticksPerSnowCm tick után nő 1 cm a hó
        // De mivel az Ora 1-ről indul, szükséges a maradék számítása
        if (currentTime % ticksPerSnowCm == 0) {
            ho++;
        }
        
        // Jég számítása: ha ho >= 4, akkor jeg = floor((ho - 1) / 2)
        if (ho >= 4) {
            jeg = (int) Math.floor((ho - 1) / 2.0);
        } else {
            jeg = 0;
        }
        
        zuzottKo = false;
    }



    /**
     * Letakarítja az útszakaszt, eltávolítva a havat és a jeget a fej típusa alapján.
     * @param f a fejfej, amely a takarítást végzi
     * @return true, ha volt mit takarítani, false egyébként
     */
    public boolean letakaritas(Fej f) {
        boolean voltMitTakaritani = false;
        
        // Sárkanyfej: azonnal eltünteti mind a havat, mind a jeget
        if (f instanceof Sarkanyfej) {
            if (ho > 0 || jeg > 0) {
                voltMitTakaritani = true;
                ho = 0;
                jeg = 0;
            }
        }
        // JegtoroFej: csak a jeget távolítja el
        else if (f instanceof JegtoroFej) {
            if (jeg > 0) {
                voltMitTakaritani = true;
                jeg = 0;
            }
        }
        // SoproFej és HanyoFej: csökkentik a havat (50%-ot vagy valamennyit)
        else if (f instanceof SoproFej || f instanceof HanyoFej) {
            if (ho > 0) {
                voltMitTakaritani = true;
                ho = (int) Math.ceil(ho * 0.5); // 50%-ot meghagyva, a maradékot eltávolítva
            }
        }
        // ZuzalekFej és SoszoroFej: egyéb logika
        else if (f instanceof ZuzalekFej) {
            // Zúzott kő jelölése, de hó/jég eltávolítás
            if (ho > 0 || jeg > 0) {
                voltMitTakaritani = true;
                ho = (int) Math.ceil(ho * 0.7);
                jeg = (int) Math.ceil(jeg * 0.7);
                zuzottKo = true;
            }
        }
        // Alapértelmezett: próbál valamit eltávolítani
        else {
            if (ho > 0 || jeg > 0) {
                voltMitTakaritani = true;
                ho = (int) Math.ceil(ho * 0.5);
                jeg = (int) Math.ceil(jeg * 0.5);
            }
        }
        
        return voltMitTakaritani;
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

    @Override
    public String getInfo() {
        return (id + " útszakasz adatai: " + "hó=" + ho + " jég=" + jeg + " zúzottköves=" + zuzottKo + " havon áthaladt=" + havonAthaladt + " közlekedő jármű=" + (kozlekedoJarmu != null ? kozlekedoJarmu.getId() : "nincs") + " félrehúzódott jármű=" + (felrehuzodottJarmu != null ? felrehuzodottJarmu.getId() : "nincs") + "szülő úttest=" + szuloUttest.getId() + " épület=" + epulet.toString());
    }
}

