package zuzmara.model.fejek;

import zuzmara.model.Fej;
import zuzmara.model.Utszakasz;

/** 
 * A HanyoFej osztaly a hó elhányását végzi, egyenesen az út szélére.
 * A Fej osztályból származik
 * 
 */
public class HanyoFej extends Fej {
    
    /**
     * Az adott útszakaszon, ha hó vagy feltört jég van azt kilöki egyenesen az út szélére. 
     * Ha nem hó vagy feltört jég van, akkor nem csinál semmit.
     */
    @Override
    public void takarit(Utszakasz u){
        return;
    } 
}
