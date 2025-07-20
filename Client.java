import java.util.List;


import java.util.List;
import java.util.ArrayList; // Ou une autre impl�mentation de List

public class Client {
    // Vos champs existants
    private String nom;
    private double latitude;
    private double longitude;

    // --- CHAMP � AJOUTER ---
    private List<String> preferences; // Pour stocker les pr�f�rences, ex: ["Alloco", "Atti�k�"]

    // Votre constructeur doit �tre mis � jour pour accepter les pr�f�rences
    public Client(String nom, double latitude, double longitude, List<String> preferences) {
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.preferences = preferences;
    }

    // --- M�THODE MANQUANTE � AJOUTER ---
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

    // Autres m�thodes �ventuelles de votre classe...
}