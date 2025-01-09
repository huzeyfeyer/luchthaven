public class Passagier extends Persoon {
    private double bagageGewicht;

    public Passagier(String naam, String voornaam, int leeftijd, Adres adres, double bagageGewicht) {
        super(naam, voornaam, leeftijd, adres);
        this.bagageGewicht = bagageGewicht;
    }

    public double getBagageGewicht() {
        return bagageGewicht;
    }

    @Override
    public String toString() {
        return super.toString() + ", BagageGewicht: " + bagageGewicht + "kg";
    }
}
