package net.richstudios.ub.util.level.song;

import net.richstudios.ub.util.level.song.note.Note;

public class Song {
	
	private String name;
	private String author;
	private Note[] notes;
	
	public Song(String name, String author, Note[] notes) {
		super();
		this.name = name;
		this.author = author;
		this.notes = notes;
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

	public Note[] getNotes() {
		return notes;
	}

	public void setNotes(Note[] notes) {
		this.notes = notes;
	}
	
	

}
