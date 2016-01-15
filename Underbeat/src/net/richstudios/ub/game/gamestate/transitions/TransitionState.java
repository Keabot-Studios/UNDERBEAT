package net.richstudios.ub.game.gamestate.transitions;

import java.awt.Graphics2D;

import net.richstudios.ub.game.gamestate.GameState;
import net.richstudios.ub.game.gamestate.GameStateManager;
import net.richstudios.ub.util.InputHandler;

public abstract class TransitionState extends GameState {

	protected float timer;
	protected float totalTime;

	protected GameState prevState;
	protected GameState nextState;

	public TransitionState(GameStateManager gsm, GameState prevState, GameState nextState) {
		super(gsm);
		this.prevState = prevState;
		this.nextState = nextState;
	}

	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}

	protected void finish() {
		gsm.set(nextState);
	}

	public void update() {
		timer++;
	}

	public abstract void draw(Graphics2D g);

	public void init() {
	}

	public void handleInput(InputHandler keys) {
	}

}
