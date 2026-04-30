

/**
 * A jég feltörését végzi, hogy utána azt a megfelelő fejjel el lehessen takarítani.
 * A Fej osztályból származik
 */
public class JegtoroFej extends Fej {
    
    /**
     * Konstruktor, beállítja a fej életerejét 50-re
     * @param nev 
     */
    public JegtoroFej(String nev) {
        super(nev, 50);
    }
    
    /**
     * Meghívja az útszakasz letakarítási logikáját, átadva saját magát.
     * @param u Az érintett útszakasz.  
     */
    @Override
        public void takarit(Utszakasz u){
            u.letakaritas(this);
        }
}
