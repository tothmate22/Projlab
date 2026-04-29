import java.io.*;
import java.util.*;

public class Console {

    private final Palya palya;
    private final Ora ora;
    private Random random = new Random();

    public Console(Palya palya, Ora ora) {
        this.palya = palya;
        this.ora   = ora;
    }

    public void feldolgoz(String sor) {
        if (sor == null) return;
        sor = sor.trim();
        if (sor.isEmpty() || sor.startsWith("//")) return;

        String[] szavak = sor.split("\\s+");
        String cmd = szavak[0].toLowerCase();

        try {
            switch (cmd) {
                case "load": cmdLoad(szavak); break;
                case "tick": cmdTick(szavak); break;
                case "random": cmdRandom(szavak); break;
                case "move": cmdMove(szavak); break;
                case "info": cmdInfo(szavak); break;
                case "buyhokotro": cmdBuyHokotro(szavak); break;  
                case "buyfej": cmdBuyFej(szavak); break;
                case "switchhead": cmdSwitchHead(szavak); break;
                case "makeutszakasz": cmdMakeUtszakasz(szavak); break;
                case "makealagutszakasz": cmdMakeAlagutszakasz(szavak); break;
                case "makehidszakasz": cmdMakeHidszakasz(szavak); break;
                case "makeuttest": cmdMakeUttest(szavak); break;
                case "makekeresztezodes": cmdMakeKeresztezodes(szavak); break;
                case "bindkijarat": cmdBindKijarat(szavak); break;
                case "setepulet": cmdSetEpulet(szavak); break;
                case "makeauto": cmdMakeAuto(szavak); break;
                case "makebusz": cmdMakeBusz(szavak); break;
                case "makehokotro": cmdMakeHokotro(szavak); break;
                case "makefej": cmdMakeFej(szavak); break;
                case "maketakarito": cmdMakeTakarito(szavak); break;
                case "makebuszvezeto": cmdMakeBuszvezeto(szavak); break;
                case "setho": cmdSetHo(szavak); break;
                case "setjeg": cmdSetJeg(szavak); break;
                case "setzuzalek": cmdSetZuzalek(szavak); break;
                case "makeautomento": cmdMakeAutomento(szavak); break;
                case "exit": cmdExit(); break;
                default: System.out.println("HIBA: Ismeretlen parancs: " + cmd);
            }
        } catch (Hiba e) {
            System.out.println("HIBA: " + cmd + ": " + e.getMessage());
        }
    }

    private void cmdLoad(String[] szavak) throws Hiba {
        ellen(szavak, 2, "load <fájlnév>");
        String fajlNev = szavak[1];
        try (BufferedReader br = new BufferedReader(new FileReader(fajlNev))) {
            String sor;
            while ((sor = br.readLine()) != null) {
                feldolgoz(sor);
            }
        } catch (IOException e) {
            throw new Hiba("Nem sikerült megnyitni a fájlt: " + fajlNev);
        }
    }

    private void cmdTick(String[] szavak) throws Hiba {
        ellen(szavak, 2, "tick <n>");
        int n = parseInt(szavak[1]);
        for (int i = 0; i < n; i++) {
            ora.tick();
        }
    }

    private void cmdRandom(String[] szavak) throws Hiba {
        ellen(szavak, 2, "random <egész szám>");
        int seed = parseInt(szavak[1]);
        random = new Random(seed);
        palya.setRandom(random);
    }

    private void cmdMove(String[] szavak) throws Hiba {
        ellen(szavak, 3, "move <játékos_neve> <elore|jobbra|balra>");
        String nev = szavak[1];
        String irany = szavak[2].toLowerCase();

        Jatekos jatekos = palya.getJatekos(nev);
        if (jatekos == null) throw new Hiba("Ismeretlen játékos: " + nev);

        Utszakasz cel = switch (irany) {
            case "elore" -> jatekos.getJarmu().getPozicio().getSzakaszElore();
            case "jobbra" -> jatekos.getJarmu().getPozicio().getSzakaszJobbra();
            case "balra" -> jatekos.getJarmu().getPozicio().getSzakaszBalra();
            default -> throw new Hiba("Ismeretlen irány: " + irany + " (elore / jobbra / balra)");
        };

        jatekos.utvonalatValtoztat(cel);
    }

    private void cmdInfo(String[] szavak) throws Hiba {
        ellen(szavak, 2, "info <objektum_neve>");
        String nev = szavak[1];
        IInfos obj = palya.getInfos(nev);
        if (obj == null) throw new Hiba("Ismeretlen objektum: " + nev);
        System.out.println(obj.getInfo());
    }

    private void cmdBuyHokotro(String[] szavak) throws Hiba {
        ellen(szavak, 4, "buyHokotro <hokotro_neve> <takarito_neve> <kezdo_utszakasz>");
        String hokotroNev = szavak[1];
        String takaritoNev = szavak[2];
        String utszakaszNev = szavak[3];

        Takarito takarito = (Takarito) palya.getJatekos(takaritoNev);
        if (takarito == null) throw new Hiba("Ismeretlen takarító: " + takaritoNev);
        Utszakasz kezdo = palya.getUtszakasz(utszakaszNev);
        if (kezdo == null) throw new Hiba("Ismeretlen útszakasz: " + utszakaszNev);

        takarito.vasarolHokotro(hokotroNev, kezdo, palya);
    }

    private void cmdBuyFej(String[] szavak) throws Hiba {
        ellen(szavak, 4, "buyFej <fej_neve> <fejtípus> <hokotro_neve>");
        String fejNev = szavak[1];
        String fejTipus = szavak[2].toLowerCase();
        String hokotroNev = szavak[3];

        Hokotro hokotro = palya.getHokotro(hokotroNev);
        if (hokotro == null) throw new Hiba("Ismeretlen hókotró: " + hokotroNev);
        Takarito takarito = hokotro.getTakarito();

        takarito.vasarolFej(fejNev, fejTipus, hokotro, palya);
    }

    private void cmdSwitchHead(String[] szavak) throws Hiba {
        ellen(szavak, 2, "switchhead <takarító_neve>");
        String takaritoNev = szavak[1];

        Takarito takarito = (Takarito) palya.getJatekos(takaritoNev);
        if (takarito == null) throw new Hiba("Ismeretlen takarító: " + takaritoNev);
        takarito.getHokotro().cserelFej();
    }

    private void cmdMakeUtszakasz(String[] szavak) throws Hiba {
        ellen(szavak, 4, "makeUtszakasz <utszakasz_neve> <uttest_neve> <sav_sorszam>");
        String nev = szavak[1];
        String uttest = szavak[2];
        int sav = parseInt(szavak[3]) - 1;

        Uttest ut = palya.getUttest(uttest);
        if (ut == null) throw new Hiba("Ismeretlen úttest: " + uttest);

        Utszakasz u = new Utszakasz(ut, "URES", nev);
        ut.addUtszakasz(u, sav);
        palya.addUtszakasz(nev, u);
    }

    private void cmdMakeAlagutszakasz(String[] szavak) throws Hiba {
        ellen(szavak, 4, "makeAlagutszakasz <alagutszakasz_neve> <uttest_neve> <sav_sorszam>");
        String nev = szavak[1];
        String uttest = szavak[2];
        int sav = parseInt(szavak[3]) - 1;

        Uttest ut = palya.getUttest(uttest);
        if (ut == null) throw new Hiba("Ismeretlen úttest: " + uttest);

        Alagutszakasz a = new Alagutszakasz(ut, "URES", nev);
        ut.addUtszakasz(a, sav);
        palya.addUtszakasz(nev, a);
    }

    private void cmdMakeHidszakasz(String[] szavak) throws Hiba {
        ellen(szavak, 4, "makeHidszakasz <hidszakasz_neve> <uttest_neve> <sav_sorszam>");
        String nev = szavak[1];
        String uttest = szavak[2];
        int sav = parseInt(szavak[3]) - 1;

        Uttest ut = palya.getUttest(uttest);
        if (ut == null) throw new Hiba("Ismeretlen úttest: " + uttest);

        Hidszakasz h = new Hidszakasz(ut, "URES", nev);
        ut.addUtszakasz(h, sav);
        palya.addUtszakasz(nev, h);
    }

    private void cmdMakeUttest(String[] szavak) throws Hiba {
        ellen(szavak, 3, "makeUttest <uttest_neve> <cel_keresztezodes>");
        String nev = szavak[1];
        String kereszt = szavak[2];

        Keresztezodes k = palya.getKeresztezodes(kereszt);
        if (k == null) throw new Hiba("Ismeretlen kereszteződés: " + kereszt);

        Uttest ut = new Uttest(new ArrayList<>(), nev);
        ut.setCelKeresztezodes(k);
        palya.addUttest(nev, ut);
    }

    private void cmdMakeKeresztezodes(String[] szavak) throws Hiba {
        ellen(szavak, 2, "makeKeresztezodes <keresztezodes_neve>");
        String nev = szavak[1];
        Keresztezodes k = new Keresztezodes(nev);
        palya.addKeresztezodes(nev, k);
    }

    private void cmdBindKijarat(String[] szavak) throws Hiba {
        ellen(szavak, 3, "bindKijarat <keresztezodes_neve> <kijarat_uttest>");
        String keresztNev = szavak[1];
        String uttestNev = szavak[2];

        Keresztezodes k = palya.getKeresztezodes(keresztNev);
        if (k == null) throw new Hiba("Ismeretlen kereszteződés: " + keresztNev);
        Uttest ut = palya.getUttest(uttestNev);
        if (ut == null) throw new Hiba("Ismeretlen úttest: " + uttestNev);

        k.kijaratHozzaadas(ut);
    }

    private void cmdSetEpulet(String[] szavak) throws Hiba {
        ellen(szavak, 3, "setEpulet <utszakasz_neve> <epulet_tipus>");
        String utszakaszNev = szavak[1];
        String epuletTipus = szavak[2];

        Utszakasz u = palya.getUtszakasz(utszakaszNev);
        if (u == null) throw new Hiba("Ismeretlen útszakasz: " + utszakaszNev);

        u.setEpulet(Epulet.fromString(epuletTipus));
    }

    private void cmdMakeAuto(String[] szavak) throws Hiba {
        ellen(szavak, 5, "makeAuto <auto_neve> <pozicio_utszakasz> <cel_utszakasz> <allapot>");
        String nev = szavak[1];
        String pozNev = szavak[2];
        String celNev = szavak[3];
        String allapotStr = szavak[4];

        Utszakasz poz = palya.getUtszakasz(pozNev);
        if (poz == null) throw new Hiba("Ismeretlen útszakasz: " + pozNev);
        Utszakasz cel = palya.getUtszakasz(celNev);
        if (cel == null) throw new Hiba("Ismeretlen útszakasz: " + celNev);

        AutoAllapot allapot = AutoAllapot.fromString(allapotStr);
        Auto a = new Auto(nev, poz, cel, allapot, palya);
        poz.setKozlekedoJarmu(a);
        palya.addJarmu(nev, a);
        ora.addLepheto(a);
    }

    private void cmdMakeBusz(String[] szavak) throws Hiba {
        ellen(szavak, 5, "makeBusz <busz_neve> <buszvezeto_neve> <kezdo_utszakasz> <cel_utszakasz>");
        String nev = szavak[1];
        String bvNev = szavak[2];
        String kezdoNev = szavak[3];
        String celNev = szavak[4];

        Buszvezeto bv = (Buszvezeto) palya.getJatekos(bvNev);
        if (bv == null) throw new Hiba("Ismeretlen buszvezető: " + bvNev);
        Utszakasz kezdo = palya.getUtszakasz(kezdoNev);
        if (kezdo == null) throw new Hiba("Ismeretlen útszakasz: " + kezdoNev);
        Utszakasz cel = palya.getUtszakasz(celNev);
        if (cel == null) throw new Hiba("Ismeretlen útszakasz: " + celNev);

        Busz b = new Busz(nev, kezdo, cel, bv);
        kezdo.setKozlekedoJarmu(b);
        bv.setSajatBusz(b);
        palya.addJarmu(nev, b);
        ora.addLepheto(b);
    }

    private void cmdMakeHokotro(String[] szavak) throws Hiba {
        ellen(szavak, 4, "makeHokotro <hokotro_neve> <takarito_neve> <kezdo_utszakasz>");
        String nev = szavak[1];
        String takaritoNev = szavak[2];
        String kezdoNev = szavak[3];

        Takarito takarito = (Takarito) palya.getJatekos(takaritoNev);
        if (takarito == null) throw new Hiba("Ismeretlen takarító: " + takaritoNev);
        Utszakasz kezdo = palya.getUtszakasz(kezdoNev);
        if (kezdo == null) throw new Hiba("Ismeretlen útszakasz: " + kezdoNev);

        Hokotro h = new Hokotro(nev, kezdo, takarito);
        kezdo.setKozlekedoJarmu(h);
        takarito.setHokotro(h);
        palya.addJarmu(nev, h);
        palya.addHokotro(nev, h);
        ora.addLepheto(h);
    }

    private void cmdMakeFej(String[] szavak) throws Hiba {
        ellen(szavak, 4, "makeFej <fej_neve> <fejtípus> <hokotro_neve>");
        String fejNev = szavak[1];
        String fejTipus = szavak[2].toLowerCase();
        String hokotroNev = szavak[3];

        Hokotro hokotro = palya.getHokotro(hokotroNev);
        if (hokotro == null) throw new Hiba("Ismeretlen hókotró: " + hokotroNev);

        Fej fej = fejFactory(fejNev, fejTipus);
        hokotro.addFej(fej);
        palya.addFej(fejNev, fej);
    }

    private void cmdMakeTakarito(String[] szavak) throws Hiba {
        ellen(szavak, 3, "makeTakarito <takarito_neve> <kezdo_egyenleg>");
        String nev = szavak[1];
        int egyenleg = parseInt(szavak[2]);
        Takarito t = new Takarito(nev, egyenleg);
        palya.addJatekos(nev, t);
    }

    private void cmdMakeBuszvezeto(String[] szavak) throws Hiba {
        ellen(szavak, 3, "makeBuszvezeto <buszvezeto_neve> <kezdo_egyenleg>");
        String nev = szavak[1];
        int egyenleg = parseInt(szavak[2]);
        Buszvezeto bv = new Buszvezeto(nev, egyenleg);
        palya.addJatekos(nev, bv);
    }

    private void cmdMakeAutomento(String[] szavak) throws Hiba {
        ellen(szavak, 2, "makeAutomento <automento_neve>");
        String nev = szavak[1];
        Automento am = new Automento(nev, palya);
        palya.addAutomento(nev, am);
        ora.addLepheto(am);
    }

    private void cmdSetHo(String[] szavak) throws Hiba {
        ellen(szavak, 3, "setHo <utszakasz_neve> <ho_ertek>");
        Utszakasz u = getUtszakaszOrHiba(szavak[1]);
        u.setHo(parseInt(szavak[2]));
    }

    private void cmdSetJeg(String[] szavak) throws Hiba {
        ellen(szavak, 3, "setJeg <utszakasz_neve> <jeg_ertek>");
        Utszakasz u = getUtszakaszOrHiba(szavak[1]);
        u.setJeg(parseInt(szavak[2]));
    }

    private void cmdSetZuzalek(String[] szavak) throws Hiba {
        ellen(szavak, 3, "setZuzalek <utszakasz_neve> <true|false>");
        Utszakasz u = getUtszakaszOrHiba(szavak[1]);
        boolean ertek = Boolean.parseBoolean(szavak[2]);
        u.setZuzottKo(ertek);
    }

    private void cmdExit() {
        System.out.println("Kilépés...");
        System.exit(0);
    }

    private void ellen(String[] szavak, int min, String hasznalat) throws Hiba {
        if (szavak.length < min) {
            throw new Hiba("Hiányos paraméterek. Helyes használat: " + hasznalat);
        }
    }

    private int parseInt(String s) throws Hiba {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new Hiba("Egész számot vártam, kaptam: " + s);
        }
    }

    private Utszakasz getUtszakaszOrHiba(String nev) throws Hiba {
        Utszakasz u = palya.getUtszakasz(nev);
        if (u == null) throw new Hiba("Ismeretlen útszakasz: " + nev);
        return u;
    }

    private Fej fejFactory(String nev, String tipus) throws Hiba {
        return switch (tipus) {
            case "soprofej" -> new SoproFej(nev);
            case "hanyofej" -> new HanyoFej(nev);
            case "jegtoro" -> new JegtoroFej(nev);
            case "soszoro", "soszorofej" -> new SoszoroFej(nev);
            case "sarkany", "sarkanyfej" -> new SarkanyFej(nev);
            case "zuzottkofej", "zuzalekfej" -> new ZuzalekFej(nev);
            default -> throw new Hiba("Ismeretlen fejtípus: " + tipus + " (soprofej / hanyofej / jegtoro / soszoro / sarkany / zuzottkofej)");
        };
    }

    public static class Hiba extends Exception {
        public Hiba(String msg) { super(msg); }
    }
}