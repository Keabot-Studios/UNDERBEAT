package net.keabotstudios.ub;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import net.keabotstudios.superin.Controllable;
import net.keabotstudios.superin.Input;
import net.keabotstudios.superlog.Logger;
import net.keabotstudios.superlog.Logger.LogLevel;
import net.keabotstudios.ub.audio.AudioManager;
import net.keabotstudios.ub.game.GameInfo;
import net.keabotstudios.ub.game.gamestate.GameStateManager;
import net.keabotstudios.ub.game.save.GameSettings;
import net.keabotstudios.ub.game.save.PlayerInfo;
import net.keabotstudios.ub.util.Util;

public class Game extends Canvas implements Controllable, Runnable {
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private boolean running = false;
	private int fps, ups;

	private Logger logger;
	private Input input;

	private JFrame frame;
	public float screenScale = 1f;
	public int screenWidth = GameInfo.GAME_WIDTH;
	public int screenHeight = GameInfo.GAME_HEIGHT;
	public int screenXOff = 0;
	public int screenYOff = 0;

	private GameSettings settings;
	private PlayerInfo playerInfo;

	private GameStateManager gsm;
	
	private AudioManager audioManager;

	public static void main(String[] args) {
		Logger l = new Logger();
		GameSettings settings = new GameSettings();
		settings.updateFromFile();
		if (args.length > 0) {
			List<String> argsList = Arrays.asList(args);
			if (argsList.contains("debug")) {
				settings.debugMode = true;
				l.setLogLevel(LogLevel.INFO);
				l.debugLn("DEBUG MODE ENABLED");
			}
		}
		new Game(l, settings, GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
	}

	public Game(Logger logger, GameSettings settings, GraphicsDevice currentDisplay) {
		this.logger = logger;
		this.settings = settings;
		this.playerInfo = new PlayerInfo();
		this.audioManager = new AudioManager();
		playerInfo.updateFromFile();
		
		audioManager = new AudioManager();
		audioManager.loadAudio("audio/voice", "wav", "sans");
		//audioManager.play("test", false);
		for(int i = 0; i <= 50; i++) {
			audioManager.play("sans", false);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			audioManager.stop();
		}
		
		GameInfo.init(logger);

		Dimension size = new Dimension(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

		createJFrame(currentDisplay);

		input = new Input(this, settings.useXInput);
		input.setInputs(settings.controls);

		gsm = new GameStateManager(this);

		if (settings.fullscreen) {
			calculateScreenBounds(currentDisplay);
		}

		frame.setVisible(true);
		requestFocus();
		start();
	}

	private void calculateScreenBounds(GraphicsDevice display) {
		GraphicsDevice gd = display;
		int screenDeviceWidth = gd.getDisplayMode().getWidth();
		int screenDeviceHeight = gd.getDisplayMode().getHeight();
		if (settings.fullscreen) {
			screenScale = Util.getScaleOfRectangeInArea(screenDeviceWidth, screenDeviceHeight, GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
			screenWidth = (int) (GameInfo.GAME_WIDTH * screenScale);
			screenHeight = (int) (GameInfo.GAME_HEIGHT * screenScale);
			screenXOff = (int) ((screenDeviceWidth - screenWidth) / 2.0f);
			screenYOff = (int) ((screenDeviceHeight - screenHeight) / 2.0f);
		} else {
			screenScale = 1f;
			screenWidth = GameInfo.GAME_WIDTH;
			screenHeight = GameInfo.GAME_HEIGHT;
			screenXOff = 0;
			screenYOff = 0;
		}
		Dimension size = new Dimension(screenWidth, screenHeight);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	private void createJFrame(GraphicsDevice display) {
		frame = new JFrame();
		frame.add(this);
		if (settings.fullscreen) {
			frame.setUndecorated(true);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			display.setFullScreenWindow(frame);
		} else {
			frame.setSize(new Dimension(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT));
			Rectangle displayBounds = display.getDefaultConfiguration().getBounds();
			int fX = (int) (displayBounds.x + displayBounds.getWidth() / 2 + frame.getX() - frame.getWidth() / 2);
			int fY = (int) (displayBounds.y + displayBounds.getHeight() / 2 + frame.getY() - frame.getHeight() / 2);
			frame.setLocation(fX, fY);
		}
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(GameInfo.TITLE);
		frame.setIconImages(GameInfo.WINDOW_ICONS);
	}

	public void requestFocus() {
		frame.requestFocus();
		requestFocusInWindow();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this, GameInfo.TITLE + " Game Thread");
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		int frames = 0;
		int updates = 0;
		double skippedSecs = 0;
		long prevTime = System.nanoTime();
		double secsPerTick = 1.0 / (double) GameInfo.MAX_UPS;

		init();

		while (running) {
			long currTime = System.nanoTime();
			long elapsedTime = currTime - prevTime;
			prevTime = currTime;
			skippedSecs += elapsedTime / 1000000000.0;
			while (skippedSecs > secsPerTick) {
				update();

				skippedSecs -= secsPerTick;
				updates++;
				if (updates % GameInfo.MAX_UPS == 0) {
					fps = frames;
					ups = updates;
					updates = 0;
					frames = 0;
				}
			}
			render();
			frames++;
		}
	}

	private void init() {

	}

	private void update() {
		GameInfo.update(fps, ups);
		input.updateControllerInput();
		if (input.isKeyboardKeyTyped(KeyEvent.VK_F4)) {
			GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
			frame.remove(this);
			frame.dispose();
			settings.fullscreen = !settings.fullscreen;
			calculateScreenBounds(device);
			createJFrame(device);

			frame.setVisible(true);
			requestFocus();
			input.update();
			return;
		}
		gsm.update(input);
		input.update();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		gsm.render(g);
		g.dispose();
		bs.show();
	}

	public Component getComponent() {
		return this;
	}

	public Logger getLogger() {
		return logger;
	}

}
