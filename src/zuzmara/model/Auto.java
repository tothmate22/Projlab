package zuzmara.model;
import zuzmara.enums;


public class Auto extends Jarmu implements Icsuszhat, ILepheto{
  private Utszakasz cel;
  private AutoAllapot allapot;

  public Auto(Utszakasz poz,Utszakasz cel, Autoallapot ap){
    super(poz);
    this.cel = cel;
    this.allapot = ap;
    Skeleton.nyit("Auto <<create>>");
    Skeleton.zar("Auto letrejott.");
  }
  
  public void halad(Utszakasz cel) {
    Skeleton.nyit("Auto.halad(Utszakasz)");

    if (!cel.foglaltE()) {
        cel.belep(this);
        celbaErt();
    } else {
        elakad();
    }

    Skeleton.zar("Auto.halad() visszater");
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

  public void kicsuszik() {
    Skeleton.nyit("Auto.kicsuszik()");
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
