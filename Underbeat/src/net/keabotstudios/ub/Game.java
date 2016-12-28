package net.keabotstudios.ub;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;
import javax.swing.JFrame;

import net.keabotstudios.superin.Controllable;
import net.keabotstudios.superin.Input;
import net.keabotstudios.superlog.Logger;
import net.keabotstudios.superlog.Logger.LogLevel;
import net.keabotstudios.ub.game.GameInfo;
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
	private float screenImageScale = 1f;
	private int fullScreenImageWidth = 0;
	private int fullScreenImageHeight = 0;
	private int fullScreenXOff = 0;
	private int fullScreenYOff = 0;
	
	private GameSettings settings;
	private PlayerInfo playerInfo;
	
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
		playerInfo.updateFromFile();
		
		input = new Input(this, settings.useXInput);
		input.setInputs(settings.controls);
		
		Dimension size = new Dimension(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

		createJFrame(currentDisplay);
		
		if (settings.fullscreen) {
			calculateFullscreenBounds(currentDisplay);
		}
		
		frame.setVisible(true);
		requestFocus();
		start();
	}
	
	private void calculateFullscreenBounds(GraphicsDevice display) {
		GraphicsDevice gd = display;
		int screenWidth = gd.getDisplayMode().getWidth();
		int screenHeight = gd.getDisplayMode().getHeight();
		Dimension size = new Dimension(screenWidth, screenHeight);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		screenImageScale = Util.getScaleOfRectangeInArea(screenWidth, screenHeight, GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
		fullScreenImageWidth = (int) (GameInfo.GAME_WIDTH * screenImageScale);
		fullScreenImageHeight = (int) (GameInfo.GAME_HEIGHT * screenImageScale);
		fullScreenXOff = (int) ((screenWidth - fullScreenImageWidth) / 2.0f);
		fullScreenYOff = (int) ((screenHeight - fullScreenImageHeight) / 2.0f);
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
		if(running) return;
		running = true;
		thread = new Thread(this, GameInfo.TITLE + " Game Thread");
	}
	
	public synchronized void stop() {
		if(!running) return;
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
		input.update();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (settings.fullscreen) {
			
		} else {
			
		}
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
