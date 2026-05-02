import java.util.List;
import java.util.ArrayList;

/**
 * Az Időjárás osztály felelős a hóesés szimulálásáért.
 * Nem játékos irányítja, az Óra jelzésére növeli az 
 * összes sáv hórétegét 1 cm-mel, az alagút-sávok 
 * kivételével. Figyeli, hogy a kereszteződésekben 
 * csak korlátozott mértékű hó halmozódhat fel.
 */
public class Idojaras implements ILepheto, IInfo {
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
    private String name;
    
    /**
     * Az Óra referenciája, amely alapján kalkuláljuk az időjárási frissítéseket
     */
    private Ora ora;

    /**
     * Konstruktor.
     * @param utszakaszok az összes útszakasz listája
     * @param maxIntersectionSnow maximális hó kereszteződésekben
     * @param name az időjárás neve
     * @param ora az Óra referenciája
     */
    public Idojaras(List<Utszakasz> utszakaszok, int maxIntersectionSnow, String name, Ora ora) {
        this.utszakaszok = utszakaszok;
        this.maxIntersectionSnow = maxIntersectionSnow;
        this.name = name;
        this.ora = ora;
    }

    /**
     *  Feliratkozás: A Skeleton hívja meg létrehozáskor
     */
    public void addUtszakasz(Utszakasz u) {
        utszakaszok.add(u);
    }

    /**
     * Frissíti az Utszakaszok listáját az összes jelenleg ismert útszakasszal.
     * @param ujUtszakaszok az összes Utszakasz listája
     */
    public void updateUtszakaszok(List<Utszakasz> ujUtszakaszok) {
        this.utszakaszok = new ArrayList<>(ujUtszakaszok);
    }

    /**
     * Meghívja az összes ismert Útszakasz idojarasFrissites() 
     * metódusát, átadva az Ora referenciáját.
     * Az egyes szakaszok maguk döntik el típusuk 
     * alapján, hogy kell-e havat kapniuk.
     */
    public void snowfall() {
        for (Utszakasz u : utszakaszok) {
            u.idojarasFrissites(ora);
        }
    }

    /**
     * Az ILepheto interfész által előírt metódus.
     * Az Óra hívja meg minden tick-ben,
     * hatására meghívódik a snowfall().
     */
    @Override
    public void idoEltelt() {
        snowfall();
    }

    public String getInfo() {
        return "info " + name + " (Időjárás):\n" + 
            "maxIntersectionSnow: " + maxIntersectionSnow + "\n" +
            "utszakaszokSzama: " + utszakaszok.size();
    }
}
