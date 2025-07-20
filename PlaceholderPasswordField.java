// Fichier : PlaceholderPasswordField.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderPasswordField extends JPasswordField implements FocusListener {
    private String placeholder;
    private boolean showingPlaceholder;

    public PlaceholderPasswordField(String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        super.addFocusListener(this);
        this.setEchoChar((char) 0); // Affiche le texte du placeholder en clair
        this.setForeground(Color.GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.showingPlaceholder) {
            super.setText("");
            this.setEchoChar('\u2022'); // Caractère de masquage standard (un point)
            this.setForeground(Color.BLACK);
            this.showingPlaceholder = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (new String(super.getPassword()).isEmpty()) {
            super.setText(placeholder);
            this.setEchoChar((char) 0); // Réaffiche le placeholder en clair
            this.setForeground(Color.GRAY);
            this.showingPlaceholder = true;
        }
    }
    
    @Override
    public char[] getPassword() {
        return showingPlaceholder ? new char[0] : super.getPassword();
    }
}