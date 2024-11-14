import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class PianoApp extends JFrame {

    // Piano keys (file names and display labels)
    private final String[] notes = {"C", "D", "E", "F", "G", "A", "B", "C#", "D#", "F#", "G#", "A#"};
    private final String soundPath = "sounds/"; // Make sure to have sound files in this folder

    public PianoApp() {
        setTitle("Java Piano 🎹");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setLayout(new GridLayout(1, notes.length));

        // Add buttons for each note
        for (String note : notes) {
            JButton button = new JButton(note);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.addActionListener(new PlaySoundAction(note));
            add(button);
        }
        setVisible(true);
    }

    // Inner class for playing sound on button click
    private class PlaySoundAction implements ActionListener {
        private final String note;

        public PlaySoundAction(String note) {
            this.note = note;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            playSound(note);
        }
    }

    // Method to play sound
    private void playSound(String note) {
        try {
            File soundFile = new File(soundPath + note + ".wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method to run the app
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PianoApp::new);
    }
}
