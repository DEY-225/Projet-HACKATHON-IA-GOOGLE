// Fichier : RoundedButton.java
import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // On utilise Graphics2D pour de meilleures options de dessin
        Graphics2D g2 = (Graphics2D) g.create();
        // L'antialiasing lisse les bords de la forme
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Dessine le fond arrondi
        g2.setColor(getBackground());
        // Le 50 est le rayon des coins. Plus c'est grand, plus c'est rond.
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);

        // Dessine le texte du bouton au centre
        g2.setColor(getForeground());
        FontMetrics metrics = g2.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(getText())) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2.drawString(getText(), x, y);

        g2.dispose();
    }
}