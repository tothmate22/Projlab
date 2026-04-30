import interfaces.ILepheto;
import java.util.List;
import java.util.ArrayList;

/**
 * Az Óra osztály felelős a játékbeli idő múlásáért.
 * Minden tick-ben értesíti az összes időre érzékeny objektumot.
 * Számon tartja az ütemezett eseményeket és lejáratukkor értesíti 
 * az érintett objektumokat.
 */
public class Ora {
    /**
     * Az eltelt játékidő tick-ekben mérve.
     */
    private int currentTime;

    /**
     * Ennyi tick szükséges 1 cm hó lehullásához.
     */
    private int ticksPerSnowCm;
    private String name;

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
        this.ticksPerSnowCm = ticksPerSnowCm;
        this.lepheto = new ArrayList<>();
        this.name = name;
    }

    /**
     * Egy időlépés végrehajtása.
     * Növeli a belső időt, majd meghívja az összes
     * regisztrált ILepheto objektum idoEltelt() metódusát.
     */
    public void tick() {
        currentTime++;
        for (ILepheto l : lepheto) {
            l.idoEltelt();
        }
    }

    /**
     * Egy ILepheto objektumot regisztrál az Órához.
     * @param l a regisztrálandó ILepheto objektum
     */
    public void addLepheto(ILepheto l) {
        lepheto.add(l);
    }

    public String getInfo() {
        return "info " + name + " (Óra):\n" +
            "currentTime: " + currentTime + "\n" +
            "ticksPerSnowCm: " + ticksPerSnowCm + "\n" + 
            "lephetokSzama: " + lepheto.size();
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int getTicksPerSnowCm() {
        return ticksPerSnowCm;
    }
}
