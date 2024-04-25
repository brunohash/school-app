package View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;


public class BackgroundPanel extends JPanel {
    private ImageIcon backgroundImage;

    public BackgroundPanel() {
        try {
            URL imageUrl = new URL("https://img.freepik.com/vetores-gratis/plano-de-volta-ao-fundo-da-escola-com-material-escolar_23-2149452368.jpg?w=1480&t=st=1694475370~exp=1694475970~hmac=abb629cfba0c31086e33483a2ddd1997a4a1468fa7c08605513ec80f4e7cfe88");
            backgroundImage = new ImageIcon(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}