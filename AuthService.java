// Fichier : AuthService.java

import java.util.List;

public class AuthService {

    /**
     * Tente d'authentifier un utilisateur.
     * @param username Le nom d'utilisateur saisi.
     * @param password Le mot de passe saisi.
     * @return Le Client si l'authentification r�ussit, sinon null.
     */
    public static Client authenticate(String username, String password) {
        if (username == null || password == null || username.trim().isEmpty()) {
            return null; // Pas de v�rification si les champs sont vides
        }

        List<Client> allClients = Database.getClients();

        for (Client client : allClients) {
            // On v�rifie si le nom ET le mot de passe correspondent
            if (client.getNom().equalsIgnoreCase(username) && client.getPassword().equals(password)) {
                return client; // Succ�s ! On retourne l'objet Client.
            }
        }
        
        return null; // �chec, aucun client trouv� avec ces identifiants.
    }
}