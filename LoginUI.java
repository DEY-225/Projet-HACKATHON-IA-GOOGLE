// Fichier : LoginUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginUI extends JFrame {

    // Définition des couleurs pour un accès facile
    private static final Color BG_COLOR = new Color(247, 247, 247);
    private static final Color TEXT_FIELD_BG = new Color(255, 223, 186);
    private static final Color BUTTON_BG = new Color(221, 94, 41);
    private static final Color TEXT_COLOR = new Color(221, 94, 41);

    public LoginUI() {
        // --- Configuration de la fenêtre ---
        setTitle("Connexion");
        setSize(360, 640);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BG_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();

        // --- Titre "Connexion" ---
        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 42));
        titleLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(40, 20, 40, 20); // Marge
        mainPanel.add(titleLabel, gbc);

        // --- Champ "Nom et prénoms" ---
        PlaceholderTextField nameField = new PlaceholderTextField("Nom et prénoms");
        nameField.setBackground(TEXT_FIELD_BG);
        // --- MODIFICATION 1 : Définir une taille préférée ---
        nameField.setPreferredSize(new Dimension(280, 45));
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 10, 20);
        // --- MODIFICATION 2 : Dire au layout de remplir l'espace horizontal ---
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(nameField, gbc);
        
        // --- Champ "Mot de passe" ---
        PlaceholderPasswordField passField = new PlaceholderPasswordField("Mot de passe");
        passField.setBackground(TEXT_FIELD_BG);
        // --- MODIFICATION 1 ---
        passField.setPreferredSize(new Dimension(280, 45));
        gbc.gridy = 2;
        // gbc.fill est déjà défini à HORIZONTAL, pas besoin de le répéter
        mainPanel.add(passField, gbc);
        
        // --- Bouton "Connexion" ---
        JButton loginButton = new RoundedButton("Connexion");
        loginButton.setBackground(BUTTON_BG);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(280, 50));
        gbc.gridy = 3;
        gbc.insets = new Insets(30, 20, 20, 20);
        // Pour le bouton, on ne veut pas qu'il s'étire, donc on remet le fill à NONE
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(loginButton, gbc);
        
        loginButton.addActionListener(e -> {
            dispose(); 
            MainApp.showMainUI();
        });
        
        // --- Séparateur ---
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 40, 20, 40);
        gbc.fill = GridBagConstraints.HORIZONTAL; // On veut que le séparateur s'étire
        mainPanel.add(new JSeparator(), gbc);
        
        // --- Lien d'inscription ---
        JPanel registerPanel = new JPanel(new FlowLayout());
        registerPanel.setBackground(BG_COLOR);
        JLabel noAccountLabel = new JLabel("Vous n'avez pas de compte ?");
        JLabel registerLink = new JLabel("Inscrivez-vous");
        registerLink.setFont(new Font("SansSerif", Font.ITALIC, 14));
        registerLink.setForeground(TEXT_COLOR);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                MainApp.showRegisterScreen();
            }
        });
        
        registerPanel.add(noAccountLabel);
        registerPanel.add(registerLink);
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE; // Pas d'étirement pour ce panneau
        mainPanel.add(registerPanel, gbc);

        add(mainPanel);
    }
}