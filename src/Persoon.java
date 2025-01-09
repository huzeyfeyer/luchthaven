/**
 * De Persoon-klasse is een basisklasse voor Passagiers en Personeel.
 * Bevat algemene velden zoals naam, voornaam, leeftijd en adres.
 */

public class Persoon {
    private String naam;       // Achternaam
    private String voornaam;   // Voornaam
    private int leeftijd;
    private Adres adres;

    public Persoon(String naam, String voornaam, int leeftijd, Adres adres) {
        this.naam = naam;
        this.voornaam = voornaam;
        this.leeftijd = leeftijd;
        this.adres = adres;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public Adres getAdres() {
        return adres;
    }

    @Override
    public String toString() {
        return "Naam: " + voornaam + " " + naam
                + ", Leeftijd: " + leeftijd
                + ", Adres: [" + adres + "]";
    }
}
