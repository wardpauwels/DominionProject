import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class DominionStart extends JFrame implements ActionListener {
    //Afmetingen
    private final static int BREEDTE = 1280, HOOGTE = 780;

    //Jlabels

    //JTextField

    //Jpanels
    JPanel centerPanel = new JPanel();
    JPanel button1Panel = new JPanel();

    //JButtons
    JButton buttonNewGame = new JButton();
    JButton buttonLoadGame = new JButton();
    JButton buttonSettings = new JButton();
    JButton buttonExit = new JButton();

    //Dimensions
    Dimension startButtonDim = new Dimension(100, 30);

    //Objects
    SettingsWindow settings = new SettingsWindow();

    public static void main(String[] args) {
        DominionStart game = new DominionStart();

        try {
            game.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("/Users/wardpauwels/IdeaProjects/DominionProject/Dominion/src/swingStuff/background.jpg")))));
        }catch (IOException e){
            System.out.println("Image not found");
        }
        game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.setMinimumSize(new Dimension(BREEDTE, HOOGTE));
        game.setName("Dominion");
        game.setTitle("Dominion");
        game.setResizable(true);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.pack();
        game.inhoud();
        game.setVisible(true);
    }

    private void inhoud() {
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        JLabel label;

        //Top component
        try {
            label = new JLabel(new ImageIcon(ImageIO.read(new File("/Users/wardpauwels/IdeaProjects/DominionProject/Dominion/src/swingStuff/logoMed.png"))));
            label.setSize(new Dimension(10,10));
            pane.add(label, BorderLayout.PAGE_START);
        } catch (IOException e) {
            System.out.println("Image not found");
        }

        //Center
        try {
            buttonNewGame.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("swingStuff/newGame.png"))));
            buttonLoadGame.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("swingStuff/loadGame.png"))));
            buttonSettings.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("swingStuff/settings.png"))));
            buttonExit.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("swingStuff/exit.png"))));


        } catch (IOException ex) {
            System.out.println("An image not found");
        }

        buttonNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonLoadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonExit.setAlignmentX(Component.CENTER_ALIGNMENT);

        pane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setSize(new Dimension(500, 150));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(213, 134, 145, 0));
        centerPanel.add(buttonNewGame);
        centerPanel.add(buttonLoadGame);
        centerPanel.add(buttonSettings);
        centerPanel.add(buttonExit);


        //linksCenter
        //pane.add(button, BorderLayout.LINE_START);

        //RechtsCenter
        //pane.add(button, BorderLayout.LINE_END);

        //bottom component
        label = new JLabel("Copyright© Team Awesome - 2016", SwingConstants.HORIZONTAL);
        label.setForeground(Color.WHITE);
        pane.add(label, BorderLayout.PAGE_END);

        buttonExit.addActionListener(this);
        buttonNewGame.addActionListener(this);
        buttonLoadGame.addActionListener(this);
        buttonSettings.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object bron = e.getSource();
        if (bron == buttonExit) {
            System.exit(0);
        }
        if (bron == buttonSettings) {
            openSettingsWindow();
        }
        if (bron == buttonNewGame) {

        }
        if (bron == buttonLoadGame) {

        }

    }

    public void openSettingsWindow(){
        settings.setSize(new Dimension(250,250));
        settings.setLocationRelativeTo(null);
        settings.setTitle("Settings");
        settings.setResizable(false);
        settings.inhoud();
        settings.setVisible(true);
    }
}