package zuzmara.model;
/**
 * Ősosztály: Jatekos (absztrakt ősosztály).
 * Felelősség: A játékos belső, logikai leképezése a rendszerben a busz irányítására vonatkozóan. 
 * Ő hozza meg a navigációs döntéseket a busz számára. Egy buszvezetőhöz pontosan egy busz tartozik.
 */
public class Buszvezeto extends Jatekos {

    /**
     * Referencia arra az egyetlen buszra, amit a játékos a játék során irányít, 
     * a pénzt az ősosztály kezeli.
     */
    private Busz sajatBusz;

    /**
     * A Buszvezeto konstruktora.
     */
    public Buszvezeto(Busz b) {
        // Meghívjuk a Jatekos ősosztály konstruktorát (pl. egy alapértelmezett névvel)
        super("Buszsofor_1"); 
        
        this.sajatBusz = b;
        
        Skeleton.nyit("Buszvezeto <<create>>");
        Skeleton.zar("Buszvezeto letrejott.");
    }

    /**
     * A külső "Játékos" aktor ezen a metóduson keresztül adja ki a parancsot 
     * a busz útvonalának módosítására.
     * (A penztKap metódust a Jatekos ősosztályból örökli).
     * * @param ujLepes A szomszédos útszakasz, amire a sofőr navigálni szeretne.
     */
    public void utvonalatValtoztat(Utszakasz ujLepes) {
        Skeleton.nyit("Buszvezeto.utvonalatValtoztat(Utszakasz)");

        // Szkeleton belső ellenőrzés: ha van buszunk, kiadjuk a parancsot
        if (this.sajatBusz != null) {
            System.out.println("    [A buszvezeto kiteszi az indexet a kivalasztott utszakasz fele.]");
            
            // A Busz osztályban az előbb megírt függvényt hívjuk meg!
            this.sajatBusz.setKovetkezoLepes(ujLepes);
        }

        Skeleton.zar("Buszvezeto.utvonalatValtoztat() visszater");
    }
}
