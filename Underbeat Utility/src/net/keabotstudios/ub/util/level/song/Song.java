package net.keabotstudios.ub.util.level.song;

import net.keabotstudios.ub.util.level.song.note.Note;

public class Song {
	
	private String name;
	private String artist;
	private Note[] notes;
	
	public Song(String name, String artist, Note[] notes) {
		super();
		this.name = name;
		this.artist = artist;
		this.notes = notes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Note[] getNotes() {
		return notes;
	}

	public void setNotes(Note[] notes) {
		this.notes = notes;
	}
	
	

}
