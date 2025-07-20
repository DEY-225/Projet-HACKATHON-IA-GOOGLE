public class PreferenceManager {

    /**
     * Enregistre le mot-cl� d'une recherche dans les pr�f�rences du client.
     * Simule un processus d'apprentissage en arri�re-plan.
     *
     * @param client Le client qui a effectu� la recherche.
     * @param motCleRecherche Le mot-cl� tap� par le client.
     */
    public static void enregistrerRecherche(Client client, String motCleRecherche) {
        if (motCleRecherche == null || motCleRecherche.trim().isEmpty()) {
            return; // Ne pas enregistrer de recherches vides
        }

        System.out.println("\n[Processus en arri�re-plan] Analyse de l'activit� de l'utilisateur...");
        boolean aEteAjoute = client.ajouterPreference(motCleRecherche);

        if (aEteAjoute) {
            System.out.println("-> La recherche pour \"" + motCleRecherche + "\" a �t� ajout�e � vos pr�f�rences pour de futures suggestions.");
        } else {
            System.out.println("-> La pr�f�rence pour \"" + motCleRecherche + "\" existe d�j�.");
        }
    }
}


