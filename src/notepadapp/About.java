package notepadapp;

import java.awt.*;
import javax.swing.*;

public class About extends JFrame {

    About() {
        setTitle("About");
        setBounds(100, 100, 500, 400);

        ImageIcon icon = new ImageIcon(getClass().getResource("Notepad.png"));
        setIconImage(icon.getImage());

        setLayout(null);

        JLabel iconlabel = new JLabel(new ImageIcon(getClass().getResource("Notepad2.png")));
        iconlabel.setBounds(100, 50, 100, 100);
        add(iconlabel);

        JLabel textlabel = new JLabel("<html> Welcome to Notepad<br>Notepad is a simple text editor for Microsoft Windows and a basic text_editing program which enables computer user to create documents<br>All rights reserved#@2021</html>");
        textlabel.setBounds(100, 50, 350, 300);
        textlabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 12));
        add(textlabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String args[]) {
        new About();
    }
}
