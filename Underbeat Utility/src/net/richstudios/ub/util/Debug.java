package net.richstudios.ub.util;

import net.richstudios.ub.util.level.Level;
import net.richstudios.ub.util.level.song.Song;
import net.richstudios.ub.util.level.song.note.HoldNote;
import net.richstudios.ub.util.level.song.note.NormalNote;
import net.richstudios.ub.util.level.song.note.Note;
import net.richstudios.ub.util.level.song.note.Note.NoteLine;

public class Debug {
	
	public static final Level TEST_lEVEL = new Level("Test", "Rich Studios", new Song("Test", "Rich Studios", new Note[] {
			new NormalNote(NoteLine.RED, 1000),
			new HoldNote(NoteLine.BLUE, 2000, 3000),
			new NormalNote(NoteLine.GREEN, 6000),
			new NormalNote(NoteLine.RED, 6500)
	}), null);

}
