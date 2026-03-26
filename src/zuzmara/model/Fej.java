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
    public void kopas(){}
}
