package zuzmara.model;

import zuzmara.enums.Epulet;

public class Utszakasz {
    private int ho;
    private int jeg;
    private int havonAthaladt;
    private Jarmu kozlekedoJarmu;
    private Jarmu felrehuzodottJarmu;
    private Uttest szuloUttest;
    private Epulet epulet;

    public Utszakasz() {
        ho = 0;
        jeg = 0;
        havonAthaladt = 0;
        kozlekedoJarmu = null;
        felrehuzodottJarmu = null;
        szuloUttest = null;
        epulet = null;
    }
    
    public Utszakasz(Uttest szuloUttest, String epuletTipus) {
        ho = 0;
        jeg = 0;
        havonAthaladt = 0;
        kozlekedoJarmu = null;
        felrehuzodottJarmu = null;
        this.szuloUttest = szuloUttest;
        this.epulet = Epulet.valueOf(epuletTipus);
    }

    public boolean jarmutElore() {
        return false;
    }

    public boolean jarmuSavotvalt_jobbra() {
        return false;
    }

    public boolean jarmuSavotvalt_balra() {
        return false;
    }

    public void belep(Jarmu jarmu) {
    }

    public void idojarasFrissites() {
    }

    public boolean foglaltE() {
        return kozlekedoJarmu != null;
    }

    public void letakaritas() {
    }

    public Epulet getEpulet() {
        return epulet;
    }

    public void setHo(int ho) {
        this.ho = ho;
    }

    /**
     * Beállítja a közlekedő járművet az utszakaszra.
     * @param kozlekedoJarmu a közlekedő jármű
     */
    public void setKozlekedoJarmu(Jarmu kozlekedoJarmu) {
        this.kozlekedoJarmu = kozlekedoJarmu;
    }
}

