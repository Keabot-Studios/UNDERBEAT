package net.richstudios.ub.util.level;

import net.richstudios.ub.util.level.song.Song;

public class Level {
	
	private String name, author;
	private Song song;
	private Visual visual;
	
	public Level(String name, String author, Song song, Visual visual) {
		this.name = name;
		this.author = author;
		this.song = song;
		this.visual = visual;
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

}
