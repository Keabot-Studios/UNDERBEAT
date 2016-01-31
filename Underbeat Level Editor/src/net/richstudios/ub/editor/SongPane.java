package net.richstudios.ub.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import net.richstudios.ub.util.graphics.ImageUtils;
import net.richstudios.ub.util.level.song.note.Note.NoteLine;

public class SongPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final BufferedImage[] hearts;
	
	public SongPane() {
		BufferedImage heart = ImageUtils.loadImageRaw("/icons/soul.png");
		hearts = new BufferedImage[NoteLine.values().length];
		for(int i = 0; i < NoteLine.values().length; i++) {
			hearts[i] = ImageUtils.replaceColor(heart, Color.WHITE, NoteLine.values()[i].getColor());
		}
	}
	
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
			g.drawImage(hearts[i], heartSize / 2, lineY - heartSize / 2, heartSize, heartSize, null);
		}
	}

}
