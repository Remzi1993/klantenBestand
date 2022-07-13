package klantenbestand.models;

public class Klant {
    private final int idKlant;
    private String tussenvoegsel;
    private String klantnaam;
    private String woonplaats;

    public Klant(int idKlant) {
        this.idKlant = idKlant;
    }

    public Klant(int idKlant, String tussenvoegsel, String klantnaam, String woonplaats) {
        this.idKlant = idKlant;
        this.tussenvoegsel = tussenvoegsel;
        this.klantnaam = klantnaam;
        this.woonplaats = woonplaats;
    }

    public int getIdKlant() {
        return idKlant;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getKlantnaam() {
        return klantnaam;
    }

    public void setKlantnaam(String klantnaam) {
        this.klantnaam = klantnaam;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String klantToFileString() {
        return String.format("%d,%s,%s,%s", idKlant, tussenvoegsel, klantnaam, woonplaats);
    }

    @Override
    public String toString() {
        String naam;
        if (tussenvoegsel == null) {
            naam = klantnaam;
        } else {
            naam = tussenvoegsel + " " + klantnaam;
        }
        return String.format("%-2d - %-20s%-25s", idKlant, naam, woonplaats);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Klant klant) {
            return getIdKlant() == klant.getIdKlant() && getTussenvoegsel().equals(klant.getTussenvoegsel()) &&
                    getKlantnaam().equals(klant.getKlantnaam()) && getWoonplaats().equals(klant.getWoonplaats());
        }

        return false;
    }
}
