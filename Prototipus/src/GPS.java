import java.util.*;

/**
 * A GPS osztály felelős az autók útvonalának meghatározásáért.
 * A pálya útszakaszai alapján megkeresi a legrövidebb járható út következő lépését.
 */
public class GPS {

    /** A pálya, amelyben az útszakaszok és járművek találhatók. */
    private Palya palya;

    /**
     * GPS konstruktor.
     *
     * @param palya a játék aktuális pályája
     */
    public GPS(Palya palya) {
        this.palya = palya;
    }

    /**
     * Meghatározza a legrövidebb járható út következő útszakaszát.
     * A metódus BFS-alapú keresést használ, és csak az első lépést adja vissza.
     *
     * @param kezdo az aktuális útszakasz
     * @param cel a végső célútszakasz
     * @return a következő útszakasz, vagy null, ha nincs járható út
     */
    public Utszakasz kovetkezoLepes(Utszakasz kezdo, Utszakasz cel) {
        if (kezdo == null || cel == null) {
            return null;
        }

        if (kezdo == cel) {
            return cel;
        }

        Queue<Utszakasz> sor = new LinkedList<>();
        Set<Utszakasz> meglatogatott = new HashSet<>();
        Map<Utszakasz, Utszakasz> szulo = new HashMap<>();

        sor.add(kezdo);
        meglatogatott.add(kezdo);

        while (!sor.isEmpty()) {
            Utszakasz aktualis = sor.poll();

            if (aktualis == cel) {
                break;
            }

            for (Utszakasz szomszed : szomszedok(aktualis)) {
                if (szomszed != null && !meglatogatott.contains(szomszed) && jarhato(szomszed)) {
                    meglatogatott.add(szomszed);
                    szulo.put(szomszed, aktualis);
                    sor.add(szomszed);
                }
            }
        }

        if (!meglatogatott.contains(cel)) {
            return null;
        }

        Utszakasz lepes = cel;
        while (szulo.containsKey(lepes) && szulo.get(lepes) != kezdo) {
            lepes = szulo.get(lepes);
        }

        return lepes;
    }

    /**
     * Visszaadja az adott útszakasz közvetlen szomszédait.
     * Jelenlegi modellben elsődlegesen a szakaszElore kapcsolatot használja.
     *
     * @param szakasz a vizsgált útszakasz
     * @return a szomszédos útszakaszok listája
     */
    private List<Utszakasz> szomszedok(Utszakasz szakasz) {
        List<Utszakasz> eredmeny = new ArrayList<>();

        Utszakasz elore = szakasz.getSzakaszElore();
        if (elore != null) {
            eredmeny.add(elore);
        }

        return eredmeny;
    }

    /**
     * Eldönti, hogy az adott útszakasz járható-e.
     *
     * @param szakasz a vizsgált útszakasz
     * @return igaz, ha az útszakasz járható
     */
    private boolean jarhato(Utszakasz szakasz) {
        return !szakasz.foglaltE() && szakasz.getHo() <= 25;
    }
    /**
    *
    *@return Automentok listája első automentoje
    */
    public Automento getElsoAutomento() {
        return palya.getElsoAutomento();
    }
}