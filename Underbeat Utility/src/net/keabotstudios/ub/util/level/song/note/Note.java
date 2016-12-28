package net.keabotstudios.ub.util.level.song.note;

import java.awt.Color;

public abstract class Note {
	
	public enum NoteLine {
		RED((byte) 0, new Color(255, 0, 0)),
		AQUA((byte) 1, new Color(66, 252, 255)),
		ORANGE((byte) 2, new Color(252, 166, 0)),
		BLUE((byte) 3, new Color(0, 60, 255)),
		GREEN((byte) 4, new Color(0, 192, 0)),
		PURPLE((byte) 5, new Color(213, 53, 217)),
		YELLOW((byte) 6, new Color(255, 255, 0));
		
		private final byte id;
		private final Color color;
		
		private NoteLine(byte id, Color color) {
			this.id = id;
			this.color = color;
		}

		public byte getId() {
			return id;
		}

		public Color getColor() {
			return color;
		}
		
		public static NoteLine getLineFromId(byte id) {
			for(int i = 0; i < values().length; i++) {
				if(values()[i].getId() == id) {
					return values()[i];
				}
			}
			return null;
		}
	}
	
	public enum NoteType {
		NORMAL((byte)0), HOLD((byte)0);
		
		private byte id;
		
		private NoteType(byte id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
	}
	
	protected NoteType type;
	protected long position;
	
	protected Note(NoteType type, long position) {
		this.type = type;
		this.position = position;
	}
	
	public void setType(NoteType type) {
		this.type = type;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public NoteType getType() {
		return type;
	}
	
	public long getPosition() {
		return position;
	}

}
