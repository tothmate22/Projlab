package zuzmara.model;

import zuzmara.interfaces.ILepheto;
import java.util.List;

/**
 * Az Időjárás osztály felelős a hóesés szimulálásáért.
 * Nem játékos irányítja, az Óra jelzésére növeli az 
 * összes sáv hórétegét 1 cm-mel, az alagút-sávok 
 * kivételével. Figyeli, hogy a kereszteződésekben 
 * csak korlátozott mértékű hó halmozódhat fel.
 */
public class Idojaras implements ILepheto{
    /**
     * A kereszteződésekben felhalmozódható maximális 
     * hóvastagság cm-ben.
     */
    private int maxIntersectionSnow;

    /**
     * Az összes ismert útszakasz listája.
     * Az idoEltelt() hatására meghívja rajtuk az 
     * idojarasFrissites() metódust.
     */
    private List<Utszakasz> utszakaszok;

    /**
     * Konstruktor.
     * @param utszakaszok az összes útszakasz listája
     * @param maxIntersectionSnow maximális hó kereszteződésekben
     */
    public Idojaras(List<Utszakasz> utszakaszok, int maxIntersectionSnow) {
        this.utszakaszok = utszakaszok;
        this.maxIntersectionSnow = maxIntersectionSnow;
    }

    /**
     * Meghívja az összes ismert Útszakasz idojarasFrissites() 
     * metódusát. Az egyes szakaszok maguk döntik el típusuk 
     * alapján, hogy kell-e havat kapniuk.
     */
    public void snowfall() {
        
    }

    /**
     * Az ILepheto interfész által előírt metódus.
     * Az Óra hívja meg minden tick-ben,
     * hatására meghívódik a snowfall().
     */
    @Override
    public void idoEltelt() {
        
    }
}
