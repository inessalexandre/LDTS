package g1008.View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class MusicPlayer {
    private Clip backgroundMusic;
    private Clip dropPieceSound;
    private AudioInputStream in;


    public MusicPlayer() {
        this.dropPieceSound = loadDropSound();
        this.backgroundMusic = loadMusic();
    }

    private Clip loadMusic() throws NullPointerException{
        try {
            in = AudioSystem.getAudioInputStream(new File("src/main/resources/music/TetrisSong.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(in);
            FloatControl gainControl = (FloatControl)backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return backgroundMusic;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Clip loadDropSound() throws NullPointerException{
        try {
            AudioInputStream in2;
            in2 = AudioSystem.getAudioInputStream(new File("src/main/resources/music/PieceDown.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(in2);
            FloatControl gainControl = (FloatControl)backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return backgroundMusic;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startMusic() {
        backgroundMusic.setMicrosecondPosition(0);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pieceDropped() {
        dropPieceSound.setMicrosecondPosition(0);
        dropPieceSound.start();
        dropPieceSound.loop(0);
    }
}
