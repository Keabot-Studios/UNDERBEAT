package net.richstudios.ub.editor;

import java.awt.Dimension;

import javax.swing.JPanel;

public class SpriteSheetPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 250;
	private static final int HEIGHT = 250;
	
	public SpriteSheetPane() {
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

}
