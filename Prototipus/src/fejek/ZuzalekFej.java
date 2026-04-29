package zuzmara.model.fejek;

import zuzmara.model.Fej;
import zuzmara.model.Utszakasz;

public class ZuzalekFej extends Fej {
    private int zuzottkoTartaly;

    /**
     * Konstruktor a zúzalékszóró fejhez.
     * @param tartalyKapacitas A tartály kezdeti tartalma.
     */
    public ZuzalekFej(int tartalyKapacitas) {
        this.zuzottkoTartaly = tartalyKapacitas;
        this.eletero = 50; // Alapértelmezett életerő
    }

    /**
     * Elvégzi a zúzalékszórást az adott útszakaszon.
     * Meghívja az útszakasz letakarítás metódusát, majd csökkenti a tartály tartalmát
     * @param u Az érintett útszakasz.
     */
    @Override
    public void takarit(Utszakasz u) {
        if (u.letakaritas(this)) {
            this.zuzottkoTartaly -= 5;
        }
    }

    /**
     * Állapotjelentést ad a teszteléshez
     * @return A fej típusa, életereje és a maradék zúzalék mennyisége.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + ", zuzalek=" + zuzottkoTartaly;
    }

    public int getZuzottkoTartaly() {
        return zuzottkoTartaly;
    }

    public void setZuzottkoTartaly(int zuzottkoTartaly) {
        this.zuzottkoTartaly = zuzottkoTartaly;
    }
}
