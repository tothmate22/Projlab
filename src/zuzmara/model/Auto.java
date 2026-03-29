package zuzmara.model;

import zuzmara.enums.AutoAllapot;
import zuzmara.interfaces.ICsuszhat;
import zuzmara.interfaces.ILepheto;

/**
 * Az Auto osztály egy közlekedő járművet reprezentál a rendszerben.
 * Az autó képes haladni, megcsúszni, ütközni, valamint különböző állapotokba kerülni.
 * 
 * Az osztály a Jarmu ősosztályból származik, és megvalósítja az ICsuszhat és ILepheto interfészeket.
 * A működés során a Skeleton osztály segítségével naplózza a metódushívásokat.
 */
public class Auto extends Jarmu implements ICsuszhat, ILepheto {

    /** Az autó cél útszakasza */
    private Utszakasz cel;

    /** Az autó aktuális állapota */
    private AutoAllapot allapot;

    /**
     * Auto konstruktor.
     * Létrehozza az autót a megadott kezdő pozícióval és állapottal.
     *
     * @param poz az autó kezdeti pozíciója
     * @param ap az autó kezdeti állapota
     */
    public Auto(Utszakasz poz, AutoAllapot ap) {
        super(poz);
        Skeleton.nyit("Auto <<create>>");
        this.allapot = ap;
        Skeleton.zar("Auto letrejott.");
    }

    /**
     * Az autó megpróbál a cél útszakaszra haladni.
     * A Skeleton kérdés alapján eldől, hogy a cél foglalt-e.
     * Ha nem foglalt, az autó belép és célba ér.
     * Ha foglalt, az autó elakad.
     *
     * @param cel a cél útszakasz
     */
    @Override
    public void halad(Utszakasz cel) {
        Skeleton.nyit("Auto.halad(Utszakasz)");
        this.cel = cel;

        boolean foglalt = Skeleton.kerdez("Foglalt a cel utszakasz? (i/n)");

        if (!foglalt) {
            cel.belep(this);
            celbaErt();
        } else {
            elakad();
        }

        Skeleton.zar("Auto.halad() visszater");
    }

    /**
     * Idő múlásának kezelése.
     * Skeleton szinten jelenleg nem tartalmaz működést.
     */
    @Override
    public void idoEltelt() {
        Skeleton.nyit("Auto.idoEltelt()");
        Skeleton.zar("Auto.idoEltelt() visszater");
    }

    /**
     * Az autó célba ér.
     * Állapota VÁRAKOZIK lesz.
     */
    public void celbaErt() {
        Skeleton.nyit("Auto.celbaErt()");
        allapotValtoztat(AutoAllapot.VARAKOZIK);
        Skeleton.zar("Auto.celbaErt() visszater");
    }

    /**
     * Az autó elakad (pl. túl nagy hó miatt).
     * Állapota ELAKADT lesz.
     */
    public void elakad() {
        Skeleton.nyit("Auto.elakad()");
        allapotValtoztat(AutoAllapot.ELAKADT);
        Skeleton.zar("Auto.elakad() visszater");
    }

    /**
     * Az autó megcsúszik.
     * A Skeleton kérdés alapján eldől, hogy történik-e ütközés.
     * Ha van előtte jármű, akkor meghívja az utkozott() metódust.
     */
    @Override
    public void kicsuszik() {
        Skeleton.nyit("Auto.kicsuszik()");

        boolean vanElotteJarmu = Skeleton.kerdez("Van az auto elott jarmu, amivel utkozik?(i/n)");

        if (vanElotteJarmu) {
            this.utkozott(null);
        }

        Skeleton.zar("Auto.kicsuszik() visszater");
    }

    /**
     * Az autó ütközik egy másik járművel.
     * Állapota MENTÉSRE_VÁR lesz.
     *
     * @param masik a másik jármű (skeleton szinten lehet null)
     */
    @Override
    public void utkozott(Jarmu masik) {
        Skeleton.nyit("Auto.utkozott(Jarmu)");
        allapotValtoztat(AutoAllapot.MENTESRE_VAR);
        Skeleton.zar("Auto.utkozott() visszater");
    }

    /**
     * Az autó állapotának megváltoztatása.
     *
     * @param uj az új állapot
     */
    public void allapotValtoztat(AutoAllapot uj) {
        Skeleton.nyit("Auto.allapotValtoztat()");
        this.allapot = uj;
        Skeleton.zar("Auto.allapotValtoztat() visszater");
    }
}

