package net.richstudios.ub.util.level.song.note;


public class HoldNote extends NormalNote {
	
	protected long length;

	public HoldNote(NoteLine line, long position, long length) {
		super(line, position);
		this.type = NoteType.HOLD;
		this.length = length;
	}

}
