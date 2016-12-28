package net.keabotstudios.ub.audio;

import java.net.URL;
import java.util.HashMap;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import net.keabotstudios.ub.Game;

public class AudioManager {

	MediaPlayer player;

	boolean playing = false;

	public AudioManager() {
		new JFXPanel();
		audio = new HashMap<String, URL>();
	}

	private HashMap<String, URL> audio;

	public void loadAudio(String folder, String... names) {
		for (String fileName : names) {
			System.out.println("Loading Audio: " + "/" + folder + "/" + fileName + ".mp3");
			audio.put(fileName.toLowerCase(), Game.class.getResource("/" + folder + "/" + fileName + ".mp3"));
		}
	}

	public void resetLoadAudio(String folder, String... names) {
		audio.clear();

		loadAudio(folder, names);
	}

	public void play(String name, boolean loop) {
		stop();
		if (audio.containsKey(name.toLowerCase())) {
			Media media = new Media(audio.get(name.toLowerCase()).toString());
			player = new MediaPlayer(media);
			player.setCycleCount(loop ? MediaPlayer.INDEFINITE : 1);
			resume();
		}
	}

	public void stop() {
		player.stop();
		player = null;
		playing = false;
	}

	public void pause() {
		player.pause();
		playing = false;
	}

	public void resume() {
		if (player != null) {
			player.play();
			playing = true;
		}
	}
}
