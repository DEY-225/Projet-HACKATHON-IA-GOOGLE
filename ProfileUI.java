// Fichier : ProfileUI.java

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfileUI extends JFrame {

    /**
     * Constructeur qui cr�e la fen�tre de profil pour un client donn�.
     * @param client Le client dont les informations doivent �tre affich�es.
     */
    public ProfileUI(Client client) {
        // --- Configuration de la fen�tre ---
        setTitle("Profil de " + client.getNom());
        setSize(340, 300);
        setResizable(false);
        // DISPOSE_ON_CLOSE ne ferme que cette fen�tre, pas toute l'application
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Ouvre la fen�tre par rapport � la fen�tre principale (ou au centre si null)
        setLocationRelativeTo(null);

        // --- Panneau principal avec des marges ---
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10)); // 2 colonnes, espacement de 10px
        panel.setBorder(new EmptyBorder(15, 15, 15, 15)); // Marge int�rieure

        // --- Affichage des informations ---
        panel.add(new JLabel("Nom :"));
        panel.add(new JLabel(client.getNom()));

        panel.add(new JLabel("Latitude :"));
        panel.add(new JLabel(String.valueOf(client.getLatitude())));

        panel.add(new JLabel("Longitude :"));
        panel.add(new JLabel(String.valueOf(client.getLongitude())));

        panel.add(new JLabel("Pr�f�rences :"));
        // Joindre la liste des pr�f�rences en une seule cha�ne de caract�res
        String preferencesStr = String.join(", ", client.getPreferences());
        panel.add(new JLabel(preferencesStr));

        // --- Bouton pour fermer ---
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose()); // 'dispose()' ferme cette fen�tre

        // Ajout des composants � la fen�tre
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(closeButton, BorderLayout.SOUTH);
    }
}