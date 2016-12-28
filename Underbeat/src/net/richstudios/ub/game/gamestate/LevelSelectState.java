package net.richstudios.ub.game.gamestate;

import java.awt.Graphics2D;

import net.richstudios.ub.util.io.InputHandler;
import net.richstudios.ub.util.level.Level;

public class LevelSelectState extends GameState {
	
	private Level[] levels;
	private int selectedLevel = 0;

	public LevelSelectState(GameStateManager gsm, Level[] levels) {
		super(gsm);
		this.levels = levels;
	}

	public void init() {

	}

	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleInput(InputHandler input) {
		// TODO Auto-generated method stub

	}

}
