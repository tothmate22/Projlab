package zuzmara.model;
import java.util.*;

public class Keresztezodes extends Uttest {
    private List<Uttest> kijaratok = new ArrayList<>();

    public void kijaratHozzaadas(Uttest uttest) {
        this.kijaratok.add(uttest);
    }
}