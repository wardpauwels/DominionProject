import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;

public class SettingsWindow extends JFrame implements ActionListener {
    private final static int BREEDTE = 100, HOOGTE = 100;
    private int musicVolume = 5;
    private boolean fullScreen = false;

    //Jlabel
    JLabel headLabel = new JLabel("SETTINGS");
    JLabel soundLabel = new JLabel("Sound Volume");

    //Jpanel
    JPanel titlePanel = new JPanel();
    JPanel setPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel soundPanel = new JPanel();

    //Jbutton
    JButton okButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");

    //JCheckBox
    JCheckBox fullScreenBox = new JCheckBox("Fullscreen");

    //JSlider
    JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, musicVolume);

    public void setvolume(int volume) {
        musicVolume = volume;
    }

    public void toggleFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public int getMusicVolume() {
        return musicVolume;
    }

    public boolean getFullScreen() {
        return fullScreen;
    }

    public void inhoud() {
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        pane.add(titlePanel);
        pane.add(setPanel);
        pane.add(buttonPanel);

        headLabel.setFont(headLabel.getFont().deriveFont(18.0f));
        titlePanel.add(headLabel);

        setPanel.add(fullScreenBox);
        setPanel.add(soundPanel);
        soundPanel.setLayout(new BoxLayout(soundPanel, BoxLayout.Y_AXIS));
        soundPanel.add(soundLabel);
        soundPanel.add(volumeSlider);

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    @Override
    public void actionPerformed (ActionEvent e){
        Object bron = e.getSource();
        if (bron == okButton){
            toggleFullScreen(fullScreenBox.isSelected());
            setvolume((int)volumeSlider.getValue());
            closeWindow();
        } else if (bron == cancelButton){
            closeWindow();
            fullScreenBox.setSelected(fullScreen);
            volumeSlider.setValue(musicVolume);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (source == volumeSlider) {
            musicVolume = (int)source.getValue();
        }
    }

    public void closeWindow(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}