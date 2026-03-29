package zuzmara.model.fejek;

import zuzmara.model.Fej;
import zuzmara.model.Skeleton;
import zuzmara.model.Utszakasz;

/**
 * A jég feltörését végzi, hogy utána azt a megfelelő fejjel el lehessen takarítani.
 * A Fej osztályból származik
 */
public class JegtoroFej extends Fej {
    
    public JegtoroFej() {
        this.eletero = 50;
    }
    
    /**
     * Az adott útszakaszon, ha jég van azt feltöri és feltört jégként ott hagyja az úton. 
     * Az út csúszóssága ezzel eltűnik. 
     * A feltört jég mivel nem csúszik gyakorlatilag hóvá alakul, ezt az alakítást továbbítja az útszakasz felé
     */
    @Override
        public void takarit(Utszakasz u){
            Skeleton.getInstance().nyit("JegtoroFej.takarit(u1)");
            kopas();
            u.letakaritas();
            Skeleton.getInstance().zar("JegtoroFej.takarit() visszater");
        }
}
