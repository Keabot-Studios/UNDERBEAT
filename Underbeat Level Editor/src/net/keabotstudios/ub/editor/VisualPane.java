package net.keabotstudios.ub.editor;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class VisualPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
