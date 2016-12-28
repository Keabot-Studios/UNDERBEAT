package net.keabotstudios.ub.audio;

import java.net.URL;
import java.util.HashMap;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import net.keabotstudios.ub.Game;

public class AudioManager {

	private HashMap<String, MediaPlayer> musicAudio;
	private HashMap<String, MediaPlayer> soundAudio;

	public AudioManager() {
		new JFXPanel();
		musicAudio = new HashMap<String, MediaPlayer>();
		soundAudio = new HashMap<String, MediaPlayer>();
	}

	private HashMap<String, URL> audio;

	public void loadAudio(String folder, String fileType, String... names) {
		for (String fileName : names) {
			System.out.println("Loading Audio: " + "/" + folder + "/" + fileName + "." + fileType);
			audio.put(fileName.toLowerCase(), Game.class.getResource("/" + folder + "/" + fileName + "." + fileType));
		}
	}

	public void resetLoadAudio(String folder, String fileType, String... names) {
		audio.clear();

		loadAudio(folder, fileType, names);
	}

	private void loadAudio(HashMap<String, MediaPlayer> map, boolean clearCurAudio, String folder,
			AudioData... dataList) {
		if (clearCurAudio)
			map.clear();
		for (AudioData data : dataList) {
			URL res = Game.class.getResource("/" + folder + "/" + data.getName() + ".mp3");
			Media media = new Media(res.toString());
			MediaPlayer player = new MediaPlayer(media);
			player.setCycleCount(data.doesLoop() ? MediaPlayer.INDEFINITE : 1);
			player.stop();
			map.put(data.getName().toLowerCase(), player);
		}
	}

	public void loadSound(boolean clearCurAudio, String folder, AudioData... dataList) {
		loadAudio(soundAudio, clearCurAudio, folder, dataList);
	}

	public void loadMusic(boolean clearCurAudio, String folder, AudioData... dataList) {
		loadAudio(musicAudio, clearCurAudio, folder, dataList);
	}

	public void loadSound(String folder, AudioData... dataList) {
		loadAudio(soundAudio, false, folder, dataList);
	}

	public void loadMusic(String folder, AudioData... dataList) {
		loadAudio(musicAudio, false, folder, dataList);
	}

	private void play(HashMap<String, MediaPlayer> map, String name) {
		if (map.containsKey(name.toLowerCase())) {
			stop(map, name);
			resume(map, name);
		}
	}

	private void stop(HashMap<String, MediaPlayer> map, String name) {
		if (map.containsKey(name.toLowerCase())) {
			MediaPlayer player = map.get(name.toLowerCase());
			player.stop();
		}
	}

	private void pause(HashMap<String, MediaPlayer> map, String name) {
		if (map.containsKey(name.toLowerCase())) {
			MediaPlayer player = map.get(name.toLowerCase());
			player.pause();
		}
	}

	private void resume(HashMap<String, MediaPlayer> map, String name) {
		if (map.containsKey(name.toLowerCase())) {
			MediaPlayer player = map.get(name.toLowerCase());
			player.play();
		}
	}

	public void playMusic(String name) {
		play(musicAudio, name);
	}

	public void playSound(String name) {
		play(soundAudio, name);
	}

	public void stopMusic(String name) {
		stop(musicAudio, name);
	}

	public void stopSound(String name) {
		stop(soundAudio, name);
	}

	public void pauseMusic(String name) {
		pause(musicAudio, name);
	}

	public void pauseSound(String name) {
		pause(soundAudio, name);
	}

	public void resumeMusic(String name) {
		resume(musicAudio, name);
	}

	public void resumeSound(String name) {
		resume(soundAudio, name);
	}

	private void stopAllAudioType(HashMap<String, MediaPlayer> map) {
		for (MediaPlayer player : map.values())
			player.stop();
	}

	public void stopAllMusic() {
		stopAllAudioType(musicAudio);
	}

	public void stopAllSounds() {
		stopAllAudioType(soundAudio);
	}

	public void stopAllAudio() {
		stopAllMusic();
		stopAllSounds();
	}

	private void pauseAllAudioType(HashMap<String, MediaPlayer> map) {
		for (MediaPlayer player : map.values())
			player.pause();
	}

	public void pauseAllMusic() {
		pauseAllAudioType(musicAudio);
	}

	public void pauseAllSounds() {
		pauseAllAudioType(soundAudio);
	}

	public void pauseAllAudio() {
		pauseAllMusic();
		pauseAllSounds();
	}

	private void resumeAllAudioType(HashMap<String, MediaPlayer> map) {
		for (MediaPlayer player : map.values()) {
			if (player.getCurrentTime() != player.getStartTime() && player.getCurrentTime() != player.getStopTime())
				player.play();
		}
	}

	public void resumeAllMusic() {
		resumeAllAudioType(musicAudio);
	}

	public void resumeAllSounds() {
		resumeAllAudioType(soundAudio);
	}

	public void resumeAllAudio() {
		resumeAllAudioType(musicAudio);
		resumeAllAudioType(soundAudio);
	}
}
