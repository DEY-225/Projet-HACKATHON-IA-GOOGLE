import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class SuggestionEngine {

    private static final double RAYON_SUGGESTION_KM = 2.0; // Rayon pour les suggestions proactives
    private static final Set<String> suggestionsDejaFaites = new HashSet<>();

    /**
     * G�n�re des suggestions pour un client sp�cifique en se basant sur ses pr�f�rences.
     * @param client Le client pour qui g�n�rer les suggestions.
     * @param toutesLesVendeuses La liste de toutes les vendeuses disponibles.
     * @return Une liste de suggestions pertinentes.
     */
    public static List<Suggestion> genererSuggestions(Client client, List<Vendeuse> toutesLesVendeuses) {
        List<Suggestion> suggestions = new ArrayList<>();
        List<String> preferences = client.getPreferences(); // ex: ["Alloco", "Atti�k�"]

        for (String preference : preferences) {
            List<Vendeuse> vendeusesPertinentes = toutesLesVendeuses.stream()
                // On ne garde que les vendeuses ouvertes
                .filter(Vendeuse::estOuverte)
                // On filtre par produit (pr�f�rence du client)
                .filter(v -> v.getProduits().stream().anyMatch(p -> p.equalsIgnoreCase(preference)))
                // On filtre par distance
                .filter(v -> AppTest.calculerDistance(client.getLatitude(), client.getLongitude(), v.getLatitude(), v.getLongitude()) <= RAYON_SUGGESTION_KM)
                // On trie par note (meilleures d'abord)
                .sorted((v1, v2) -> Double.compare(v2.getNote(), v1.getNote()))
                .collect(Collectors.toList());

            for (Vendeuse vendeuse : vendeusesPertinentes) {
                // Pour �viter de sugg�rer plusieurs fois la m�me chose
                String suggestionId = client.getNom() + ":" + vendeuse.getNomCommerce();
                if (!suggestionsDejaFaites.contains(suggestionId)) {
                    String raison = "Bas� sur votre int�r�t pour : " + preference;
                    suggestions.add(new Suggestion(vendeuse, raison));
                    suggestionsDejaFaites.add(suggestionId);
                }
            }
        }
        return suggestions;
    }
}