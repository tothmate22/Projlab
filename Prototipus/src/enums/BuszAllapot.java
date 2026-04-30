package enums;
/**
 * A busz belső állapotainak lehetőségei.
 * Három eshetőséget fed le: normál működés, baleset miatti javítás, vagy mély hó miatti elakadás.
 */
public enum BuszAllapot {
    /**
     * Működőképes állapot. A busz reagál a navigációs parancsokra.
     */
    NORMAL,

    /**
     * Baleset utáni kényszerpihenő (1 játékbeli óra / 60 tick). Nem mozdul.
     */
    JAVITAS_ALATT,

    /**
     * Mély hó miatti mozgásképtelenség. A busz nem tud továbbhaladni.
     */
    ELAKADT
}
