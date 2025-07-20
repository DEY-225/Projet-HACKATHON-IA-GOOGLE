import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Database {

    public static List<Client> getClients() {
        Client client1 = new Client("Amara Diakité", 5.345, -4.02, Arrays.asList("Alloco", "Brochettes"));
        Client client2 = new Client("Bintou Coulibaly", 5.355, -4.03, Arrays.asList("Attiéké", "Poisson Braisé"));
        Client client3 = new Client("Yannick Konan", 5.340, -4.01, Arrays.asList("Garba", "Frites"));
        Client client4 = new Client("Fatoumata Traoré", 5.360, -4.025, Arrays.asList("Jus de Bissap", "Alloco"));
        return Arrays.asList(client1, client2, client3, client4);
    }
    
    

    public static List<Vendeuse> getVendeuses() {
        List<Vendeuse> vendeurs = new ArrayList<>();

        String[] produitsDisponibles = {
            "Alloco", "Attiéké", "Frites", "Poulet Braisé", "Poisson Braisé",
            "Brochettes", "Garba", "Jus de Bissap", "Jus de Gingembre", "Dêguê", "Choukouya"
        };

        String[] nomsVendeuses = {"Adjoua", "Thérèse", "Kady", "Awa", "Aminata", "Joséphine", "Marie", "Fatou", "Nafissa", "Bintou"};
        String[] nomsVendeurs = {"Moussa", "Ibrahim", "Koffi", "Ousmane", "Didier", "Ali", "Serge", "Hamed", "Yacouba", "Abdoulaye"};
        String[] prefixesVendeurs = { "Tonton", "Papa", "Le Vieux", "Chez" };
        String[] nomsCommerces = {"Le Délice", "La Saveur", "Le Coin Gourmand", "Le Bon Goût", "La Pause", "Le Régal", "L'Express"};
        String[] nomsRues = {"Rue des Palmiers", "Boulevard de la Lagune", "Avenue de la Paix", "Rue des Artisans", "Carrefour de la Joie", "Place de l'Indépendance", "Rue du Commerce", "Boulevard du Marché", "Avenue des Reines"};

        for (int i = 0; i < 50; i++) {
            String nomPersonne, nomCommerce, nomBase;

            if (ThreadLocalRandom.current().nextBoolean()) {
                nomBase = nomsVendeurs[ThreadLocalRandom.current().nextInt(nomsVendeurs.length)];
                String prefix = prefixesVendeurs[ThreadLocalRandom.current().nextInt(prefixesVendeurs.length)];
                nomPersonne = prefix + " " + nomBase;
                nomCommerce = nomsCommerces[ThreadLocalRandom.current().nextInt(nomsCommerces.length)] + " de " + nomBase;
            } else {
                nomBase = nomsVendeuses[ThreadLocalRandom.current().nextInt(nomsVendeuses.length)];
                nomPersonne = "Tanty " + nomBase;
                nomCommerce = nomsCommerces[ThreadLocalRandom.current().nextInt(nomsCommerces.length)] + " d'" + nomBase;
            }

            double note = Math.round((3.5 + ThreadLocalRandom.current().nextDouble(1.5)) * 10.0) / 10.0;
            double latitude = 5.33 + ThreadLocalRandom.current().nextDouble(0.04);
            
            // --- LIGNE CORRIGÉE ---
            // On génère un nombre positif qu'on soustrait pour obtenir une longitude dans l'intervalle souhaité
            double longitude = -4.01 - ThreadLocalRandom.current().nextDouble(0.03); // entre -4.01 et -4.04

            String adresse = nomsRues[ThreadLocalRandom.current().nextInt(nomsRues.length)] + ", N°" + (i + 1);

            List<String> produits = new ArrayList<>();
            int nbProduits = ThreadLocalRandom.current().nextInt(1, 4);
            for (int j = 0; j < nbProduits; j++) {
                String produit = produitsDisponibles[ThreadLocalRandom.current().nextInt(produitsDisponibles.length)];
                if (!produits.contains(produit)) {
                    produits.add(produit);
                }
            }
            if (produits.isEmpty()) produits.add("Alloco");

            boolean estOuverte = ThreadLocalRandom.current().nextDouble() < 0.8;
            vendeurs.add(new Vendeuse(nomPersonne, nomCommerce, note, latitude, longitude, adresse, produits, estOuverte));
        }

        vendeurs.set(0, new Vendeuse("Tanty Adjoua", "Alloco Chic", 4.8, 5.346, -4.021, "Rue des Jardins", Arrays.asList("Alloco", "Frites"), true));
        vendeurs.set(1, new Vendeuse("Tonton Moussa", "Le Garba Choc de Moussa", 4.7, 5.339, -4.012, "Rue du Port", Collections.singletonList("Garba"), true));
        vendeurs.set(2, new Vendeuse("Reine de l'Attiéké", "Chez Awa", 4.9, 5.351, -4.029, "Place du Marché", Arrays.asList("Attiéké", "Poisson Braisé"), true));
        vendeurs.set(3, new Vendeuse("Papa Koffi", "Le Poulet d'Or", 4.6, 5.355, -4.022, "Avenue du Rire", Arrays.asList("Poulet Braisé", "Brochettes"), true));
        vendeurs.set(4, new Vendeuse("Tata Kady", "Alloco Express", 4.2, 5.360, -4.030, "Avenue de la République", Arrays.asList("Alloco", "Poulet Braisé"), false));
        vendeurs.set(5, new Vendeuse("Maman Bintou", "Les Jus Frais", 4.9, 5.341, -4.028, "Carrefour Jeunesse", Arrays.asList("Jus de Bissap", "Jus de Gingembre"), true));
        vendeurs.set(6, new Vendeuse("Le Vieux Ali", "Choukouya du Soir", 4.5, 5.349, -4.015, "Boulevard des Martyrs", Collections.singletonList("Choukouya"), true));
        vendeurs.set(7, new Vendeuse("Mireille & Co", "La Friterie du Lycée", 4.0, 5.358, -4.026, "Rue des Ecoles", Arrays.asList("Frites", "Jus de Bissap"), false)); // Fermée
        vendeurs.set(8, new Vendeuse("Tanty Nafissa", "Le Dêguê Sucré", 4.7, 5.338, -4.025, "Près de la Grande Mosquée", Collections.singletonList("Dêguê"), true));
        vendeurs.set(9, new Vendeuse("Serge le Brochettier", "Brochettes Party", 4.3, 5.343, -4.019, "Rond-Point de la Victoire", Arrays.asList("Brochettes", "Alloco", "Frites"), true));
        return vendeurs;
    }
}