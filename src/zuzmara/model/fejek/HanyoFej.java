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
    
    public HanyoFej() {
        this.eletero = 50;
    }
    
    /**
     * Az adott útszakaszon, ha hó vagy feltört jég van azt kilöki egyenesen az út szélére. 
     * Ha nem hó vagy feltört jég van, akkor nem csinál semmit.
     */
    @Override
    public void takarit(Utszakasz u){
        Skeleton.getInstance().nyit("HanyoFej.takarit(u1)");
        kopas();
        u.letakaritas();
        Skeleton.getInstance().zar("HanyoFej.takarit() visszater");
    } 
}
