public class Suggestion {
    private final Vendeuse vendeuse;
    private final String raison; // e.g., "Bas� sur votre int�r�t pour l'Alloco"

    public Suggestion(Vendeuse vendeuse, String raison) {
        this.vendeuse = vendeuse;
        this.raison = raison;
    }

    public Vendeuse getVendeuse() {
        return vendeuse;
    }

    public String getRaison() {
        return raison;
    }
}