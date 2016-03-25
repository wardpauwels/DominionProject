import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class DominionStart extends JFrame implements ActionListener {
    final static int BREEDTE = 1280, HOOGTE = 800;
    public static JButton b1;
    public static JLabel l1;

    public static void main(String[] args) {
        DominionStart kader = new DominionStart();
        kader.setSize(BREEDTE, HOOGTE);
        kader.setDefaultCloseOperation(EXIT_ON_CLOSE);
        kader.setTitle("Dominion");
        kader.inhoud();
        kader.setVisible(true);
    }

    private void inhoud() {
        Container venster = getContentPane();
        venster.setLayout(new FlowLayout());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}