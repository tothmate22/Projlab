package zuzmara.model;

import zuzmara.enums.Epulet;
import zuzmara.model.fejek.Sarkanyfej;

/**
 * Szkeleton osztály, a tesztesetekhez tartozó függvényeket tartalmazza, irányítja a tesztelést
 */
public class Skeleton {
    /**
     * A singleton class megvalositas
     * privat kontruktor, hogy ne lehessen tobbet letrehozni
     * getInstance, hogy megkapjuk
     */
    private Skeleton(){}
    
    /** Az aktuális hívási mélység, a behúzás meghatározásához. */
    private static int depth = 0;

    private static class SkeletonHelper{
        private static final Skeleton INSTANCE = new Skeleton();
    }
    public static Skeleton getInstance(){return SkeletonHelper.INSTANCE;}
    
    /**
     * Autó normal haladasanak a tesztelese
     */
    public void autoNormalHaladasTeszt(){}

    /**
     * Auto elakadasanak a tesztelese
     */
    public void autoElakadasTeszt(){}

    /**
     * Auto megcsuszik es utkozik teszteles
     */
    public void autoCsuszasUtkozesTeszt(){}

    /**
     * Auto elszallitasanak a tesztelese
     */
    public void autoElszallitasTeszt(){}

    /**
     * Hokotro nem tud belepni egy mezore teszteles
     */
    public void hokotroNemTudBelepniTeszt(){}

    /**
     * Hokotro sarkanyfejjel takarit teszt
     */
    public void hokotroSarkanyFejTeszt(){
        System.out.println("Hokotrón sárkányfej tesztelése...");
    }

    /**
     * Egy fej eleterej elfogy, ezutan elveszik teszteles
     */
    public void fejEleterejeElfogyTeszt(){}

    /**
     * Uj hokotro vasarlasanak a tesztje
     */
    public void hokotroVasarlasTeszt(){}

    /**
     * Busz celba eresenek a tesztje
     */
    public void buszCelbaErTeszt(){}

    /**
     * Busz balesetbe kerul teszteles
     */
    public void buszBalesetTeszt(){}

    /**
     * Busz utvonalat modositja a buszvezeto teszt
     */
    public void buszUtvonalmodositasTeszt(){}

    /**
     * Busz savvaltasanak a tesztelese
     */
    public void buszSikeresSavvaltasTeszt(){}

    /**
     * Busz megcsuszik es utkozik teszteles
     */
    public void buszCsuszasUtkozesTeszt(){}

    /**
     * Fejvasarlas tesztelese, ami sikeres
     */
    public void sikeresVasarlasTeszt(){}

    /**
     * Sikertelen fejvasarlas tesztelese
     */
    public void sikertelenVasarlasTeszt(){}

    /**
     * Nem szennyezet út takaritasanak tesztelese
    */
    public void urestakaritasTeszt(){}

    /**
     * Jeges ut takaritasanak tesztelese
     */
    public void jegtakaritasTeszt(){}

    /**
     * Utszakasz allapotvaltozasanak tesztelese
     */
    public void utszakaszIdojarasFrissitesTeszt(){}

    /**
     * Hidszakasz allapotvaltozasanak tesztelese
     */
    public void hidszakaszIdojarasFrissitesTeszt(){}

    /**
     * Alagutszakasz allapotvaltozasanak tesztelese
     */
    public void alagutSzakaszIdojarasFrissitesTeszt(){}

    /**
     * Hokotro soszorassal takarit teszt
     */
    public void soSzorasTeszt(){}
}
