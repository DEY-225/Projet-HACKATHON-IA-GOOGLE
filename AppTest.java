// Fichier : AppTest.java

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AppTest {

    private static final double RAYON_RECHERCHE_KM = 10.0;

    public static void main(String[] args) {
        Client client = Database.getClients().get(0);
        executerRechercheActive(client, "Alloco");
        System.out.println("\n--------------------------------------------------\n");
        executerSuggestionsProactives(client);
    }

    public static void executerRechercheActive(Client clientActif, String motCleRecherche) {
        List<Vendeuse> toutesLesVendeuses = Database.getVendeuses();
        List<Vendeuse> resultatsFiltres = toutesLesVendeuses.stream()
            .filter(Vendeuse::estOuverte)
            .filter(v -> v.getProduits().stream().anyMatch(p -> p.equalsIgnoreCase(motCleRecherche)))
            .filter(v -> calculerDistance(clientActif.getLatitude(), clientActif.getLongitude(), v.getLatitude(), v.getLongitude()) <= RAYON_RECHERCHE_KM)
            .sorted(Comparator.comparingDouble((Vendeuse v) -> calculerDistance(clientActif.getLatitude(), clientActif.getLongitude(), v.getLatitude(), v.getLongitude()))
                              .thenComparing(Vendeuse::getNote, Comparator.reverseOrder()))
            .collect(Collectors.toList());

        System.out.println("--- Résultats de la recherche (triés du plus proche au plus éloigné) ---");
        if (resultatsFiltres.isEmpty()) {
            System.out.println("Aucun vendeur de '" + motCleRecherche + "' ouvert trouvé dans un rayon de " + RAYON_RECHERCHE_KM + " km.");
        } else {
            resultatsFiltres.forEach(v -> {
                double distance = calculerDistance(clientActif.getLatitude(), clientActif.getLongitude(), v.getLatitude(), v.getLongitude());
                String produitsVendus = String.join(", ", v.getProduits());
                System.out.printf(
                    "Commerce: %s (par %s)\n" +
                    "  -> Distance: %.2f km\n" +
                    "  -> Note: %.1f/5\n" +
                    "  -> Adresse: %s\n" +
                    "  -> Produits: %s\n\n",
                    v.getNomCommerce(), v.getNomVendeuse(), distance, v.getNote(), v.getAdresseLisible(), produitsVendus
                );
            });
        }
    }

    // --- ICI ON GARDE UNE SEULE VERSION DE LA MÉTHODE ---
    public static List<String> getAllUniqueProducts() {
        return Database.getVendeuses().stream()
                .flatMap(vendeur -> vendeur.getProduits().stream())
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
    }

    public static void executerSuggestionsProactives(Client clientActif) {
        List<Vendeuse> toutesLesVendeuses = Database.getVendeuses();
        System.out.println("\n--- Suggestions proactives pour " + clientActif.getNom() + " ---\n");
        List<Suggestion> suggestionsPourClient = SuggestionEngine.genererSuggestions(clientActif, toutesLesVendeuses);
        if (suggestionsPourClient.isEmpty()) {
            System.out.println("Aucune nouvelle suggestion pour vous pour le moment.");
        } else {
            suggestionsPourClient.forEach(suggestion -> {
                Vendeuse vendeuse = suggestion.getVendeuse();
                double distance = calculerDistance(clientActif.getLatitude(), clientActif.getLongitude(), vendeuse.getLatitude(), vendeuse.getLongitude());
                System.out.printf(
                    "Suggestion : %s\n  -> Raison : %s\n  -> Commerce : %s (Note : %.1f/5)\n  -> Distance : %.2f km\n\n",
                    vendeuse.getNomCommerce(), suggestion.getRaison(), vendeuse.getNomVendeuse(), vendeuse.getNote(), distance
                );
            });
        }
    }

    public static double calculerDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    
}