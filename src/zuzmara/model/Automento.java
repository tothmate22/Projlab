package zuzmara.model;
import zuzmara.enums.AutoAllapot;

public class Automento {

    public void elszallit(Auto a) {
        Skeleton.nyit("Automento.elszallit(Auto)");

        a.allapotValtoztat(AutoAllapot.VARAKOZIK);

        Skeleton.zar("Automento.elszallit() visszater");
    }

    public void mentesRegisztral(Auto a) {
        Skeleton.nyit("Automento.mentesRegisztral(Auto)");
        Skeleton.zar("Automento.mentesRegisztral() visszater");
    }
}

