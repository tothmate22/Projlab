import java.util.Scanner;

/**
 * Belépési pont.
 * Létrehozza a Palya-t és az Ora-t, majd egy végtelen ciklusban
 * olvassa a standard bemenetet és a Console-n keresztül értelmezi.
 */
public class Main {

    public static void main(String[] args) {
        Palya palya = new Palya();
        Ora ora = new Ora();
        Console console = new Console(palya, ora);

        if (args.length > 0) {console.feldolgoz("load " + args[0]);}

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String sor = scanner.nextLine();
            console.feldolgoz(sor);
        }
    }
}