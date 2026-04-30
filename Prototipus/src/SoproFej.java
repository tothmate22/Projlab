

/**
 * A hó elsöprését végzi egy sávval jobbra
 * A Fej osztályból származik
 */
public class SoproFej extends Fej {
    
    /**
     * Konstruktor, beállítja a fej életerejét 50-re
     * @param nev 
     */
    public SoproFej(String nev) {
        super(nev, 50);
    }

    /**
     * Meghívja az útszakasz letakarítás metódusát saját magát megadva paraméterként
     * @param u Az érintett útszakasz
     */
    @Override
    public void takarit(Utszakasz u){
        u.letakaritas(this);
    }
}
