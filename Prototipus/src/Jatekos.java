/**
 * A játékban részt vevő emberi vagy MI általi szereplők (Buszvezető, Takarító) közös jellemzőit definiáló absztrakt osztály.
 * Felelőssége a játékos azonosításához szükséges adatok (név) és a gazdasági erőforrások (egyenleg) kezelése.
 */
public abstract class Jatekos {

    /**
     * A játékos egyedi azonosítója.
     * Láthatósága protected, típusa String.
     */
    protected String nev;

    /**
     * A játékos aktuális pontszáma/pénze, amelyet a feladatok teljesítéséért kap.
     * Láthatósága protected, típusa int.
     */
    protected int egyenleg;

    /**
     * A Jatekos ősosztály konstruktora, mely inicializálja az azonosítót és a kezdő egyenleget.
     * * @param nev A játékos neve.
     * @param egyenleg A játékos induló egyenlege.
     */
    public Jatekos(String nev, int egyenleg) {
        this.nev = nev;
        this.egyenleg = egyenleg;
    }

    /**
     * Növeli a játékos egyenlegét a paraméterben kapott pozitív egész számmal.
     * Algoritmus: Az egyenleg változó értékét megnöveli az osszeg értékével (egyenleg = egyenleg + osszeg).
     * * @param osszeg Az összeg, amivel a játékos egyenlege növekszik.
     */
    public void penztKap(int osszeg) {
        if (osszeg > 0) {
            this.egyenleg = this.egyenleg + osszeg;
        }
    }

    /**
     * Lekérdezi a játékos aktuális egyenlegét a ponttáblázat vagy a tesztek számára.
     * Algoritmus: Visszatér az egyenleg attribútummal.
     * * @return A játékos aktuális egyenlege.
     */
    public int getEgyenleg() {
        return this.egyenleg;
    }

    /**
     * Visszaadja a játékos nevét.
     * Algoritmus: Visszatér a nev attribútummal.
     * * @return A játékos neve.
     */
    public String getNev() {
        return this.nev;
    }

    protected abstract Jarmu getJarmu();
}
