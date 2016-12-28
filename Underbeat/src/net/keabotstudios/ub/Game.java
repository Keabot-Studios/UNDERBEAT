package net.keabotstudios.ub;

import java.awt.Canvas;
import java.awt.Component;

import net.keabotstudios.superin.Controllable;
import net.keabotstudios.superin.Input;
import net.keabotstudios.superlog.Logger;

public class Game extends Canvas implements Controllable, Runnable {
	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean running = false;
	
	private Logger logger;
	private Input input;
	
	public Game() {
		logger = new Logger();
		input = new Input(this, true);
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this, "UNDERBEAT Game Thread");
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
		while(running) {
			
		}
	}

	public Component getComponent() {
		return this;
	}

	public Logger getLogger() {
		return logger;
	}

}
