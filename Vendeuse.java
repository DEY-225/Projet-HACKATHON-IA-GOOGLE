import java.util.List;

public class Vendeuse {
    private String nomVendeuse;
    private String nomCommerce;
    private double note;
    private double latitude;
    private double longitude;
    private String adresseLisible;
    private List<String> produits;
    private boolean estOuverte;

    public Vendeuse(String nomVendeuse, String nomCommerce, double note, double latitude, double longitude, String adresseLisible, List<String> produits, boolean estOuverte) {
        this.nomVendeuse = nomVendeuse;
        this.nomCommerce = nomCommerce;
        this.note = note;
        this.latitude = latitude;
        this.longitude = longitude;
        this.adresseLisible = adresseLisible;
        this.produits = produits;
        this.estOuverte = estOuverte;
    }

    // Getters
    public String getNomVendeuse() { return nomVendeuse; }
    public String getNomCommerce() { return nomCommerce; }
    public double getNote() { return note; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getAdresseLisible() { return adresseLisible; }
    public List<String> getProduits() { return produits; }
    public boolean estOuverte() { return estOuverte; }
}