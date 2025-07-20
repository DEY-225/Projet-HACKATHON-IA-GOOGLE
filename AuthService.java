// Fichier : AuthService.java

import java.util.List;

public class AuthService {

    /**
     * Tente d'authentifier un utilisateur.
     * @param username Le nom d'utilisateur saisi.
     * @param password Le mot de passe saisi.
     * @return Le Client si l'authentification réussit, sinon null.
     */
    public static Client authenticate(String username, String password) {
        if (username == null || password == null || username.trim().isEmpty()) {
            return null; // Pas de vérification si les champs sont vides
        }

        List<Client> allClients = Database.getClients();

        for (Client client : allClients) {
            // On vérifie si le nom ET le mot de passe correspondent
            if (client.getNom().equalsIgnoreCase(username) && client.getPassword().equals(password)) {
                return client; // Succès ! On retourne l'objet Client.
            }
        }
        
        return null; // Échec, aucun client trouvé avec ces identifiants.
    }
}