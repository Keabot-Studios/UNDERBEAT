package net.richstudios.ub.util.level.visual.movement;

public abstract class Movement {
	public enum MovementType {
		TRANSLATE((byte) 0x00), ROTATE((byte) 0x01), SCALE((byte) 0x02);

		private final byte id;

		private MovementType(byte id) {
			this.id = id;
		}
	}

	public final MovementType type;

	protected Movement(MovementType type) {
		this.type = type;
	}
}
