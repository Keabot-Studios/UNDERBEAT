package net.richstudios.ub.util.level.visual.movement;

import java.awt.Point;

public class MovementTranslate extends Movement {
	
	public static final byte INTERPOLATION_LINEAR = 0x00;
	public static final byte INTERPOLATION_BILINEAR = 0x01;
	
	private Point[] points;
	private int dx, dy;
	/** frames 
	 * @see(net.richstudios.ub.util.ref.References.FPS) */
	private int speed;
	private byte intpMode;

	protected MovementTranslate(Point[] points, int speed, byte intpMode) {
		super(MovementType.TRANSLATE);
		if(points == null || points.length == 0) throw new NullPointerException("Points cannot be empty or null!");
		this.points = points;
		this.speed = speed;
		this.intpMode = intpMode;
	}
	
	public Point calcPosition(Point cPos, int fos) {
		return new Point(cPos);
	}

}
