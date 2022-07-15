package klantenbestand;

import klantenbestand.models.Klant;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class KlantenFactory {
    private ArrayList<String> plaatsenLijst;
    private ArrayList<String> namenLijst;
    private ArrayList<Klant> klantenLijst;
    private File file;
    private static final String UTF8_BOM = "\uFEFF";

    public KlantenFactory() {
        plaatsenLijst = new ArrayList<>();
        namenLijst = new ArrayList<>();
        klantenLijst = new ArrayList<>();
    }

    public void vulPlaatsenLijst() {
        file = new File("resources/Plaatsnamenlijst.txt");

        try (
                Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
        ) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().strip();

                if (line.startsWith(UTF8_BOM)) {
                    line = line.substring(1);
                }

                plaatsenLijst.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Data file doesn't exist!");
        } catch (Exception e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void vulNamenLijst() {
        file = new File("resources/Namenlijst.csv");

        try (
                Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
        ) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().strip();

                if (line.startsWith(UTF8_BOM)) {
                    line = line.substring(1);
                }

                String[] values = line.split(",");
                String namePrefix = values[0];
                String surname = values[1];
                namenLijst.add(namePrefix + " " + surname);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Data file doesn't exist!");
        } catch (Exception e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void vulKlantenLijst(int lijstGrootte) {
        int idKlant = 1;

        for (int i = 0; i < lijstGrootte; i++) {
            String[] naamSplit = kiesRandomNaam().split(",");
            String achternaam;

            if (naamSplit.length > 1) {
                String tussenvoegsel = naamSplit[0];
                achternaam = naamSplit[1];
                Klant nieuweKlant = new Klant(idKlant++, tussenvoegsel, achternaam, kiesRandomPlaats());
                klantenLijst.add(nieuweKlant);
            } else {
                achternaam = naamSplit[0];
                Klant nieuweKlant = new Klant(idKlant++, achternaam, kiesRandomPlaats());
                klantenLijst.add(nieuweKlant);
            }
        }
    }

    public void maakKlantenBestand() {
        // TODO: Schrijf alle klanten in de ArrayList klantenLijst naar het bestand "resources/Klanten.txt"
        // Tip: Gebruik de klantToFileString-methode van het klant-object!

        file = new File("resources/Klantenlijst.txt");

        try (
                PrintWriter writer = new PrintWriter(file)
        ) {
            for (Klant klant : klantenLijst) {
                writer.println(klant.klantToFileString());
            }

        } catch (FileNotFoundException e) {
            System.err.println("Couldn't save data file!");
        } catch (Exception e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void leesKlantenBestand() {
        // TODO: Lees het bestand "resources/Klanten.txt" en zet elke regel in de ArrayList klantenLijst.

        file = new File("resources/Klantenlijst.txt");

        try (
                Scanner scanner = new Scanner(file);
        ) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().strip();

                if (line.startsWith(UTF8_BOM)) {
                    line = line.substring(1);
                }

                String[] values = line.split(",");
                int idKlant = Integer.parseInt(values[0]);
                String achternaam;

                if (values.length <= 3) {
                    achternaam = values[1];
                    Klant nieuweKlant = new Klant(idKlant, achternaam, kiesRandomPlaats());
                    klantenLijst.add(nieuweKlant);
                } else {
                    String tussenvoegsel = values[1];
                    achternaam = values[2];
                    Klant nieuweKlant = new Klant(idKlant, tussenvoegsel, achternaam, kiesRandomPlaats());
                    klantenLijst.add(nieuweKlant);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Data file doesn't exist!");
        } catch (Exception e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void printNamenLijst() {
        System.out.println("NamenLijst:");

        int teller = 0;

        for (String naam : namenLijst) {
            if (teller % 8 == 0) {
                System.out.println();
            }

            System.out.printf("%20s", naam);
            teller++;
        }

        System.out.println("\n");
    }

    public void printPlaatsenLijst() {
        System.out.println("PlaatsenLijst:");

        int teller = 0;
        for (String plaats : plaatsenLijst) {
            if (teller % 8 == 0) {
                System.out.println();
            }
            System.out.printf("%-25s", plaats);
            teller++;
        }

        System.out.println("\n");
    }

    public ArrayList<Klant> getKlantenLijst() {
        return klantenLijst;
    }

    private String kiesRandomPlaats() {
        int randomIndex = (int) (plaatsenLijst.size() * Math.random());
        return plaatsenLijst.get(randomIndex);
    }

    private String kiesRandomNaam() {
        int randomIndex = (int) (namenLijst.size() * Math.random());
        return namenLijst.get(randomIndex);
    }
}
