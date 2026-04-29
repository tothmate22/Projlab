package zuzmara.model.fejek;

import zuzmara.model.Fej;
import zuzmara.model.Skeleton;
import zuzmara.model.Utszakasz;

/**
 * Só szórásával olvasztja a havat és a jeget idő függvényében
 * A Fej osztályból származik
 */
public class SoszoroFej extends Fej{
    private int sotartaly; //a só mennyiségét tárolja

    /**
     * Konstruktor a sószórós fejhez.
     * @param sotartaly A tartály induló sómennyisége.
     */
    public SoszoroFej(int sotartaly) {
        this.sotartaly = sotartaly;
        this.eletero = 50;
    }
    
    /**
    * Sót szór az útszakaszra, elindítva annak olvadási folyamatát
     * Ha a szórás sikeres, csökkenti a tartály tartalmát.
     * @param u Az érintett útszakasz
     */
    @Override
    public void takarit(Utszakasz u){
        u.letakaritas(this);
        this.sotartaly-=5;
    }

    /**
     * Állapotjelentés a teszteléshez.
     * @return A fej adatai kiegészítve a sómennyiséggel.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + ", so=" + sotartaly;
    }

    public int getSotartaly() {
        return sotartaly;
    }

    public void setSotartaly(int sotartaly) {
        this.sotartaly = sotartaly;
    }
}
