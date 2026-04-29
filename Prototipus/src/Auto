import java.util.Random;

/**
 * Az Auto osztály a pályán önállóan közlekedő, nem játékos által irányított járművet reprezentál.
 * Az autó a GPS segítségével a legrövidebb járható út következő útszakaszára próbál lépni.
 */
public class Auto extends Jarmu implements ILepheto, ICsuszhat {

    /** Az autó végső célútszakasza. */
    private Utszakasz cel;

    /** Az autó aktuális állapota. */
    private AutoAllapot allapot;

    /** Központi GPS referencia, amelyet az autó útvonalválasztásra használ. */
    private GPS gps;

    /** Véletlenszám-generátor a megcsúszás eldöntéséhez. */
    private Random random;

    /**
     * Létrehoz egy autót a megadott azonosítóval, kezdőpozícióval, céllal és állapottal.
     *
     * @param id az autó azonosítója
     * @param pozicio az autó kezdeti pozíciója
     * @param cel az autó célútszakasza
     * @param allapot az autó kezdeti állapota
     * @param gps a központi GPS objektum
     */
    public Auto(String id, Utszakasz pozicio, Utszakasz cel, AutoAllapot allapot, GPS gps) {
        super(id);
        this.pozicio = pozicio;
        this.cel = cel;
        this.allapot = allapot;
        this.gps = gps;
        this.random = new Random();
    }

    /**
     * Az autó megpróbál a megadott cél felé haladni.
     * A következő lépést a GPS számolja ki.
     *
     * @param cel a végső célútszakasz
     */
    @Override
    public void halad(Utszakasz cel) {
        this.cel = cel;

        if (allapot != AutoAllapot.HALAD || cel == null || gps == null) {
            return;
        }

        Utszakasz kovetkezo = gps.kovetkezoLepes(pozicio, cel);

        if (kovetkezo == null) {
            return;
        }

        if (kovetkezo.getHo() >= 25) {
            elakad();
            return;
        }

        if (kovetkezo.getJeg() > 0 && random.nextInt(100) < 20) {
            kicsuszik();
            return;
        }

        if (kovetkezo.foglaltE()) {
            return;
        }

        if (pozicio != null) {
            pozicio.setKozlekedoJarmu(null);
        }

        kovetkezo.belep(this);
        this.pozicio = kovetkezo;

        if (kovetkezo == cel) {
            celbaErt();
        }
    }

    /**
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
     */
    public void celbaErt() {
        allapotValtoztat(AutoAllapot.VARAKOZIK);
    }

    /**
     * Az autó elakad, ezért ELAKADT állapotba kerül.
     */
    public void elakad() {
        allapotValtoztat(AutoAllapot.ELAKADT);
    }

    /**
     * Az autó jeges úton megcsúszik.
     * Ha a következő útszakasz foglalt, ütközik, különben előrecsúszik.
     */
    @Override
    public void kicsuszik() {
        Utszakasz csuszasCel = pozicio.getSzakaszElore();

        if (csuszasCel == null) {
            return;
        }

        if (csuszasCel.foglaltE()) {
            utkozott(csuszasCel.getKozlekedoJarmu());
        } else {
            if (pozicio != null) {
                pozicio.setKozlekedoJarmu(null);
            }
            csuszasCel.belep(this);
            this.pozicio = csuszasCel;
        }
    }

    /**
     * Az autó ütközött egy másik járművel, ezért mentésre vár.
     *
     * @param masik az a jármű, amellyel az autó ütközött
     */
    @Override
    public void utkozott(Jarmu masik) {
        allapotValtoztat(AutoAllapot.MENTESRE_VAR);
    }

    /**
     * Beállítja az autó új állapotát.
     *
     * @param uj az új állapot
     */
    public void allapotValtoztat(AutoAllapot uj) {
        this.allapot = uj;
    }

    /**
     * Visszaadja az autó aktuális állapotát.
     *
     * @return az aktuális AutoAllapot
     */
    public AutoAllapot getAllapot() {
        return allapot;
    }

    /**
     * Visszaadja az autó célútszakaszát.
     *
     * @return az autó célja
     */
    public Utszakasz getCel() {
        return cel;
    }

    /**
     * Szöveges állapotjelentést ad az info parancs számára.
     *
     * @return az autó állapotát leíró szöveg
     */
    public String getInfo() {
        return "info " + id + " (Auto):\n"
                + "pozicio: " + (pozicio == null ? "nincs" : pozicio.getId()) + "\n"
                + "cel: " + (cel == null ? "nincs" : cel.getId()) + "\n"
                + "allapot: " + allapot;
    }
}
