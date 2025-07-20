// Fichier : ProfileUI.java

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfileUI extends JFrame {

    /**
     * Constructeur qui crée la fenêtre de profil pour un client donné.
     * @param client Le client dont les informations doivent être affichées.
     */
    public ProfileUI(Client client) {
        // --- Configuration de la fenêtre ---
        setTitle("Profil de " + client.getNom());
        setSize(340, 300);
        setResizable(false);
        // DISPOSE_ON_CLOSE ne ferme que cette fenêtre, pas toute l'application
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Ouvre la fenêtre par rapport à la fenêtre principale (ou au centre si null)
        setLocationRelativeTo(null);

        // --- Panneau principal avec des marges ---
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10)); // 2 colonnes, espacement de 10px
        panel.setBorder(new EmptyBorder(15, 15, 15, 15)); // Marge intérieure

        // --- Affichage des informations ---
        panel.add(new JLabel("Nom :"));
        panel.add(new JLabel(client.getNom()));

        panel.add(new JLabel("Latitude :"));
        panel.add(new JLabel(String.valueOf(client.getLatitude())));

        panel.add(new JLabel("Longitude :"));
        panel.add(new JLabel(String.valueOf(client.getLongitude())));

        panel.add(new JLabel("Préférences :"));
        // Joindre la liste des préférences en une seule chaîne de caractères
        String preferencesStr = String.join(", ", client.getPreferences());
        panel.add(new JLabel(preferencesStr));

        // --- Bouton pour fermer ---
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose()); // 'dispose()' ferme cette fenêtre

        // Ajout des composants à la fenêtre
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(closeButton, BorderLayout.SOUTH);
    }
}