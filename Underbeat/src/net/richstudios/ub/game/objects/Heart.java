package net.richstudios.ub.game.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import net.richstudios.ub.game.loading.Textures;
import net.richstudios.ub.util.graphics.ImageUtils;
import net.richstudios.ub.util.level.song.note.Note.NoteLine;
import net.richstudios.ub.util.ref.References;

public class Heart extends GameObject {
	
	public static final int HEART_SIZE = 16;
	
	private static final int IDLE = 0, BEAT = 1, MOVING = 2;
	private int status = IDLE;
	
	private double size = 1, tx, ty, ts, dx, dy, ds;
	private int length, timer;
	
	private BufferedImage heart;

	public Heart(double x, double y, NoteLine line) {
		super(x, y, HEART_SIZE, HEART_SIZE);
		this.heart = ImageUtils.replaceColor(Textures.getTexture("soul"), Color.WHITE, line.getColor());
	}

	public void update() {
		if (status == MOVING) {
			timer++;
			this.x += this.dx;
			this.y += this.dy;
			if(timer == length) {
				this.x = this.tx;
				this.y = this.ty;
				
				this.tx = 0;
				this.ty = 0;
				this.dx = 0;
				this.dy = 0;
				
				this.timer = 0;
				this.length = 0;
				
				status = IDLE;
			}
		} else if(status == BEAT) {
			timer++;
			this.size += this.ds;
			if(timer == length) {
				this.size = 1;
				this.ds = 0;
				this.ts = 0;
			
				this.timer = 0;
				this.length = 0;
				
				status = IDLE;
			}
		}
	}
	
	public void beat() {
		if(status != IDLE) return;
		this.timer = 0;
		this.length = References.FPS / 4;
		this.size = 1.5;
		this.ts = 1;
		this.ds = (ts - size) / length;
		status = BEAT;
	}

	public void draw(Graphics2D g) {
		if(!visible) return;
		g.drawImage(heart, (int) (x - (width * size) / 2), (int) (y - (height * size) / 2), (int) (width * size), (int) (height * size), null);
	}
	
	public void translate(double tx, double ty, float length) {
		if (status != IDLE)
			return;

		status = MOVING;
		this.tx = tx;
		this.ty = ty;
		this.length = (int) (length * References.FPS);

		if (this.tx != this.x)
			this.dx = (this.tx - this.x) / this.length;
		else
			this.dx = 0;
		
		if (this.ty != this.y)
			this.dy = (this.ty - this.y) / this.length;
		else
			this.dy = 0;
		
		if(dx == 0 && dy == 0) status = IDLE;
	}

}
