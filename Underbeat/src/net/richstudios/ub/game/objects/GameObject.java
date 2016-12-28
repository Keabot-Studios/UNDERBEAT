package net.richstudios.ub.game.objects;

import java.awt.Graphics2D;

public abstract class GameObject {
	
	protected double x, y, width, height;
	protected boolean visible = true;
	
	public GameObject(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics2D g);

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean getVisible() {
		return visible;
	}
}
