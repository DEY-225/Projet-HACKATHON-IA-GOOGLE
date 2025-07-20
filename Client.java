import java.util.List;


import java.util.List;
import java.util.ArrayList; // Ou une autre implémentation de List

public class Client {
    // Vos champs existants
    private String nom;
    private double latitude;
    private double longitude;

    // --- CHAMP À AJOUTER ---
    private List<String> preferences; // Pour stocker les préférences, ex: ["Alloco", "Attiéké"]

    // Votre constructeur doit être mis à jour pour accepter les préférences
    public Client(String nom, double latitude, double longitude, List<String> preferences) {
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.preferences = preferences;
    }

    // --- MÉTHODE MANQUANTE À AJOUTER ---
    public List<String> getPreferences() {
        return this.preferences;
    }

    // Vos autres getters existants
    public String getNom() {
        return this.nom;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    // Autres méthodes éventuelles de votre classe...
}