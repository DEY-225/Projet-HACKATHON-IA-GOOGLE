// Fichier : MainUI.java

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class MainUI extends JFrame {

    // --- Composants UI ---
    private JTextField searchField;
    private JButton searchButton;
    private JButton suggestionButton;
    private JButton profilButton;
    private JTextArea resultArea;
    private JScrollPane scrollPane;

    // --- Composants pour l'autocomplétion ---
    private JPopupMenu suggestionPopup;
    private JList<String> suggestionList;
    private DefaultListModel<String> suggestionListModel;
    private List<String> allProducts;

    // --- Données métier (variable de classe) ---
    // En la déclarant ici, elle est accessible partout dans cette classe.
    private Client clientActif;

    public MainUI() {
        // --- Initialisation des données ---
        // On initialise la variable de classe.
        this.clientActif = Database.getClients().get(0); 
        this.allProducts = AppTest.getAllUniqueProducts();

        // --- Configuration de la fenêtre ---
        setTitle("Trouve Ma Vendeuse");
        setSize(360, 640);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Panneau de contrôle (en haut) ---
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        searchField = new JTextField(15);
        searchPanel.add(searchField);
        searchButton = new JButton("Rechercher");
        searchPanel.add(searchButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        controlPanel.add(searchPanel);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // --- Zone de résultats (au centre) ---
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultArea.setMargin(new Insets(10, 10, 10, 10));
        scrollPane = new JScrollPane(resultArea);

        // --- Panneau de navigation (en bas) ---
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        suggestionButton = new JButton("Suggestions");
        profilButton = new JButton("Profil");
        navigationPanel.add(suggestionButton);
        navigationPanel.add(profilButton);

        // --- Logique d'autocomplétion ---
        setupAutocomplete();

        // --- AJOUT DES COMPOSANTS À LA FENÊTRE ---
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(navigationPanel, BorderLayout.SOUTH);

        // --- ACTIONS DES BOUTONS ---
        searchButton.addActionListener(e -> lancerRechercheActive());
        suggestionButton.addActionListener(e -> lancerSuggestionsProactives());
        profilButton.addActionListener(e -> ouvrirProfil());
    }

    private void setupAutocomplete() {
        suggestionPopup = new JPopupMenu();
        suggestionListModel = new DefaultListModel<>();
        suggestionList = new JList<>(suggestionListModel);
        suggestionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    searchField.setText(suggestionList.getSelectedValue());
                    suggestionPopup.setVisible(false);
                }
            }
        });
        suggestionPopup.add(new JScrollPane(suggestionList));
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { updateSuggestions(); }
            @Override public void removeUpdate(DocumentEvent e) { updateSuggestions(); }
            @Override public void changedUpdate(DocumentEvent e) { /* Rien à faire */ }
        });
    }

    private void updateSuggestions() {
        String searchText = searchField.getText().toLowerCase();
        suggestionListModel.clear();
        if (searchText.isEmpty()) {
            suggestionPopup.setVisible(false);
            return;
        }
        for (String product : allProducts) {
            if (product.toLowerCase().startsWith(searchText)) {
                suggestionListModel.addElement(product);
            }
        }
        if (suggestionListModel.getSize() > 0) {
            suggestionPopup.show(searchField, 0, searchField.getHeight());
            suggestionList.setFixedCellWidth(searchField.getWidth());
        } else {
            suggestionPopup.setVisible(false);
        }
    }

    private void lancerRechercheActive() {
        String motCleRecherche = searchField.getText();
        if (motCleRecherche == null || motCleRecherche.trim().isEmpty()) {
            resultArea.setText("Veuillez entrer un produit à rechercher.");
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        AppTest.executerRechercheActive(this.clientActif, motCleRecherche);
        System.setOut(System.out);
        resultArea.setText(baos.toString());
        resultArea.setCaretPosition(0);
    }

    private void lancerSuggestionsProactives() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        AppTest.executerSuggestionsProactives(this.clientActif);
        System.setOut(System.out);
        resultArea.setText(baos.toString());
        resultArea.setCaretPosition(0);
    }

    private void ouvrirProfil() {
        // Maintenant, cette ligne fonctionne car clientActif est une variable de classe.
        ProfileUI profileWindow = new ProfileUI(this.clientActif);
        profileWindow.setVisible(true);
    }
      public MainUI(Client client) {
        // L'application est maintenant initialisée avec le client qui s'est connecté.
        this.clientActif = client; 
        
        // Le reste du code du constructeur est exactement le même !
        this.allProducts = AppTest.getAllUniqueProducts();
        setTitle("Trouve Ma Vendeuse");
        // ... etc.
    }
}