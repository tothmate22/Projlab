/**
 * A játékos által irányított aktor, aki a busz navigálásáért felelős.
 * Feladata a busz útvonalának stratégiai megtervezése és a végállomások közötti közlekedés vezérlése.
 * Örökli a Jatekos osztály pénzkezelési képességeit, így ő gyűjti a sikeres fordulókért járó jutalmat.
 */
public class Buszvezeto extends Jatekos implements IInfo {

    /**
     * Referencia arra az egyetlen buszra, amit a játékos a játék során irányít.
     */
    private Busz sajatBusz;

    /**
     * Buszvezeto konstruktora.
     * * @param nev A játékos neve.
     * @param egyenleg A játékos kezdő egyenlege.
     */
    public Buszvezeto(String nev, int egyenleg) {
        super(nev, egyenleg);
    }

    /**
     * Beállítja a játékos saját buszát.
     * * @param busz A busz, amit a játékos vezet.
     */
    public void setSajatBusz(Busz busz) {
        this.sajatBusz = busz;
        if (busz != null) {
            busz.felszall(this);
        }
    }

    /**
     * Visszaadja a játékos járművét.
     * * @return A buszvezető által irányított busz.
     */
    @Override
    public Jarmu getJarmu() {
        return this.sajatBusz;
    }

    /**
     * A játékos ezen a metóduson keresztül adja ki a parancsot a busz mozgási szándékának módosítására.
     * A metódus a paraméterként kapott útszakaszt továbbadja a busznak.
     * * @param ujCel A cél útszakasz, amerre a busz haladni fog.
     */
    public void utvonalatValtoztat(Utszakasz ujCel) {
        if (this.sajatBusz != null) {
            this.sajatBusz.setKovetkezoLepes(ujCel);
        }
    }

    /**
     * Visszaadja a játékos saját buszát.
     * * @return A játékos által irányított busz, vagy null ha még nincs.
     */
    public Busz getSajatBusz() {
        return this.sajatBusz;
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }
}
