package cmsc123.mp03.ui;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	
	private static Clip clip;
	
	public void play(String title) {
		try {
			File file = new File("assets/music/"+title);
			AudioInputStream input = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(input);
			
			if (!title.equals("Buzzer.wav")) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.loop(0);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		if (clip.isRunning()) {
			clip.stop();
		}
	}
}
