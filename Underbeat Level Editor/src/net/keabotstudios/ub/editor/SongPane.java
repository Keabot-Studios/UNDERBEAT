package net.keabotstudios.ub.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class SongPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public SongPane() {
		
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
