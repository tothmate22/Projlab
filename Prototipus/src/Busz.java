import java.util.Random;

/**
 * A tömegközlekedési eszköz fizikai megvalósítása a pályán.
 * Felelős a mozgásért, az ütközések kezeléséért és a saját állapotának (elakadás, javítás) nyilvántartásáért.
 * Két kijelölt végállomás között közlekedik a sofőr (Buszvezető) utasításai alapján.
 */
public class Busz extends Jarmu implements ILepheto, ICsuszhat {

    /**
     * A kijelölt végállomás, ahol a busz jutalmat kap a sikeres fordulóért.
     */
    private Utszakasz celAllomas;

    /**
     * A sofőr által beállított haladási vagy sávváltási irány a következő időpillanatra.
     */
    private Utszakasz kovetkezoLepes;

    /**
     * Baleset utáni kényszerpihenő hossza tickekben mérve.
     */
    private int hatralevoJavitasildo;

    /**
     * Visszamutató referencia a jármű tulajdonosára, hogy jutalmazni tudja.
     */
    private Buszvezeto vezerlo;

    /**
     * A jármű aktuális belső állapotát tárolja az állapotgéphez.
     */
    private BuszAllapot allapot;

    /**
     * Véletlenszám-generátor a megcsúszás esélyének számításához.
     */
    private Random random;

    /**
     * A Busz osztály konstruktora.
     * @param id A busz egyedi azonosítója.
     */
    public Busz(String id) {
        super(id);
        this.allapot = BuszAllapot.NORMAL;
        this.hatralevoJavitasildo = 0;
        this.random = new Random();
    }

    /**
     * Megpróbál átlépni a megadott irányban lévő útszakaszra.
     * Lekezeli a hó, a jég és a foglalt útszakasz szabályait.
     * * @param cel A cél útszakasz, ahova a busz lépni szeretne.
     */
    @Override
    public void halad(Utszakasz cel) {
        // Baleset esetén vagy cél hiányában nem csinál semmit
        if (this.allapot == BuszAllapot.JAVITAS_ALATT || cel == null) {
            return;
        }

        // Hó ellenőrzése
        if (cel.getHo() >= 25) {
            this.allapot = BuszAllapot.ELAKADT;
            return;
        }

        // Jég ellenőrzése és a 20%-os megcsúszás sorsolása
        if (cel.getJeg() > 0 && random.nextInt(100) < 20) {
            if (cel.foglaltE()) {
                this.utkozott(cel.getKozlekedoJarmu());
                return;
            } else {
                this.kicsuszik();
                return;
            }
        }

        // Torlódás ellenőrzése (ha normálisan lépne)
        if (cel.foglaltE()) {
            return;
        }

        // Sikeres lépés
        // (Feltételezzük, hogy a régi szakaszról való kilépést a belep metódus vagy a hívó kezeli)
        this.pozicio = cel;
        cel.belep(this);
        
        // Mivel sikeresen lépett, biztosan mozgásképes
        this.allapot = BuszAllapot.NORMAL;

        // Végállomás ellenőrzése
        // Feltételezzük, hogy az Epulet enum tartalmaz egy MEGALLO értéket
        if (cel.getEpulet() == Epulet.MEGALLO && cel == this.celAllomas) {
            this.celbaErt();
        }
    }

    /**
     * Ütközés esetén hívódik meg, elindítja a javítási folyamatot az állapot átállításával.
     * * @param masik A jármű, amivel a busz ütközött.
     */
    @Override
    public void utkozott(Jarmu masik) {
        this.hatralevoJavitasildo = 60; // 1 óra büntetés
        this.allapot = BuszAllapot.JAVITAS_ALATT;
    }

    /**
     * Jeges úton 20% eséllyel véletlen irányba mozdítja a buszt, elveszítve a sofőr irányítását.
     */
    @Override
    public void kicsuszik() {
        Utszakasz csuszasCel = this.pozicio.getSzakaszElore();
        
        if (csuszasCel != null) {
            if (csuszasCel.foglaltE()) {
                // Ha foglalt az előtte lévő út, ütközik az ott állóval
                this.utkozott(csuszasCel.getKozlekedoJarmu());
                this.allapot = BuszAllapot.JAVITAS_ALATT;
            } else {
                // Csak a saját pozícióját frissíti és belép az új útra
                this.pozicio = csuszasCel;
                csuszasCel.belep(this);
            }
        }
    }

    /**
     * Sikeres forduló esetén meghívódik, és kiutalja a jutalmat a sofőrnek.
     */
    public void celbaErt() {
        // 1. Jutalom kiutalása a társított Buszvezetőnek
        if (this.vezerlo != null) {
            this.vezerlo.penztKap(100);
        }

        // 2. A jelenlegi cél teljesítve, töröljük a célállomást
        // Így elkerüljük, hogy a busz minden tickben újra "beérjen"
        this.celAllomas = null;

        // 3. A tervezett következő lépés törlése (megállás)
        // A buszvezetőnek új útvonalat kell majd kijelölnie a visszaúthoz
        this.setKovetkezoLepes(null);
    }

    /**
     * A belső időzítő hívja meg. Kezeli a javítási idő leteltét, illetve továbbítja a mozgási szándékot.
     */
    @Override
    public void idoEltelt() {
        if (this.allapot == BuszAllapot.JAVITAS_ALATT) {
            this.hatralevoJavitasildo = this.hatralevoJavitasildo - 1;
            
            // Ha letelt a büntetés, a busz ismét normál állapotba kerül
            if (this.hatralevoJavitasildo == 0) {
                this.allapot = BuszAllapot.NORMAL;
            }
        } else {
            // Ha NORMAL vagy ELAKADT állapotban van, megpróbál lépni
            if (this.kovetkezoLepes != null) {
                this.halad(this.kovetkezoLepes);
            }
        }
    }

    /**
     * Beállítja a jármű távlati célállomását.
     * * @param vegAllomas A célként kijelölt útszakasz.
     */
    public void setCelAllomas(Utszakasz vegAllomas) {
        this.celAllomas = vegAllomas;
    }

    /**
     * Beállítja a busz következő időegységben végrehajtandó mozgásának célpontját.
     * * @param ujLepes A következő tervezett útszakasz.
     */
    public void setKovetkezoLepes(Utszakasz ujLepes) {
        this.kovetkezoLepes = ujLepes;
    }

    /**
     * Visszaadja a korábban beállított következő lépés értékét.
     * * @return A következő útszakasz, vagy null.
     */
    public Utszakasz getKovetkezoLepes() {
        return this.kovetkezoLepes;
    }

    /**
     * Összekapcsolja a buszt az azt irányító játékossal.
     * * @param bv A buszt vezető játékos (Buszvezeto).
     */
    public void felszall(Buszvezeto bv) {
        this.vezerlo = bv;
    }

    /**
     * Szöveges állapotjelentést ad a tesztelő nyelv számára.
     * * @return A busz állapotát, javítási idejét és a jelenlegi tartózkodási helyét tartalmazó formázott string.
     */
    public String getInfo() {
        String pozicioId = (this.pozicio != null) ? this.pozicio.getId() : "nincs";
        return "Busz állapota: " + this.allapot + 
               ", javítási idő: " + this.hatralevoJavitasildo + 
               ", pozíció: " + pozicioId;
    }
}
