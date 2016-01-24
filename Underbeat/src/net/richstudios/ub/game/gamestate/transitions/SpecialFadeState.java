package net.richstudios.ub.game.gamestate.transitions;

import java.awt.Color;
import java.awt.Graphics2D;

import net.richstudios.ub.game.gamestate.GameState;
import net.richstudios.ub.game.gamestate.GameStateManager;
import net.richstudios.ub.util.ref.References;

public class SpecialFadeState extends TransitionState {
	
	private Color fadeColor;
	private float alpha, alphaStep;

	public SpecialFadeState(GameStateManager gsm, GameState prevState, GameState nextState, Color fadeColor, float length) {
		super(gsm, prevState, nextState);
		this.totalTime = length * References.FPS;
		this.fadeColor = fadeColor;
		this.alphaStep = 1f / totalTime;
	}
	
	public void update() {
		super.update();
		if(timer >= totalTime) {
			this.finish();
		}
		
		if(alpha < 1f) {
			alpha += alphaStep;
			if(alpha > 1f) {
				alpha = 1f;
			}
		}
	}

	public void draw(Graphics2D g) {
		prevState.draw(g);
		g.setColor(new Color(fadeColor.getRed(), fadeColor.getGreen(), fadeColor.getBlue(), (int)(255 * alpha)));
		g.fillRect(0, 0, References.WIDTH, References.HEIGHT);
	}

}
