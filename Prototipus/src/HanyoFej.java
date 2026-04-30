

/** 
 * A HanyoFej osztaly a hó elhányását végzi, egyenesen az út szélére.
 * A Fej osztályból származik
 * 
 */
public class HanyoFej extends Fej {
    
    /**
     * Konstruktor, beállítja a fej életerejét 50-re
     * @param nev 
     */
    public HanyoFej(String nev) {
        super(nev, 50);
    }
    
    /**
     * Meghívja az útszakasz letakarítás metódusát saját magát megadva paraméterként.
     * @param u Az érintett útszakasz.
     */
    @Override
    public void takarit(Utszakasz u) {
        u.letakaritas(this);
    }
}
