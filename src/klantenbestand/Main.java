package klantenbestand;

import klantenbestand.models.Klant;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        KlantenFactory klantenFabriek = new KlantenFactory();

        // Deze code test of het bestand NamenlijstGroot correct is ingelezen.
        klantenFabriek.vulNamenLijst();
        klantenFabriek.printNamenLijst();

        // Deze code test of het bestand Plaatsnamenlijst correct is ingelezen.
        klantenFabriek.vulPlaatsenLijst();
        klantenFabriek.printPlaatsenLijst();

        // Test of het Klanten.txt bestand wordt aangemaakt met daarin alle klanten uit de lijst.
        klantenFabriek.vulKlantenLijst(200);
        klantenFabriek.maakKlantenBestand();

        // Test of het Klanten.txt bestand weer kan worden ingelezen
        KlantenFactory klantenFabriek2 = new KlantenFactory();
        klantenFabriek2.leesKlantenBestand();

        // Vergelijk het origineel met de ingelezen variant
        vergelijkKlanten(klantenFabriek.getKlantenLijst(), klantenFabriek2.getKlantenLijst());
    }

    private static void vergelijkKlanten(List<Klant> klantenLijst1, List<Klant> klantenLijst2) {
        System.out.println("Klantenlijsten aan het vergelijken...");

        if (klantenLijst1.size() != klantenLijst2.size()) {
            System.out.printf(
                    "\tFOUT! Aantal objecten in de lijsten komt niet overeen (%d/%d)!%n",
                    klantenLijst1.size(),
                    klantenLijst2.size()
            );

            return;
        }

        int aantalFouten = 0;

        for (int i = 0; i < klantenLijst1.size(); i++) {
            Klant klant1 = klantenLijst1.get(i);
            Klant klant2 = klantenLijst2.get(i);

            if (klant1.equals(klant2)) {
                continue;
            }

            System.out.printf(
                    "\tFOUT! Klant op index %d komt niet overeen!%n\t\tKlant #1: %s%n\t\tKlant #2: %s%n",
                    i,
                    klant1.klantToFileString(),
                    klant2.klantToFileString()
            );

            aantalFouten++;
        }

        if(aantalFouten == 0) {
            System.out.println("\tConslusie: Klantenlijsten zijn gelijk!");
        }
        else {
            System.out.printf("\tConslusie: Klantenlijsten zijn niet gelijk aan elkaar (%d fouten)!%n", aantalFouten);
        }
    }
}
