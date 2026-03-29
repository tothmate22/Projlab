package zuzmara.model;

import java.util.ArrayList;

public class Takarito extends Jatekos{
    ArrayList<Hokotro> hokotrok;

    /**
     * A hókotró és a hókotró fejek vásárlása esetén a játékos pénze az adott összeggel csökken
     */
    public void vasarol(int osszeg){
        Skeleton.getInstance().nyit("Takarito.vasarol(" + osszeg + ")");
        penztKap(-osszeg);          // penztKap(-2000)
        Hokotro ujHokotro = new Hokotro();  // «create» h2: Hokotro
        this.hokotrok.add(ujHokotro);
        Skeleton.getInstance().zar("Takarito.vasarol() visszater");
    }

    /**
     * A hókotró irányítását végző függvény amellyel megadja a játékos hogy merre szeretne menni
     */
    public void iranyit(Utszakasz kovetkezo){
        Skeleton.getInstance().nyit("Takarito.iranyit(u1)");
        hokotrok.get(0).halad(kovetkezo);
        Skeleton.getInstance().zar("Takarito.iranyit() visszater");
    }

    /**
     * A hókotró hozzáadása a takarítóhoz
     * @param h1
     */
    public void setHokotro(Hokotro h1) {
        hokotrok.add(h1);
        h1.setTakarito(this);
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
