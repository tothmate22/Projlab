import java.io.*;
import java.nio.file.*;

public class Tesztfuttato {

    private static int sikeres = 0;
    private static int sikertelen = 0;

    public static void main(String[] args) throws Exception {
        tesztFuttat("teszt10", "Új hókotró vásárlása");

        System.out.println();
        System.out.println("Eredmény: " + sikeres + " sikeres, " + sikertelen + " sikertelen");
    }

    static void tesztFuttat(String tesztNev, String leiras) throws Exception {
        String inputFajl = "bemenetek/" + tesztNev + "_input.txt";
        String kimenetFajl = "kimenetek/" + tesztNev + "ki.txt";
        String joKimenetFajl = "joKimenetek/" + tesztNev + "ki.txt";

        new File("kimenetek").mkdirs();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream kimenet = new PrintStream(buffer);
        InputStream bemenet = new FileInputStream(inputFajl);

        Main.futtat(bemenet, kimenet);

        Files.writeString(Path.of(kimenetFajl), buffer.toString());

        boolean egyezik = fajlokEgyeznek(kimenetFajl, joKimenetFajl);

        if (egyezik) {
            System.out.println("[SIKERES] " + tesztNev + " – " + leiras);
            sikeres++;
        } else {
            System.out.println("[SIKERTELEN] " + tesztNev + " – " + leiras);
            kiirKulonbseg(kimenetFajl, joKimenetFajl);
            sikertelen++;
        }
    }

    private static boolean fajlokEgyeznek(String kapott, String elvart) throws IOException {
        String kapottSzoveg = Files.readString(Path.of(kapott)).stripTrailing();
        String elvartSzoveg = Files.readString(Path.of(elvart)).stripTrailing();
        return kapottSzoveg.equals(elvartSzoveg);
    }

    private static void kiirKulonbseg(String kapottFajl, String elvartFajl) throws IOException {
        String[] kapott = Files.readString(Path.of(kapottFajl)).stripTrailing().split("\n");
        String[] elvart = Files.readString(Path.of(elvartFajl)).stripTrailing().split("\n");

        int maxSor = Math.max(kapott.length, elvart.length);
        for (int i = 0; i < maxSor; i++) {
            String k = i < kapott.length ? kapott[i] : "<hiányzó sor>";
            String e = i < elvart.length ? elvart[i] : "<hiányzó sor>";
            if (!k.equals(e)) {
                System.out.println("  Sor " + (i + 1) + ":");
                System.out.println("    Várt:    " + e);
                System.out.println("    Kapott:  " + k);
            }
        }
    }
}