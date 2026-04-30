package zuzmara.model;

import zuzmara.enums.AutoAllapot;

/**
 * Az Automento osztály az autók elszállításáért felelős.
 * Feladata a mentésre váró autók kezelése, illetve azok állapotának módosítása.
 * 
 * A működés során a Skeleton osztály segítségével naplózza a metódushívásokat.
 */
public class Automento {

    /**
     * Els szállít egy autót.
     * Az autó állapotát VÁRAKOZIK állapotra állítja,
     * jelezve, hogy a mentés sikeresen megtörtént.
     *
     * @param a az elszállítandó autó
     */
    public void elszallit(Auto a) {
        Skeleton.nyit("Automento.elszallit(Auto)");

        a.allapotValtoztat(AutoAllapot.VARAKOZIK);

        Skeleton.zar("Automento.elszallit() visszater");
    }

    /**
     * Regisztrál egy mentésre váró autót.
     * Skeleton szinten jelenleg csak naplózás történik.
     *
     * @param a a mentésre váró autó
     */
    public void mentesRegisztral(Auto a) {
        Skeleton.nyit("Automento.mentesRegisztral(Auto)");
        Skeleton.zar("Automento.mentesRegisztral() visszater");
    }
}
