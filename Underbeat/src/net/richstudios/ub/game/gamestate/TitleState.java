package net.richstudios.ub.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import net.richstudios.ub.game.loading.Textures;
import net.richstudios.ub.game.objects.Heart;
import net.richstudios.ub.util.InputHandler;
import net.richstudios.ub.util.References;
import net.richstudios.ub.util.StringUtils;
import net.richstudios.ub.util.level.song.note.Note.NoteLine;

public class TitleState extends GameState {

	private int timer = 0;

	private boolean showingPrompt = false;
	private static final String PROMPT_STRING = "[PRESS ENTER]";
	
	private Heart[] hearts;

	public TitleState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		hearts = new Heart[NoteLine.values().length];
		int heartLineSpace = 80;
		int lineWidth = heartLineSpace * (NoteLine.values().length - 1);
		for(int i = 0; i < hearts.length; i++) {
			hearts[i] = new Heart((References.WIDTH / 2) - (lineWidth / 2) + (heartLineSpace * i), 100, NoteLine.values()[i]);
		}
	}

	public void update() {
		if (timer >= 2 * References.FPS && !showingPrompt) {
			showingPrompt = true;
		}
		for(int i = 0; i < hearts.length; i++) {
			hearts[i].update();
			if(timer%(hearts.length * References.FPS / 4) == i * References.FPS / 4) {
				hearts[i].beat();
			}
		}
		timer++;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, References.WIDTH, References.HEIGHT);

		int titleWidth = Textures.getTexture("title").getWidth() * 6;
		int titleHeight = Textures.getTexture("title").getHeight() * 6;
		int noteWidth = (int) (Textures.getTexture("titleNote").getWidth() * 6);
		int noteHeight = (int) (Textures.getTexture("titleNote").getHeight() * 6 + Math.sin(System.currentTimeMillis() / 100D) * 15D);
		g.drawImage(Textures.getTexture("title"), References.WIDTH / 2 - titleWidth / 2, References.HEIGHT / 2 - titleHeight / 2 - 10, titleWidth, titleHeight, null);
		g.drawImage(Textures.getTexture("titleNote"), References.WIDTH / 2 - noteWidth / 2, References.HEIGHT / 2 - noteHeight / 2 - 10, noteWidth, noteHeight, null);
		for(int i = 0; i < hearts.length; i++) {
			hearts[i].draw(g);
		}
		if (showingPrompt) {
			g.setColor(Color.DARK_GRAY);
			g.setFont(new Font("Verdana", Font.PLAIN, 14));
			g.drawString(PROMPT_STRING, References.WIDTH / 2 - StringUtils.getStringWidth(g.getFont(), PROMPT_STRING) / 2, (int) (370 + (Math.cos(-System.currentTimeMillis() / 100) * 3)));
		}
	}

	public void handleInput(InputHandler input) {
		if (showingPrompt && input.isKeyPressed(InputHandler.ENTER)) {
			// TODO
		}
	}

}
