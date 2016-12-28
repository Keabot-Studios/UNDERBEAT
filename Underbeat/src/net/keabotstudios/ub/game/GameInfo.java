package net.keabotstudios.ub.game;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import net.keabotstudios.superlog.Logger;
import net.keabotstudios.ub.util.Util.ImageUtil;

public class GameInfo {
	
	public static final String TITLE = "UNDERBEAT";
	public static final String VERSION = "0.01a";

	public static final ArrayList<Image> WINDOW_ICONS = new ArrayList<Image>();

	public static final String APPDATA_FOLDER_NAME = "underbeat";

	public static final float ASPECT_RATIO = 3.0f / 4.0f;
	public static final int GAME_WIDTH = 640;
	public static final int GAME_HEIGHT = (int) (GAME_WIDTH * ASPECT_RATIO);

	public static final int MAX_UPS = 30;

	public static long TIME;
	public static int FPS, UPS;

	public static void update(int fps, int ups) {
		TIME++;
		FPS = fps;
		UPS = ups;
	}

	public static void init(Logger l) {
		WINDOW_ICONS.add(ImageUtil.loadImage("/textures/icons/icon64.png", l));
		WINDOW_ICONS.add(ImageUtil.loadImage("/textures/icons/icon32.png", l));
		WINDOW_ICONS.add(ImageUtil.loadImage("/textures/icons/icon16.png", l));
		TIME = 0;
	}

	public static String getAppdataFolderPath() {
		String OS = (System.getProperty("os.name")).toUpperCase();
		if (OS.contains("WIN")) {
			return System.getenv("AppData") + File.separator + APPDATA_FOLDER_NAME;
		} else {
			return System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support" + File.separator + APPDATA_FOLDER_NAME;
		}
	}
}
