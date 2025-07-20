// Fichier : MainApp.java

import javax.swing.SwingUtilities;

public class MainApp {

    /**
     * C'est le SEUL main que votre application graphique utilisera.
     */
    public static void main(String[] args) {
        // Au d�marrage, on affiche la fen�tre de connexion.
        showLoginScreen();
    }

    // M�thode pour ouvrir la fen�tre de Connexion
    public static void showLoginScreen() {
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }

    // M�thode pour ouvrir la fen�tre d'Inscription
    public static void showRegisterScreen() {
        SwingUtilities.invokeLater(() -> new RegisterUI().setVisible(true));
    }
    
    // M�thode pour ouvrir l'application principale (la recherche)
    public static void showMainUI() {
        SwingUtilities.invokeLater(() -> new MainUI().setVisible(true));
    }
    
    
}