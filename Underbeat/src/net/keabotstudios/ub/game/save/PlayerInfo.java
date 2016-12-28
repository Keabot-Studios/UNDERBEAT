package net.keabotstudios.ub.game.save;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Random;

import net.keabotstudios.superserial.containers.SSDatabase;
import net.keabotstudios.superserial.containers.SSField;
import net.keabotstudios.superserial.containers.SSObject;
import net.keabotstudios.superserial.containers.SSString;

public class PlayerInfo extends Saveable {

	// TODO STATS! (Ranks on levels, # of perfects, greats, goods, poors, bads, misses)

	private String playerName;

	public PlayerInfo() {
		playerName = "Frisk";
	}

	public boolean write() {
		SSDatabase playerInfo = new SSDatabase(getFileName());

		SSObject root = new SSObject("root");

		root.addString(new SSString("playerName", playerName));

		playerInfo.addObject(root);

		getFile().getParentFile().mkdirs();
		try {
			byte[] data = new byte[playerInfo.getSize()];
			playerInfo.writeBytes(data, 0);
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
			SSDatabase playerInfo = SSDatabase.Deserialize(data);
			SSObject root = playerInfo.getObject("root");

			playerName = root.getString("playerName").getString();

			System.out.println("Loaded " + getFileName() + " successfully from: " + getFilePath());
			return true;
		} catch (Exception e) {
			System.err.println("Can't read " + getFileName() + ".ssd file from: " + getFilePath() + ", rewriting file.");
			write();
			read();
			return false;
		}
	}

	public String getFileName() {
		return "playerInfo";
	}

	public String getPlayerName() {
		return playerName;
	}

}
