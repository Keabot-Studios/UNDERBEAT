package net.richstudios.ub.game.gamestate;

import java.awt.Graphics2D;
import java.util.Random;

import net.richstudios.ub.game.objects.BeatBoard;
import net.richstudios.ub.game.objects.Heart;
import net.richstudios.ub.util.level.song.note.Note.NoteLine;

public class TutorialState extends LevelState {
	
	private BeatBoard bb;
	private Heart[] hearts;

	public TutorialState(GameStateManager gsm) {
		super(gsm, null);
		bb = new BeatBoard(gsm.getGame().getWidth() / 2 - BeatBoard.MSG_BOX_WIDTH / 2, gsm.getGame().getHeight() - BeatBoard.MSG_BOX_HEIGHT - (gsm.getGame().getWidth() / 2 - BeatBoard.MSG_BOX_WIDTH / 2));
		hearts = new Heart[NoteLine.values().length];
		for(int i = 0; i < hearts.length; i++) {
			hearts[i] = new Heart(bb.getX() + 20, bb.getY() + 16 + (Heart.HEART_SIZE + 4) * i, NoteLine.values()[i]);
		}
	}
	
	public void draw(Graphics2D g) {
		bb.draw(g);
		for(Heart h : hearts) {
			h.draw(g);
		}
	}
	Random r = new Random();
	public void update() {
		bb.update();
		for(Heart h : hearts) {
			if(r.nextInt(20) <= 2) { 
				h.beat();
			}
		}
		for(Heart h : hearts) {
			h.update();
		}
	}

}
