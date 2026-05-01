

import java.util.ArrayList;

public class Takarito extends Jatekos implements IInfo {
    private Hokotro hokotro;

    /**
     * A Takarító konstruktora.
     * @param nev A játékos egyedi azonosítója
     * @param kezdoEgyenleg A kezdő pénzösszeg
     */
    public Takarito(String nev, int kezdoEgyenleg) {
        super(nev, kezdoEgyenleg);
        this.hokotro = null; // Kezdetben nincs hókotrója
    }
    
    
    /**
     * Visszaadja a játékos járművét.
     * * @return A takarító által irányított hókotró.
     */
    @Override
    public Jarmu getJarmu() {
        return this.hokotro;
    }

    /**
     * Új hókotró vásárlása.
     * Csak akkor sikeres, ha van elegendő pénz és nincs aktív hókotrója
     */
    public void vasarolHokotro() {
        if (this.egyenleg >= 1000 && this.hokotro == null) {
            this.hokotro = new Hokotro();
            this.hokotro.setTakarito(this);
            this.egyenleg -= 1000;
        }
    }

    /**
     * Megadott típusú takarítófej vásárlása a hókotró számára.
     * Ellenőrzi az egyenleget és azt, hogy a hókotrónak van-e már ilyen feje
     * @param tipus A vásárolni kívánt fej típusa
     * @param ar A fej ára.
     */
    public void vasarolFej(Fej ujFej, int ar) {
        if (this.hokotro != null && this.egyenleg >= ar) {
            this.hokotro.addFej(ujFej); 
            this.egyenleg -= ar;
        }
    }

/**
     * A hókotró irányítása a megadott útszakaszra.
     * @param kovetkezo A cél útszakasz.
     */
    public void iranyit(Utszakasz kovetkezo) {
        if (this.hokotro != null) {
            this.hokotro.halad(kovetkezo);
        }
    }

    /**
     * Váltás a hókotró következő elérhető fejére
     */
    public void switchHead() {
        if (this.hokotro != null) {
            this.hokotro.cserelFej();
        }
    }

    /**
     * Állapotjelentést ad a teszteléshez
     * @return Szöveges formátum: név, egyenleg és a hókotró adatai
     */
    public String getInfo() {
        return "Takarító adatai: nev=" + nev + 
               ", egyenleg=" + egyenleg + 
               ", hokotro=" + (hokotro != null ? "van" : "nincs");
    }

    public void setHokotro(Hokotro h) {
        this.hokotro = h;
        if (h != null) {
            h.setTakarito(this);
        }
    }

    public Hokotro getHokotro() {
        return this.hokotro;
    }


    public void vasarolHokotro(String hokotroNev, Takarito takarito, Utszakasz kezdo, Palya palya) {
        // Ellenőrizzük, van-e elég pénz (pl. 1000) és nincs-e már hókotrója
        if (this.egyenleg >= 1000 && this.hokotro == null) {
            // Létrehozzuk az új hókotrót a megadott névvel és kezdőpozícióval
            Hokotro ujHokotro = new Hokotro(hokotroNev, kezdo);
            
            // Beállítjuk a takarítót és a pályát
            ujHokotro.setTakarito(this);
            this.hokotro = ujHokotro;
            
            // Hozzáadjuk a pályához, hogy a többi jármű is lássa
            palya.addJarmu(hokotroNev, ujHokotro);
            
            // Levonjuk a pénzt
            this.egyenleg -= 1000;
            
            System.out.println("Sikeres vasarlas: " + hokotroNev);
        } else {
            System.out.println("Sikertelen vasarlas: nincs eleg penz vagy mar van hokotro.");
        }
    }


    public void vasarolFej(String fejNev, String fejTipus, Hokotro hokotro2, Palya palya) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'vasarolFej'");
    }
}
