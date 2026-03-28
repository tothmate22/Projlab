package zuzmara.model;

import java.util.ArrayList;

public class Takarito extends Jatekos{
    ArrayList<Hokotro> hokotrok;

    /**
     * A hókotró és a hókotró fejek vásárlása esetén a játékos pénze az adott összeggel csökken
     */
    public void vasarol(int osszeg){}

    /**
     * A hókotró irányítását végző függvény amellyel megadja a játékos hogy merre szeretne menni
     */
    public void iranyit(Utszakasz kovetkezo){}

    /**
     * A hókotró hozzáadása a takarítóhoz
     * @param h1
     */
    public void setHokotro(Hokotro h1) {
        hokotrok.add(h1);
    }

    /**
     * A Takarító konstruktora, amely meghívja a Jatekos konstruktorát a név átadásával, és inicializálja a hókotró listát.
     * @param nev
     */
    public Takarito(String nev) {
        super(nev);
        this.hokotrok = new ArrayList<>();
    } 
}
