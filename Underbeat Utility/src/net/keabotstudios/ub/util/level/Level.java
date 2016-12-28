package net.keabotstudios.ub.util.level;

import net.keabotstudios.ub.util.level.song.Song;

public class Level {
	
	private String name, author;
	private Song song;
	private int difficulty;
	
	public Level(String name, String author, Song song, int difficulty) {
		this.name = name;
		this.author = author;
		this.song = song;
		this.difficulty = difficulty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}
	
	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

}
