/**
 * De Adres-klasse bewaart informatie over
 * de straatnaam, huisnummer/bus en woonplaats van een persoon.
 */

public class Adres {
    private String straatnaam;
    private String bus;
    private int number;
    private String woonplaats;
    private int postcode;

    public Adres(String straatnaam, String bus, int number, String woonplaats, int postcode) {
        this.straatnaam = straatnaam;
        this.bus = bus;
        this.number = number;
        this.woonplaats = woonplaats;
        this.postcode = postcode;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        String busInfo = (bus == null || bus.isEmpty()) ? "" : ", Bus: " + bus;
        return "Straatnaam: " + straatnaam
                + ", Nr: " + number
                + busInfo
                + ", Postcode: " + postcode
                + ", Woonplaats: " + woonplaats;
    }
}
