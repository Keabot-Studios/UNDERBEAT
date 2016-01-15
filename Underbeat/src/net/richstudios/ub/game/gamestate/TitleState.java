package net.richstudios.ub.game.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;

import net.richstudios.ub.game.loading.Textures;
import net.richstudios.ub.util.InputHandler;
import net.richstudios.ub.util.References;

public class TitleState extends GameState {

	public TitleState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {

	}

	public void update() {

	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, References.WIDTH, References.HEIGHT);
		
		int titleWidth = (int) (Textures.getTexture("title").getWidth() * 6 + (Math.sin(System.currentTimeMillis() / 100) * 3));
		int titleHeight = (int) (Textures.getTexture("title").getHeight() * 6.1 + (Math.sin(System.currentTimeMillis() / 100) * 7));
		g.drawImage(Textures.getTexture("title"), References.WIDTH / 2 - titleWidth / 2, References.HEIGHT / 2 - titleHeight / 2 - 10, titleWidth, titleHeight, null);
	}

	public void handleInput(InputHandler input) {

	}

}
