import java.util.ArrayList;
import java.util.List;

/**
 * De Vliegtuig-klasse representeert een vliegtuig
 * met begin- en eindbestemming, aantal economy- en businessstoelen,
 * en lijsten voor passagiers en personeel.
 */

public class Vliegtuig {
    private String vluchtcode;
    private String beginbestemming;   // baslangic
    private String eindbestemming;    // varis
    private int economyStoelen;
    private int businessStoelen;

    private List<Passagier> passagiers;
    private List<Personeel> personeel;


    private boolean flightCheckUitgevoerd;

    public Vliegtuig(String vluchtcode,
                     String beginbestemming,
                     String eindbestemming,
                     int economyStoelen,
                     int businessStoelen) {
        this.vluchtcode = vluchtcode;
        this.beginbestemming = beginbestemming;
        this.eindbestemming = eindbestemming;
        this.economyStoelen = economyStoelen;
        this.businessStoelen = businessStoelen;

        this.passagiers = new ArrayList<>();
        this.personeel = new ArrayList<>();


        this.flightCheckUitgevoerd = false;
    }


    public String getVluchtcode() {
        return vluchtcode;
    }
    public String getBeginbestemming() {
        return beginbestemming;
    }
    public String getEindbestemming() {
        return eindbestemming;
    }


    public void setFlightCheckUitgevoerd(boolean flightCheckUitgevoerd) {
        this.flightCheckUitgevoerd = flightCheckUitgevoerd;
    }


    public boolean kanOpstijgen() {

        int aantalPiloten = 0;
        for (Personeel p : personeel) {
            if ("Piloot".equalsIgnoreCase(p.getBeroep())) {
                aantalPiloten++;
            }
        }
        if (aantalPiloten < 1) {
            System.out.println("Geen (of onvoldoende) piloot aanwezig!");
            return false;
        }


        if (personeel.size() < 3) {
            System.out.println("Niet genoeg personeel!");
            return false;
        }


        if (!flightCheckUitgevoerd) {
            System.out.println("Flightcheck is nog niet uitgevoerd!");
            return false;
        }


        return true;
    }

    public boolean voegPassagierToe(Ticket ticket) {
        if (!this.vluchtcode.equalsIgnoreCase(ticket.getVlucht().getVluchtcode())) {
            System.out.println("Fout: Ticket is niet voor deze vlucht (" + vluchtcode + ").");
            return false;
        }

        String klasse = ticket.getKlasse();
        Passagier passagier = ticket.getPassagier();

        if ("Economy".equalsIgnoreCase(klasse)) {
            if (economyStoelen > 0) {
                economyStoelen--;
                passagiers.add(passagier);
                System.out.println("Passagier " + passagier.getVoornaam() + " " + passagier.getNaam()
                        + " toegevoegd aan Economy (stoel: " + ticket.getStoelNummer() + ").");
                return true;
            } else {
                System.out.println("Geen Economy-stoelen meer beschikbaar!");
                return false;
            }
        } else if ("Business".equalsIgnoreCase(klasse)) {
            if (businessStoelen > 0) {
                businessStoelen--;
                passagiers.add(passagier);
                System.out.println("Passagier " + passagier.getVoornaam() + " " + passagier.getNaam()
                        + " toegevoegd aan Business (stoel: " + ticket.getStoelNummer() + ").");
                return true;
            } else {
                System.out.println("Geen Business-stoelen meer beschikbaar!");
                return false;
            }
        } else {
            System.out.println("Onbekende klasse: " + klasse);
            return false;
        }
    }

    public void voegPersoneelToe(Personeel p) {
        personeel.add(p);
        System.out.println("Personeelslid " + p.getVoornaam() + " " + p.getNaam()
                + " (Beroep: " + p.getBeroep() + ") toegevoegd aan vlucht " + vluchtcode);
    }

    @Override
    public String toString() {

        String txt = "";
        txt += "Vluchtcode: " + vluchtcode
                + "\nBeginbestemming: " + beginbestemming
                + "\nEindbestemming: " + eindbestemming
                + "\nFlightcheck: " + (flightCheckUitgevoerd ? "Uitgevoerd" : "Niet uitgevoerd")
                + "\n--- Passagiers ---\n";

        if (passagiers.isEmpty()) {
            txt += "(Geen passagiers)\n";
        } else {

            for (Passagier p : passagiers) {
                txt += "- " + p.getVoornaam() + " " + p.getNaam()
                        + " (Bagage: " + p.getBagageGewicht() + "kg)\n";
            }
        }

        txt += "--- Personeel ---\n";
        if (personeel.isEmpty()) {
            txt += "(Geen personeel)\n";
        } else {

            for (Personeel pr : personeel) {
                txt += "- " + pr.getVoornaam() + " " + pr.getNaam()
                        + " (Beroep: " + pr.getBeroep() + ")\n";
            }
        }
        return txt;
    }

}
