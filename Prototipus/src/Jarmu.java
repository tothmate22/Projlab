/**
 * Általános absztrakt ősosztály minden, a pályán közlekedni képes egység számára.
 * Összefogja a közös állapotokat (pozíció) és definiálja a mozgás alapvető interfészét.
 * Biztosítja, hogy minden jármű lekérdezhető legyen a pillanatnyi tartózkodási helyéről.
 */
public abstract class Jarmu {

    /**
     * A jármű egyedi azonosítója (a specifikáció alapján a leszármazottak, pl. Busz ezen keresztül azonosíthatók).
     */
    protected String id;

    /**
     * Az az útszakasz (cella), amelyen a jármű jelenleg tartózkodik.
     * Láthatósága protected, hogy a leszármazottak (Busz, Auto, Hokotro) közvetlenül elérhessék.
     */
    protected Utszakasz pozicio;

    /**
     * A Jarmu osztály konstruktora.
     * @param id A jármű egyedi azonosítója.
     */
    public Jarmu(String id) {
        this.id = id;
    }

    /**
     * Visszaadja a jármű egyedi azonosítóját.
     * @return Az azonosító (String).
     */
    public String getId() {
        return this.id;
    }

    /**
     * Absztrakt metódus, amelyet minden konkrét jármű (Busz, Auto, Hokotro) a saját logikája szerint valósít meg.
     * Ez a metódus felelős az egyik útszakaszról a másikra való átlépésért.
     * * @param cel A cél útszakasz, amire a jármű lépni próbál.
     */
    public abstract void halad(Utszakasz cel);

    /**
     * Visszaadja a jármű aktuális pozícióját.
     * Algoritmus: Visszatér a pozicio attribútummal.
     * * @return Az aktuális útszakasz, ahol a jármű tartózkodik.
     */
    public Utszakasz getPozicio() {
        return this.pozicio;
    }

    /**
     * Beállítja a jármű pozícióját a paraméterként kapott útszakaszra.
     * Algoritmus: A pozicio attribútumnak értékül adja az u paramétert.
     * * @param u Az új útszakasz, ahova a jármű kerül.
     */
    public void setPozicio(Utszakasz u) {
        this.pozicio = u;
    }

    public Jarmu getJarmu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getJarmu'");
    }
}
