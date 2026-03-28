package zuzmara.model;

import zuzmara.enums.Epulet;

public class Utszakasz {
    protected int ho;
    protected int jeg;
    protected int havonAthaladt;
    protected Jarmu kozlekedoJarmu;
    protected Jarmu felrehuzodottJarmu;
    protected Uttest szuloUttest;
    protected Epulet epulet;

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

    public boolean jarmutElore() {
        Skeleton.nyit("Utszakasz.jarmutElore()");
        // Jármű előre haladása logikája
        Skeleton.zar("Utszakasz.jarmutElore() visszater: " + true);

        return true;
    }

    public boolean jarmuSavotvalt_jobbra() {
        Skeleton.nyit("Utszakasz.jarmuSavotvalt_jobbra()");
        // Jármű savváltása jobbra logikája
        Skeleton.zar("Utszakasz.jarmuSavotvalt_jobbra() visszater: " + false);

        return false;
    }

    public boolean jarmuSavotvalt_balra() {
        Skeleton.nyit("Utszakasz.jarmuSavotvalt_balra()");
        // Jármű savváltása balra logikája
        Skeleton.zar("Utszakasz.jarmuSavotvalt_balra() visszater: " + false);

        return false;
    }

    public void belep(Jarmu jarmu) {
        Skeleton.nyit("Utszakasz.belep()");
        kozlekedoJarmu = jarmu;
        Skeleton.zar("Utszakasz.belep() végrehajtva");

    }

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

    public boolean foglaltE() {
        Skeleton.nyit("Utszakasz.foglaltE()");
        Skeleton.zar("Utszakasz.foglaltE() visszater: " + (kozlekedoJarmu != null));
        return kozlekedoJarmu != null;
    }

    public void letakaritas() {
        Skeleton.nyit("Utszakasz.letakaritas()");
        // Letakarítás logikája
        Skeleton.zar("Utszakasz.letakaritas() végrehajtva");
    }

    public Epulet getEpulet() {
        Skeleton.nyit("Utszakasz.getEpulet()");
        Skeleton.zar("Utszakasz.getEpulet() visszater: " + epulet);
        return epulet;
    }

    public int getHo() {
        Skeleton.nyit("Utszakasz.getHo()");
        Skeleton.zar("Utszakasz.getHo() visszater: " + ho);
        return ho;
    }

    public void setHo(int ho) {
        Skeleton.nyit("Utszakasz.setHo()");
        Skeleton.zar("Utszakasz.setHo() végrehajtva");
        this.ho = ho;
    }   

    public Jarmu getKozlekedoJarmu() {
        Skeleton.nyit("Utszakasz.getKozlekedoJarmu()");
        Skeleton.zar("Utszakasz.getKozlekedoJarmu() visszater: " + kozlekedoJarmu);
        return kozlekedoJarmu;
    }
}

