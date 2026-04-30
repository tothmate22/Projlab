
/**
 * Biokerozin használatával felolvasztja az összes havat és jeget azonnal
 * A Fej osztályból származik
 */
public class Sarkanyfej extends Fej {
    private int kerozintartaly; //a biokerozin mennyiségét tárolja

    /**
     * Konstruktor a sárkányfejhez.
     * @param kerozintartaly A tartály induló kapacitása.
     */
    public Sarkanyfej(int kerozintartaly) {
        this.kerozintartaly = kerozintartaly;
        this.eletero = 50;
    }
    
    /**
     * Meghívja az útszakasz letakarítás metódusát, és ha a takarítás sikeres volt,
     * csökkenti a kerozin mennyiségét.
     * @param u Az érintett útszakasz.
     */
    @Override
    public void takarit(Utszakasz u) {
        if (u.letakaritas(this)) {
            this.kerozintartaly -= 10;
        }
    }

    /**
     * Visszaadja a kerozintartály aktuális állapotát.
     */
    public int getKerozintartaly() {
        return kerozintartaly;
    }

    /**
     * Állapotjelentés a teszteléshez.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + ", kerozin=" + kerozintartaly;
    }
}
