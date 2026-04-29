package zuzmara.model.fejek;

import zuzmara.model.Fej;
import zuzmara.model.Skeleton;
import zuzmara.model.Utszakasz;

/**
 * A jég feltörését végzi, hogy utána azt a megfelelő fejjel el lehessen takarítani.
 * A Fej osztályból származik
 */
public class JegtoroFej extends Fej {
    
    /**
     * Konstruktor, beállítja a fej életerejét 50-re
     */
    public JegtoroFej() {
        this.eletero = 50;
    }
    
    /**
     * Meghívja az útszakasz letakarítási logikáját, átadva saját magát.
     * @param u Az érintett útszakasz.  
     */
    @Override
        public void takarit(Utszakasz u){
            u.letakaritas(this);
        }
}
