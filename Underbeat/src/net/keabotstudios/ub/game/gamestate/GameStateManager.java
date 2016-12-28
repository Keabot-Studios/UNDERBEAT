package net.keabotstudios.ub.game.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;

import net.keabotstudios.superin.Input;
import net.keabotstudios.ub.Game;
import net.keabotstudios.ub.util.Util.StringUtil;

public class GameStateManager {

	private final GameState NULL_STATE = new GameState(this) {

		public void init() {}

		public void update(Input input) {}

		public void render(Graphics2D g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, gsm.getGame().getWidth(), gsm.getGame().getHeight());
			g.setColor(Color.WHITE);
			g.fillRect(gsm.getGame().screenXOff, gsm.getGame().screenYOff, gsm.getGame().screenWidth, gsm.getGame().screenHeight);
			String text = "!NULL GAMESTATE!";
			int textW = StringUtil.getStringWidth(g.getFont(), text);
			int textH = StringUtil.getStringHeight(g.getFont(), text);
			g.setColor(Color.GRAY);
			g.fillRect(gsm.getGame().getWidth() / 2 - (textW + 10) / 2, gsm.getGame().getHeight() / 2 - (textH + 10) / 2, textW + 10, textH + 10);
			g.setColor(Color.RED);
			g.drawString(text, gsm.getGame().getWidth() / 2 - textW / 2, gsm.getGame().getHeight() / 2 + textH / 3);
		}

	};

	private Game game;

	private GameState currentState;

	public GameStateManager(Game game) {
		this.game = game;
	}

	public void update(Input input) {
		if (currentState == null)
			NULL_STATE.update(input);
		else
			currentState.update(input);
	}

	public void render(Graphics2D g) {
		if (currentState == null)
			NULL_STATE.render(g);
		else
			currentState.render(g);
	}


	public void set(GameState s) {
		currentState = s;
	}

	public Game getGame() {
		return game;
	}
}
