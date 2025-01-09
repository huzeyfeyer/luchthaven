public class Personeel extends Persoon {
    private String beroep; // "Piloot", "Cabinepersoneel", ...

    public Personeel(String naam, String voornaam, int leeftijd, Adres adres, String beroep) {
        super(naam, voornaam, leeftijd, adres);
        this.beroep = beroep;
    }

    public String getBeroep() {
        return beroep;
    }



    public void voerFlightCheckUit(Vliegtuig v) {
        if ("Piloot".equalsIgnoreCase(beroep)) {
            System.out.println("Flightcheck uitgevoerd door piloot "
                    + getVoornaam() + " " + getNaam()
                    + " op vlucht " + v.getVluchtcode());
            v.setFlightCheckUitgevoerd(true);
        } else {
            System.out.println(getVoornaam() + " " + getNaam()
                    + " is geen piloot en kan geen flightcheck uitvoeren.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Beroep: " + beroep;
    }
}
