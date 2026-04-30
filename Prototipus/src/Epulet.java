

public enum Epulet {
    MEGALLO,
    HAZ,
    MUNKAHELY,
    BAZIS,
    URES;

    static Epulet fromString(String epuletTipus) {
        switch (epuletTipus) {
            case "MEGALLO":
                return MEGALLO;
            case "HAZ":
                return HAZ;
            case "MUNKAHELY":
                return MUNKAHELY;
            case "BAZIS":
                return BAZIS;
            case "URES":
                return URES;
            default:
                throw new IllegalArgumentException("Ismeretlen épület típus: " + epuletTipus);
        }
    }
}