package zuzmara.model;

/**
 * A Fej absztrakt osztály, minden fej belőle származik
 */
public abstract class Fej {
    private int eletero;    //a kopas kovetkeztében hátralévő éelterőt tárolja
    
    /**
     * Minden fej felüldefiniálja a saját módjára
     */
    public void takarit(Utszakasz u){
        return;
    }

    /**
     * A kopas megvalosítasa, a takaritas következtében
     */
    public void kopas(){
        Skeleton.getInstance().nyit("Fej.kopas()");
        this.eletero--;
        Skeleton.getInstance().zar("Fej.kopas() visszater");
    }

    /**
     * beállítja a fej életerejét
     * @param i
     */
    public void setEletero(int i) {
        this.eletero = i;
    }

    public int getEletero() {
        return eletero;
    }
}
