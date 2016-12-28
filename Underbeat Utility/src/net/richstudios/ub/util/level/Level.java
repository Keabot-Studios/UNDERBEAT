package net.richstudios.ub.util.level;

import net.richstudios.ub.util.level.song.Song;
import net.richstudios.ub.util.level.visual.Visual;

public class Level {
	
	private String name, author;
	private Song song;
	private Visual visual;
	private int difficulty;
	
	public Level(String name, String author, Song song, Visual visual, int difficulty) {
		this.name = name;
		this.author = author;
		this.song = song;
		this.visual = visual;
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

	public Visual getVisual() {
		return visual;
	}

	public void setVisual(Visual visual) {
		this.visual = visual;
	}
	
	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

}
