/**
 * Az AutoAllapot enum az autó lehetséges belső állapotait írja le.
 * Az autó viselkedése az aktuális állapottól függ:
 * haladhat, várakozhat, elakadhat, vagy mentésre várhat.
 */
public enum AutoAllapot {

    /**
     * Az autó aktívan közlekedik a célja felé.
     */
    HALAD,

    /**
     * Az autó célba ért, vagy mentés után újra használható állapotban van.
     */
    VARAKOZIK,

    /**
     * Az autó mély hó vagy más akadály miatt nem tud továbbhaladni.
     */
    ELAKADT,

    /**
     * Az autó ütközés után autómentőre vár.
     */
    MENTESRE_VAR;

    static AutoAllapot fromString(String allapotStr) {
        switch (allapotStr) {
            case "HALAD":
                return HALAD;
            case "VARAKOZIK":
                return VARAKOZIK;
            case "ELAKADT":
                return ELAKADT;
            case "MENTESRE_VAR":
                return MENTESRE_VAR;
            default:
                throw new IllegalArgumentException("Ismeretlen autóállapot: " + allapotStr);
        }
    }
}
