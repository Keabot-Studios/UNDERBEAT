package net.richstudios.ub.util.level;

import net.richstudios.ub.util.level.song.Song;

public class Level {
	
	private Song song;
	private Visual visual;
	
	public Level(Song song, Visual visual) {
		this.song = song;
		this.visual = visual;
	}

}
