package net.keabotstudios.ub.util;

import net.keabotstudios.ub.util.level.Level;
import net.keabotstudios.ub.util.level.song.Song;
import net.keabotstudios.ub.util.level.song.note.HoldNote;
import net.keabotstudios.ub.util.level.song.note.NormalNote;
import net.keabotstudios.ub.util.level.song.note.Note;
import net.keabotstudios.ub.util.level.song.note.Note.NoteLine;

public class Debug {
	
	public static final Level TEST_lEVEL = new Level("Test", "Rich Studios", new Song("Test", "Rich Studios", new Note[] {
			new NormalNote(NoteLine.RED, 1000),
			new HoldNote(NoteLine.BLUE, 2000, 3000),
			new NormalNote(NoteLine.GREEN, 6000),
			new NormalNote(NoteLine.RED, 6500)
	}), 2);

}
