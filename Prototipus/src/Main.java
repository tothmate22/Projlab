import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equals("--teszt")) {
            Tesztfuttato.main(new String[]{});
            return;
        }
        futtаt(System.in, System.out);
    }

    public static void futtat(InputStream input, PrintStream output) {
        PrintStream eredeti = System.out;
        System.setOut(output);

        Palya palya = new Palya();
        Ora ora = new Ora();
        Console console = new Console(palya, ora);

        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            console.feldolgoz(scanner.nextLine());
        }

        System.setOut(eredeti);
    }
}