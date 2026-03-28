package zuzmara.model;
package zuzmara.model;

/**
 * Absztrakt ősosztály a város úthálózatán mozgó objektumok (Auto, Busz, Hokotro) számára. 
 * Felelős a jármű aktuális pozíciójának nyilvántartásáért, és kikényszeríti a haladás 
 * metódusának megvalósítását a leszármazottakból.
 */
public abstract class Jarmu {
    
    /**
     * Annak az útszakasznak a referenciája, amelyen a jármű éppen áll.
     */
    protected Utszakasz pozicio;

    /**
     * A Jarmu konstruktora. 
     * Beállítja a jármű kezdeti pozícióját a pályán.
     * Mivel ez absztrakt osztály, ezt a leszármazottak (pl. Busz) 
     * konstruktora hívja meg a super() kulcsszóval.
     * * @param pozicio A kezdő útszakasz, ahova a járművet "lehelyezzük".
     */
    public Jarmu(Utszakasz pozicio) {
        this.pozicio = pozicio;
        // Itt szándékosan nincs Skeleton.nyit/zar, mert ezt a Busz/Auto/stb. 
        // konstruktora már becsomagolja a saját <<create>> hívásába!
    }

    /**
     * Absztrakt metódus, amely a helyváltoztatásra szolgál. 
     * A leszármazott osztályoknak (pl. Auto, Busz) saját maguknak kell 
     * implementálniuk a mozgás specifikus logikáját.
     * * @param cel A cél útszakasz, ahova a jármű lépni próbál.
     */
    public abstract void halad(Utszakasz cel);

    /**
     * Visszaadja a jármű aktuális pozícióját.
     * * @return Az útszakasz, amin a jármű éppen áll.
     */
    public Utszakasz getPozicio() {
        Skeleton.getInstance().nyit("Jarmu.getPozicio()");
        Skeleton.getInstance().zar("Jarmu.getPozicio() visszater");
        return this.pozicio;
    }

    /**
     * Beállítja a jármű új pozícióját.
     * Ezt a metódust tipikusan az Utszakasz hívja meg, amikor a jármű sikeresen 
     * rálép (belep) egy új mezőre.
     * * @param ujPozicio Az új útszakasz referenciája.
     */
    public void setPozicio(Utszakasz ujPozicio) {
        Skeleton.getInstance().nyit("Jarmu.setPozicio(Utszakasz)");
        this.pozicio = ujPozicio;
        Skeleton.getInstance().zar("Jarmu.setPozicio() visszater");
    }

    /**
     * Bár a 3.3.12 alapértelmezetten nem sorolja fel, a 3.3.3 Busz leírása utal rá, 
     * hogy az utkozott() a Jarmu ősosztályból (és az ICsuszhat-ból) is származik. 
     * Ezért biztosítunk egy alapértelmezett, felülírható implementációt.
     * * @param masik A másik jármű, amivel az ütközés történik.
     */
    public void utkozott(Jarmu masik) {
        Skeleton.getInstance().nyit("Jarmu.utkozott(Jarmu)");
        System.out.println("    [A Jarmu ososztaly alapertelmezett utkozott() metodusa lefutott.]");
        Skeleton.getInstance().zar("Jarmu.utkozott() visszater");
    }
}
