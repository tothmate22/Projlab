package zuzmara.model;

import zuzmara.enums.AutoAllapot;
import zuzmara.interfaces.ICsuszhat;
import zuzmara.interfaces.ILepheto;

public class Auto extends Jarmu implements ICsuszhat, ILepheto{
  private Utszakasz cel;
  private AutoAllapot allapot;

  public Auto(Utszakasz poz,AutoAllapot ap){
    super(poz);
    Skeleton.nyit("Auto <<create>>");
    this.allapot = ap;
    Skeleton.zar("Auto letrejott.");
  }
  @Override
  public void halad(Utszakasz cel) {
    Skeleton.nyit("Auto.halad(Utszakasz)");
    this.cel = cel;
    boolean foglalt = Skeleton.kerdez("Foglalt a cel utszakasz? (i/n)");
    if (!foglalt) {
        cel.belep(this);
        celbaErt();
    } else {
        elakad();
    }

    Skeleton.zar("Auto.halad() visszater");
  }
  @Override
  public void idoEltelt(){
    Skeleton.nyit("Auto.idoEltelt()");
    Skeleton.zar("Auto.idoEltelt() visszater");
  }

  public void celbaErt() {
    Skeleton.nyit("Auto.celbaErt()");
    allapotValtoztat(AutoAllapot.VARAKOZIK);
    Skeleton.zar("Auto.celbaErt() visszater");
  }
  
  public void elakad() {
    Skeleton.nyit("Auto.elakad()");
    allapotValtoztat(AutoAllapot.ELAKADT);
    Skeleton.zar("Auto.elakad() visszater");
  }
  @Override
  public void kicsuszik() {
    Skeleton.nyit("Auto.kicsuszik()");

    boolean vanElotteJarmu = Skeleton.kerdez("Van az auto elott jarmu, amivel utkozik?(i/n)");
    if (vanElotteJarmu) {
        this.utkozott(null);
    }

    Skeleton.zar("Auto.kicsuszik() visszater");
  }
  
  @Override
  public void utkozott(Jarmu masik) {
    Skeleton.nyit("Auto.utkozott(Jarmu)");
    allapotValtoztat(AutoAllapot.MENTESRE_VAR);
    Skeleton.zar("Auto.utkozott() visszater");
  }
  
  public void allapotValtoztat(AutoAllapot uj) {
    Skeleton.nyit("Auto.allapotValtoztat()");
    this.allapot = uj;
    Skeleton.zar("Auto.allapotValtoztat() visszater");
  }
  
}

