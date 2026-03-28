package zuzmara.model;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Skeleton sk = Skeleton.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Skeleton Tesztmenü ===");
            System.out.println(" 1. autoNormalHaladasTeszt");
            System.out.println(" 2. autoElakadasTeszt");
            System.out.println(" 3. autoCsuszasUtkozesTeszt");
            System.out.println(" 4. autoElszallitasTeszt");
            System.out.println(" 5. hokotroNemTudBelepniTeszt");
            System.out.println(" 6. hokotroSarkanyFejTeszt");
            System.out.println(" 7. fejEleterejeElfogyTeszt");
            System.out.println(" 8. hokotroVasarlasTeszt");
            System.out.println(" 9. buszCelbaErTeszt");
            System.out.println("10. buszBalesetTeszt");
            System.out.println("11. buszUtvonalmodositasTeszt");
            System.out.println("12. buszSikeresSavvaltasTeszt");
            System.out.println("13. buszCsuszasUtkozesTeszt");
            System.out.println("14. sikeresVasarlasTeszt");
            System.out.println("15. sikertelenVasarlasTeszt");
            System.out.println("16. urestakaritasTeszt");
            System.out.println("17. jegtakaritasTeszt");
            System.out.println("18. utszakaszIdojarasFrissitesTeszt");
            System.out.println("19. hidszakaszIdojarasFrissitesTeszt");
            System.out.println("20. alagutSzakaszIdojarasFrissitesTeszt");
            System.out.println("21. soSzorasTeszt");
            System.out.println(" 0. Kilépés");
            System.out.print("Választás: ");

            int valasz;
            try {
                valasz = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Érvénytelen bemenet, kérj számot!");
                continue;
            }

            switch (valasz) {
                case 1  -> sk.autoNormalHaladasTeszt();
                case 2  -> sk.autoElakadasTeszt();
                case 3  -> sk.autoCsuszasUtkozesTeszt();
                case 4  -> sk.autoElszallitasTeszt();
                case 5  -> sk.hokotroNemTudBelepniTeszt();
                case 6  -> sk.hokotroSarkanyFejTeszt();
                case 7  -> sk.fejEleterejeElfogyTeszt();
                case 8  -> sk.hokotroVasarlasTeszt();
                case 9  -> sk.buszCelbaErTeszt();
                case 10 -> sk.buszBalesetTeszt();
                case 11 -> sk.buszUtvonalmodositasTeszt();
                case 12 -> sk.buszSikeresSavvaltasTeszt();
                case 13 -> sk.buszCsuszasUtkozesTeszt();
                case 14 -> sk.sikeresVasarlasTeszt();
                case 15 -> sk.sikertelenVasarlasTeszt();
                case 16 -> sk.urestakaritasTeszt();
                case 17 -> sk.jegtakaritasTeszt();
                case 18 -> sk.utszakaszIdojarasFrissitesTeszt();
                case 19 -> sk.hidszakaszIdojarasFrissitesTeszt();
                case 20 -> sk.alagutSzakaszIdojarasFrissitesTeszt();
                case 21 -> sk.soSzorasTeszt();
                case 0  -> {
                    System.out.println("Kilépés...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Érvénytelen választás!");
            }
        }
    }
}
