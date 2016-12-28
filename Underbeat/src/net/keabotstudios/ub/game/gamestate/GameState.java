
package net.keabotstudios.ub.game.gamestate;

import java.awt.Graphics2D;

import net.keabotstudios.superin.Input;

public abstract class GameState {

	protected GameStateManager gsm;

	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public abstract void init();

	public abstract void update(Input input);

	public abstract void render(Graphics2D g);

}
