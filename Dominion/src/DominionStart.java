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

        kader.setLayout(new BorderLayout());
        kader.setContentPane(new JLabel(new ImageIcon("swingStuff/image.jpg")));
        kader.setLayout(new FlowLayout());
        l1=new JLabel("Here is a button");
        b1=new JButton("I am a button");
        kader.add(l1);
        kader.add(b1);
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