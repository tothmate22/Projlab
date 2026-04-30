package zuzmara.model;

import zuzmara.enums.AutoAllapot;
import zuzmara.enums.Epulet;
import zuzmara.model.fejek.JegtoroFej;
import zuzmara.model.fejek.Sarkanyfej;
import zuzmara.model.fejek.SoszoroFej;

import java.time.Clock;
import java.util.Scanner;
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

    private static Scanner scanner = new Scanner(System.in);
    
    private static class SkeletonHelper{
        private static final Skeleton INSTANCE = new Skeleton();
    }
    public static Skeleton getInstance(){return SkeletonHelper.INSTANCE;}

    public static void nyit(String metodusNev) {
        nyomtatIndent();
        System.out.println("--> " + metodusNev);
        depth++; 
    }

    public static void zar(String visszateres) {
        depth--; 
        nyomtatIndent();
        System.out.println("<-- " + visszateres);
    }
    
   public static boolean kerdez(String kerdes) {
        while (true) { // Végtelen ciklus, amíg nem kapunk jó választ
            nyomtatIndent();
            System.out.print("[?] " + kerdes + " (i/n): ");
            String valasz = scanner.nextLine().trim().toLowerCase();
            
            // Ha a válasz egyértelmű IGEN
            if (valasz.equals("i") || valasz.equals("igen") || valasz.equals("y") || valasz.equals("yes")) {
                return true;
            } 
            // Ha a válasz egyértelmű NEM
            else if (valasz.equals("n") || valasz.equals("nem") || valasz.equals("no")) {
                return false;
            } 
            // Ha valami hülyeséget írt be
            else {
                nyomtatIndent();
                System.out.println("[!] Hibas bemenet! Kerlek csak 'i' (igen) vagy 'n' (nem) betuvel valaszolj!");
            }
        }
    }

    private static void nyomtatIndent() {
        for (int i = 0; i < depth; i++) {
            System.out.print("    "); 
        }
    }

    /**
     * Autó normal haladasanak a tesztelese
     */
    public void autoNormalHaladasTeszt() {
        System.out.println("\n--- [ TESZT INDUL: Auto normal haladas ] ---");
        System.out.println("--- [ INICIALIZALAS ] ---");
        // 1: new Utszakasz()
        Uttest ut = new Uttest();
        Utszakasz u1 = new Utszakasz(ut, "HAZ");
        Utszakasz u2 = new Utszakasz(ut, "MUNKAHELY");
        // 2: new Auto()
        Auto a1 = new Auto(u1, AutoAllapot.HALAD);

        // 3: pozíciók beállítása
        a1.setPozicio(u1);
        u1.setKozlekedoJarmu(a1);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        a1.halad(u2);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Auto elakadasanak a tesztelese
     * Az autó új útszakaszra lép rá, amin ha legalább 20cm hó van, elakad
     */
    public void autoElakadasTeszt() {
        System.out.println("\n--- [ TESZT INDUL: Auto elakad ] ---");
        System.out.println("--- [ INICIALIZALAS ] ---");
        // 1: new Utszakasz()
        Uttest ut = new Uttest();
        Utszakasz u1 = new Utszakasz(ut, "HAZ");
        Utszakasz u2 = new Utszakasz(ut, "MUNKAHELY");
        // 2: new Auto()
        Auto a1 = new Auto(u1, AutoAllapot.HALAD);
        // 3: pozíciók beállítása
        a1.setPozicio(u1);
        u1.setKozlekedoJarmu(a1);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");
        //ha 25 cm hó van legalább a sávon, akkor elakad
        a1.halad(u2); // itt a kerdez() dönti el

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Auto megcsuszik es utkozik teszteles
     * Felhasználótól megkérdezi van-e előtte autó aminek nekiütközne
     */
    public void autoCsuszasUtkozesTeszt() {
        System.out.println("\n--- [ TESZT INDUL: Auto csuszas es utkozes ] ---");
        System.out.println("--- [ INICIALIZALAS ] ---");
        // 1: new Utszakasz()
        Uttest ut = new Uttest();
        Utszakasz u1 = new Utszakasz(ut, "HAZ");
        // 2: new Auto()
        Auto a1 = new Auto(u1, AutoAllapot.HALAD);
        // 3: pozíciók beállítása
        a1.setPozicio(u1);
        u1.setKozlekedoJarmu(a1);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");
        //Ha van előtte autó akkor megváltozik az állapota, ha nincs akkor nincs gond.
        a1.kicsuszik(); // itt kérdez: van-e előtte autó

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Auto elszallitasanak a tesztelese
     * Már mentésre váró autó létrehozása
     * Az autómentő elszállítja az autót, amitől az állapota megváltozik várakozikra
     */
    public void autoElszallitasTeszt() {
        System.out.println("\n--- [ TESZT INDUL: Auto elszallitasa ] ---");
        System.out.println("--- [ INICIALIZALAS ] ---");
        // 1: new Utszakasz()
        Uttest ut = new Uttest();
        Utszakasz u1 = new Utszakasz(ut, "HAZ");
        // 2: new Auto()-mentésre vár
        Auto a1 = new Auto(u1, AutoAllapot.MENTESRE_VAR);
        // 3: new automento()
        Automento am = new Automento();
        // 4: pozíciók beállítása
        a1.setPozicio(u1);
        u1.setKozlekedoJarmu(a1);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        am.elszallit(a1);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }
    /**
     * Hokotro nem tud belepni egy mezore teszteles.
     * A celutszakasz (u2) foglalt, mert egy Auto all rajta.
     * A hokotro megprobálja a lepetest, de jarmutElore() false-t ad vissza.
     */
    public void hokotroNemTudBelepniTeszt() {
        System.out.println("\n--- [ TESZT INDUL: Hokotro nem tud belepni az utszakaszra ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");

        // 1: new Hokotro()
        Hokotro h1 = new Hokotro();

        // 2: new Utszakasz() - jelenlegi pozicio, itt all a hokotro
        Uttest ut = new Uttest();
        Utszakasz u1 = new Utszakasz(ut, "HAZ");

        // 3: new Utszakasz() - kovetkezo utszakasz (ez lesz foglalt)
        Utszakasz u2 = new Utszakasz(ut, "HAZ");

        // 4: new Auto() - ez foglalja el u2-t
        Auto a = new Auto(u2, AutoAllapot.HALAD);

        // 5-6: poziciok beallitasa
        h1.setPozicio(u1);
        u1.setKozlekedoJarmu(h1);  // h1 all u1-en
        u2.setKozlekedoJarmu(a);   // u2 foglalt -> foglaltE() = true

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        // u1.jarmutElore() belsejeben megnezi h1-nek hova lephet,
        // u2.foglaltE() = true, ezert nem tud belepni, false-t ad vissza
        h1.halad(u2);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Hokotro sarkanyfejjel takarit teszt.
     * A takarito iranyitja a hokotrót egy havas utszakaszra,
     * a sarkanyfej azonnal felolvasztja a havat, a hokotro pénzt keres.
     */
    public void hokotroSarkanyFejTeszt() {
        System.out.println("\n--- [ TESZT INDUL: Hokotro sarkányfejjel takarít, hó elolvad ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");

        // 1: new Takarito()
        Takarito t1 = new Takarito("Teszt takarito");

        // 2: new Hokotro()
        Hokotro h1 = new Hokotro();

        // 3: new Sarkanyfej(kerozintartaly=100)
        Sarkanyfej sf1 = new Sarkanyfej(100);

        // 4: new Utszakasz() - kezdo es cel utszakasz
        Uttest ut = new Uttest();
        Utszakasz u0 = new Utszakasz(ut, "HAZ");
        Utszakasz u1 = new Utszakasz(ut, "HAZ");

        // kapcsolatok beallitasa
        h1.addFej(sf1);
        h1.setAktualisFej(sf1);
        t1.setHokotro(h1);
        h1.setPozicio(u0);
        u0.setKozlekedoJarmu(h1);
        u1.setHo(10);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        t1.iranyit(u1);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Egy fej eleterej elfogy, ezutan elveszik teszteles.
     * Takaritas kozben sf1 eletereje nullara csokken,
     * a hokotro eltavolitja (removeFej) es a kovetkezo fejre valt (cserelFej).
     */
    public void fejEleterejeElfogyTeszt() {
        System.out.println("\n--- [ TESZT INDUL: Fej eletereje elfogy, automatikus csere ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");

        // 1: new Takarito()
        Takarito t1 = new Takarito("Teszt takarito");

        // 2: new Hokotro()
        Hokotro h1 = new Hokotro();

        // 3: new Sarkanyfej - eletero=1, hogy biztosan elfogyjon takaritaskor
        Sarkanyfej sf1 = new Sarkanyfej(100);
        sf1.setEletero(1);

        // 4: new Utszakasz() - havas utszakasz
        Uttest ut = new Uttest();
        Utszakasz u0 = new Utszakasz(ut, "HAZ");
        Utszakasz u1 = new Utszakasz(ut, "HAZ");

        // kapcsolatok beallitasa
        t1.setHokotro(h1);
        h1.addFej(sf1);
        h1.setAktualisFej(sf1);
        h1.setPozicio(u0);
        u0.setKozlekedoJarmu(h1);
        u1.setHo(10);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        // iranyit -> halad -> takarit -> sf1.kopas (eletero=0)
        // -> letakaritas -> removeFej(sf1) -> cserelFej() -> penztKeres
        t1.iranyit(u1);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Uj hokotro vasarlasanak a tesztje.
     * A takarito egyenlege elegendo (>= 2000), ezert
     * levonodik az ar (penztKap(-2000)) es letrejon az uj Hokotro peldany.
     */
    public void hokotroVasarlasTeszt() {
        System.out.println("\n--- [ TESZT INDUL: Hokotro vasarlas ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");

        // 1: new Takarito() - elegendo egyenleggel
        Takarito t1 = new Takarito("Teszt takarito");
        t1.setEgyenleg(3000);

        // a takarito mar rendelkezik egy elromlott hokotróval
        Hokotro h1 = new Hokotro();
        t1.setHokotro(h1);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        // vasarol(2000): egyenleg >= 2000 -> penztKap(-2000) -> «create» h2: Hokotro
        t1.vasarol(2000);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Busz celba eresenek a tesztje
     */
    public void buszCelbaErTeszt() {
        System.out.println("\n--- [ TESZT INDUL: 1. Busz celba er ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");
        
        // 1: <<create>>
        Uttest ut1 = new Uttest(); 
        // 2: <<create>>Utszakasz(ut1, NORMAL, HAZ)
        Utszakasz u_jelen = new Utszakasz(ut1, "HAZ");
        // 3: <<create>>Utszakasz(ut1, NORMAL, MEGALLO)
        Utszakasz u_cel = new Utszakasz(ut1, "MEGALLO");
        
        // 4: <<create>> (Buszvezeto - de a konstruktorban kapja a buszt, így a sorrend picit igazítva)
        // 5: Busz(u_jelen, 0, b1) -> Busz letrehozasa pozicioval es buntetesidovel
        Busz b1 = new Busz(u_jelen, 0);
        Buszvezeto bv1 = new Buszvezeto(b1);
        
        // 6: felszall(bv1)
        b1.felszall(bv1);
        
        // Célállomás beállítása a teszthez
        b1.setCelAllomas(u_cel);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");
        // A szekvencia alapján a busz megpróbál a célba haladni
        b1.halad(u_cel);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Busz baleset utáni várakozás
     */
    public void balesetesBuszVarakozikTeszt() {
        System.out.println("\n--- [ TESZT INDUL: 3. Balesetes busz varakozik (Ido mulasa) ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");
        
        // 1: <<create>> Ora
        Ora o = new Ora(0);
        // 2: <<create>> Uttest
        Uttest ut = new Uttest();
        // 3: <<create>> Utszakasz(ut, NORMAL, HAZ)
        Utszakasz u = new Utszakasz(ut, "HAZ");
        
        // 4: <<create>> Busz(u, 60) -> 60 tick = 1 óra büntetés
        Busz b = new Busz(u, 60);
        // 5: <<create>> Buszvezeto(b)
        Buszvezeto bv = new Buszvezeto(b);
        
        // 6: felszall(bv)
        b.felszall(bv);
        
        // 7: addLepheto(b)
        o.addLepheto(b);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");
        // A szekvencia alapján telik az idő
        o.tick();

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Busz utvonalat modositja a buszvezeto teszt
     */
    public void utszakaszModositasTeszt() {
        System.out.println("\n--- [ TESZT INDUL: 2. Utszakasz modositas a busznak ] ---");
        
        Uttest u = new Uttest();
        Utszakasz u_jelen = new Utszakasz(u, "HAZ");
        Utszakasz u_cel = new Utszakasz(u, "HAZ");
        
        Busz b = new Busz(u_jelen, 0);
        Buszvezeto bv = new Buszvezeto(b);
        b.felszall(bv);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");
        // Csak a döntést hozzuk meg (indexelés)
        bv.utvonalatValtoztat(u_cel);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Busz savvaltasanak a tesztelese
     */
    public void buszSikeresSavvaltasaTeszt() {
        System.out.println("\n--- [ TESZT INDUL: 3. Busz sikeres savvaltasa ] ---");
        
        Uttest ut = new Uttest();
        Utszakasz u_jelen = new Utszakasz(ut,  "HAZ");
        Utszakasz u_cel = new Utszakasz(ut, "HAZ");
        
        Busz b = new Busz(u_jelen, 0);
        Buszvezeto bv = new Buszvezeto(b);
        b.felszall(bv);
        
        u_jelen.belep(b); // Rárakjuk a kezdőpontra

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");
        
        // A diagram alapján a Skeleton meghívja a halad() metódust a buszon
        // Fontos: a busz belsejében a jarmuSavotvalt_balra() fog meghívódni!
        b.halad(u_cel);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Busz megcsuszik es utkozik teszteles
     */
   public void buszMegcsuszikEsUtkozikTeszt() {
        System.out.println("\n--- [ TESZT INDUL: 4. Busz utkozik ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");
        
        // 1: <<create>> Uttest
        Uttest ut1 = new Uttest();
        // 2: <<create>>(ut1, NORMAL, HAZ) -> u0
        Utszakasz u0 = new Utszakasz(ut1, "HAZ");
        // 3: <<create>>(ut1, NORMAL, HAZ) -> u1
        Utszakasz u1 = new Utszakasz(ut1, "HAZ");
        
        // 4: <<create>>(u1, 0) -> b1 (A szenvedő busz)
        Busz b1 = new Busz(u1, 0);
        // 5: <<create>>(b1) -> bv1
        Buszvezeto bv1 = new Buszvezeto(b1);
        
        // 6: <<create>>(u0, 0) -> b2 (Az induló busz)
        Busz b2 = new Busz(u0, 0);
        // 7: <<create>>(b2) -> bv2
        Buszvezeto bv2 = new Buszvezeto(b2);
        
        // 8: felszall(bv1)
        b1.felszall(bv1);
        // 9: felszall(bv2)
        b2.felszall(bv2);
        
        // 10: belep(b1) -> Ráállítjuk az u1-re
        u1.belep(b1);
        // 11: belep(b2) -> Ráállítjuk az u0-ra
        u0.belep(b2);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");
        // A szekvenciadiagram alapján léptetjük a buszt a jarmutElore() metódussal
        // Az u0-n lévő jármű (b2) megpróbál haladni az u1 felé.
        u0.jarmutElore(u1);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Fejvasarlas tesztelese, ami sikeres
     */
    public void sikeresVasarlasTeszt(){
        System.out.println("\n--- [ TESZT INDUL: Sikeres fej vasarlas ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");

        // 1: new Takarito() - elegendo egyenleggel
        Takarito t1 = new Takarito("Teszt takarito");
        t1.setEgyenleg(3000);

        // 2: new Hokotro()
        Hokotro h1 = new Hokotro();

        // 3: new Fej()
        Fej f = new Sarkanyfej(100);

        // 4: kapcsolatok beallitasa
        t1.setHokotro(h1);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        // t1.vasarol(osszeg, f) -> h1.vasarol() -> h1.addFej(f) -> t1.setEgyenleg(...)
        int osszeg = 100;
        t1.vasarol(osszeg, f);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Sikertelen fejvasarlas tesztelese
     */
    public void sikertelenVasarlasTeszt(){
        System.out.println("\n--- [ TESZT INDUL: Sikertelen fej vasarlas (mar letezo fej) ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");

        // 1: new Takarito() - elegendo egyenleggel
        Takarito t1 = new Takarito("Teszt takarito");
        t1.setEgyenleg(3000);

        // 2: new Hokotro()
        Hokotro h1 = new Hokotro();

        // 4: kapcsolatok beallitasa
        t1.setHokotro(h1);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        // t1.vasarol(osszeg, soproFej) -> h1.vasarol()
        // duplikalt fej eseten nincs penzlevonas
        int osszeg = 100;
        Skeleton.nyit("Takarito.vasarol(" + osszeg + ", soproFej)");
        h1.vasarol();
        Skeleton.zar("Takarito.vasarol() visszater");

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Nem szennyezet út takaritasanak tesztelese
    */
    public void urestakaritasTeszt(){
        System.out.println("\n--- [ TESZT INDUL: Hokotro nem szennyezett utszakaszra lep ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");

        // 1: new Takarito()
        Takarito t1 = new Takarito("Teszt takarito");

        // 2: new Hokotro()
        Hokotro h1 = new Hokotro();

        // 3: new Utszakasz() - jelenlegi es kovetkezo pozicio
        Uttest ut = new Uttest();
        Utszakasz u0 = new Utszakasz(ut, "HAZ");
        Utszakasz u1 = new Utszakasz(ut, "HAZ");

        // kapcsolatok beallitasa
        t1.setHokotro(h1);
        h1.setPozicio(u0);
        u0.setKozlekedoJarmu(h1);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        // sk.Skeleton -> t1.Takarito: iranyit(u1)
        // t1.Takarito -> h1.Hokotro: halad(u1)
        // h1.Hokotro -> u1.Utszakasz: jarmutElore() -> true
        // h1.Hokotro -> u1.Utszakasz: belep(this)
        t1.iranyit(u1);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Jeges ut takaritasanak tesztelese
     */
    public void jegtakaritasTeszt(){
        System.out.println("\n--- [ TESZT INDUL: Hokotro jégtörőfejjel takarít ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");

        // 1: new Takarito()
        Takarito t1 = new Takarito("Teszt takarito");

        // 2: new Hokotro()
        Hokotro h1 = new Hokotro();

        // 3: new JegtoroFej(sotartaly=100)
        JegtoroFej sf1 = new JegtoroFej();
    

        // 4: new Utszakasz() - kezdo es cel utszakasz
        Uttest ut = new Uttest();
        Utszakasz u0 = new Utszakasz(ut, "HAZ");
        Utszakasz u1 = new Utszakasz(ut, "HAZ");

        // kapcsolatok beallitasa
        h1.addFej(sf1);
        h1.setAktualisFej(sf1);
        t1.setHokotro(h1);
        h1.setPozicio(u0);
        u0.setKozlekedoJarmu(h1);
        u1.setHo(10);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        t1.iranyit(u1);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Utszakasz allapotvaltozasanak tesztelese
     */
    public void utszakaszIdojarasFrissitesTeszt(){
        System.out.println("\n--- [ TESZT INDUL: 18. utszakaszIdojarasFrissitesTeszt ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");
        Ora o = new Ora(1);
        Uttest ut = new Uttest();
        Utszakasz u = new Utszakasz(ut, "HAZ");
        o.addMegfigyeltUtszakasz(u);
        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");
        o.tick();
        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Hidszakasz allapotvaltozasanak tesztelese
     */
    public void hidszakaszIdojarasFrissitesTeszt(){
        System.out.println("\n--- [ TESZT INDUL: 19. hidszakaszIdojarasFrissitesTeszt ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");
        Ora o = new Ora(1);
        Uttest ut = new Uttest();
        Hidszakasz u = new Hidszakasz(ut);
        o.addMegfigyeltUtszakasz(u);
        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");
        o.tick();
        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Alagutszakasz allapotvaltozasanak tesztelese
     */
    public void alagutSzakaszIdojarasFrissitesTeszt(){
        System.out.println("\n--- [ TESZT INDUL: 20. alagutSzakaszIdojarasFrissitesTeszt ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");
        Ora o = new Ora(1);
        Uttest ut = new Uttest();
        Alagutszakasz u = new Alagutszakasz(ut);
        o.addMegfigyeltUtszakasz(u);
        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");
        o.tick();
        System.out.println("\n--- [ TESZT VEGE ] ---");
    }

    /**
     * Hokotro soszorassal takarit teszt
     */
    public void soSzorasTeszt(){
        System.out.println("\n--- [ TESZT INDUL: Hokotro sószórófejjel takarít, hó elolvad ] ---");
        System.out.println("--- [ INICIALIZALAS (Kommunikacios diagram alapjan) ] ---");

        // 1: new Takarito()
        Takarito t1 = new Takarito("Teszt takarito");

        // 2: new Hokotro()
        Hokotro h1 = new Hokotro();

        // 3: new SoszoroFej(sotartaly=100)
        SoszoroFej sf1 = new SoszoroFej(100);
    

        // 4: new Utszakasz() - kezdo es cel utszakasz
        Uttest ut = new Uttest();
        Utszakasz u0 = new Utszakasz(ut, "HAZ");
        Utszakasz u1 = new Utszakasz(ut, "HAZ");

        // kapcsolatok beallitasa
        h1.addFej(sf1);
        h1.setAktualisFej(sf1);
        t1.setHokotro(h1);
        h1.setPozicio(u0);
        u0.setKozlekedoJarmu(h1);
        u1.setHo(10);

        System.out.println("\n--- [ SZEKVENCIA KEZDODIK ] ---");

        t1.iranyit(u1);

        System.out.println("\n--- [ TESZT VEGE ] ---");
    }
}
