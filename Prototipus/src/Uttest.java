
import java.util.*;

/**
 * Az Uttest osztály egy úttestet reprezentál, amely több savból is állhat.
 * Felelősség: az osztály tárolja az úttest szakaszait, és biztosítja a járművek mozgatását az úttesten, valamint a sávváltás lehetőségét. Az Uttest osztály felelős a járművek helyzetének frissítéséért és a sávváltás logikájáért is.
 */
public class Uttest {
    private List<List<Utszakasz>> savok = new ArrayList<>();
    private String id;
    private Keresztezodes celKeresztezodes;


    /**
     * Konstruktor, amely létrehozza az úttestet a megadott szakaszokkal.
     * @param savok az úttest szakaszait tartalmazó lista, ahol minden belső lista egy savot reprezentál
     */
    public Uttest(List<List<Utszakasz>> savok, String id) {
        this.savok = savok;
        this.id = id;
    }

    /**
     * Visszaadja az úttest célkereszteződését.
     * @return a célkereszteződés
     */
    public Keresztezodes getCelKeresztezodes() {
        return celKeresztezodes;
    }

    /**
     * Beállítja az úttest célkereszteződését.
     * @param celKeresztezodes a beállítandó célkereszteződés
     */
    public void setCelKeresztezodes(Keresztezodes celKeresztezodes) {
        this.celKeresztezodes = celKeresztezodes;
    }

    /**
     * Visszaadja az úttest sávjait.
     * @return
     */
    public List<List<Utszakasz>> getSavok() {
        return savok;
    }

    /**
     * Visszaadja a megadott útszakaszhoz tartozó sávot.
     * @param szakasz
     * @return
     */
    List<Utszakasz> getSzakaszSavja(Utszakasz szakasz) {
        for (List<Utszakasz> sav : savok) {
            if (sav.contains(szakasz)) {
                return sav;
            }
        }
        return null;
    }

    /**
    * Hozzáad egy útszakaszt az úttest adott sávjához.
    * @param szakasz az útszakasz, amelyet hozzá kell adni
    * @param savIndex a sáv indexe, amelyhez a szakaszt hozzá kell adni
    */
    public void addUtszakasz(Utszakasz szakasz, int savIndex) {
        while (savok.size() <= savIndex) {
            savok.add(new ArrayList<Utszakasz>());
        }
        
        // Most már garantált, hogy létezik a lista a savIndex helyen
        if (savIndex >= 0) {
            savok.get(savIndex).add(szakasz);
        }
    }

    public Utszakasz getUtszakasz(int savIndex, int szakaszIndex) {
        if (savIndex < savok.size() && savIndex >= 0) {
            List<Utszakasz> sav = savok.get(savIndex);
            if (szakaszIndex < sav.size() && szakaszIndex >= 0) {
                return sav.get(szakaszIndex);
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }
    
}
