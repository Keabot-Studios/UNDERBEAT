package net.richstudios.ub.util.level.song.note;

public class NormalNote extends Note {
	
	protected NoteLine line;

	public NormalNote(NoteLine line, long position) {
		super(NoteType.NORMAL, position);
		this.line = line;
	}

	public NoteLine getLine() {
		return line;
	}

}
