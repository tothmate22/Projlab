package zuzmara.model.fejek;

import zuzmara.model.Fej;
import zuzmara.model.Utszakasz;

/**
 * A hó elsöprését végzi egy sávval jobbra
 * A Fej osztályból származik
 */
public class SoproFej extends Fej {
    
    /**
     * Az adott útszakaszon, ha hó vagy feltört jég van arrébb tolja azt jobbra a szomszédos sávba, ha van, ha nincs akkor az út szélére. 
     * Ha nem hó vagy feltört jég van, akkor nem csinál semmit.
     */
    @Override
    public void takarit(Utszakasz u){
        return;
    }
}
