// Fichier : MainApp.java

import javax.swing.SwingUtilities;

public class MainApp {

    /**
     * C'est le SEUL main que votre application graphique utilisera.
     */
    public static void main(String[] args) {
        // Au démarrage, on affiche la fenêtre de connexion.
        showLoginScreen();
    }

    // Méthode pour ouvrir la fenêtre de Connexion
    public static void showLoginScreen() {
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }

    // Méthode pour ouvrir la fenêtre d'Inscription
    public static void showRegisterScreen() {
        SwingUtilities.invokeLater(() -> new RegisterUI().setVisible(true));
    }
    
    // Méthode pour ouvrir l'application principale (la recherche)
    public static void showMainUI() {
        SwingUtilities.invokeLater(() -> new MainUI().setVisible(true));
    }
    
    
}