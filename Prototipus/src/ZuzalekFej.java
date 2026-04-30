

public class ZuzalekFej extends Fej {
    private int zuzottkoTartaly;

    /**
     * Konstruktor a zúzalékszóró fejhez.
     * @param tartalyKapacitas A tartály kezdeti tartalma.
     */
    public ZuzalekFej(String nev, int tartalyKapacitas) {
        super(nev, 50);
        this.zuzottkoTartaly = tartalyKapacitas;
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
