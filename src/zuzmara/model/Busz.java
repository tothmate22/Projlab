package zuzmara.model;
/**
 * Ősosztály: Jarmu (absztrakt ősosztály).
 * Megvalósított interfészek: ILepheto, ICsuszhat.
 * Felelősség: Két végállomás között közlekedő tömegközlekedési eszköz. 
 * Számon tartja a saját állapotát, és ha ütközik, egy játékbeli órára felfüggeszti a mozgását.
 */
public class Busz extends Jarmu implements ILepheto, ICsuszhat {

    /**
     * A busz végállomása (amely MEGALLO épülettel rendelkezik). 
     * Ez a busz hosszútávú úticélja, ide akar eljutni.
     */
    private Utszakasz celAllomas;

    /**
     * A közvetlenül szomszédos útszakasz, amire a busz a következő 
     * időpillanatban (tick) rá szándékozik lépni.
     */
    private Utszakasz kovetkezoLepes;

    /**
     * Baleset esetén a hátralévő büntetésideje (tick-ekben mérve). Normál esetben 0.
     */
    private int hatralevoJavitasiIdo;

    /**
     * Visszamutató referencia a tulajdonosra, hogy a busz tudjon 
     * kinek pénzt adni a forduló végén.
     */
    private Buszvezeto vezerlo;

    /**
     * A Busz konstruktora.
     */
    public Busz(Utszakasz pozicio, int javitasiIdo) {
        super(pozicio);
        this.hatralevoJavitasiIdo = javitasiIdo;
        Skeleton.nyit("Busz <<create>>");
        Skeleton.zar("Busz letrejott.");
    }

    /**
     * Hozzárendeli a buszhoz az őt irányító buszvezetőt. A játék/teszt 
     * inicializálási fázisában hívódik meg.
     */
    public void felszall(Buszvezeto bv) {
        Skeleton.nyit("Busz.felszall(Buszvezeto)");
        this.vezerlo = bv;
        Skeleton.zar("Busz.felszall() visszater");
    }

    /**
     * Hosszútávú útvonal (végállomás) beállítását elősegítő metódus.
     */
    public void setCelAllomas(Utszakasz vegAllomas) {
        Skeleton.nyit("Busz.setCelAllomas(Utszakasz)");
        this.celAllomas = vegAllomas;
        Skeleton.zar("Busz.setCelAllomas() visszater");
    }

    /**
     * Beállítja a busz közvetlen következő lépését. A Buszvezeto hívja meg.
     */
    public void setKovetkezoLepes(Utszakasz ujLepes) {
        Skeleton.nyit("Busz.setKovetkezoLepes(Utszakasz)");
        this.kovetkezoLepes = ujLepes;
        Skeleton.zar("Busz.setKovetkezoLepes() visszater");
    }

    /**
     * Visszaadja a busz számára kijelölt soron következő szomszédos útszakaszt.
     */
    public Utszakasz getKovetkezoLepes() {
        Skeleton.nyit("Busz.getKovetkezoLepes()");
        Skeleton.zar("Busz.getKovetkezoLepes() visszater");
        return this.kovetkezoLepes;
    }

    /**
     * A Jarmu ősosztályból felüldefiniált metódus. A busz megpróbál rálépni a paraméterben 
     * kapott szomszédos útszakaszra. Csak akkor hajtódik végre, ha a hatralevoJavitasiIdo nulla.
     */
    @Override
    public void halad(Utszakasz kovetkezo) {
        Skeleton.nyit("Busz.halad(Utszakasz)");

        if (this.hatralevoJavitasiIdo == 0) {
            // A Szkeleton logikája szerint a felhasználó dönti el, hogy az út foglalt-e
            boolean foglalt = Skeleton.kerdez("Foglalt a cel utszakasz? (i/n)");
            
            if (foglalt) {
                // Ha foglalt, lekérjük a járművet és ütközünk vele (a szekvenciadiagram alapján)
                Jarmu masik = kovetkezo.getKozlekedoJarmu();
                this.utkozott(masik);
            } else {
                // Ha nem foglalt, rálépünk (adminisztráljuk az útszakasz cserét)
                System.out.println("    [A busz sikeresen atlepett az uj utszakaszra.]");
                
                // Ellenőrizzük, hogy célba értünk-e
                boolean celbaErtunk = Skeleton.kerdez("Ez az utszakasz a busz vegallomasa (MEGALLO)? (i/n)");
                if (celbaErtunk) {
                    this.celbaErt();
                }
            }
        } else {
            System.out.println("    [A busz balesetes, nem tud haladni!]");
        }

        Skeleton.zar("Busz.halad() visszater");
    }

    /**
     * Az ICsuszhat interfészből megvalósított metódus. Akkor hívódik meg, 
     * ha a busz megcsúszik a jégen.
     */
    @Override
    public void kicsuszik() {
        Skeleton.nyit("Busz.kicsuszik()");

        boolean csuszik = Skeleton.kerdez("Tenyleg elveszti az iranyitast a busz a jegen? (i/n)");
        if (csuszik) {
            System.out.println("    [A busz megcsuszott!]");
            boolean utkozik = Skeleton.kerdez("Nekicsuszott egy masik jarmunek? (i/n)");
            if (utkozik) {
                // Kamu ütközés szimulálása a szkeletonban
                this.utkozott(null); 
            }
        }

        Skeleton.zar("Busz.kicsuszik() visszater");
    }

    /**
     * Az ICsuszhat interfészből és a Jarmu ősosztályból származó metódus. 
     * Ha a busz baleset részese lesz, ez beállítja a hatralevoJavitasiIdo-t.
     */
    @Override
    public void utkozott(Jarmu masik) {
        Skeleton.nyit("Busz.utkozott(Jarmu)");

        this.hatralevoJavitasiIdo = 60; // 60 tick = 1 óra
        System.out.println("    [Baleset! A hatralevo javitasi ido 60-ra lett allitva.]");
        
        // Ha valós objektumot kaptunk paraméterként, meghívhatjuk rajta is az ütközést
        if (masik != null) {
            boolean tovabbUtkozik = Skeleton.kerdez("Ertesitsuk a masik jarmuvet is az utkozesrol? (i/n)");
            if (tovabbUtkozik) {
                masik.utkozott(this);
            }
        }

        Skeleton.zar("Busz.utkozott() visszater");
    }

    /**
     * Amikor a busz rálép a celAllomas-t jelentő útszakaszra, ez a metódus hívódik meg. 
     * Meghívja a vezerlo.penztKap(100) metódust.
     */
    public void celbaErt() {
        Skeleton.nyit("Busz.celbaErt()");

        if (this.vezerlo != null) {
            this.vezerlo.penztKap(100);
        }

        Skeleton.zar("Busz.celbaErt() visszater");
    }

    /**
     * Az ILepheto interfészből megvalósított metódus (az Idozito hívja meg ciklusonként). 
     * Csökkenti a hátralévő javítási időt.
     */
    @Override
    public void idoEltelt() {
        Skeleton.nyit("Busz.idoEltelt()");

        if (this.hatralevoJavitasiIdo > 0) {
            this.hatralevoJavitasiIdo--;
            System.out.println("    [Javitasi ido csokkent. Hatra van: " + this.hatralevoJavitasiIdo + "]");
        }

        Skeleton.zar("Busz.idoEltelt() visszater");
    }
}
