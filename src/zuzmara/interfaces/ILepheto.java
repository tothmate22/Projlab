package zuzmara.interfaces;
 
/**
 * Interfész a játékbeli idő múlására érzékeny objektumok számára.
 * Biztosítja, hogy a megvalósító osztályok egységesen tudjanak
 * reagálni az időzítő (Óra) jelzéseire.
 */
public interface ILepheto {
 
    /**
     * Az Ora osztály hívja meg minden időbélyegnél (tick).
     * Az implementáló osztályok ebben a metódusban hajtják végre
     * az idő múlásához kötött logikájukat (pl. haladás, hóesés, várakozás).
     */
    void idoEltelt();
}
 