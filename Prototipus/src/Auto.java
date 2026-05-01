import java.util.Random;

/**
 * Az Auto osztály egy autonóm járművet reprezentál,
 * amely a pályán önállóan, GPS segítségével közlekedik.
 *
 * Az autó minden tick során megpróbál a célja felé haladni,
 * figyelembe véve a környezeti feltételeket (hó, jég, foglaltság).
 */
public class Auto extends Jarmu implements ILepheto, ICsuszhat, IInfo {

    /** Az autó aktuális célútszakasza. */
    private Utszakasz cel;

    /** Az autó aktuális állapota. */
    private AutoAllapot allapot;

    /** A GPS objektum, amely az útvonal meghatározásáért felel. */
    private GPS gps;

    /** Véletlenszám-generátor a csúszás modellezéséhez. */
    private Random random;

    /**
     * Létrehoz egy új autót.
     *
     * @param id az autó azonosítója
     * @param pozicio az autó kezdeti pozíciója
     * @param cel az autó célútszakasza
     * @param allapot az autó kezdeti állapota
     * @param palya a pálya, amelyen az autó közlekedik
     */
    public Auto(String id, Utszakasz pozicio, Utszakasz cel, AutoAllapot allapot, Palya palya) {
        super(id);
        this.pozicio = pozicio;
        this.cel = cel;
        this.allapot = allapot;
        this.gps = new GPS(palya);
        this.random = palya.getRandom() == null ? new Random() : palya.getRandom();
    }

    /**
     * Az autó megpróbál egy lépést tenni a célja felé.
     * A következő útszakaszt a GPS határozza meg.
     *
     * @param cel a célútszakasz
     */
    @Override
    public void halad(Utszakasz cel) {
        this.cel = cel;

        // Csak akkor halad, ha HALAD állapotban van
        if (allapot != AutoAllapot.HALAD || cel == null) {
            return;
        }

        // Következő lépés meghatározása
        Utszakasz kovetkezo = gps.kovetkezoLepes(pozicio, cel);

        if (kovetkezo == null) {
            return;
        }

        // Ha túl nagy a hó, az autó elakad
        if (kovetkezo.getHo() >= 25) {
            elakad();
            return;
        }

        // Ha jeges és nincs zúzalék, az autó megcsúszhat
        if (kovetkezo.getJeg() > 0 && !kovetkezo.zuzottKovesE() && random.nextInt(100) < 20) {
            kicsuszik();
            return;
        }

        // Ha foglalt az útszakasz, az autó nem lép (követési távolság)
        if (kovetkezo.foglaltE()) {
            return;
        }

        // Kilép az aktuális útszakaszról
        if (pozicio != null) {
            pozicio.setKozlekedoJarmu(null);
        }

        // Belép az új útszakaszra
        if (this.pozicio.jarmutElore(kovetkezo)) {
            this.pozicio = kovetkezo;
        }

        // Ha elérte a célt
        if (kovetkezo == cel) {
            celbaErt();
        }
    }

    /**
     * Egy időlépés kezelése.
     * Meghívja a haladást vagy kezeli az elakadt állapotot.
     */
    @Override
    public void idoEltelt() {
        if (cel == null) {
            return;
        }

        // Normál haladás
        if (allapot == AutoAllapot.HALAD) {
            halad(cel);
            return;
        }

        // Elakadt állapotból visszatérés, ha javul a helyzet
        if (allapot == AutoAllapot.ELAKADT) {
            Utszakasz kovetkezo = gps.kovetkezoLepes(pozicio, cel);

            if (kovetkezo != null && kovetkezo.getHo() < 25) {
                allapotValtoztat(AutoAllapot.HALAD);
                halad(cel);
            }
        }
    }

    /**
     * Az autó célba ér, és várakozó állapotba kerül.
     */
    public void celbaErt() {
        allapotValtoztat(AutoAllapot.VARAKOZIK);
    }

    /**
     * Az autó elakad, mert nem tud továbbhaladni.
     */
    public void elakad() {
        allapotValtoztat(AutoAllapot.ELAKADT);
    }

    /**
     * Az autó megcsúszik jeges úton.
     * Ha ütközik egy másik járművel, mentésre váró állapotba kerül.
     */
    @Override
    public void kicsuszik() {
        Utszakasz csuszasCel = pozicio == null ? null : pozicio.getSzakaszElore();

        if (csuszasCel == null) {
            return;
        }

        // Ütközés
        if (csuszasCel.foglaltE()) {
            utkozott(csuszasCel.getKozlekedoJarmu());
            return;
        }

        // Csúszás előre
        if (pozicio != null) {
            pozicio.setKozlekedoJarmu(null);
        }

        csuszasCel.belep(this);
        this.pozicio = csuszasCel;

        if (csuszasCel == cel) {
            celbaErt();
        }
    }

    /**
     * Az autó ütközik egy másik járművel.
     * Regisztrálja magát az autómentőnél.
     *
     * @param masik a másik jármű
     */
    @Override
    public void utkozott(Jarmu masik) {
        allapotValtoztat(AutoAllapot.MENTESRE_VAR);

        Automento automento = gps.getElsoAutomento();
        if (automento != null) {
            automento.mentesRegisztral(this);
        }
    }

    /**
     * Az autó állapotának módosítása.
     *
     * @param uj az új állapot
     */
    public void allapotValtoztat(AutoAllapot uj) {
        this.allapot = uj;
    }

    /**
     * Visszaadja az autó aktuális állapotát.
     *
     * @return az autó állapota
     */
    public AutoAllapot getAllapot() {
        return allapot;
    }

    /**
     * Visszaadja az autó célját.
     *
     * @return a célútszakasz
     */
    public Utszakasz getCel() {
        return cel;
    }

    /**
     * Az info parancshoz szükséges szöveges reprezentáció.
     *
     * @return az autó állapotát leíró szöveg
     */
    @Override
    public String getInfo() {
        return "info " + id + " (Auto):\n"
                + "pozicio: " + (pozicio == null ? "nincs" : pozicio.getId()) + "\n"
                + "cel: " + (cel == null ? "nincs" : cel.getId()) + "\n"
                + "allapot: " + allapot;
    }
}