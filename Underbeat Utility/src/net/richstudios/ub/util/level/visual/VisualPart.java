package net.richstudios.ub.util.level.visual;

import net.richstudios.ub.util.level.visual.movement.Movement;

public abstract class VisualPart {
	
	/**
	 * Name of this part
	 */
	public final String name;
	/**
	 * Movement that the part animates by
	 * @see Movement
	 */
	public Movement movement;
	/**
	 * Coordinates of texture on a spritesheet
	 */
	public int tx, ty;
	/**
	 * Size of texture on a spritesheet
	 */
	public int tw, th;
	/**
	 * Coordinates of part on the screen
	 */
	public int x, y;
	/**
	 * Size of part on the screen
	 */
	public int w, h;
	/**
	 * Rotation of the part in radians
	 */
	public double rotation;
	
	public VisualPart(String name) {
		this.name = name;
	}
	

}
