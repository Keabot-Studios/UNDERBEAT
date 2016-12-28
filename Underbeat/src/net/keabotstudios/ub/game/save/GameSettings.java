package net.keabotstudios.ub.game.save;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import net.keabotstudios.superin.InputAxis;
import net.keabotstudios.superserial.containers.SSDatabase;
import net.keabotstudios.superserial.containers.SSField;
import net.keabotstudios.superserial.containers.SSObject;
import net.keabotstudios.ub.game.GameDefaults;
import net.keabotstudios.ub.game.GameInfo;

public class GameSettings extends Saveable {

	public boolean debugMode, fullscreen, useXInput;
	public InputAxis[] controls;

	public GameSettings() {
		controls = GameDefaults.CONTROLS.clone();
		fullscreen = GameDefaults.FULLSCREEN;
		useXInput = GameDefaults.USE_XINPUT;
	}

	public boolean write() {
		SSDatabase settings = new SSDatabase(getFileName());

		SSObject root = new SSObject("root");
		root.addField(SSField.Boolean("fullscreen", this.fullscreen));
		root.addField(SSField.Boolean("useXInput", this.useXInput));
		settings.addObject(root);

		getFile().getParentFile().mkdirs();
		try {
			byte[] data = new byte[settings.getSize()];
			settings.writeBytes(data, 0);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(getFilePath()));
			stream.write(data);
			stream.close();
			System.out.println("Wrote " + getFileName() + " successfully to: " + getFilePath());
			return true;
		} catch (Exception e) {
			System.err.println("Can't write " + getFileName() + ".ssd file to: " + getFilePath());
			return false;
		}
	}

	public boolean read() {
		File file = getFile();
		try {
			byte[] data = Files.readAllBytes(file.toPath());
			SSDatabase settings = SSDatabase.Deserialize(data);

			SSObject root = settings.getObject("root");
			this.fullscreen = root.getField("fullscreen").getBoolean();
			this.useXInput = root.getField("useXInput").getBoolean();

			System.out.println("Loaded " + getFileName() + " successfully from: " + getFilePath());
			return true;
		} catch (Exception e) {
			System.err.println("Can't read " + getFileName() + ".ssd file from: " + getFilePath() + ", rewriting file.");
			write();
			read();
			return false;
		}
	}

	public GameSettings clone() {
		GameSettings copy = new GameSettings();
		copy.debugMode = debugMode;
		copy.fullscreen = fullscreen;
		copy.controls = controls.clone();
		copy.useXInput = useXInput;
		return copy;
	}

	public String getFileName() {
		return "settings";
	}
}
