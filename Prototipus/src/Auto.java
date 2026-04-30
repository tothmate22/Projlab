import java.util.Random;
<<<<<<< HEAD

/**
 * Az Auto osztály a pályán önállóan közlekedő, nem játékos által irányított járművet reprezentál.
 * Az autó a GPS segítségével a legrövidebb járható út következő útszakaszára próbál lépni.
 */
public class Auto extends Jarmu implements ILepheto, ICsuszhat {

    /** Az autó végső célútszakasza. */
=======
import Prototipus.src.interfaces;
/**
 * Az Auto osztály egy autonóm járművet reprezentál,
 * amely a pályán önállóan, GPS segítségével közlekedik.
 *
 * Az autó minden tick során megpróbál a célja felé haladni,
 * figyelembe véve a környezeti feltételeket (hó, jég, foglaltság).
 */
public class Auto extends Jarmu implements ILepheto, ICsuszhat, IInfos {

    /** Az autó aktuális célútszakasza. */
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
    private Utszakasz cel;

    /** Az autó aktuális állapota. */
    private AutoAllapot allapot;

<<<<<<< HEAD
    /** Központi GPS referencia, amelyet az autó útvonalválasztásra használ. */
    private GPS gps;

    /** Véletlenszám-generátor a megcsúszás eldöntéséhez. */
    private Random random;

    /**
     * Létrehoz egy autót a megadott azonosítóval, kezdőpozícióval, céllal és állapottal.
=======
    /** A GPS objektum, amely az útvonal meghatározásáért felel. */
    private GPS gps;

    /** Véletlenszám-generátor a csúszás modellezéséhez. */
    private Random random;

    /**
     * Létrehoz egy új autót.
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
     *
     * @param id az autó azonosítója
     * @param pozicio az autó kezdeti pozíciója
     * @param cel az autó célútszakasza
     * @param allapot az autó kezdeti állapota
<<<<<<< HEAD
     * @param gps a központi GPS objektum
     */
    public Auto(String id, Utszakasz pozicio, Utszakasz cel, AutoAllapot allapot, GPS gps) {
=======
     * @param palya a pálya, amelyen az autó közlekedik
     */
    public Auto(String id, Utszakasz pozicio, Utszakasz cel, AutoAllapot allapot, Palya palya) {
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
        super(id);
        this.pozicio = pozicio;
        this.cel = cel;
        this.allapot = allapot;
<<<<<<< HEAD
        this.gps = gps;
        this.random = new Random();
    }

    /**
     * Az autó megpróbál a megadott cél felé haladni.
     * A következő lépést a GPS számolja ki.
     *
     * @param cel a végső célútszakasz
=======
        this.gps = new GPS(palya);
        this.random = palya.getRandom() == null ? new Random() : palya.getRandom();
    }

    /**
     * Az autó megpróbál egy lépést tenni a célja felé.
     * A következő útszakaszt a GPS határozza meg.
     *
     * @param cel a célútszakasz
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
     */
    @Override
    public void halad(Utszakasz cel) {
        this.cel = cel;

<<<<<<< HEAD
        if (allapot != AutoAllapot.HALAD || cel == null || gps == null) {
            return;
        }

=======
        // Csak akkor halad, ha HALAD állapotban van
        if (allapot != AutoAllapot.HALAD || cel == null) {
            return;
        }

        // Következő lépés meghatározása
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
        Utszakasz kovetkezo = gps.kovetkezoLepes(pozicio, cel);

        if (kovetkezo == null) {
            return;
        }

<<<<<<< HEAD
=======
        // Ha túl nagy a hó, az autó elakad
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
        if (kovetkezo.getHo() >= 25) {
            elakad();
            return;
        }

<<<<<<< HEAD
        if (kovetkezo.getJeg() > 0 && random.nextInt(100) < 20) {
=======
        // Ha jeges és nincs zúzalék, az autó megcsúszhat
        if (kovetkezo.getJeg() > 0 && !kovetkezo.zuzottKovesE() && random.nextInt(100) < 20) {
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
            kicsuszik();
            return;
        }

<<<<<<< HEAD
=======
        // Ha foglalt az útszakasz, az autó nem lép (követési távolság)
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
        if (kovetkezo.foglaltE()) {
            return;
        }

<<<<<<< HEAD
=======
        // Kilép az aktuális útszakaszról
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
        if (pozicio != null) {
            pozicio.setKozlekedoJarmu(null);
        }

<<<<<<< HEAD
        kovetkezo.belep(this);
        this.pozicio = kovetkezo;

=======
        // Belép az új útszakaszra
        kovetkezo.belep(this);
        this.pozicio = kovetkezo;

        // Ha elérte a célt
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
        if (kovetkezo == cel) {
            celbaErt();
        }
    }

    /**
<<<<<<< HEAD
     * Az idő múlásakor az autó, ha haladó állapotban van,
     * megpróbál egy lépést tenni a célja felé.
     */
    @Override
    
    public void idoEltelt() {
    if (cel == null) {
        return;
    }

    if (allapot == AutoAllapot.HALAD) {
        halad(cel);
        return;
    }

    if (allapot == AutoAllapot.ELAKADT) {
        Utszakasz kovetkezo = gps.kovetkezoLepes(pozicio, cel);

        if (kovetkezo != null && kovetkezo.getHo() < 25) {
            allapotValtoztat(AutoAllapot.HALAD);
            halad(cel);
        }
    }
}
    /**
     * Az autó célba ér, ezért várakozó állapotba kerül.
=======
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
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
     */
    public void celbaErt() {
        allapotValtoztat(AutoAllapot.VARAKOZIK);
    }

    /**
<<<<<<< HEAD
     * Az autó elakad, ezért ELAKADT állapotba kerül.
=======
     * Az autó elakad, mert nem tud továbbhaladni.
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
     */
    public void elakad() {
        allapotValtoztat(AutoAllapot.ELAKADT);
    }

    /**
<<<<<<< HEAD
     * Az autó jeges úton megcsúszik.
     * Ha a következő útszakasz foglalt, ütközik, különben előrecsúszik.
     */
    @Override
    public void kicsuszik() {
        Utszakasz csuszasCel = pozicio.getSzakaszElore();
=======
     * Az autó megcsúszik jeges úton.
     * Ha ütközik egy másik járművel, mentésre váró állapotba kerül.
     */
    @Override
    public void kicsuszik() {
        Utszakasz csuszasCel = pozicio == null ? null : pozicio.getSzakaszElore();
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a

        if (csuszasCel == null) {
            return;
        }

<<<<<<< HEAD
        if (csuszasCel.foglaltE()) {
            utkozott(csuszasCel.getKozlekedoJarmu());
        } else {
            if (pozicio != null) {
                pozicio.setKozlekedoJarmu(null);
            }
            csuszasCel.belep(this);
            this.pozicio = csuszasCel;
=======
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
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
        }
    }

    /**
<<<<<<< HEAD
     * Az autó ütközött egy másik járművel, ezért mentésre vár.
     *
     * @param masik az a jármű, amellyel az autó ütközött
=======
     * Az autó ütközik egy másik járművel.
     * Regisztrálja magát az autómentőnél.
     *
     * @param masik a másik jármű
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
     */
    @Override
    public void utkozott(Jarmu masik) {
        allapotValtoztat(AutoAllapot.MENTESRE_VAR);
<<<<<<< HEAD
    }

    /**
     * Beállítja az autó új állapotát.
=======

        Automento automento = gps.getElsoAutomento();
        if (automento != null) {
            automento.mentesRegisztral(this);
        }
    }

    /**
     * Az autó állapotának módosítása.
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
     *
     * @param uj az új állapot
     */
    public void allapotValtoztat(AutoAllapot uj) {
        this.allapot = uj;
    }

    /**
     * Visszaadja az autó aktuális állapotát.
     *
<<<<<<< HEAD
     * @return az aktuális AutoAllapot
=======
     * @return az autó állapota
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
     */
    public AutoAllapot getAllapot() {
        return allapot;
    }

    /**
<<<<<<< HEAD
     * Visszaadja az autó célútszakaszát.
     *
     * @return az autó célja
=======
     * Visszaadja az autó célját.
     *
     * @return a célútszakasz
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
     */
    public Utszakasz getCel() {
        return cel;
    }

    /**
<<<<<<< HEAD
     * Szöveges állapotjelentést ad az info parancs számára.
     *
     * @return az autó állapotát leíró szöveg
     */
=======
     * Az info parancshoz szükséges szöveges reprezentáció.
     *
     * @return az autó állapotát leíró szöveg
     */
    @Override
>>>>>>> 829204be75183e00933c192c0f12aeaa2169315a
    public String getInfo() {
        return "info " + id + " (Auto):\n"
                + "pozicio: " + (pozicio == null ? "nincs" : pozicio.getId()) + "\n"
                + "cel: " + (cel == null ? "nincs" : cel.getId()) + "\n"
                + "allapot: " + allapot;
    }
}
