package Controller.Tool;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundManager {
    Clip clip;
    URL[] soundUrl = new URL[10];

    public SoundManager() {
        soundUrl[0] = getClass().getResource("/Sound/music_background.wav");
        soundUrl[1] = getClass().getResource("/Sound/music_gameplay.wav");
        soundUrl[2] = getClass().getResource("/Sound/sound_effect_death.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
