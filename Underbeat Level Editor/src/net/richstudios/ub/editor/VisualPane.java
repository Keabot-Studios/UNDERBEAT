package net.richstudios.ub.editor;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import net.richstudios.ub.util.level.song.note.Note.NoteLine;

public class VisualPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int linesHeight = (int) (getHeight() / 1.4);
		int lineSpacing = linesHeight / (NoteLine.values().length - 1);
		int heartSize = getHeight() / 20;
		
		for(int i = 0; i < NoteLine.values().length; i++) {
			int lineY = getHeight() / 2 - linesHeight / 2 + lineSpacing * i;
			g.setColor(NoteLine.values()[i].getColor());
			g.drawLine(0, lineY, getWidth(), lineY);
		}
	}

}
