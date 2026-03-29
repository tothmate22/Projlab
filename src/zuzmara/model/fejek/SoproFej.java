package zuzmara.model.fejek;

import zuzmara.model.Fej;
import zuzmara.model.Skeleton;
import zuzmara.model.Utszakasz;

/**
 * A hó elsöprését végzi egy sávval jobbra
 * A Fej osztályból származik
 */
public class SoproFej extends Fej {
    
    public SoproFej() {
        this.eletero = 50;
    }
    
    /**
     * Az adott útszakaszon, ha hó vagy feltört jég van arrébb tolja azt jobbra a szomszédos sávba, ha van, ha nincs akkor az út szélére. 
     * Ha nem hó vagy feltört jég van, akkor nem csinál semmit.
     */
    @Override
    public void takarit(Utszakasz u){
        Skeleton.getInstance().nyit("SoproFej.takarit(u1)");
        kopas();
        u.letakaritas();
        Skeleton.getInstance().zar("SoproFej.takarit() visszater");
    }
}
