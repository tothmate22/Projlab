import java.util.List;
import java.util.ArrayList;

/**
 * Az Óra osztály felelős a játékbeli idő múlásáért.
 * Minden tick-ben értesíti az összes időre érzékeny objektumot.
 * Számon tartja az ütemezett eseményeket és lejáratukkor értesíti 
 * az érintett objektumokat.
 */
public class Ora implements IInfo {
    /**
     * Az eltelt játékidő tick-ekben mérve.
     */
    private int currentTime;

    /**
     * Ennyi tick szükséges 1 cm hó lehullásához.
     */
    private int ticksPerSnowCm;
    private String name;
    public String getOra() {
        return name;
    }

    /**
     * Az összes időre érzékeny objektum listája.
     * Minden tick()-ben meghívja rajtuk az idoEltelt() metódust.
     */
    private List<ILepheto> lepheto;

    /**
     * Konstruktor.
     * @param ticksPerSnowCm ennyi tick szükséges 1 cm hó lehullásához
     * @param name az óra neve
     */
    public Ora(int ticksPerSnowCm, String name) {
        this.currentTime = 0;
        this.ticksPerSnowCm = ticksPerSnowCm == 0 ? 1 : ticksPerSnowCm; // Biztosítjuk, hogy legalább 1
        this.lepheto = new ArrayList<>();
        this.name = name;
    }

    /**
     * Egy időlépés végrehajtása.
     * Növeli a belső időt, előbb az Idojaras-t (időjárás frissítés),
     * majd az összes többi regisztrált ILepheto objektum idoEltelt() metódusát.
     */
    public void tick() {
        currentTime++;
        // Előbb az Idojaras (időjárás frissítés)
        for (ILepheto l : lepheto) {
            if (l instanceof Idojaras) {
                l.idoEltelt();
                break;  // Csak az első Idojaras
            }
        }
        // Utána az összes többi (járművek, etc)
        for (ILepheto l : lepheto) {
            if (!(l instanceof Idojaras)) {
                l.idoEltelt();
            }
        }
    }

    /**
     * Egy ILepheto objektumot regisztrál az Órához.
     * @param l a regisztrálandó ILepheto objektum
     */
    public void addLepheto(ILepheto l) {
        lepheto.add(l);
    }

    /**
     * Egy ILepheto objektumot regisztrál az Órához az elején.
     * (Elsősorban az Idojaras-hoz használjuk)
     * @param l a regisztrálandó ILepheto objektum
     */
    public void addLehetoAtStart(ILepheto l) {
        lepheto.add(0, l);
    }

    public String getInfo() {
        return "info " + name + " (Óra):\n" + "currentTime: " + currentTime + "\n" + "ticksPerSnowCm: " + ticksPerSnowCm + "\n" + "lephetokSzama: " + lepheto.size();
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int getTicksPerSnowCm() {
        return ticksPerSnowCm;
    }
}
