package zuzmara.model.fejek;

import zuzmara.model.Fej;
import zuzmara.model.Skeleton;
import zuzmara.model.Utszakasz;

/** 
 * A HanyoFej osztaly a hó elhányását végzi, egyenesen az út szélére.
 * A Fej osztályból származik
 * 
 */
public class HanyoFej extends Fej {
    
    /**
     * Konstruktor, beállítja a fej életerejét 50-re
     */
    public HanyoFej() {
        this.eletero = 50;
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
