import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equals("--teszt")) {
            Tesztfuttato.main(Arrays.copyOfRange(args, 1, args.length));
            return;
        }
        futtat(System.in, System.out, true);
    }

    public static void futtat(InputStream input, PrintStream output) {
        futtat(input, output, false);
    }

    public static void futtat(InputStream input, PrintStream output, boolean kilepesEngedelyezett) {
        PrintStream eredeti = System.out;
        System.setOut(output);

        Ora ora = new Ora(1, "ora");
        Palya palya = new Palya(ora);
        
        // Idojaras létrehozása az Ora-val
        Idojaras idojaras = new Idojaras(palya.getUtszakaszok(), 200, "idojaras", ora);
        ora.addLepheto(idojaras);
        
        Console console = new Console(palya, ora, kilepesEngedelyezett);
        console.setIdojaras(idojaras);

        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String sor = scanner.nextLine();
            // Az Idojaras feltöltése az új Utszakaszokkal
            idojaras.updateUtszakaszok(palya.getUtszakaszok());
            console.feldolgoz(sor);
        }

        System.setOut(eredeti);
    }
}