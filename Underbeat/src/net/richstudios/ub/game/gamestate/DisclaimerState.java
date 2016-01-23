package net.richstudios.ub.game.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;

import net.richstudios.ub.game.gamestate.transitions.SpecialFadeState;
import net.richstudios.ub.game.loading.Textures;
import net.richstudios.ub.util.InputHandler;
import net.richstudios.ub.util.References;

public class DisclaimerState extends GameState {

	public DisclaimerState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		
	}

	public void update() {
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, References.WIDTH, References.HEIGHT);
		g.drawImage(Textures.getTexture("disclaimer"), References.WIDTH / 2 - Textures.getTexture("disclaimer").getWidth() / 2, References.HEIGHT / 2 - Textures.getTexture("disclaimer").getHeight() / 2 - 70, null);
		g.drawImage(Textures.getTexture("sansShrug"), (References.WIDTH / 2) - (Textures.getTexture("sansShrug").getWidth() / 2), 340, null);
	}

	public void handleInput(InputHandler input) {
		if(input.confirmKeyTyped()) {
			gsm.set(new SpecialFadeState(gsm, this, new TitleState(gsm), Color.BLACK, 1f));
		}
	}

}
