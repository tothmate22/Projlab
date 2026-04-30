import java.util.ArrayList;
import java.util.List;
import Prototipus.enums.AutoAllapot;
import Prototipus.interfaces.ILepheto;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Az Automento osztály a mentésre váró autók kezeléséért felelős.
 * Az autók ütközés után mentésre várnak, és egy adott idő elteltével
 * az autómentő elszállítja őket.
 *
 * Az osztály ILepheto interfészt valósít meg, így az idő múlására reagál.
 */
public class Automento implements ILepheto {

    /**
     * A mentésre váró autók és a hozzájuk tartozó hátralévő idő tárolása.
     * A kulcs az autó, az érték pedig a hátralévő idő (tick-ekben).
     */
    private Map<Auto, Integer> mentendoAutok;
    //Automente neve, ami által lehet keresni
    private string nev;

    /**
     * Létrehoz egy üres autómentő objektumot.
     */
    public Automento(string name) {
        this.mentendoAutok = new HashMap<>();
        this.nev = name;
    }

    /**
     * Regisztrál egy autót mentésre váróként.
     * Ha az autó már regisztrálva van, nem történik újabb hozzáadás.
     *
     * @param a a mentésre váró autó
     */
    public void mentesRegisztral(Auto a) {
        if (a != null && a.getAllapot() == AutoAllapot.MENTESRE_VAR) {
            // 30 tick múlva kerül elszállításra
            mentendoAutok.putIfAbsent(a, 30);
        }
    }

    /**
     * Egy időegység elteltének kezelése.
     * Minden mentésre váró autó esetén csökkenti a hátralévő időt.
     * Ha az idő eléri a 0-t, az autót elszállítja.
     */
    @Override
    public void idoEltelt() {
        Iterator<Map.Entry<Auto, Integer>> it = mentendoAutok.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Auto, Integer> entry = it.next();

            Auto auto = entry.getKey();
            int hatralevoIdo = entry.getValue() - 1;

            if (hatralevoIdo <= 0) {
                // Az autó elszállítása (állapot visszaállítása)
                auto.allapotValtoztat(AutoAllapot.VARAKOZIK);
                it.remove();
            } else {
                entry.setValue(hatralevoIdo);
            }
        }
    }

    /**
     * Azonnali elszállítási művelet.
     * Csak akkor hajtódik végre, ha az autó mentésre vár.
     *
     * @param a az elszállítandó autó
     */
    public void elszallit(Auto a) {
        if (a == null || a.getAllapot() != AutoAllapot.MENTESRE_VAR) {
            return;
        }

        a.allapotValtoztat(AutoAllapot.VARAKOZIK);
        mentendoAutok.remove(a);
    }

    /**
     * Az autómentő állapotát adja vissza szöveges formában.
     * Az info parancs használhatja.
     *
     * @return az autómentő aktuális állapota
     */
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("info automento (Automento):\n");
        sb.append("mentendoAutok: [");

        int i = 0;
        for (Auto a : mentendoAutok.keySet()) {
            sb.append(a.getId());
            if (i < mentendoAutok.size() - 1) {
                sb.append(", ");
            }
            i++;
        }

        sb.append("]");
        return sb.toString();
    }
}
