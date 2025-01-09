import java.util.List;
import java.util.Scanner;

/**
 * De Main-klasse start de applicatie en toont het keuzemenu.
 * Via het keuzemenu kunnen gebruikers passagiers en vluchten aanmaken,
 * tickets maken, boarding doen, personeel toewijzen en vluchtinfo printen.
 */

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Luchthaven luchthaven = new Luchthaven();

    public static void main(String[] args) {
        int keuze;
        do {
            printMenu();
            keuze = Integer.parseInt(scanner.nextLine());

            switch (keuze) {
                case 1:
                    nieuwePassagier();
                    break;
                case 2:
                    nieuweVlucht();
                    break;
                case 3:
                    ticketAanmaken();
                    break;
                case 4:
                    boarding();
                    break;
                case 5:
                    personeelToewijzen();
                    break;
                case 6:
                    printVluchtInfo();
                    break;
                case 7:
                    flightCheckEnOpstijgen();
                    break;
                case 0:
                    System.out.println("Applicatie afgesloten.");
                    break;
                default:
                    System.out.println("Ongeldige keuze!");
            }
        } while (keuze != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Luchthaven Menu ---");
        System.out.println("1. Nieuwe passagier aanmaken");
        System.out.println("2. Nieuwe vlucht aanmaken");
        System.out.println("3. Ticket aanmaken");
        System.out.println("4. Boarding (passagier op vlucht)");
        System.out.println("5. Personeel toewijzen aan vlucht");
        System.out.println("6. Print vluchtinfo naar bestand");
        System.out.println("7. Flightcheck uitvoeren en check opstijgen");
        System.out.println("0. Afsluiten");
        System.out.print("Keuze: ");
    }


    private static void nieuwePassagier() {
        System.out.print("Achternaam: ");
        String naam = scanner.nextLine();
        System.out.print("Voornaam: ");
        String voornaam = scanner.nextLine();
        System.out.print("Leeftijd: ");
        int leeftijd = Integer.parseInt(scanner.nextLine());


        System.out.print("Straatnaam: ");
        String straat = scanner.nextLine();
        System.out.print("Bus (optioneel): ");
        String bus = scanner.nextLine();
        System.out.print("Huisnummer: ");
        int nr = Integer.parseInt(scanner.nextLine());
        System.out.print("Woonplaats: ");
        String woonplaats = scanner.nextLine();
        System.out.print("Postcode: ");
        int pc = Integer.parseInt(scanner.nextLine());
        Adres adres = new Adres(straat, bus, nr, woonplaats, pc);

        System.out.print("Bagagegewicht (in kg): ");
        double bagage = Double.parseDouble(scanner.nextLine());

        luchthaven.maakNieuwePassagier(naam, voornaam, leeftijd, adres, bagage);
        System.out.println("Passagier aangemaakt.");
    }


    private static void nieuweVlucht() {
        System.out.print("Vluchtcode: ");
        String code = scanner.nextLine();

        System.out.print("Beginbestemming: ");
        String begin = scanner.nextLine();

        System.out.print("Eindbestemming: ");
        String eind = scanner.nextLine();

        System.out.print("Aantal Economy-stoelen: ");
        int eco = Integer.parseInt(scanner.nextLine());

        System.out.print("Aantal Business-stoelen: ");
        int bus = Integer.parseInt(scanner.nextLine());

        luchthaven.maakNieuweVlucht(code, begin, eind, eco, bus);
        System.out.println("Vlucht " + code + " is aangemaakt.");
    }


    private static void ticketAanmaken() {
        System.out.print("Achternaam passagier: ");
        String naam = scanner.nextLine();
        System.out.print("Voornaam passagier: ");
        String voornaam = scanner.nextLine();

        Passagier p = luchthaven.zoekPassagier(naam, voornaam);
        if (p == null) {
            System.out.println("Passagier niet gevonden!");
            return;
        }

        System.out.print("Vluchtcode: ");
        String code = scanner.nextLine();
        Vliegtuig v = luchthaven.zoekVliegtuig(code);
        if (v == null) {
            System.out.println("Vlucht niet gevonden!");
            return;
        }

        System.out.print("Klasse (Economy/Business): ");
        String kls = scanner.nextLine();

        Ticket t = luchthaven.maakTicket(p, v, kls);
        if (t != null) {
            System.out.println("Ticket aangemaakt: " + t);
        }
    }


    private static void boarding() {
        System.out.print("Achternaam passagier: ");
        String naam = scanner.nextLine();
        System.out.print("Voornaam passagier: ");
        String voornaam = scanner.nextLine();

        Passagier p = luchthaven.zoekPassagier(naam, voornaam);
        if (p == null) {
            System.out.println("Passagier niet gevonden!");
            return;
        }

        List<Ticket> tickets = luchthaven.zoekTicketsVoorPassagier(p);
        if (tickets.isEmpty()) {
            System.out.println("Geen ticket(s) gevonden voor passagier.");
            return;
        }

        if (tickets.size() == 1) {
            boolean ok = luchthaven.boardPassagier(tickets.get(0));
            System.out.println(ok ? "Boarding geslaagd." : "Boarding mislukt.");
        } else {
            // Meerdere tickets => kies vluchtcode
            System.out.println("Meerdere tickets gevonden:");
            for (Ticket t : tickets) {
                System.out.println(" - Vluchtcode: " + t.getVlucht().getVluchtcode()
                        + ", Klasse: " + t.getKlasse()
                        + ", Stoel: " + t.getStoelNummer());
            }
            System.out.print("Kies vluchtcode om te boarden: ");
            String code = scanner.nextLine();

            Ticket gekozen = null;
            for (Ticket t : tickets) {
                if (t.getVlucht().getVluchtcode().equalsIgnoreCase(code)) {
                    gekozen = t;
                    break;
                }
            }
            if (gekozen == null) {
                System.out.println("Ongeldige keuze.");
                return;
            }
            boolean ok = luchthaven.boardPassagier(gekozen);
            System.out.println(ok ? "Boarding geslaagd." : "Boarding mislukt.");
        }
    }


    private static void personeelToewijzen() {
        System.out.println("1) Bestaand personeelslid");
        System.out.println("2) Nieuw personeelslid");
        int k = Integer.parseInt(scanner.nextLine());

        Personeel pers = null;
        if (k == 1) {
            System.out.print("Achternaam personeel: ");
            String naam = scanner.nextLine();
            System.out.print("Voornaam personeel: ");
            String voornaam = scanner.nextLine();

            pers = luchthaven.zoekPersoneel(naam, voornaam);
            if (pers == null) {
                System.out.println("Personeelslid niet gevonden!");
                return;
            }
        } else if (k == 2) {
            pers = maakNieuwPersoneel();
        } else {
            System.out.println("Ongeldige keuze.");
            return;
        }

        System.out.print("Vluchtcode: ");
        String code = scanner.nextLine();
        Vliegtuig v = luchthaven.zoekVliegtuig(code);
        if (v == null) {
            System.out.println("Vlucht niet gevonden!");
            return;
        }

        luchthaven.voegPersoneelToeAanVlucht(pers, v);
    }

    private static Personeel maakNieuwPersoneel() {
        System.out.print("Achternaam: ");
        String naam = scanner.nextLine();
        System.out.print("Voornaam: ");
        String voornaam = scanner.nextLine();
        System.out.print("Leeftijd: ");
        int leeftijd = Integer.parseInt(scanner.nextLine());


        System.out.print("Straatnaam: ");
        String straat = scanner.nextLine();
        System.out.print("Bus (optioneel): ");
        String bus = scanner.nextLine();
        System.out.print("Huisnummer: ");
        int nr = Integer.parseInt(scanner.nextLine());
        System.out.print("Woonplaats: ");
        String woonplaats = scanner.nextLine();
        System.out.print("Postcode: ");
        int pc = Integer.parseInt(scanner.nextLine());
        Adres adres = new Adres(straat, bus, nr, woonplaats, pc);

        System.out.print("Beroep (Piloot, etc.): ");
        String beroep = scanner.nextLine();

        return luchthaven.maakNieuwPersoneel(naam, voornaam, leeftijd, adres, beroep);
    }


    private static void printVluchtInfo() {
        System.out.print("Vluchtcode: ");
        String code = scanner.nextLine();

        Vliegtuig v = luchthaven.zoekVliegtuig(code);
        if (v == null) {
            System.out.println("Vlucht niet gevonden!");
            return;
        }

        luchthaven.printVluchtInfo(v);
    }


    private static void flightCheckEnOpstijgen() {
        System.out.print("Vluchtcode: ");
        String code = scanner.nextLine();
        Vliegtuig v = luchthaven.zoekVliegtuig(code);
        if (v == null) {
            System.out.println("Vlucht niet gevonden!");
            return;
        }

        System.out.println("Wie voert de flightcheck uit? (piloot)");
        System.out.print("Achternaam: ");
        String naam = scanner.nextLine();
        System.out.print("Voornaam: ");
        String voornaam = scanner.nextLine();

        Personeel piloot = luchthaven.zoekPersoneel(naam, voornaam);
        if (piloot == null) {
            System.out.println("Personeelslid niet gevonden!");
            return;
        }


        piloot.voerFlightCheckUit(v);


        if (v.kanOpstijgen()) {
            System.out.println("Vlucht " + code + " kan opstijgen!");
        } else {
            System.out.println("Vlucht " + code + " kan (nog) niet opstijgen!");
        }
    }
}
