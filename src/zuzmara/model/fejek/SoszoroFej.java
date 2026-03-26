package zuzmara.model.fejek;

import zuzmara.model.Fej;
import zuzmara.model.Utszakasz;

/**
 * Só szórásával olvasztja a havat és a jeget idő függvényében
 * A Fej osztályból származik
 */
public class SoszoroFej extends Fej{
    private int sotartaly; //a só mennyiségét tárolja

    /**
     * Az adott útszakaszra sót szór, annak az olvadását elindítja, innentől az útszakasz kezeli az olvadást. 
     * Útszakaszonként a tartályban lévő só mennyiségét csökkenti.
     */
    @Override
    public void takarit(Utszakasz u){
        return;
    }
}
