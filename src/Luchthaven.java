import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Luchthaven {
    private List<Passagier> passagiers;
    private List<Personeel> personeelsLeden;
    private List<Vliegtuig> vluchten;
    private List<Ticket> tickets;


    private static final double BAGAGE_LIMIET = 20.0;

    public Luchthaven() {
        passagiers = new ArrayList<>();
        personeelsLeden = new ArrayList<>();
        vluchten = new ArrayList<>();
        tickets = new ArrayList<>();
    }


    public Passagier maakNieuwePassagier(String naam, String voornaam, int leeftijd, Adres adres, double bagageGewicht) {
        Passagier p = new Passagier(naam, voornaam, leeftijd, adres, bagageGewicht);
        passagiers.add(p);
        return p;
    }


    public Vliegtuig maakNieuweVlucht(String vluchtcode,
                                      String beginbestemming,
                                      String eindbestemming,
                                      int economyStoelen,
                                      int businessStoelen) {
        Vliegtuig v = new Vliegtuig(vluchtcode, beginbestemming, eindbestemming, economyStoelen, businessStoelen);
        vluchten.add(v);
        return v;
    }


    public Ticket maakTicket(Passagier passagier, Vliegtuig vlucht, String klasse) {
        if (passagier.getBagageGewicht() > BAGAGE_LIMIET) {
            System.out.println("Bagage te zwaar (" + passagier.getBagageGewicht() + " kg). Geen ticket aangemaakt!");
            return null;
        }
        Ticket t = new Ticket(passagier, vlucht, klasse);
        tickets.add(t);
        return t;
    }


    public boolean boardPassagier(Ticket ticket) {
        return ticket.getVlucht().voegPassagierToe(ticket);
    }


    public Personeel maakNieuwPersoneel(String naam, String voornaam, int leeftijd, Adres adres, String beroep) {
        Personeel p = new Personeel(naam, voornaam, leeftijd, adres, beroep);
        personeelsLeden.add(p);
        return p;
    }

    public void voegPersoneelToeAanVlucht(Personeel p, Vliegtuig v) {
        v.voegPersoneelToe(p);
    }


    public void printVluchtInfo(Vliegtuig v) {
        String bestandsNaam = "vlucht_" + v.getVluchtcode() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bestandsNaam))) {
            writer.write(v.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Vluchtinfo opgeslagen in " + bestandsNaam);
    }


    public Passagier zoekPassagier(String naam, String voornaam) {
        for (Passagier p : passagiers) {
            if (p.getNaam().equalsIgnoreCase(naam)
                    && p.getVoornaam().equalsIgnoreCase(voornaam)) {
                return p;
            }
        }
        return null;
    }

    public Vliegtuig zoekVliegtuig(String code) {
        for (Vliegtuig v : vluchten) {
            if (v.getVluchtcode().equalsIgnoreCase(code)) {
                return v;
            }
        }
        return null;
    }

    public Personeel zoekPersoneel(String naam, String voornaam) {
        for (Personeel p : personeelsLeden) {
            if (p.getNaam().equalsIgnoreCase(naam)
                    && p.getVoornaam().equalsIgnoreCase(voornaam)) {
                return p;
            }
        }
        return null;
    }

    public List<Ticket> zoekTicketsVoorPassagier(Passagier passagier) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getPassagier().equals(passagier)) {
                result.add(t);
            }
        }
        return result;
    }
}
