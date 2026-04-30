package zuzmara.model;
import java.util.*;

public class Keresztezodes {
    private String id;
    private List<Uttest> kijaratok = new ArrayList<>();

    /**
     * Konstruktor.
     * @param id a kereszteződés egyedi azonosítója
     */
    public Keresztezodes(String id) {
        this.id = id;
    }

    /**
     * Hozzáad egy kijáratot a kereszteződéshez.
     * @param uttest
     */
    public void kijaratHozzaadas(Uttest uttest) {
        this.kijaratok.add(uttest);
    }


    /**
     * Visszaadja a kereszteződés kijáratait.
     * @return
     */
    public List<Uttest> getKijaratok() {
        return kijaratok;
    }


    /**
     * Visszaadja a kereszteződés adott indexű kijáratát.
     * @param index a kijárat indexe
     * @return a kijáratként szolgáló Uttest, ha az index érvényes, egyébként az első kijárat
     */
    public Uttest getKijarat(int index) {
        if (kijaratok.size() > index && index >= 0) {
            return kijaratok.get(index);
        }
        return kijaratok.get(0);
    }


    /**
     * Visszaadja a kereszteződés egyedi azonosítóját.
     * @return a kereszteződés azonosítója
     */
    public String getId() {
        return id;
    }

}