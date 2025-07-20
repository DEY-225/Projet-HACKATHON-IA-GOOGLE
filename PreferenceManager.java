public class PreferenceManager {

    /**
     * Enregistre le mot-clé d'une recherche dans les préférences du client.
     * Simule un processus d'apprentissage en arrière-plan.
     *
     * @param client Le client qui a effectué la recherche.
     * @param motCleRecherche Le mot-clé tapé par le client.
     */
    public static void enregistrerRecherche(Client client, String motCleRecherche) {
        if (motCleRecherche == null || motCleRecherche.trim().isEmpty()) {
            return; // Ne pas enregistrer de recherches vides
        }

        System.out.println("\n[Processus en arrière-plan] Analyse de l'activité de l'utilisateur...");
        boolean aEteAjoute = client.ajouterPreference(motCleRecherche);

        if (aEteAjoute) {
            System.out.println("-> La recherche pour \"" + motCleRecherche + "\" a été ajoutée à vos préférences pour de futures suggestions.");
        } else {
            System.out.println("-> La préférence pour \"" + motCleRecherche + "\" existe déjà.");
        }
    }
}


