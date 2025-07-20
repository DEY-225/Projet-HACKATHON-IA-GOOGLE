// Fichier : PlaceholderTextField.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderTextField extends JTextField implements FocusListener {
    private String placeholder;
    private boolean showingPlaceholder;

    public PlaceholderTextField(String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        super.addFocusListener(this);
        this.setForeground(Color.GRAY);
        // Marge intérieure pour que le texte ne colle pas au bord
        this.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.showingPlaceholder) {
            super.setText("");
            this.setForeground(Color.BLACK);
            this.showingPlaceholder = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (super.getText().isEmpty()) {
            super.setText(placeholder);
            this.setForeground(Color.GRAY);
            this.showingPlaceholder = true;
        }
    }
    
    @Override
    public String getText() {
        // Si le placeholder est affiché, on considère le champ comme vide
        return showingPlaceholder ? "" : super.getText();
    }
}