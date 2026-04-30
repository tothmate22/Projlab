

/**
 * A Fej absztrakt osztály, minden fej belőle származik
 */
public abstract class Fej {
    protected int eletero;    //a kopas kovetkeztében hátralévő éelterőt tárolja
    
    /**
     * Minden fej felüldefiniálja a saját módjára
     */
    public abstract void takarit(Utszakasz u);

    /**
     * A kopas megvalosítasa, a takaritas következtében
     */
    public void kopas(){
        this.eletero--;
    }

    /**
     * Szöveges állapotjelentést ad a teszteléshez.
     * @return A fej típusa és aktuális életereje.
     */
    public String getInfo() {
        return this.getClass().getSimpleName() + ": eletero=" + eletero;
    }

    /**
     * beállítja a fej életerejét
     * @param i az eletero uj erteke
     */
    public void setEletero(int i) {
        this.eletero = i;
    }

    public int getEletero() {
        return eletero;
    }
}
