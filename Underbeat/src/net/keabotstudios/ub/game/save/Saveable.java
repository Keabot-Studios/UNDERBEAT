package net.keabotstudios.ub.game.save;

import java.io.File;

import net.keabotstudios.superserial.containers.SSDatabase;
import net.keabotstudios.ub.game.GameInfo;

public abstract class Saveable {

	public abstract String getFileName();

	public File getFile() {
		return new File(getFilePath());
	}

	public String getFilePath() {
		return GameInfo.getAppdataFolderPath() + File.separator + getFileName() + SSDatabase.FILE_EXTENTION;
	}

	public abstract boolean read();

	public abstract boolean write();

	public void updateFromFile() {
		if (!exists())
			write();
		else
			read();
	}

	public boolean exists() {
		File file = new File(getFilePath());
		return file.exists();
	}

}
