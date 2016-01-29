package net.richstudios.ub.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.richstudios.ub.game.gamestate.DisclaimerState;
import net.richstudios.ub.game.gamestate.GameStateManager;
import net.richstudios.ub.game.loading.Sound;
import net.richstudios.ub.game.loading.Textures;
import net.richstudios.ub.util.file.LevelFileParser;
import net.richstudios.ub.util.io.InputHandler;
import net.richstudios.ub.util.level.Level;
import net.richstudios.ub.util.ref.References;

public class Game extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private boolean running;
	private long targetTime = 1000 / References.FPS;

	private BufferedImage image;
	private Graphics2D g;

	private GameStateManager gsm;

	private InputHandler input;

	private JFrame frame;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		super();
		frame = new JFrame(References.NAME + " | " + References.VERSION);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>();
		icons.add(Textures.icon16x);
		icons.add(Textures.icon32x);
		icons.add(Textures.icon64x);
		frame.setIconImages(icons);
		frame.setSize(References.WIDTH, References.HEIGHT);
		frame.setContentPane(this);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	private void init() {
		image = new BufferedImage(References.WIDTH, References.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		input = new InputHandler(this);
		Sound.init();
		Textures.init();
		gsm = new GameStateManager(input);
		gsm.push(new DisclaimerState(gsm));
		running = true;
		
		try {
			Level test = LevelFileParser.parseLocal("testLevel.ubl");
			System.out.println(test.getName());
			System.out.println(test.getAuthor());
			System.out.println(test.getSong().getName());
			System.out.println(test.getSong().getArtist());
			for(int i = 0; i < test.getSong().getNotes().length; i++) {
				System.out.println(test.getSong().getNotes()[i].getType() + ", " + test.getSong().getNotes()[i].getPosition() + ", " + test.getSong().getNotes()[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		init();

		long start;
		long elapsed;
		long wait;

		while (running) {

			start = System.nanoTime();

			update();
			draw();

			elapsed = System.nanoTime() - start;

			wait = targetTime - elapsed / 1000000;
			try {
				if (wait > 0)
					Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void update() {
		if (input.isKeyTyped(InputHandler.F12)) {
			Settings.debug = !Settings.debug;
		}
		gsm.update();
		input.update();
	}

	private void draw() {
		gsm.draw(g);
		if (Settings.debug) {
			int tx = input.mouseX;
			int ty = input.mouseY;
			g.setColor(Color.PINK);
			g.fillRect(tx, ty, 1, 1);
		}
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, References.WIDTH, References.HEIGHT, null);
		g2.setColor(Color.blue);
		g2.dispose();
	}

}
