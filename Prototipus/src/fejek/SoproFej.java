package fejek;

import zuzmara.model.Fej;
import zuzmara.model.Skeleton;
import zuzmara.model.Utszakasz;

/**
 * A hó elsöprését végzi egy sávval jobbra
 * A Fej osztályból származik
 */
public class SoproFej extends Fej {
    
    /**
     * Konstruktor, beállítja a fej életerejét 50-re
     */
    public SoproFej() {
        this.eletero = 50;
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
