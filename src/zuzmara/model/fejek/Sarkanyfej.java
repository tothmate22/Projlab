package zuzmara.model.fejek;

import zuzmara.model.Fej;
import zuzmara.model.Utszakasz;

/**
 * Biokerozin használatával felolvasztja az összes havat és jeget azonnal
 * A Fej osztályból származik
 */
public class Sarkanyfej extends Fej {
    private int kerozintartaly; //a biokerozin mennyiségét tárolja

    public Sarkanyfej(int kerozintartaly) {
        this.kerozintartaly = kerozintartaly;
    }
    /**
     * Az adott útszakaszon lévő jeget és havat azonnal felégeti, ezzel az egyből el is tűnik onnan, ennek az állítását továbbítja az útszakasz felé
     */
    @Override
    public void takarit(Utszakasz u){
        return;
    }
}
