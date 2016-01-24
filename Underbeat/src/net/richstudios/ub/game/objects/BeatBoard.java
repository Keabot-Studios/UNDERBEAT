package net.richstudios.ub.game.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import net.richstudios.ub.util.level.song.Song;
import net.richstudios.ub.util.ref.References;

public class BeatBoard extends GameObject {

	public static final int MSG_BOX_WIDTH = 578, MSG_BOX_HEIGHT = 152;
	public static final int BOARD_THICKNESS = 6;

	private static final int IDLE = 0, MOVING = 1;
	private int status = IDLE;

	private double tx, ty, twidth, theight, dx, dy, dwidth, dheight;
	private int length, timer;
	
	private Song song;
	private long time;

	public BeatBoard(double x, double y) {
		super(x, y, MSG_BOX_WIDTH, MSG_BOX_HEIGHT);
	}

	public void update() {
		if (status == MOVING) {
			timer++;
			this.x += this.dx;
			this.y += this.dy;
			this.width += this.dwidth;
			this.height += this.dheight;
			if(timer == length) {
				this.x = this.tx;
				this.y = this.ty;
				this.width = this.twidth;
				this.height = this.theight;
				
				this.tx = 0;
				this.ty = 0;
				this.twidth = 0;
				this.theight = 0;
				this.dx = 0;
				this.dy = 0;
				this.dwidth = 0;
				this.dheight = 0;
				
				this.timer = 0;
				this.length = 0;
				
				status = IDLE;
			}
		}
	}

	public void translate(double tx, double ty, double twidth, double theight, float length) {
		if (status == MOVING)
			return;

		status = MOVING;
		this.tx = tx;
		this.ty = ty;
		this.twidth = twidth;
		this.theight = theight;
		this.length = (int) (length * References.FPS);

		if (this.tx != this.x)
			this.dx = (this.tx - this.x) / this.length;
		else
			this.dx = 0;
		
		if (this.ty != this.y)
			this.dy = (this.ty - this.y) / this.length;
		else
			this.dy = 0;

		if (this.twidth != this.width)
			this.dwidth = (this.twidth - this.width) / this.length;
		else
			this.dwidth = 0;
		
		if (this.theight != this.height)
			this.dheight = (this.theight - this.height) / this.length;
		else
			this.dheight = 0;
		
		if(dx == 0 && dy == 0 && dheight == 0 && dwidth == 0) status = IDLE;
	}

	public void move(double tx, double ty, float length) {
		translate(tx, ty, width, height, length);
	}

	public void resize(double twidth, double theight, float length) {
		translate(x, y, twidth, theight, length);
	}

	public void draw(Graphics2D g) {
		if(!visible) return;
		g.setColor(Color.BLACK);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		//DRAW NOTES
		g.setColor(Color.WHITE);
		for (int i = 0; i < BOARD_THICKNESS; i++) {
			g.drawRect((int) (x + i), (int) (y + i), (int) (width - (i * 2)), (int) (height - (i * 2)));
		}
	}
	
	public boolean isMoving() {
		return status == MOVING;
	}
	
	public void setSong(Song song) {
		this.song = song;
	}
	
	public void setTime(long time) {
		this.time = time;
	}

}
