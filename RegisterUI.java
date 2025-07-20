// Fichier : RegisterUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterUI extends JFrame {

    private static final Color BG_COLOR = new Color(247, 247, 247);
    private static final Color TEXT_FIELD_BG = new Color(255, 223, 186);
    private static final Color BUTTON_BG = new Color(221, 94, 41);
    private static final Color TEXT_COLOR = new Color(221, 94, 41);

    public RegisterUI() {
        setTitle("Inscription");
        setSize(360, 640);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BG_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Inscription");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 42));
        titleLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(40, 20, 20, 20);
        mainPanel.add(titleLabel, gbc);

        PlaceholderTextField nameField = new PlaceholderTextField("Nom et prénoms");
        nameField.setBackground(TEXT_FIELD_BG);
        nameField.setPreferredSize(new Dimension(280, 45));
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 10, 20);
        mainPanel.add(nameField, gbc);
        
        PlaceholderPasswordField passField = new PlaceholderPasswordField("Mot de passe");
        passField.setBackground(TEXT_FIELD_BG);
        passField.setPreferredSize(new Dimension(280, 45));
        gbc.gridy = 2;
        mainPanel.add(passField, gbc);

        PlaceholderPasswordField confirmPassField = new PlaceholderPasswordField("Confirmation du Mot de passe");
        confirmPassField.setBackground(TEXT_FIELD_BG);
        confirmPassField.setPreferredSize(new Dimension(280, 45));
        gbc.gridy = 3;
        mainPanel.add(confirmPassField, gbc);

        PlaceholderTextField userTypeField = new PlaceholderTextField("Client ou vendeur");
        userTypeField.setBackground(TEXT_FIELD_BG);
        userTypeField.setPreferredSize(new Dimension(280, 45));
        gbc.gridy = 4;
        mainPanel.add(userTypeField, gbc);
        
        JButton registerButton = new RoundedButton("S'inscrire");
        registerButton.setBackground(BUTTON_BG);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        registerButton.setPreferredSize(new Dimension(280, 50));
        gbc.gridy = 5;
        gbc.insets = new Insets(30, 20, 20, 20);
        mainPanel.add(registerButton, gbc);
        
        gbc.gridy = 6;
        gbc.insets = new Insets(20, 40, 20, 40);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(new JSeparator(), gbc);
        
        JPanel loginPanel = new JPanel(new FlowLayout());
        loginPanel.setBackground(BG_COLOR);
        JLabel haveAccountLabel = new JLabel("Avez-vous un compte ?");
        haveAccountLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JLabel loginLink = new JLabel("Connectez-vous");
        loginLink.setFont(new Font("SansSerif", Font.ITALIC, 14));
        loginLink.setForeground(TEXT_COLOR);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                MainApp.showLoginScreen();
            }
        });
        
        loginPanel.add(haveAccountLabel);
        loginPanel.add(loginLink);
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(loginPanel, gbc);

        add(mainPanel);
    }
}