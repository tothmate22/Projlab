import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.*;
import java.util.regex.Pattern;

public class Tesztfuttato {

    private static int sikeres = 0;
    private static int sikertelen = 0;

    public static void main(String[] args) throws Exception {
        List<String> tesztek = args.length > 0 ? Arrays.asList(args) : osszesTesztNev();
        for (String tesztNev : tesztek) {
            tesztFuttat(tesztNev, "Automatikus teszt");
        }

        System.out.println();
        System.out.println("Eredmény: " + sikeres + " sikeres, " + sikertelen + " sikertelen");
    }

    static void tesztFuttat(String tesztNev, String leiras) throws Exception {
        String beFajl = "src/bemenetek/" + tesztNev + "Be.txt";
        String inputFajl = "src/bemenetek/" + tesztNev + "Input.txt";
        String kimenetFajl = "kimenetek/" + tesztNev + "ki.txt";
        String joKimenetFajl = "src/joKimenetek/" + tesztNev + "ki.txt";

        new File("kimenetek").mkdirs();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream kimenet = new PrintStream(buffer);
        Path bePath = Path.of(beFajl);
        Path inputPath = Path.of(inputFajl);
        boolean beExists = Files.exists(bePath);
        boolean inputExists = Files.exists(inputPath);
        InputStream bemenet;
        if (beExists && inputExists) {
            Path parancsFajl;
            Path palyaFajl;
            if (parancsFajl(bePath) && !parancsFajl(inputPath)) {
                parancsFajl = bePath;
                palyaFajl = inputPath;
            } else if (parancsFajl(inputPath) && !parancsFajl(bePath)) {
                parancsFajl = inputPath;
                palyaFajl = bePath;
            } else {
                palyaFajl = bePath;
                parancsFajl = inputPath;
            }
            bemenet = new ByteArrayInputStream(osszefuzottTartalom(palyaFajl, parancsFajl).getBytes(StandardCharsets.UTF_8));
        } else if (inputExists) {
            bemenet = new FileInputStream(inputFajl);
        } else if (beExists) {
            bemenet = new FileInputStream(beFajl);
        } else {
            throw new FileNotFoundException("Neither input nor Be file found for " + tesztNev);
        }

        Main.futtat(bemenet, kimenet, false);

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

    private static List<String> osszesTesztNev() throws IOException {
        try (Stream<Path> utak = Files.list(Path.of("src", "bemenetek"))) {
            return utak
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(nev -> nev.endsWith("Input.txt"))
                    .map(nev -> nev.substring(0, nev.length() - "Input.txt".length()))
                    .sorted()
                    .toList();
        }
    }

    private static boolean fajlokEgyeznek(String kapott, String elvart) throws IOException {
        List<String> kSorok = Files.readAllLines(Path.of(kapott), StandardCharsets.UTF_8);
        List<String> eSorok = Files.readAllLines(Path.of(elvart), StandardCharsets.UTF_8);
        
        // Tisztítás: szóközök levágása a sorok végéről + üres sorok kihagyása a fájl végén
        List<String> kTisztitott = kSorok.stream().map(String::stripTrailing).filter(s -> !s.isEmpty()).toList();
        List<String> eTisztitott = eSorok.stream().map(String::stripTrailing).filter(s -> !s.isEmpty()).toList();
        
        return kTisztitott.equals(eTisztitott);
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

    private static boolean parancsFajl(Path fajl) throws IOException {
        String tartalom = Files.readString(fajl);
        return Pattern.compile("(?m)^(load|info|move|tick|random|buy|exit|setjeg|setho|setzuzalek|setepulet)\\b").matcher(tartalom).find();
    }

    private static String osszefuzottTartalom(Path palyaFajl, Path parancsFajl) throws IOException {
        String palya = Files.readString(palyaFajl);
        String parancsok = Files.readString(parancsFajl);
        if (!palya.endsWith("\n") && !palya.endsWith("\r")) {
            palya = palya + System.lineSeparator();
        }
        return palya + parancsok;
    }
}