/**
 * Een Ticket bevat de koppeling tussen Passagier en Vliegtuig,
 * met daarbij een klasse (Economy/Business) en een stoelnummer.
 */

public class Ticket {
    private Passagier passagier;
    private Vliegtuig vlucht;
    private String klasse;
    private String stoelNummer;

    public Ticket(Passagier passagier, Vliegtuig vlucht, String klasse) {
        this.passagier = passagier;
        this.vlucht = vlucht;
        this.klasse = klasse;


        int randomNummer = (int) (Math.random() * 200) + 1;
        this.stoelNummer = String.valueOf(randomNummer);
    }

    public Passagier getPassagier() {
        return passagier;
    }

    public Vliegtuig getVlucht() {
        return vlucht;
    }

    public String getKlasse() {
        return klasse;
    }

    public String getStoelNummer() {
        return stoelNummer;
    }

    @Override
    public String toString() {
        return "Ticket [Passagier: " + passagier
                + ", Vlucht: " + vlucht
                + ", Klasse: " + klasse
                + ", Stoelnummer: " + stoelNummer + "]";
    }
}
